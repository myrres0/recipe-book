import { Component } from '@angular/core';
import { User } from '../../../user_settings/user';
import { UserSettingsService } from '../../../user_settings/user-settings.service';
import { UserInfoResponse } from '../../../user_settings/user-info-response';

@Component({
  selector: 'app-user-settings',
  templateUrl: './user-settings.component.html',
  styleUrl: './user-settings.component.css'
})
export class UserSettingsComponent {

  user !: User;

  constructor(private userService: UserSettingsService) {
    this.user = new User();
    this.getCurrentUserInfo();
   }

  getCurrentUserInfo(){
    this.userService.getCurrentUser().subscribe((data: UserInfoResponse) => {
    this.user.firstname = data.firstname;
    this.user.lastname = data.lastname;
    this.user.email = data.email;
   });
  }


  onSubmit() {
    let request = new UserInfoResponse(this.user.firstname, this.user.lastname, this.user.email);
    console.log(request);
    this.userService.updateUserInfo(request).subscribe({
      next: () => {
        console.log('User info updated successfully');
      },
      error: (error: any) => {
        console.log(error);
      }
    });
  }
  
  


}
