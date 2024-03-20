import { Component } from '@angular/core';
import { FormBuilder, FormControlName, FormGroup, Validators } from '@angular/forms';
import { Observable } from 'rxjs';
import { AccountService } from 'src/app/core/service/account.service';
const email = localStorage.getItem('email');

@Component({
  selector: 'app-account-setting',
  templateUrl: './account-setting.component.html',
  styleUrls: ['./account-setting.component.scss']
})

export class AccountSettingComponent {
  account = {
    email: '',
    firstName: '',
    lastName: '',
    DOB: '',
    address: '',
  }
  constructor(
    private accountService: AccountService,
    private fb: FormBuilder,
  ) {
    this.accountForm = fb.group({
      email: email,
      firstName: ['', [Validators.required]],
      lastName: ['', [Validators.required]],
      address: ['', [Validators.required]],
    })
  }

  accountForm!: FormGroup;

  ngOnInit(): void {
    //Called after the constructor, initializing input properties, and the first call to ngOnChanges.
    //Add 'implements OnInit' to the class.
    if (email) {
      this.getAccount(email).subscribe(
        (response: any) => {
          this.account = response.data;
        },
        (err: any) => { }
      )
    }

  }
  getAccount(email: any) {
    return this.accountService.getAccountByUserEmail(email);
  }


  onSubmit() {

    if (!this.accountForm.invalid) {
      this.accountService.updateAccount(this.accountForm.value).subscribe(
        (response: any) => {
          alert(response.message);
          this.account = response.data;
        },
        (err: any) => {
          console.error(err);


        }
      )
    }
  }
}
