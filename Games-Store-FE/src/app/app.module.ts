import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './shared/components/layout/header/header.component';
import { SupportComponent } from './shared/components/support/support.component';
import { DistributionComponent } from './shared/components/distribution/distribution.component';
import { DiscoverComponent } from './shared/components/discover/discover.component';
import { BrowseComponent } from './shared/components/browse/browse.component';
import { NewsComponent } from './shared/components/news/news.component';
import { WishlistComponent } from './shared/components/wishlist/wishlist.component';
import { CartComponent } from './shared/components/cart/cart.component';
import { MenuComponent } from './shared/components/menu/menu.component';
import { SignInComponent } from './shared/components/authentication-form/sign-in/sign-in.component';
import { LayoutComponent } from './shared/components/layout/layout.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { HttpClientModule } from "@angular/common/http";
import { ProductComponent } from './shared/components/product/product.component';
import { SignUpComponent } from './shared/components/authentication-form/sign-up/sign-up.component';
import { InformInfUserComponent } from './shared/components/authentication-form/inform-inf-user/inform-inf-user.component';
import { ContentComponent } from "./shared/components/layout/content/content.component";
import { FooterComponent } from "./shared/components/layout/footer/footer.component";
import { DialogComponent } from './shared/components/dialog/dialog.component';
import { PrimengModuleModule } from './modules/primeng-module/primeng-module.module';


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
