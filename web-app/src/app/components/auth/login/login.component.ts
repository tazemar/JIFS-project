import { Component } from '@angular/core';
import {Router, RouterLink} from '@angular/router';
import {AuthService} from '../../../core/services/auth.service';
import {FormsModule} from '@angular/forms';
import {Observable} from 'rxjs';
import {NgIf} from '@angular/common';
import {SvgIconComponent} from "angular-svg-icon";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  imports: [
    RouterLink,
    FormsModule,
    SvgIconComponent
  ],
  styleUrl: './login.component.scss'
})
export class LoginComponent {

  email: string = '';
  password: string = '';
  errorMessage: string = '';

  token$!: Observable<string>;

  constructor(private authService: AuthService, private router: Router) { }

  async onSubmit(): Promise<void> {
    if (this.email && this.password) {
      try {
        this.authService.login(this.email, this.password).subscribe({
          next: (response) => {
            this.authService.storeToken(response);
          },
          error: (e) => console.error(e),
          complete: () => this.router.navigate([''])
        })
      } catch (error) {
        this.errorMessage = 'Erreur lors de la connexion';
      }
    } else {
      this.errorMessage = 'Email et mot de passe requis!';
    }
  }
}
