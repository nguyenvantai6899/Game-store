import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DialogModule } from 'primeng/dialog';
import { CarouselModule } from 'primeng/carousel';
import { ButtonModule } from 'primeng/button';
import { TagModule } from 'primeng/tag';
@NgModule({
  declarations: [

  ],
  imports: [
    CommonModule,
  ],
  exports: [
    DialogModule,
    CarouselModule,
    ButtonModule,
    TagModule
  ]
})
export class PrimengModuleModule { }
