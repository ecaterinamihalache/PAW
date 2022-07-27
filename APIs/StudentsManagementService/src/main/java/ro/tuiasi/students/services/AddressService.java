package ro.tuiasi.students.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuiasi.students.models.Address;
import ro.tuiasi.students.models.StudentInfo;
import ro.tuiasi.students.repositories.AddressRepository;
import ro.tuiasi.students.interfaces.AddressServiceInterface;

import java.util.List;

@Service
public class AddressService implements AddressServiceInterface
{
    @Autowired
    private AddressRepository addressRepository;

    @Override
    public Address createAddress(Address address, Integer id)
    {
        try
        {
            Address _address = new Address();
            _address.setId(id);
            _address.setCity(address.getCity());
            _address.setCounty(address.getCounty());
            _address.setStreet_name(address.getStreet_name());
            _address.setCountry(address.getCountry());
            _address.setPostal_code(address.getPostal_code());

            return addressRepository.save(_address);
        }
        catch (Exception e)
        {
            throw e;
        }
    }

    @Override
    public List<Address> getAllStudentsAddresses()
    {
        return addressRepository.findAll();
    }

    @Override
    public Address getAddressById(Integer id) {
        return addressRepository.findById(id).get();
    }

    @Override
    public void deleteById(Integer id)
    {
        addressRepository.deleteById(id);
    }
}
