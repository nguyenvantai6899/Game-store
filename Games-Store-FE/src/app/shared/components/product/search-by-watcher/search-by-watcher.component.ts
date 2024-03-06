import { Component } from '@angular/core';
import { Product } from 'src/app/core/models/product';
import { ProductService } from 'src/app/core/service/product.service';

@Component({
  selector: 'app-search-by-watcher',
  templateUrl: './search-by-watcher.component.html',
  styleUrls: ['./search-by-watcher.component.scss']
})
export class SearchByWatcherComponent {
  mostPlayer!: Product[];
  topSeller!: Product[];
  topRated!: Product[];
  constructor(private productService: ProductService) { }

  ngOnInit(): void {
    this.productService.getProducts().subscribe(
      (response: any) => {
        const products = response.data;
        this.mostPlayer = this.getMostPlayer(products);
        this.mostPlayer = this.mostPlayer.slice(this.mostPlayer?.length - 5, this.mostPlayer?.length);
        this.topSeller = this.getTopSeller(products);
        this.topRated = this.getTopRated(products);
        this.topRated = this.topRated.slice(this.topRated.length - 5, this.topRated.length);
      },
      (err: any) => {
        console.error(err);
      }
    );
  }
  getMostPlayer(products: Product[]) {
    let mostPlayer: Product[] = products;
    mostPlayer = mostPlayer.sort((a, b) => {
      return b.downloads - a.downloads;
    });
    return mostPlayer.slice(0, 5)
  }
  getTopSeller(products: Product[]) {
    let topSeller: Product[] = [];
    products.forEach((product) => {
      if (product.price > 0) {
        topSeller.push(product);
      }
    });
    topSeller = topSeller.sort((a, b) => {
      return a.downloads * a.price - b.downloads * b.price;
    });
    return topSeller.slice(topSeller.length - 5, topSeller.length);
  }
  getTopRated(products: Product[]) {
    return products.sort((a, b) => {
      return b.downloads * b.price - a.downloads * a.price;
    });;
  }
}
