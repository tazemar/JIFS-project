import { Component } from '@angular/core';
import {FormsModule} from '@angular/forms';
import {SvgIconComponent} from 'angular-svg-icon';
import {AuthService} from '../../../core/services/auth.service';
import {Router} from '@angular/router';

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

  constructor(private authService: AuthService, private router: Router) {}

  async onSubmit(): Promise<void> {
    if (this.username && this.email && this.password) {
      try {
        this.authService.createAccount(this.username, this.email, this.password).subscribe({
          next: (response) => {
            console.log(response);
            this.authService.storeToken(response);
          },
          error: (e) => console.error(e),
          complete: () => this.router.navigate([''])
        })
      } catch (error) {
        this.errorMessage = 'Erreur lors de la creation du compte';
      }
    } else {
      this.errorMessage = 'Email et mot de passe requis!';
    }
  }

}
