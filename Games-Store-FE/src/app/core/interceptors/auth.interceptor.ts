import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.development';

const API = environment.apiUrl;
@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor() { }

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    const authToken = localStorage.getItem('token')

    if (request.url.includes(API + '/auth/**')) {
      return next.handle(request);
    }

    // Clone the request and add an Authorization header if the token is available
    if (authToken) {
      request = request.clone({
        setHeaders: {
          Authorization: `Bearer ${authToken}`
        }
      });
    }
    // Pass the modified request to the next handler
    return next.handle(request);
  }
}
