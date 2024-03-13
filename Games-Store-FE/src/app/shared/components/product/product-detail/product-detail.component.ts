import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Route } from '@angular/router';
import { Observable, switchMap } from 'rxjs';
import { Discount } from 'src/app/core/models/discount';
import { Product } from 'src/app/core/models/product';
import { CartService } from 'src/app/core/service/cart.service';
import { ProductService } from 'src/app/core/service/product.service';
import { WishlistService } from 'src/app/core/service/wishlist.service';

@Component({
  selector: 'app-product-detail',
  templateUrl: './product-detail.component.html',
  styleUrls: ['./product-detail.component.scss']
})
export class ProductDetailComponent implements OnInit {
  id !: number;
  product?: Product;
  checkProductExist: boolean = false;
  checkProductExistWishlist: boolean = false;
  toast: boolean = false;
  showCheckout: boolean = false;
  constructor(
    private route: ActivatedRoute,
    private productService: ProductService,
    private cartService: CartService,
    private wishlistService: WishlistService
  ) { }
  ngOnInit() {
    this.route.queryParams.pipe(
      switchMap(params => {
        this.id = params['id'];
        if (this.id !== null && this.id !== undefined) {
          return this.getProductById(this.id);
        }
        return new Observable();
      })
    ).subscribe(
      (response: any) => {
        this.product = response.data;
        if (this.product) {
          this.checkProductFromCart(this.product);
          this.checkProductFromWishlist(this.product);
        }
      },
      (err: any) => {
        console.error(err);
      }
    );
  }
  getProductById(id: number): Observable<Product> {
    return this.productService.getProductById(id);
  }

  addToCart(productID: number) {
    const email: any = localStorage.getItem('email');
    this.cartService.addProductIntoCart(email, productID).subscribe(
      (response: any) => {
        if (response.data) {
          this.cartService.updateCart();
          this.checkProductExist = true;
        }
      },
      (err: any) => {
        console.error(err);
      }
    )
  }

  addToWishlist(productID: number) {
    const email: any = localStorage.getItem('email');
    this.wishlistService.addProductIntoWishlist(email, productID).subscribe(
      (response: any) => {
        if (response.data) {
          this.wishlistService.updateCart();
          this.checkProductExistWishlist = true;
        }
      },
      (err: any) => {
        console.error(err);
      }
    )
  }

  checkProductFromCart(product: any) {
    const email: any = localStorage.getItem('email');
    let products: Product[];
    this.cartService.getCartByUserEmail(email).subscribe(
      (response: any) => {
        products = response.data.products;
        for (const p of products) {
          if (p.id === product.id) {
            this.checkProductExist = true;
            break;
          }
        }
      },
      (err: any) => {
        console.error(err);
      }
    )
  }
  checkProductFromWishlist(product: any) {
    const email: any = localStorage.getItem('email');
    let products: Product[];
    this.wishlistService.getWishlistByUserEmail(email).subscribe(
      (response: any) => {
        for (const p of response.data) {
          if (p.product.id === product.id) {
            this.checkProductExistWishlist = true;
            break;
          }
        }
      },
      (err: any) => {
        console.error(err);
      }
    )
  }
  showToast(): void {
    this.toast = true;
  }
  showCheckoutClick(): void {
    this.showCheckout = true;
  }
  receiveMessage(event: boolean) {
    this.showCheckout = !event
  }
}
