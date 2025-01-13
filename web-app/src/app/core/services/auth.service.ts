import {Injectable, OnDestroy} from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private apiUrl = 'http://localhost:8080/api/users';

  constructor(private http: HttpClient) {
  }

  login(email: string, password: string): Observable<string> {
    return this.http.post(this.apiUrl, { email, password }, {responseType: 'text'})
  }

  createAccount(username: string, email: string, password: string) {
    return this.http.post(this.apiUrl, { username, email, password }, {responseType: 'text'})
  }

  isAuthenticated(): boolean {
    return !!sessionStorage.getItem('auth_token');
  }

  storeToken(token: string ): void {
    sessionStorage.setItem('auth_token', token);
  }

  logout(): void {
    sessionStorage.removeItem('auth_token');
  }
}
