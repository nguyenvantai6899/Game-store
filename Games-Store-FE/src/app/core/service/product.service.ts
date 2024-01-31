import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.development';

const API = environment.apiUrl;
@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private router: Router, private httpClient: HttpClient) { }
  getProducts(): Observable<any> {
    return this.httpClient.get<any>(API + "/product/")
  }
  createProduct(product: any, files: File[]): Observable<any> {
    const formData = new FormData();
    formData.append('product', JSON.stringify(product));
    files.forEach((file) => {
      formData.append("image", file);
    });
    console.log("image: ", formData.get('image'));


    return this.httpClient.post(API + "/product/save-product", formData);
  }
  getProductById(id: number): Observable<any> {
    return this.httpClient.get<any>(API + "/product/get-product-by-id/" + id);
  }

}
