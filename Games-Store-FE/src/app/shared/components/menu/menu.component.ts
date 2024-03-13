import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Cart } from 'src/app/core/models/cart';
import { Wishlist } from 'src/app/core/models/wishlist';
import { CartService } from 'src/app/core/service/cart.service';
import { WishlistService } from 'src/app/core/service/wishlist.service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.scss']
})
export class MenuComponent implements OnInit {
  quantityProductFromCart: number = 0;
  quantityProductFromWishlist: number = 0;
  constructor(
    private cartService: CartService,
    private wishlistService: WishlistService
  ) { }

  ngOnInit(): void {
    const email: string | null = localStorage.getItem('email');
    this.cartService.cartChanged.subscribe(
      (cart) => {
        this.getCart(email).subscribe(
          (response: any) => {
            this.quantityProductFromCart = response.data.products.length;
          }
        )
      }
    )
    this.wishlistService.wishlistChanged.subscribe(
      (cart) => {
        this.getWishlist(email).subscribe(
          (response: any) => {
            this.quantityProductFromWishlist = response.data.length;
          }
        )
      }
    )

  }
  getCart(email: any): Observable<Cart> {
    return this.cartService.getCartByUserEmail(email);
  }
  getWishlist(email: any): Observable<Wishlist> {
    return this.wishlistService.getWishlistByUserEmail(email);
  }
}
