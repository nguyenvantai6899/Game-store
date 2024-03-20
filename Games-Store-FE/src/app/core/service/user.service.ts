import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.development';
const API = environment.apiUrl;

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(
    private httpClient: HttpClient
  ) {

  }
  getProducts(): Observable<any> {
    return this.httpClient.get<any>(API + "/product/")
  }
}
