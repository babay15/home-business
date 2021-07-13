package id.co.voistrix.homebusiness.controllers;

import id.co.voistrix.homebusiness.dto.WebResponse;
import id.co.voistrix.homebusiness.dto.auth.AuthenticationRequest;
import id.co.voistrix.homebusiness.dto.auth.AuthenticationResponse;
import id.co.voistrix.homebusiness.services.VoistrictUserDetailsService;
import id.co.voistrix.homebusiness.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.net.ssl.HttpsURLConnection;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private VoistrictUserDetailsService voistrictUserDetailsService;

    @Autowired
    JwtUtil jwtUtil;

    @PostMapping(value = "/create-token")
    public ResponseEntity<?> createAuthToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );

        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username and password", e);
        }

        final UserDetails userDetails = voistrictUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);

        WebResponse webResponse = new WebResponse();
        webResponse.setCode(HttpStatus.OK.value());
        webResponse.setStatus(HttpStatus.OK.getReasonPhrase());
        webResponse.setData(new AuthenticationResponse(jwt));
        return new ResponseEntity(webResponse, HttpStatus.OK);
    }
}
