package ro.tuiasi.students.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ro.tuiasi.students.interfaces.AccommodationsApiRequestServiceInterface;
import ro.tuiasi.students.models.Room;

@Service
public class RestTemplateAccommodationsService implements AccommodationsApiRequestServiceInterface {

    @Value("http://${accommodations.management.service.host}:${accommodations.management.service.port}/accommodations-service/accommodations/rooms")
    private String ACCOMODATION_SERVICE_ROOMS_URL;

    private final RestTemplate restTemplate;

    public RestTemplateAccommodationsService()
    {
        this.restTemplate = new RestTemplateBuilder().build();
    }

    public ResponseEntity<Room[]> getAllRoomsRequest() {
        return restTemplate.getForEntity(ACCOMODATION_SERVICE_ROOMS_URL, Room[].class);
    }

}
