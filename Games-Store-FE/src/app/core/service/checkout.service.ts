import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, of } from 'rxjs';
import { environment } from 'src/environments/environment.development';
const API = environment.apiUrl;

@Injectable({ providedIn: 'root' })
export class CheckoutService {
    constructor(
        private httpClient: HttpClient
    ) { }
    get httpOptions(): any {
        return {
            headers: new HttpHeaders({
                'Content-Type': 'application/json',
            })
        };
    }
    getCheckoutByUserEmail(userEmail: any): Observable<any> {
        return this.httpClient.get(API + `/checkout/get-checkout-by-email/${userEmail}`);
    }

    saveCheckout(checkout: any): Observable<any> {
        return this.httpClient.post<any>(API + '/checkout/save-checkout', checkout, this.httpOptions)
            .pipe(
                catchError(this.handleError('saveCheckout', []))
            );
    }


    test(check: any): Observable<any> {
        return this.httpClient.post<any>(API + '/checkout/test', check)

    }
    private handleError<T>(operation = 'operation', result?: T) {
        return (error: any): Observable<T> => {
            console.error("err", error); // log the error
            return of(result as T);
        };
    }


}
