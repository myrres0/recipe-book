package myrres.foodOrganizer.service.impl;


import lombok.RequiredArgsConstructor;
import myrres.foodOrganizer.jpa.entity.UserEntity;
import myrres.foodOrganizer.jpa.repository.UserRepository;
import myrres.foodOrganizer.rest.api.ChangePasswordRequest;
import myrres.foodOrganizer.rest.api.ChangeUserInfoRequest;
import myrres.foodOrganizer.rest.api.UserInfoResponse;
import myrres.foodOrganizer.service.UserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public void changePassword(ChangePasswordRequest request, Principal connectedUser) {
        var user = ((UserEntity) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal()); //get current user

        //check if the current password is correct
        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Current password is incorrect");
        }
        if (!request.getNewPassword().equals(request.getConfirmPassword())) {
            throw new IllegalArgumentException("New passwords do not match");
        }
        //update and save the password
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
    }


    public void changeUserInfo(ChangeUserInfoRequest request, Principal connectedUser) {
        var user = ((UserEntity) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal()); //get current user
        if (request.getFirstname() != null) {
            user.setFirstname(request.getFirstname());
        }
        if (request.getLastname() != null) {
            user.setLastname(request.getLastname());
        }
        userRepository.save(user);
    }

    public String deleteUser(Principal connectedUser) {
        var user = ((UserEntity) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal()); //get current user
        userRepository.delete(user);
        return "User" + user.getEmail() + "deleted";
    }

    public UserInfoResponse getUserInfo(Principal connectedUser) {
        var user = ((UserEntity) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal()); //get current user
        return new UserInfoResponse(user.getFirstname(), user.getLastname(), user.getEmail());
    }
}
