import { Component, ElementRef, ViewChild } from '@angular/core';
import { ProductDiscount } from 'src/app/core/models/product';
import { ProductService } from 'src/app/core/service/product.service';

@Component({
  selector: 'app-carousel-swiper-slider',
  templateUrl: './carousel-swiper-slider.component.html',
  styleUrls: ['./carousel-swiper-slider.component.scss']
})
export class CarouselSwiperSliderComponent {
  productDiscount: ProductDiscount[] = [];
  displayCount = 5;
  currentIndex = 0;
  @ViewChild('swiperContent') swiperContent: ElementRef | undefined;

  constructor(
    private productService: ProductService
  ) { }

  ngOnInit(): void {
    //Called after the constructor, initializing input properties, and the first call to ngOnChanges.
    //Add 'implements OnInit' to the class.
    this.productService.getAllProductDiscount().subscribe(
      (response: any) => {
        this.productDiscount = response.data;
        console.log(response.data);

        this.showProducts();
      },
      (err: any) => { },
    )
  }
  showProducts(): void {
    this.productDiscount.forEach((product, index) => {
      if (index >= this.currentIndex && index < this.currentIndex + this.displayCount) {
        product.isHidden = false;
      } else {
        product.isHidden = true;
      }
    });
  }

  next(): void {
    if (this.currentIndex + this.displayCount < this.productDiscount.length) {
      this.currentIndex += 1;
      this.showProducts();
    }
  }

  previous(): void {
    if (this.currentIndex > 0) {
      this.currentIndex -= 1;
      this.showProducts();
    }
  }

}
