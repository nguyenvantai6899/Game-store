import { HttpClient, HttpHeaders, HttpParams, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.development';
const API = environment.apiUrl;
const httpOptions = {
    headers: new HttpHeaders({
        'Content-Type': 'application/json'
    })
};
@Injectable({ providedIn: 'root' })
export class AccountService {
    constructor(
        private httpClient: HttpClient
    ) { }

    getAccountByUserEmail(userEmail: string): Observable<any> {
        return this.httpClient.put(API + `/account/${userEmail}`, null);
    }

    editPassword(email: any, currentPassword: string, newPassword: string): Observable<any> {
        return this.httpClient.post(API + '/account/change-password?email='
            + email + '&newPassword=' + newPassword + '&currentPassword=' + currentPassword, null)
    }

    updateAccount(userDTO: any): Observable<any> {
        return this.httpClient.post<any>(API + '/account/update-account', userDTO, httpOptions);
    }

}
