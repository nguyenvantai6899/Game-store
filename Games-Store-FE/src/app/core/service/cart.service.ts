import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { BehaviorSubject, Observable } from "rxjs";
import { environment } from "src/environments/environment.development";
import { Cart } from "../models/cart";
import { Product } from "../models/product";
import { User } from "../models/user";

const API = environment.apiUrl;
@Injectable({
    providedIn: 'root'
})


export class CartService {
    constructor(
        private httpClient: HttpClient
    ) { }
    cartChanged: BehaviorSubject<any> = new BehaviorSubject<any>([]);


    getCartByUserEmail(userEmail: string): Observable<any> {
        return this.httpClient.get(API + "/cart/get-cart-by-user?email=" + userEmail);
    }
    addProductIntoCart(email: String, productId: number): Observable<any> {

        return this.httpClient.post(API + "/cart/add-product-into-cart?email=" + email + "&productId=" + productId, null);
    }
    removeProductFromCart(email: String, productId: number): Observable<any> {

        return this.httpClient.delete(API + "/cart/remove-product-from-cart?email=" + email + "&productId=" + productId);
    }
    removeAllProductFromCart(email: any): Observable<any> {
        return this.httpClient.delete(API + "/cart/remove-all-product-from-cart?email=" + email);
    }
    getCartById(id: number): Observable<any> {
        return this.httpClient.get(API + "/cart/get-cart-by-id?id=" + id);
    }

    updateCart() {
        this.cartChanged.next([]);
    }
}