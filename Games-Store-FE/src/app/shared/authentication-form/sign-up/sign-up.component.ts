import { Component } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/core/service/auth.service';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.scss']
})
export class SignUpComponent {
  formSignUp: FormGroup;

  constructor(
    private router: Router,
    private fb: FormBuilder,
    private authService: AuthService
  ) {
    this.formSignUp = fb.group({
      username: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]],
    }, { validator: this.passwordMatch });
  }
  get f() {
    return this.formSignUp.controls;
  }

  passwordMatch(controls: FormGroup) {
    const password = controls.get('password')?.value;
    const confirmPassword = controls.get('confirmPassword')?.value;
    return password === confirmPassword ? null : { passwordMismatch: true }
  }

  onSubmit() {
    // TODO: Use EventEmitter with form value
    console.warn(this.formSignUp.value);
    this.authService.signUp(this.formSignUp.value).subscribe(
      (response: any) => {
        console.log(response.data);

      },
      (err) => { },
    )
  }
}

