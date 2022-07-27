package ro.tuiasi.GatewayService.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import ro.tuiasi.GatewayService.Models.*;

import javax.annotation.PostConstruct;
import java.net.URI;

//port: 8081

@CrossOrigin
@RestController
public class Gateway {

    @Autowired
    private RestTemplate restTemplate;

    @Value("http://${accommodations.management.service.host}:${accommodations.management.service.port}/accommodations-service/accommodations")
    private String ACCOMODATIONS_MANAGEMENT_SERVICE_URL;

    @Value("http://${students.management.service.host}:${students.management.service.port}/student-management-service/accommodations")
    private String STUDENTS_MANAGEMENT_SERVICE_URL;

    @Value("http://${user.access.control.service.host}:${user.access.control.service.port}/uac")
    private String USER_ACCESS_CONTROL_SERVICE_URL;

    //Accounts
    @GetMapping(path="accounts/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable Integer id, @RequestHeader(required = false) String authorization) {
        if(!hasValidAuthorization(authorization))
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        String url = USER_ACCESS_CONTROL_SERVICE_URL + "/accommodations/accounts/" + id.toString();

        try {
            return restTemplate.getForEntity(url, Account.class);
        } catch(HttpClientErrorException.NotFound e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch(HttpClientErrorException.BadRequest e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch(HttpClientErrorException.Unauthorized e) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping(path="accounts")
    public ResponseEntity<Account[]> getAccounts(@RequestHeader(required = false) String authorization) {
        if(!hasValidAuthorization(authorization))
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        String url = USER_ACCESS_CONTROL_SERVICE_URL + "/accommodations/accounts";

        try {
            return restTemplate.getForEntity(url, Account[].class);
        } catch(HttpClientErrorException.NotFound e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch(HttpClientErrorException.BadRequest e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch(HttpClientErrorException.Unauthorized e) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping(path="accounts")
    public ResponseEntity<Account> addAccount(@RequestBody Account account, @RequestHeader(required = false) String authorization) {
        if(!hasValidAuthorization(authorization))
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        String url = USER_ACCESS_CONTROL_SERVICE_URL + "/accommodations/accounts";

        try {
            return restTemplate.postForEntity(url, account, Account.class);
        } catch(HttpClientErrorException.NotFound e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch(HttpClientErrorException.BadRequest e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch(HttpClientErrorException.Unauthorized e) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
    }

    @DeleteMapping(path="accounts/{id}")
    public ResponseEntity deleteAccountById(@PathVariable Integer id, @RequestHeader(required = false) String authorization) {
        if(!hasValidAuthorization(authorization))
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        String url = USER_ACCESS_CONTROL_SERVICE_URL + "/accommodations/accounts/" + id.toString();

        RequestEntity request = new RequestEntity<>(HttpMethod.DELETE, URI.create(url));

        try{
            return restTemplate.exchange(url, HttpMethod.DELETE, request, Account.class);
        } catch(HttpClientErrorException.NotFound e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch(HttpClientErrorException.BadRequest e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch(HttpClientErrorException.Unauthorized e) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
    }

    @DeleteMapping(path="accounts")
    public ResponseEntity deleteAccount(@RequestBody Account account, @RequestHeader(required = false) String authorization) {
        if(!hasValidAuthorization(authorization))
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        String url = USER_ACCESS_CONTROL_SERVICE_URL + "/accommodations/accounts";

        RequestEntity<Account> request = new RequestEntity<>(account, HttpMethod.DELETE, URI.create(url));

        try{
            return restTemplate.exchange(url, HttpMethod.DELETE, request, Account.class);
        } catch(HttpClientErrorException.NotFound e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch(HttpClientErrorException.BadRequest e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch(HttpClientErrorException.Unauthorized e) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping(path="accounts")
    public ResponseEntity<Account> updateAccount(@RequestBody Account account, @RequestHeader(required = false) String authorization) {
        if(!hasValidAuthorization(authorization))
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        String url = USER_ACCESS_CONTROL_SERVICE_URL + "/accommodations/accounts";

        RequestEntity<Account> request = new RequestEntity<>(account, HttpMethod.PUT, URI.create(url));

        try {
            return restTemplate.exchange(url, HttpMethod.PUT, request, Account.class);
        } catch(HttpClientErrorException.NotFound e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch(HttpClientErrorException.BadRequest e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch(HttpClientErrorException.Unauthorized e) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
    }

    //Addresses
    @GetMapping("/addresses/{id}")
    public ResponseEntity<Address> getAddressById(@PathVariable Integer id, @RequestHeader(required = false) String authorization) {
        if(!hasValidAuthorization(authorization))
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        String url = STUDENTS_MANAGEMENT_SERVICE_URL + "/addresses/" + id.toString();

        try {
            return restTemplate.getForEntity(url, Address.class);
        } catch(HttpClientErrorException.NotFound e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch(HttpClientErrorException.BadRequest e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch(HttpClientErrorException.Unauthorized e) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        } catch(HttpServerErrorException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/addresses/{id}")
    public ResponseEntity deleteAddressById(@PathVariable("id") Integer id, @RequestHeader(required = false) String authorization) {
        if(!hasValidAuthorization(authorization))
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        String url = STUDENTS_MANAGEMENT_SERVICE_URL + "/addresses/" + id.toString();

        RequestEntity request = new RequestEntity<>(HttpMethod.DELETE, URI.create(url));

        try {
            return restTemplate.exchange(url, HttpMethod.DELETE, request, Address.class);
        } catch(HttpClientErrorException.NotFound e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch(HttpClientErrorException.BadRequest e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch(HttpClientErrorException.Unauthorized e) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        } catch(HttpServerErrorException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/students/{id}/addresses")
    public ResponseEntity<Address> createAddress(@RequestBody Address address,
                                                 @PathVariable Integer id,
                                                 @RequestHeader(required = false) String authorization) {
        if(!hasValidAuthorization(authorization))
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        String url = STUDENTS_MANAGEMENT_SERVICE_URL + "/students/" + id.toString() + "/addresses";

        try {
            return restTemplate.postForEntity(url, address, Address.class);
        } catch(HttpClientErrorException.NotFound e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch(HttpClientErrorException.BadRequest e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch(HttpClientErrorException.Unauthorized e) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        } catch(HttpServerErrorException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    //Students
    @PostMapping("/accounts/{id}/students")
    public ResponseEntity<Student> createStudent(@RequestBody Student student,
                                                 @PathVariable Integer id,
                                                 @RequestHeader(required = false) String authorization) {
        if(!hasValidAuthorization(authorization))
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        String url = STUDENTS_MANAGEMENT_SERVICE_URL  +"/accounts/" + id.toString() + "/students";

        try {
            return restTemplate.postForEntity(url, student, Student.class);
        } catch(HttpClientErrorException.NotFound e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch(HttpClientErrorException.BadRequest e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch(HttpClientErrorException.Unauthorized e) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        } catch(HttpServerErrorException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/students")
    public ResponseEntity<Student[]> getAllStudents(@RequestHeader(required = false) String authorization) {
        if(!hasValidAuthorization(authorization))
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        String url = STUDENTS_MANAGEMENT_SERVICE_URL + "/students";

        try {
            return restTemplate.getForEntity(url, Student[].class);
        } catch(HttpClientErrorException.NotFound e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch(HttpClientErrorException.BadRequest e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch(HttpClientErrorException.Unauthorized e) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        } catch(HttpServerErrorException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Integer id, @RequestHeader(required = false) String authorization) {
        if(!hasValidAuthorization(authorization))
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        String url = STUDENTS_MANAGEMENT_SERVICE_URL + "/students/" + id.toString();

        try {
            return restTemplate.getForEntity(url, Student.class);
        } catch(HttpClientErrorException.NotFound e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch(HttpClientErrorException.BadRequest e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch(HttpClientErrorException.Unauthorized e) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        } catch(HttpServerErrorException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity deleteStudent(@PathVariable Integer id, @RequestHeader(required = false) String authorization) {
        if(!hasValidAuthorization(authorization))
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        String url = STUDENTS_MANAGEMENT_SERVICE_URL + "/students/" + id.toString();

        RequestEntity request = new RequestEntity<>(HttpMethod.DELETE, URI.create(url));

        try {
            return restTemplate.exchange(url, HttpMethod.DELETE, request, Student.class);
        } catch(HttpClientErrorException.NotFound e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch(HttpClientErrorException.BadRequest e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch(HttpClientErrorException.Unauthorized e) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        } catch(HttpServerErrorException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    //StudentInfo
    @GetMapping("/students_details/{id}")
    public ResponseEntity<StudentInfo> getStudentInfo(@PathVariable Integer id, @RequestHeader(required = false) String authorization) {
        if(!hasValidAuthorization(authorization))
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        String url = STUDENTS_MANAGEMENT_SERVICE_URL + "/students_details/" + id.toString();

        try {
            return restTemplate.getForEntity(url, StudentInfo.class);
        } catch(HttpClientErrorException.NotFound e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch(HttpClientErrorException.BadRequest e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch(HttpClientErrorException.Unauthorized e) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        } catch(HttpServerErrorException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/students_details/{id}")
    public ResponseEntity deleteStudentInfo(@PathVariable Integer id, @RequestHeader(required = false) String authorization) {
        if(!hasValidAuthorization(authorization))
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        String url = STUDENTS_MANAGEMENT_SERVICE_URL + "/students_details/" + id.toString();

        RequestEntity request = new RequestEntity<>(HttpMethod.DELETE, URI.create(url));

        try {
            return restTemplate.exchange(url, HttpMethod.DELETE, request, StudentInfo.class);
        } catch(HttpClientErrorException.NotFound e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch(HttpClientErrorException.BadRequest e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch(HttpClientErrorException.Unauthorized e) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        } catch(HttpServerErrorException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/students/{id}/students_details")
    public ResponseEntity<StudentInfo> createStudentInfo(@RequestBody StudentInfo studentInfo,
                                                         @PathVariable Integer id,
                                                         @RequestHeader(required = false) String authorization) {
        if(!hasValidAuthorization(authorization))
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        String url = STUDENTS_MANAGEMENT_SERVICE_URL + "/students/" + id.toString() + "/students_details";

        try {
            return restTemplate.postForEntity(url, studentInfo, StudentInfo.class);
        } catch(HttpClientErrorException.NotFound e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch(HttpClientErrorException.BadRequest e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch(HttpClientErrorException.Unauthorized e) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        } catch(HttpServerErrorException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    //Preferences
    @DeleteMapping("/students/{id}/preferences")
    public ResponseEntity deletePreference(@PathVariable Integer id, @RequestHeader(required = false) String authorization) {
        if(!hasValidAuthorization(authorization))
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        String url = STUDENTS_MANAGEMENT_SERVICE_URL + "/students/" + id.toString() + "/preferences";

        RequestEntity request = new RequestEntity<>(HttpMethod.DELETE, URI.create(url));

        try {
            return restTemplate.exchange(url, HttpMethod.DELETE, request, Preference.class);
        } catch(HttpClientErrorException.NotFound e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch(HttpClientErrorException.BadRequest e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch(HttpClientErrorException.Unauthorized e) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        } catch(HttpServerErrorException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/students/{id}/preferences")
    public ResponseEntity<Preference> createPreferences(@RequestBody Preference preference,
                                                        @PathVariable Integer id,
                                                        @RequestHeader(required = false) String authorization) {
        if(!hasValidAuthorization(authorization))
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        String url = STUDENTS_MANAGEMENT_SERVICE_URL + "/students/" + id.toString() + "/preferences";

        try {
            return restTemplate.postForEntity(url, preference, Preference.class);
        } catch(HttpClientErrorException.NotFound e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch(HttpClientErrorException.BadRequest e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch(HttpClientErrorException.Unauthorized e) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        } catch(HttpServerErrorException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/students/{id}/preferences")
    public ResponseEntity<Preference> getPreference(@PathVariable Integer id, @RequestHeader(required = false) String authorization) {

        if(!hasValidAuthorization(authorization))
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        String url = STUDENTS_MANAGEMENT_SERVICE_URL + "/students/" + id.toString() + "/preferences";

        try {
            return restTemplate.getForEntity(url, Preference.class);
        } catch(HttpClientErrorException.NotFound e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch(HttpClientErrorException.BadRequest e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch(HttpClientErrorException.Unauthorized e) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        } catch(HttpServerErrorException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    //UserAccess
    @PostMapping("/authenticate")
    public ResponseEntity<Token> authenticateUser(@RequestBody Credentials credentials) {
        String url = USER_ACCESS_CONTROL_SERVICE_URL + "/authenticate";

        try {
            return restTemplate.postForEntity(url, credentials, Token.class);
        } catch(HttpClientErrorException.NotFound e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch(HttpClientErrorException.BadRequest e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch(HttpClientErrorException.Unauthorized e) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        } catch(HttpServerErrorException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/validate-token")
    public ResponseEntity validateToken(@RequestBody Token token) {
        String url = USER_ACCESS_CONTROL_SERVICE_URL + "/validate-token";

        try {
            return restTemplate.postForEntity(url, token, Token.class);
        } catch(HttpClientErrorException.NotFound e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch(HttpClientErrorException.BadRequest e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch(HttpClientErrorException.Unauthorized e) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        } catch(HttpServerErrorException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    private boolean hasValidAuthorization(String authorization) {
        if(authorization == null || !authorization.startsWith("Bearer"))
            return false;

        String token = authorization.substring(7);

        String url = USER_ACCESS_CONTROL_SERVICE_URL + "/validate-token";

        try {
            Token tokenToCheck = new Token();
            tokenToCheck.token = token;
            ResponseEntity response = restTemplate.postForEntity(url, tokenToCheck, Object.class);

            if(response.getStatusCode() == HttpStatus.OK)
                return true;
        } catch(HttpClientErrorException e) {
            return false;
        } catch(HttpServerErrorException e) {
            return false;
        }

        return false;
    }
}

