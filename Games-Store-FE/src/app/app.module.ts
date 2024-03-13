import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './shared/layout/header/header.component';
import { SupportComponent } from './shared/components/support/support.component';
import { DistributionComponent } from './shared/components/distribution/distribution.component';
import { DiscoverComponent } from './shared/components/menu/discover/discover.component';
import { BrowseComponent } from './shared/components/menu/browse/browse.component';
import { NewsComponent } from './shared/components/menu/news/news.component';
import { WishlistComponent } from './shared/components/user/wishlist/wishlist.component';
import { CartComponent } from './shared/components/user/cart/cart.component';
import { MenuComponent } from './shared/components/menu/menu.component';
import { SignInComponent } from './shared/authentication-form/sign-in/sign-in.component';
import { LayoutComponent } from './shared/layout/layout.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { HttpClientModule } from "@angular/common/http";
import { ProductComponent } from './shared/components/product/product.component';
import { SignUpComponent } from './shared/authentication-form/sign-up/sign-up.component';
import { InformInfUserComponent } from './shared/authentication-form/inform-inf-user/inform-inf-user.component';
import { ContentComponent } from "./shared/layout/content/content.component";
import { FooterComponent } from "./shared/layout/footer/footer.component";
import { DialogComponent } from './shared/components/dialog/dialog.component';
import { PrimengModuleModule } from './modules/primeng-module/primeng-module.module';
import { CarouselSwiperSliderComponent } from './shared/components/product/carousel-swiper-slider/carousel-swiper-slider.component';
import { SearchByFeatureComponent } from './shared/components/product/search-by-feature/search-by-feature.component';
import { SearchByWatcherComponent } from './shared/components/product/search-by-watcher/search-by-watcher.component';
import { ProductDetailComponent } from './shared/components/product/product-detail/product-detail.component';
import { ToastComponent } from './shared/components/toast/toast.component';
import { CheckoutComponent } from './shared/components/user/checkout/checkout.component';


@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    SupportComponent,
    DistributionComponent,
    ContentComponent,
    DiscoverComponent,
    BrowseComponent,
    NewsComponent,
    WishlistComponent,
    CartComponent,
    FooterComponent,
    MenuComponent,
    SignUpComponent,
    LayoutComponent,
    ProductComponent,
    SignInComponent,
    InformInfUserComponent,
    DialogComponent,
    CarouselSwiperSliderComponent,
    SearchByFeatureComponent,
    SearchByWatcherComponent,
    ProductDetailComponent,
    ToastComponent,
    CheckoutComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    HttpClientModule,
    PrimengModuleModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
