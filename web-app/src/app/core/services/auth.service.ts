import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor() { }

  isAuthenticated(): boolean {
    // Remplacez cette logique par votre méthode d'authentification réelle
    // Exemple : vérification d'un token dans le localStorage
    return !!localStorage.getItem('auth_token');
  }

  // Méthode pour simuler une connexion
  login(token: string): void {
    localStorage.setItem('auth_token', token);
  }

  // Méthode pour simuler une déconnexion
  logout(): void {
    localStorage.removeItem('auth_token');
  }
}
