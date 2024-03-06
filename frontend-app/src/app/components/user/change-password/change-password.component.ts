import { Component } from '@angular/core';
import { UserSettingsService } from '../../../user_settings/user-settings.service';
import { ChangePasswordRequest } from '../../../user_settings/change-password-request';
import { Router } from '@angular/router';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrl: './change-password.component.css'
})
export class ChangePasswordComponent {

  oldPassword!: string;
  newPassword!: string;
  confirmNewPassword!: string;

  constructor(private userService: UserSettingsService, private router: Router) { }

  navigateToLogin(){
    this.router.navigate(['/login']);
  
  }

  onSubmit() {
    let request = new ChangePasswordRequest(this.oldPassword, this.newPassword, this.confirmNewPassword);
    this.userService.changePassword(request).subscribe({
      next: () => {
        console.log('Password changed successfully');
        this.navigateToLogin();
      },
      error: (error: any) => {
        console.log(error);
      }
    });
  
  }
}
