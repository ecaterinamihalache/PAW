package ro.tuiasi.uac.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuiasi.uac.interfaces.AccountServiceInterface;
import ro.tuiasi.uac.interfaces.HashingServiceInterface;
import ro.tuiasi.uac.interfaces.JwtFactoryServiceInterface;
import ro.tuiasi.uac.interfaces.JwtParserServiceInterface;
import ro.tuiasi.uac.models.*;

import java.util.HashMap;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("uac")
public class UserAccessController {

    @Autowired
    HSKeyProperties hs256KeyProperties;

    @Autowired
    JwtFactoryServiceInterface<String> jwtFactoryService;

    @Autowired
    JwtParserServiceInterface<String> jwtParserService;

    @Autowired
    AccountServiceInterface accountService;

    @Autowired
    HashingServiceInterface hashingService;

    @PostMapping("/authenticate")
    public ResponseEntity<TokenAndClaims> authenticateUser(@RequestBody Credentials credentials) {

        Optional<Account> optionalAccount = accountService.getAccountByCredentials(credentials.getEmail(), hashingService.encode(credentials.getPassword()));
        boolean validCredentials = optionalAccount.isPresent();

        if(validCredentials)
        {
            Account account = optionalAccount.get();
            String token = jwtFactoryService.generateToken(new HashMap<>(){{ put("role", account.getRole()); }}, hs256KeyProperties.getSecretKey());
            return ResponseEntity.status(HttpStatus.CREATED).body(new TokenAndClaims(token, account.getId(), account.getRole()));
        }
        else
        {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/validate-token")
    public ResponseEntity<?> validateToken(@RequestBody Token token)
    {
        if(jwtParserService.isTokenValid(token.getToken(), hs256KeyProperties.getSecretKey()))
            return ResponseEntity.status(HttpStatus.OK).build();
        else
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
