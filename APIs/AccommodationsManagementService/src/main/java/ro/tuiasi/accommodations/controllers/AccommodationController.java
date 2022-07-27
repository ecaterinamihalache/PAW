package ro.tuiasi.accommodations.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.tuiasi.accommodations.models.Accommodation;
import ro.tuiasi.accommodations.interfaces.AccommodationServiceInterface;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/accommodations-service/accommodations/accommodations")
public class AccommodationController
{
    @Autowired
    private AccommodationServiceInterface accommodationServiceInterface;

    @GetMapping("")
    public ResponseEntity<List<Accommodation>> getAllAccommodations()
    {
        try
        {
            List<Accommodation> listAccommodations = accommodationServiceInterface.getAllAccommodations();
            if (listAccommodations.isEmpty())
            {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(listAccommodations, HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
