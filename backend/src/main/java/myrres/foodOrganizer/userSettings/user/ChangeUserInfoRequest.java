package myrres.foodOrganizer.userSettings.user;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChangeUserInfoRequest {
    String firstname;
    String lastname;
    String email;
}
