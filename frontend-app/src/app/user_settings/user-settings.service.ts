import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ChangePasswordRequest } from './change-password-request';
import { Observable } from 'rxjs';
import { User } from './user';
import { UserInfoResponse } from './user-info-response';

@Injectable({
  providedIn: 'root'
})
export class UserSettingsService {

  private baseUrl = 'http://localhost:8080/api/v1/user';

  constructor(private httpClient: HttpClient) { }

  //change user password
  changePassword(request: ChangePasswordRequest): any {
    return this.httpClient.patch(`${this.baseUrl}`, request);
  }

  //change users info
  getCurrentUser(): Observable<UserInfoResponse> {
    return this.httpClient.get<UserInfoResponse>(`${this.baseUrl}`);
  }

  updateUserInfo(request: UserInfoResponse): Observable<Object> {
    return this.httpClient.patch(`${this.baseUrl}/update`, request);
  }
}
