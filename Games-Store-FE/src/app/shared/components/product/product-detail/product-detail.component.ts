import { Component } from '@angular/core';
import { ActivatedRoute, Route } from '@angular/router';
import { Observable, switchMap } from 'rxjs';
import { Discount } from 'src/app/core/models/discount';
import { Product } from 'src/app/core/models/product';
import { CartService } from 'src/app/core/service/cart.service';
import { ProductService } from 'src/app/core/service/product.service';

@Component({
  selector: 'app-product-detail',
  templateUrl: './product-detail.component.html',
  styleUrls: ['./product-detail.component.scss']
})
export class ProductDetailComponent {
  id !: number;
  product?: Product | null;
  checkProductExist: boolean = false;
  constructor(
    private route: ActivatedRoute,
    private productService: ProductService,
    private cartService: CartService,
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
        this.checkProductFromCart(this.product);
        console.log(this.checkProductExist);

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
      (response: any) => { },
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
        console.log(this.checkProductExist);

      },
      (err: any) => {
        console.error(err);
      }
    )
  }
}
