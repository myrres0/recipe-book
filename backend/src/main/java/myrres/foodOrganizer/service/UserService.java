package myrres.foodOrganizer.service;

import myrres.foodOrganizer.rest.api.ChangePasswordRequest;
import myrres.foodOrganizer.rest.api.ChangeUserInfoRequest;
import myrres.foodOrganizer.rest.api.UserInfoResponse;

import java.security.Principal;

public interface UserService {
    public void changePassword(ChangePasswordRequest request, Principal connectedUser);

    public void changeUserInfo(ChangeUserInfoRequest request, Principal connectedUser);

    public String deleteUser(Principal connectedUser);

    public UserInfoResponse getUserInfo(Principal connectedUser);

}
