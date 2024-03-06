import { Component } from '@angular/core';
import { User } from '../../../user_settings/user';
import { AuthenticateService } from '../../../user_settings/authenticate/authenticate.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {

  user !: User;

  constructor(private userService: AuthenticateService, private router: Router) {
    this.user = new User();
  }

  registerUser(){
    this.userService.registerUser(this.user).subscribe({
      next: ()=> {
        console.log('User registered successfully');
        this.router.navigate(['/login']);
      },
      error: (err:any) => {
        console.log(err);
      }
    });
  }


  onSubmit() {
    this.registerUser();
  }

}
