import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.development';
import { User } from '../models/user';

const API = environment.apiUrl;

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  constructor(
    private httpClient: HttpClient
  ) { }
  signIn(username: string, password: string): Observable<any> {
    return this.httpClient.post(API + `/auth/login?email=${username}&password=${password}`, '')
  }
  signUp(user: User): Observable<any> {
    return this.httpClient.post(API + '/auth/register', user)
  }
}
