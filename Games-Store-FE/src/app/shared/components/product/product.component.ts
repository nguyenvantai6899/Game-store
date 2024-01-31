import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Categories, Features, Product } from 'src/app/core/models/product';
import { ProductService } from 'src/app/core/service/product.service';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.scss']
})
export class ProductComponent implements OnInit {
  productForm!: FormGroup;
  categories!: Categories[];
  features!: Features[];
  selectedFiles: File[] = [];
  constructor(private formBuilder: FormBuilder, private productService: ProductService) { }

  onFileSelected(event: any): void {
    this.selectedFiles = Array.from(event.target.files);
  }
  ngOnInit() {
    this.productForm = this.formBuilder.group({
      id: [null],
      name: ['', Validators.required],
      description: [''],
      price: [0, Validators.min(0)],
      releaseDate: [new Date()],
      status: [true],
      category: this.formBuilder.group({
        id: [null],
        categoriesName: ['', Validators.required],
      }),
      features: this.formBuilder.array([]),
    });
  }

  // Access the productImages FormArray
  get productImages() {
    return this.productForm.get('productImages') as FormArray;
  }

  // Access the features FormArray
  get productFeatures() {
    return this.productForm.get('features') as FormArray;
  }

  // Submit form
  onSubmit() {
    const productData = this.productForm.value;
    this.productService.createProduct(productData, this.selectedFiles).subscribe(
      (response: any) => {
        console.log(response.data);
      },
      (error: any) => {
        console.error(error);
      },
    )    // Now you can send formData to your service or API
  }

}
