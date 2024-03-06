import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../user';
import { Observable } from 'rxjs';
import { AuthenticateUser } from './authenticate-user';

@Injectable({
  providedIn: 'root'
})
export class AuthenticateService {

  private baseUrl = 'http://localhost:8080/api/v1/auth';

  constructor(private httpClient: HttpClient) { }

  isAuthenticated(): boolean {
    // Check if token exists in localStorage
    return !!localStorage.getItem('token');
  }
  
  // return token in string format
  registerUser(user: User): Observable<any> {
    return this.httpClient.post(`${this.baseUrl}/register`, user);
  }

  // return token in string format
  authenticateUser(authenticateUser: AuthenticateUser): Observable<any> {
    return this.httpClient.post(`${this.baseUrl}/authenticate`, authenticateUser);
  }

  // log out
  logout() {
    localStorage.removeItem('token');
  }
}
