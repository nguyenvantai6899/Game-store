import { Component, OnInit, OnDestroy, ViewChild, ElementRef, SimpleChanges, ChangeDetectorRef } from '@angular/core';
import { Image, Product } from 'src/app/core/models/product';
import { ProductService } from 'src/app/core/service/product.service';

@Component({
  selector: 'app-discover',
  templateUrl: './discover.component.html',
  styleUrls: ['./discover.component.scss']
})
export class DiscoverComponent implements OnInit, OnDestroy {
  products: Product[] = [];
  productsCarousel: Product[] = [];
  productImageSlider: Product | undefined;
  activeIndex = 0;
  intervalSlide: any;
  @ViewChild('slideShow') slideShow!: ElementRef;
  constructor(private productService: ProductService, private cdr: ChangeDetectorRef) { }

  ngOnInit(): void {
    this.productService.getProducts().subscribe(
      (response: any) => {
        this.products = response.data || [];
        this.productsCarousel = this.products.slice(this.products.length - 6, this.products.length);
        this.initializeCarousel();
      },
      (err: any) => {
        console.error(err);
      }
    );
  }

  initializeCarousel(): void {
    if (this.productsCarousel.length > 0) {
      this.productImageSlider = this.productsCarousel[this.activeIndex];
      this.startCarousel();
    }
  }

  startCarousel(): void {
    this.intervalSlide = setInterval(() => {
      this.activeIndex++;
      if (this.activeIndex === this.productsCarousel.length) {
        this.activeIndex = 0;
      }
      this.updateSlideAnimation();
      this.productImageSlider = this.productsCarousel[this.activeIndex];
    }, 5000);
  }

  focusSlide(product: Product): void {
    this.clearIntervalSlide();
    this.productImageSlider = product;
    this.startCarousel();
  }

  setActiveIndex(index: number): void {
    this.activeIndex = index;
  }

  clearIntervalSlide(): void {
    clearInterval(this.intervalSlide);
  }


  slideAnimationClass: string = '';

  updateSlideAnimation() {
    this.slideAnimationClass = 'slide-from-right';
    this.cdr.detectChanges(); // Force change detection
    console.log(123);

    setTimeout(() => {
      this.slideAnimationClass = '';
      this.cdr.detectChanges(); // Force change detection
    }, 500); // Adjust the delay based on your animation duration
  }

  ngOnDestroy(): void {
    this.clearIntervalSlide();
  }
}
