import { DatePipe } from '@angular/common';
import { Component, EventEmitter, Input, Output, ViewEncapsulation } from '@angular/core';
import { Checkout } from 'src/app/core/models/checkout';
import { Product } from 'src/app/core/models/product';
import { User, UserDTO } from 'src/app/core/models/user';
import { AccountService } from 'src/app/core/service/account.service';
import { CartService } from 'src/app/core/service/cart.service';
import { CheckoutService } from 'src/app/core/service/checkout.service';
import { ProductService } from 'src/app/core/service/product.service';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.scss'],
  encapsulation: ViewEncapsulation.None
})
export class CheckoutComponent {
  selectedPaymentOption: string = 'paypal';
  @Input() productId!: number | null | undefined;
  @Input() cartId!: number | null | undefined;
  @Input() showCheckout: boolean = false;
  @Output() showCheckoutChange = new EventEmitter<boolean>();
  products: Product[] = [];
  email!: string | null;
  totalCart = {
    price: 0,
    priceDiscount: 0,
    subTotal: 0
  };
  checkoutDate = new Date();
  user !: User;

  constructor(
    private productService: ProductService,
    private cartService: CartService,
    private checkoutService: CheckoutService,
    private accountService: AccountService
  ) {

  }

  ngOnInit(): void {
    //Called after the constructor, initializing input properties, and the first call to ngOnChanges.
    //Add 'implements OnInit' to the class.
    this.email = this.splitEmail(localStorage.getItem('email'));
    this.cartService.cartChanged.subscribe(
      (cart) => {
        if (this.cartId) {
          this.getCartById(this.cartId);
        }
      }
    )
    const email = localStorage.getItem('email')
    if (email !== null) {
      this.accountService.getAccountByUserEmail(email).subscribe(response => {
        this.user = response.data;
      });
    }
  }
  ngAfterViewInit(): void {
    if (this.productId) {
      this.getProductByID(this.productId);
    }
    if (this.cartId) {
      this.getCartById(this.cartId);
    }

  }

  getTotalCart(product: any) {
    const totalCart = {
      price: 0,
      priceDiscount: 0,
      subTotal: 0
    };
    product.forEach((p: any) => {
      totalCart.price += p.price;
      if (p.discount?.percentDiscount) {
        totalCart.priceDiscount += p.price * p.discount.percentDiscount / 100;
      }
      totalCart.subTotal = totalCart.price - totalCart.priceDiscount;
      this.totalCart = totalCart;
    })
  }
  hideCheckout() {
    this.showCheckoutChange.emit(this.showCheckout)
  }


  getProductByID(productID: number) {
    const productList: any[] = [];
    this.productService.getProductById(productID).subscribe(
      (response: any) => {
        productList.push(response.data);
        this.products = productList;
        this.getTotalCart(this.products)
      },
      (error: any) => { },
    )
  }
  getCartById(cartID: number) {
    this.cartService.getCartById(cartID).subscribe(
      (response: any) => {
        this.products = response.data.products;
        this.getTotalCart(this.products)
      },
      (error: any) => { },
    )
  }
  splitEmail(email: any) {
    const atIndex = email.indexOf('@');
    if (atIndex !== -1) {
      return email.slice(0, atIndex);
    } else {
      throw new Error('Invalid email format');
    }
  }

  checkout() {
    if (this.products) {
      let checkoutCart: Checkout = {
        id: 0,
        productCheckout: this.products,
        user: this.user,
        paymentDate: this.checkoutDate,
        paymentMethod: this.selectedPaymentOption,
        amount: this.totalCart.subTotal,
        status: false,
      };

      checkoutCart.user = this.user;
      let test: any = {};
      this.checkoutService.saveCheckout(checkoutCart).subscribe(
        {
          next: (data) => {
            alert(data.message)
          },
          error: (err) => {
            console.log("login error", err)
          },
          complete: () => {
          }
        }
      )
      this.cartService.removeAllProductFromCart(this.user.email).subscribe(
        {
          next: (data) => {
            alert(data.message)
            this.cartService.updateCart();
          },
          error: (err) => {
            console.log("login error", err)
          },
          complete: () => {
          }
        }
      )

    }

  }
}
