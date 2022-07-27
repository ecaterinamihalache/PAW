package ro.tuiasi.students.interfaces;

import ro.tuiasi.students.models.Address;
import ro.tuiasi.students.models.StudentInfo;

import java.util.List;

public interface AddressServiceInterface
{
    Address createAddress(Address address,Integer id);
    Address getAddressById(Integer id);
    List<Address> getAllStudentsAddresses();
    void deleteById(Integer id);
}
