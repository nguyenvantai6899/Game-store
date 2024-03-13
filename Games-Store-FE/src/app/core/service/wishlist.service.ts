import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { BehaviorSubject, Observable } from "rxjs";
import { environment } from "src/environments/environment.development";

const API = environment.apiUrl;
@Injectable({
    providedIn: 'root'
})


export class WishlistService {
    constructor(
        private httpClient: HttpClient
    ) { }
    wishlistChanged: BehaviorSubject<any> = new BehaviorSubject<any>([]);


    getWishlistByUserEmail(userEmail: string): Observable<any> {
        return this.httpClient.get(API + "/wishlist/get-wishlist-by-email?email=" + userEmail);
    }
    addProductIntoWishlist(email: String, productId: number): Observable<any> {

        return this.httpClient.put(API + "/wishlist/add-product-to-wishlist?email=" + email + "&productId=" + productId, null);
    }
    removeProductFromWishlist(email: String, productId: number): Observable<any> {

        return this.httpClient.delete(API + "/wishlist/delete-product-from-wishlist?email=" + email + "&productId=" + productId);
    }

    updateCart() {
        this.wishlistChanged.next([]);
    }
}