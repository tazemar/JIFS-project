import { Component } from '@angular/core';
import {FormsModule} from '@angular/forms';
import {SvgIconComponent} from 'angular-svg-icon';
import {AuthService} from '../../../core/services/auth.service';
import {Router} from '@angular/router';
import {ErrorPopupComponent} from '../../../shared/components/error-popup/error-popup.component';
import {ErrorService} from '../../../shared/components/error-popup/error.service';

@Component({
  selector: 'app-create-account',
  templateUrl: './create-account.component.html',
  styleUrl: './create-account.component.scss',
  imports: [
    FormsModule
  ]
})
export class CreateAccountComponent {

  username: string = '';
  email: string = '';
  password: string = '';
  errorMessage: string = '';

  constructor(private authService: AuthService, private router: Router, private errorService: ErrorService) {}

  async onSubmit(): Promise<void> {
    if (this.username && this.email && this.password) {
      try {
        this.authService.createAccount(this.username, this.email, this.password).subscribe({
          next: (response) => {
            console.log(response);
          },
          error: (e) => {
            console.error(e);
            this.errorService.showError(e);
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
