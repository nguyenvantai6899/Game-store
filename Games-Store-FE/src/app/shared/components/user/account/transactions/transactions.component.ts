import { Component } from '@angular/core';
import { Checkout } from 'src/app/core/models/checkout';
import { CheckoutService } from 'src/app/core/service/checkout.service';
const email = localStorage.getItem('email');
@Component({
  selector: 'app-transactions',
  templateUrl: './transactions.component.html',
  styleUrls: ['./transactions.component.scss']
})
export class TransactionsComponent {
  transactions: Checkout[] = [];
  toggleStates: boolean[] = [];
  state: boolean = true;
  constructor(private checkoutService: CheckoutService) {

  }
  getTransactions() {
    if (email) {
      this.checkoutService.getCheckoutByUserEmail(email).subscribe(
        (response: any) => {
          this.transactions = response.data;
          this.transactions.forEach(() => {
            this.toggleStates.push(true);
          })
        },
        (err: any) => { },
      )
    }
  }

  ngOnInit(): void {
    this.getTransactions();
  }

  toggleClick(index: number) {
    this.state = !this.state;
    this.toggleStates[index] = this.state;

  }
}
