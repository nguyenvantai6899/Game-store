import { Component } from '@angular/core';
import { Form, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { IResponse } from 'src/app/core/models/response';
import { AuthService } from 'src/app/core/service/auth.service';

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.scss']
})
export class SignInComponent {
  formLogin: FormGroup;

  constructor(
    private router: Router,
    private fb: FormBuilder,
    private authService: AuthService
  ) {
    this.formLogin = fb.group({
      username: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]],
    });
  }
  get username() { return this.formLogin.get('username'); }

  get f() { return this.formLogin.controls; }

  onSubmit() {
    if (this.formLogin.invalid) {
      return;
    }
    this.authService.signIn(this.formLogin.get('username')?.value, this.formLogin.get('password')?.value).subscribe(
      (response: any) => {
        if (response.code === 401) {
          alert(response.message);
          return;
        }
        localStorage.setItem('jwt_token', response.data.token);
        localStorage.setItem('email', response.data.userInfo.email);
        this.router.navigate(['']);
      },
      (err: any) => {
        console.log(err);
      },
    )
  }
  signUp() {
    this.router.navigate(['sign-up']);
  }
}

