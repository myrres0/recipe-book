package myrres.foodOrganizer.service;

import myrres.foodOrganizer.rest.api.AuthenticationRequest;
import myrres.foodOrganizer.rest.api.AuthenticationResponse;
import myrres.foodOrganizer.rest.api.RegisterRequest;

public interface AuthenticationService {

    public AuthenticationResponse register(RegisterRequest request);
    public AuthenticationResponse authenticate(AuthenticationRequest request);


}
