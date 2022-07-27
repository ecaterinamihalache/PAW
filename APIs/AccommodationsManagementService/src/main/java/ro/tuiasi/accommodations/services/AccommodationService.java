package ro.tuiasi.accommodations.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuiasi.accommodations.models.Accommodation;
import ro.tuiasi.accommodations.repositories.AccommodationRepository;
import ro.tuiasi.accommodations.interfaces.AccommodationServiceInterface;

import java.util.List;

@Service
public class AccommodationService implements AccommodationServiceInterface
{
    @Autowired
    private AccommodationRepository accommodationRepository;

    @Override
    public List<Accommodation> getAllAccommodations()
    {
        return accommodationRepository.findAll();
    }
}
