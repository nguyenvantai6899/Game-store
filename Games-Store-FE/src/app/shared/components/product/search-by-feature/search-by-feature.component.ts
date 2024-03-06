import { Component } from '@angular/core';
import { Product } from 'src/app/core/models/product';
import { ProductService } from 'src/app/core/service/product.service';

@Component({
  selector: 'app-search-by-feature',
  templateUrl: './search-by-feature.component.html',
  styleUrls: ['./search-by-feature.component.scss']
})
export class SearchByFeatureComponent {
  products!: Product[];
  productsFree!: Product[];
  constructor(private productService: ProductService) { }

  ngOnInit(): void {
    this.productService.getProducts().subscribe(
      (response: any) => {
        this.products = response.data || [];
        this.productsFree = this.getProductFree(this.products);
        this.productsFree = this.productsFree.slice(this.productsFree.length - 2, this.productsFree.length);
      },
      (err: any) => {
        console.error(err);
      }
    );
  }
  getProductFree(products: Product[]) {
    let productFree: Product[] = [];
    products.forEach(product => {
      if (product.price == 0) {
        productFree.push(product);
      }
    });
    return productFree;
  }
}
