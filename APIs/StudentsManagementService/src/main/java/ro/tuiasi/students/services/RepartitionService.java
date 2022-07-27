package ro.tuiasi.students.services;

import antlr.StringUtils;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuiasi.students.interfaces.AccommodationsApiRequestServiceInterface;
import ro.tuiasi.students.interfaces.RepartitionServiceInterface;
import ro.tuiasi.students.models.Preference;
import ro.tuiasi.students.models.RegistrationStatus;
import ro.tuiasi.students.models.Room;
import ro.tuiasi.students.models.Student;
import ro.tuiasi.students.repositories.PreferencesRepository;
import ro.tuiasi.students.repositories.StudentRepository;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RepartitionService implements RepartitionServiceInterface {

    private final String repartitionStatusFilename = "repartition-status";
    private RegistrationStatus registrationStatus = new RegistrationStatus(true);

    @Autowired
    PreferencesRepository preferencesRepository;

    @Autowired
    StudentRepository studentRepository;

    private final static int DEFAULT_FREE_SEATS = 4;

    @Override
    public RegistrationStatus getRepartitionStatus() {
        
//        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(repartitionStatusFilename);
//        if(inputStream == null)
//            throw new NullPointerException("Invalid file for repartition status");
//
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
//        StringBuilder stringBuilder = new StringBuilder();
//        String line = "";
//        while (true) {
//            try {
//                if ((line = bufferedReader.readLine()) == null) break;
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            stringBuilder.append(line);
//        }
//
//        JSONObject json = new JSONObject(stringBuilder.toString());
//        return json.getBoolean("status");
        return registrationStatus;
    }

    @Override
    public void changeRepartitionStatus(boolean status) {
//        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(repartitionStatusFilename);
//        if(inputStream == null)
//            throw new NullPointerException("Invalid file for repartition status");
//
//        // Read json file
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
//        StringBuilder stringBuilder = new StringBuilder();
//        String line = "";
//        while (true) {
//            try {
//                if ((line = bufferedReader.readLine()) == null) break;
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            stringBuilder.append(line);
//        }
//
//        JSONObject json = new JSONObject(stringBuilder.toString());
//        json.append("status", status);
//
//        // Read write file
//        FileWriter file = null;
//        OutputStream outputStream = getClass().getClassLoader().set(repartitionStatusFilename);
//        try {
//            file = new FileWriter("/Users/Shared/crunchify.txt");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        file.write(json.toJSONString());
        registrationStatus.setRegistrationStatus(status);
    }

    @Override
    public void repartitionAlgorithm(List<Room> roomList) {

        /*------------------- NOTES -------------------

            Date initiale:
                -   Validarea faptului ca studentul are voie sa aleaga acest camin se face la preferences la insert
                -   Lista cu studenti in ordinea mediilor (validata la insert)
                -   Lista cu preferintele fiecarui student (validata la insert)

            Se verifica daca studentii din preferinte l-au ales pe el ca prima optiune ca sa se afle cati vor sta in camera
            Se parcurg preferintele studentului, si se alege camera disponibila cu numarul de locuri in functie de numarul de colegi alesi
            Daca se gaseste, se trece camera ocupata pentru toti cei implicati


            Se va folosi:
                -   Unordered hashmap pentru studenti
                -   Unordered hashmap pentru preferinte
                -   Hashmap pentru a tine evidenta locurilor libere


            Structura preferences:
            rooms -> accommodationId:RoomId, accommodationId:RoomId...
            roommates -> Id, Id...
         */

        System.out.println(getRepartitionStatus());

        List<Student> students = studentRepository.findAllOrderByYearGradeDesc();

        // Hash map pentru inregistrare camere studenti
        // Key: studentId, Value: roomId
        Map<Integer, Integer> repartitionResult = new HashMap<>();
        // Map<Integer, String> repartitionRoommatesResult = new HashMap<>();
        Map<Integer, Integer> freeSeatsRoomMap = new HashMap<>();
        for (Room room : roomList) freeSeatsRoomMap.put(room.getId(), DEFAULT_FREE_SEATS);

        // ---------------- START ----------------

        for(Student student : students)
        {
            // Se trece la urmatorul student daca acesta are camera asignata
            if(repartitionResult.get(student.getId()) != null)
                continue;

            Optional<Preference> preferenceOptional = preferencesRepository.findById(student.getId());
            Preference preference = preferenceOptional.orElse(null);

            if(preference == null)
                continue;

            // ---------------- VALIDARE COLEGI DE CAMERA ----------------

            // Lista de id-uri ale colegilor de camera

            List<Integer> roommatesIds;
            if(preference.getRoommates().length() != 0)
                roommatesIds = Arrays.stream(preference.getRoommates().split(",")).map(Integer::parseInt).collect(Collectors.toList());
            else
                roommatesIds = new ArrayList<>();

            // Se verifica daca studentii din preferinta il au trecut in lista de referinte
            for(int i = 0; i < roommatesIds.size(); ++i)
            {
                int roomMateId = roommatesIds.get(i);
                // Daca colegul de camera are deja aleasa camera, atunci treci mai departe la alt coleg
                if(repartitionResult.get(roomMateId) != null)
                {
                    roommatesIds.remove(i--);
                    continue;
                }

                Optional<Preference> roomMatePreferenceOptional = preferencesRepository.findById(roomMateId);
                Preference roomMatePreference = roomMatePreferenceOptional.orElse(null);

                // Daca colegul de camera nu are preferinte, atunci se trece la urmatorul student din preferinte
                if(roomMatePreference == null)
                {
                    roommatesIds.remove(i--);
                    continue;
                }

                // Verificare daca si colegul de camera il are trecut in lista lui de preferinte
                boolean doesRoomMateHasMe = false;

                // Daca colegul nu are colegi ca preferinte, atunci se trece mai departe
                if(roomMatePreference.getRoommates().equals("") || roomMatePreference.getRoommates() == null)
                {
                    roommatesIds.remove(i--);
                    continue;
                }

                String[] roomMateRoomMateIdArray = roomMatePreference.getRoommates().split(",");
                for(String roomMateRoomMateId : roomMateRoomMateIdArray)
                {
                    if(Integer.parseInt(roomMateRoomMateId) == student.getId())
                    {
                        doesRoomMateHasMe = true;
                        break;
                    }
                }

                if(!doesRoomMateHasMe)
                {
                    roommatesIds.remove(i--);
                }
            }

            // Creez lista de studenti ce urmeaza sa fie procesata
            List<Integer> readyStudentList = new ArrayList<>(roommatesIds);
            readyStudentList.add(0, student.getId());


            // ---------------- CAUTARE CAMERA LIBERA ----------------

            List<Integer> roomsIds = Arrays.stream(preference.getRooms().split(",")).map(it -> Integer.parseInt(it.split(":")[1])).collect(Collectors.toList());
            Set<Integer> accommodationsIds = Arrays.stream(preference.getRooms().split(",")).map(it -> Integer.parseInt(it.split(":")[0])).collect(Collectors.toSet());
            boolean roomFound = false;
            for(Integer roomId : roomsIds)
            {
                if(freeSeatsRoomMap.get(roomId) >= readyStudentList.size())
                {
                    roomFound = true;
                    freeSeatsRoomMap.put(roomId, freeSeatsRoomMap.get(roomId) - readyStudentList.size());

                    for(Integer roommateId : readyStudentList) {
                        // Se salveaza repartitia colegilor
                        repartitionResult.put(roommateId, roomId);
                    }
                    break;
                }
            }

            if(!roomFound)
            {
                // Cea mai buna camera din caminele pe care si le-a ales
               Optional<Map.Entry<Integer, Integer>> bestAvailableRoom = freeSeatsRoomMap.entrySet().stream().filter(freeSeatsEntry -> accommodationsIds.contains(freeSeatsEntry.getKey())).max(Map.Entry.comparingByKey());

               if(bestAvailableRoom.get().getValue() == 0)
               {
                   System.out.println("No available rooms left");
                   return;
               }

               Integer roomId = bestAvailableRoom.get().getKey();
               Integer leftSeats = bestAvailableRoom.get().getValue();

               for(int roomMateIndex = 0; roomMateIndex < leftSeats; ++roomMateIndex)
               {
                   repartitionResult.put(readyStudentList.get(roomMateIndex), roomId);
               }

                freeSeatsRoomMap.put(roomId, freeSeatsRoomMap.get(roomId) - leftSeats); // Ar trebui sa dea 0
            }
        }


        // ---------------- SALVARE REPARTITIE ----------------

        for(Map.Entry<Integer, Integer> repartitionEntry : repartitionResult.entrySet())
        {
            List<String> roommatesList = repartitionResult.entrySet().stream().filter(repartition -> repartition.getValue() == repartitionEntry.getValue()).map(roommateEntry -> roommateEntry.getKey().toString()).collect(Collectors.toList());
            roommatesList.remove(repartitionEntry.getKey().toString());
            String roommates = roommatesList.stream().collect(Collectors.joining(","));
            studentRepository.setRepartitionForStudent(repartitionEntry.getValue(), roommates, repartitionEntry.getKey());
        }
        registrationStatus.setRegistrationStatus(false);
    }
}
