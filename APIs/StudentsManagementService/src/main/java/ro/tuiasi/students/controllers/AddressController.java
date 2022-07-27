package ro.tuiasi.students.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuiasi.students.models.Address;
import ro.tuiasi.students.interfaces.AddressServiceInterface;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/student-management-service/accommodations")
public class AddressController
{
    @Autowired
    private AddressServiceInterface addressService;


    @GetMapping("/students-addresses")
    public ResponseEntity<List<Address>> getAllStudentsAddresses()
    {
        try
        {
            List<Address> listAddresses = addressService.getAllStudentsAddresses();
            if (listAddresses.isEmpty())
            {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(listAddresses, HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/students/{id}/addresses")
    public ResponseEntity<Address> getAddress(@PathVariable(value = "id") Integer id)
    {
        try
        {
            Address address = addressService.getAddressById(id);
            if(address == null)
            {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(address, HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/students/{id}/addresses")
    public ResponseEntity<HttpStatus> deleteAddress(@PathVariable("id") Integer id) {
        try
        {
            addressService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/students/{id}/addresses")
    public ResponseEntity<Address> createAddress(@RequestBody Address address, @PathVariable(value = "id") Integer id) {
        try
        {
            return new ResponseEntity<>(addressService.createAddress(address, id), HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
    }
}

