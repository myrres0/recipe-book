import { Component } from '@angular/core';
import { AuthenticateService } from '../../../user_settings/authenticate/authenticate.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrl: './logout.component.css'
})
export class LogoutComponent {

  constructor(private userService: AuthenticateService, private router: Router) { }

  logout(){
    this.userService.logout();
    this.router.navigate(['/login']);
  }
}
