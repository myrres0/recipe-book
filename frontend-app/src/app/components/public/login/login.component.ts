import { Component } from '@angular/core';
import { AuthenticateService } from '../../../user_settings/authenticate/authenticate.service';
import { Router } from '@angular/router';
import { AuthenticateUser } from '../../../user_settings/authenticate/authenticate-user';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  authenticate_user !: AuthenticateUser;

  constructor(private userService: AuthenticateService, private router: Router) {
    this.authenticate_user = new AuthenticateUser();
  }

  authenticateUser(){
    this.userService.authenticateUser(this.authenticate_user).subscribe({
      next: (response:any)=> {
        console.log('User authenticated successfully');
        localStorage.setItem('token', response.token);
        console.log(response.token);
        this.router.navigate(['/user-home']);
      },
      error: (err:any) => {
        console.log(err);
      }
    });
  }

  onSubmit() {
    this.authenticateUser();
  }
}
