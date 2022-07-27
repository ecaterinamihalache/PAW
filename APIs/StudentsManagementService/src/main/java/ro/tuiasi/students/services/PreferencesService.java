package ro.tuiasi.students.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuiasi.students.models.Accommodation;
import ro.tuiasi.students.models.Preference;
import ro.tuiasi.students.models.Room;
import ro.tuiasi.students.models.Student;
import ro.tuiasi.students.repositories.AccommodationsRepository;
import ro.tuiasi.students.repositories.PreferencesRepository;
import ro.tuiasi.students.interfaces.PreferencesServiceInterface;
import ro.tuiasi.students.repositories.RoomRepository;
import ro.tuiasi.students.repositories.StudentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PreferencesService implements PreferencesServiceInterface
{
    @Autowired
    PreferencesRepository preferencesRepository;

    @Autowired
    private AccommodationsRepository accommodationsRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Preference createPreferences(Preference preferences, Integer id)
    {
        try
        {
            Preference _preferences = new Preference();
            _preferences.setId(id);
            _preferences.setRooms(preferences.getRooms());
            _preferences.setRoommates(preferences.getRoommates());

            return preferencesRepository.save(_preferences);
        }
        catch (Exception e)
        {
            return null;
        }
    }

    @Override
    public Optional<Preference> getPreferenceById(Integer id)
    {
        return preferencesRepository.findById(id);
    }

    @Override
    public Preference getPreferencesForAStudent( Optional<Preference> preferenceOptional)
    {
        List<String> roomsAndAccommodations = List.of(preferenceOptional.get().getRooms().split(","));

        List<String> rooms = new ArrayList<>();
        List<String> accommodations = new ArrayList<>();

        for (int i = 0; i < roomsAndAccommodations.size(); i++) {
            String[] splitMessage = roomsAndAccommodations.get(i).split(":");
            accommodations.add(splitMessage[0]);
            rooms.add(splitMessage[1]);
        }

        String camere = "";
        for(int i=0;i<accommodations.size();i++)
        {
            Optional<Accommodation> accommodation = accommodationsRepository.findById(Integer.parseInt(accommodations.get(i)));

            Optional<Room> room = roomRepository.findById(Integer.parseInt(rooms.get(i)));
            camere += accommodation.get().getName() + ":" +room.get().getRoom() + ",";
        }

        List<String> roommates = List.of(preferenceOptional.get().getRoommates().split(","));

        List<Optional<Student>> colegiCamera = new ArrayList<>();
        String colegi = "";
        for (int k = 0; k < roommates.size(); k++)
        {
            colegiCamera.add(studentRepository.findById(Integer.parseInt(roommates.get(k))));
            colegi += colegiCamera.get(k).get().getFirstname() + " "+ colegiCamera.get(k).get().getLastname() + ",";
        }

        return new Preference(preferenceOptional.get().getId(),camere.substring(0,camere.length()-1), colegi.substring(0, colegi.length()-1));
    }

    @Override
    public void deletePreferenceById(Integer id)
    {
        preferencesRepository.deleteById(id);
    }
}
