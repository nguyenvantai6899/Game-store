import { Component } from '@angular/core';
import { Observable } from 'rxjs';
import { Cart } from 'src/app/core/models/cart';
import { CartService } from 'src/app/core/service/cart.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.scss']
})
export class CartComponent {
  cart?: Cart;
  totalCart = {
    price: 0,
    priceDiscount: 0,
    subTotal: 0
  };
  constructor(
    private cartService: CartService
  ) { }

  ngOnInit(): void {
    //Called after the constructor, initializing input properties, and the first call to ngOnChanges.
    //Add 'implements OnInit' to the class.
    const email: string | null = localStorage.getItem('email');

    this.getCart(email).subscribe(
      (response: any) => {
        this.cart = response.data
        this.getTotalCart(this.cart);
        console.log(this.totalCart);

      },
      (err: any) => {
        console.error(err);

      }
    )
  }

  getCart(email: any): Observable<Cart> {
    return this.cartService.getCartByUserEmail(email);
  }

  removeProductFromCart(productID: number) {
    const email: any = localStorage.getItem('email');
    this.cartService.removeProductFromCart(email, productID).subscribe(
      (response: any) => { },
      (err: any) => {
        console.error(err);
      }
    )
  }

  getTotalCart(cart: any) {
    cart.products.forEach((p: any) => {
      this.totalCart.price += p.price;
      this.totalCart.priceDiscount += p.price * p.discount.percentDiscount / 100;
      this.totalCart.subTotal = this.totalCart.priceDiscount;
    })
  }
}
