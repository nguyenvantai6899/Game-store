import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AccountService } from 'src/app/core/service/account.service';
const email = localStorage.getItem('email');
@Component({
  selector: 'app-password-security',
  templateUrl: './password-security.component.html',
  styleUrls: ['./password-security.component.scss']
})
export class PasswordSecurityComponent {
  constructor(
    private accountService: AccountService,
    private fb: FormBuilder,
  ) {
    this.changePassForm = fb.group({
      retypePassword: ['', [Validators.required]],
      currentPassword: ['', [Validators.required]],
      newPassword: ['', [Validators.required]],
    });
  }

  changePassForm!: FormGroup

  passwordMatch(controls: FormGroup) {
    const password = controls.get('currentPassword')?.value;
    const confirmPassword = controls.get('retypePassword')?.value;
    return password === confirmPassword ? null : { passwordMismatch: true }
  }
  onSubmit() {
    if (this.changePassForm.valid) {
      this.accountService.editPassword(email, this.changePassForm.get('currentPassword')?.value,
        this.changePassForm.get('newPassword')?.value).subscribe(
          (response: any) => {
            alert(response.message);
          },
          (err: any) => { }
        )
    }
  }
}
