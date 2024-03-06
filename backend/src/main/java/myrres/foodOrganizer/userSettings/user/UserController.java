package myrres.foodOrganizer.userSettings.user;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
@CrossOrigin("*")
public class UserController {

    private final UserService userService;


    @PatchMapping
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest request,
                                            Principal connectedUser){
        userService.changePassword(request, connectedUser);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/update")
    public ResponseEntity<?> changeUserInfo(@RequestBody ChangeUserInfoRequest request,
                                            Principal connectedUser){
        userService.changeUserInfo(request, connectedUser);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<String> deleteUser(Principal connectedUser){
        String response = userService.deleteUser(connectedUser);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<UserInfoResponse> getUserInfo(Principal connectedUser){
        UserInfoResponse response = userService.getUserInfo(connectedUser);
        return ResponseEntity.ok(response);
    }

}
