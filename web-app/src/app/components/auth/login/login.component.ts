import {Component, OnInit} from '@angular/core';
import {Router, RouterLink} from '@angular/router';
import {AuthService} from '../../../core/services/auth.service';
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators} from '@angular/forms';
import {Observable} from 'rxjs';
import {NgIf} from '@angular/common';
import {SvgIconComponent} from "angular-svg-icon";
import e, {response} from 'express';
import {ErrorService} from '../../../shared/components/error-popup/error.service';
import {TranslateModule} from '@ngx-translate/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  imports: [
    RouterLink,
    FormsModule,
    SvgIconComponent,
    ReactiveFormsModule,
    NgIf,
    TranslateModule
  ],
  styleUrl: './login.component.scss'
})
export class LoginComponent implements OnInit {

  loginAccountForm: FormGroup;

  constructor(private authService: AuthService,
              private router: Router,
              private errorService: ErrorService,
              private fb: FormBuilder) {
    this.loginAccountForm = new FormGroup({});
  }

  ngOnInit(): void {
    this.loginAccountForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]],
    });
  }

  async onSubmit(): Promise<void> {
    if (this.isFormValid()) {
      const formValues = this.loginAccountForm.value;
      if (formValues.email && formValues.password) {
        try {
          this.authService.login(formValues.email, formValues.password).subscribe({
            next: (response) => {
              this.authService.storeToken(response);
            },
            error: (e) => {
              const errorObj = JSON.parse(e.error);
              console.error(errorObj);
              this.errorService.showError(errorObj.description);
            },
            complete: () => this.router.navigate([''])
          })
        } catch (error) {
          this.errorService.showError('Erreur lors de la connexion');
        }
      } else {
        this.errorService.showError('Email et mot de passe requis!');
      }
    }
  }

  isFormValid(): boolean {
    return this.loginAccountForm.valid;
  }
}
