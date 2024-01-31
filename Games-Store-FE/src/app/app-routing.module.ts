import { Injectable, NgModule } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterModule, RouterStateSnapshot, Routes } from '@angular/router';
import { DistributionComponent } from './shared/components/distribution/distribution.component';
import { SupportComponent } from './shared/components/support/support.component';
import { MenuComponent } from './shared/components/menu/menu.component';
import { BrowseComponent } from './shared/components/browse/browse.component';
import { NewsComponent } from './shared/components/news/news.component';
import { DiscoverComponent } from './shared/components/discover/discover.component';
import { WishlistComponent } from './shared/components/wishlist/wishlist.component';
import { CartComponent } from './shared/components/cart/cart.component';
import { SignInComponent } from './shared/components/authentication-form/sign-in/sign-in.component';
import { LayoutComponent } from './shared/components/layout/layout.component';
import { ProductComponent } from './shared/components/product/product.component';
import { SignUpComponent } from './shared/components/authentication-form/sign-up/sign-up.component';

const routes: Routes = [
  {
    path: '', component: LayoutComponent,
    children: [
      {
        path: 'menu', component: MenuComponent,
        children: [
          { path: 'discover', component: DiscoverComponent },
          { path: 'browser', component: BrowseComponent },
          { path: 'news', component: NewsComponent },
          { path: 'wishlist', component: WishlistComponent },
          { path: 'cart', component: CartComponent },
        ]
      },
      { path: '', redirectTo: 'menu/discover', pathMatch: 'full' },
      { path: 'distribution', component: DistributionComponent },
    ]
  },
  { path: 'login', component: SignInComponent },
  { path: 'sign-up', component: SignUpComponent },
  { path: 'support', component: SupportComponent },
  { path: 'product', component: ProductComponent },
  { path: '**', redirectTo: '' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {

}
