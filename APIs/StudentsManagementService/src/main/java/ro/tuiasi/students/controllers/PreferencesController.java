package ro.tuiasi.students.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuiasi.students.interfaces.RepartitionServiceInterface;
import ro.tuiasi.students.models.Preference;
import ro.tuiasi.students.models.RegistrationStatus;
import ro.tuiasi.students.models.Room;
import ro.tuiasi.students.services.PreferencesService;
import ro.tuiasi.students.services.RestTemplateAccommodationsService;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/student-management-service/accommodations")
public class PreferencesController
{
    @Autowired
    private PreferencesService preferencesService;

    @Autowired
    RepartitionServiceInterface repartitionService;

    @Autowired
    private RestTemplateAccommodationsService accommodationsApiRequestService;

    @DeleteMapping("/students/{id}/preferences")
    public ResponseEntity<HttpStatus> deletePreferences(@PathVariable("id") Integer id)
    {
        try
        {
            preferencesService.deletePreferenceById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/students/{id}/preferences")
    public ResponseEntity<Preference> createPreferences(@RequestBody Preference preferences, @PathVariable(value = "id") Integer id)
    {
        try
        {
            return new ResponseEntity<>(preferencesService.createPreferences(preferences, id), HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping("/students/{id}/preferences")
    public ResponseEntity<Preference> getPreferencesForAStudent(@PathVariable(value = "id") Integer id)
    {
        Optional<Preference> preferenceOptional = preferencesService.getPreferenceById(id);

        if (preferenceOptional.isEmpty())
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(preferencesService.getPreferencesForAStudent(preferenceOptional), HttpStatus.OK);
    }

    @GetMapping("/preferences/registration-status")
    public ResponseEntity<RegistrationStatus> getRegistrationStatus()
    {
        return ResponseEntity.status(HttpStatus.OK).body(repartitionService.getRepartitionStatus());
    }

    @PostMapping("/preferences/start-repartition")
    public ResponseEntity<?> startRepartition() {
        ResponseEntity<Room[]> roomArrayResponse = accommodationsApiRequestService.getAllRoomsRequest();

        try {
            repartitionService.repartitionAlgorithm(Arrays.asList(Objects.requireNonNull(roomArrayResponse.getBody())));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Repartition algorithm error\n" + ex.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
