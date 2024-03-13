import { Component } from '@angular/core';
import { Wishlist } from 'src/app/core/models/wishlist';
import { CartService } from 'src/app/core/service/cart.service';
import { WishlistService } from 'src/app/core/service/wishlist.service';
const email: any = localStorage.getItem('email');

@Component({
  selector: 'app-wishlist',
  templateUrl: './wishlist.component.html',
  styleUrls: ['./wishlist.component.scss']
})
export class WishlistComponent {
  wishlist?: Wishlist[];
  checkProductInCart: boolean = false;
  constructor(
    private wishlistService: WishlistService,
    private cartService: CartService,
  ) { }

  ngOnInit(): void {
    //Called after the constructor, initializing input properties, and the first call to ngOnChanges.
    //Add 'implements OnInit' to the class.
    this.wishlistService.getWishlistByUserEmail(email).subscribe(
      (response: any) => {
        this.wishlist = response.data;
      },
      (err: any) => {
        console.error(err);

      }
    )
  }

  removeProductFromWishlist(productID: number) {
    this.wishlistService.removeProductFromWishlist(email, productID).subscribe(
      (response: any) => {
        this.wishlist = response.data;
        this.wishlistService.updateCart();
      },
      (err: any) => {
        console.error(err);
      }
    )
  }

  moveToCart(productID: number) {
    this.cartService.addProductIntoCart(email, productID).subscribe(
      (response: any) => {
        this.cartService.updateCart();
        this.checkProductInCart = false;
      },
      (err: any) => {
        console.error(err);
      }
    )
    this.removeProductFromWishlist(productID)
  }


}
