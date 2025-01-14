import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators} from '@angular/forms';
import {AuthService} from '../../../core/services/auth.service';
import {Router, RouterLink} from '@angular/router';
import {ErrorService} from '../../../shared/components/error-popup/error.service';
import {NgIf} from '@angular/common';

@Component({
  selector: 'app-create-account',
  templateUrl: './create-account.component.html',
  styleUrl: './create-account.component.scss',
  imports: [
    FormsModule,
    ReactiveFormsModule,
    NgIf,
    RouterLink
  ]
})
export class CreateAccountComponent implements OnInit {

  createAccountForm: FormGroup;

  constructor(private authService: AuthService,
              private router: Router,
              private errorService: ErrorService,
              private fb: FormBuilder) {
    this.createAccountForm = new FormGroup({});
  }

  ngOnInit(): void {
    this.createAccountForm = this.fb.group({
      username: ['', [Validators.required, Validators.minLength(3)]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]],
    });
  }

  isFormValid(): boolean {
    return this.createAccountForm.valid;
  }

  async onSubmit(): Promise<void> {
    if (this.isFormValid()) {
      try {
        const formValues = this.createAccountForm.value;
        this.authService.createAccount(formValues.username, formValues.email, formValues.password).subscribe({
          next: (response) => {
            console.log(response);
          },
          error: (e) => {
            const errorObj = JSON.parse(e.error);
            console.error(errorObj);
            this.errorService.showError(errorObj.description);
          },
          complete: () => this.router.navigate([''])
        })
      } catch (error) {
        this.errorService.showError('Erreur lors de la creation du compte');
      }
    } else {
      this.errorService.showError('Email et mot de passe requis!');
    }
  }
}
