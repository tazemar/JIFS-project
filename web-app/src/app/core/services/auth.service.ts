import {Injectable, OnDestroy} from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CookieService } from 'ngx-cookie-service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private apiUrl = 'http://localhost:8080/api/users';

  constructor(private http: HttpClient, private cookieService: CookieService) {
  }

  login(email: string, password: string): Observable<LoginResponse> {
    return this.http.post<LoginResponse>(this.apiUrl, { email, password })
  }

  createAccount(username: string, email: string, password: string) {
    return this.http.post(this.apiUrl + "/create", { username, email, password }, {responseType: 'text'})
  }

  isAuthenticated(): boolean {
    return !!this.cookieService.get('authToken');
  }

  storeUser(userData: LoginResponse ): void {

    const myDate = (new Date().getTime() + userData.jwtExpiration) / 1000;

    this.cookieService.set('authToken', userData.token, myDate, '/', '', true, 'Strict');
    this.cookieService.set('role', userData.role, myDate, '/', '', true, 'Strict');
    this.cookieService.set('userId', userData.id, myDate, '/', '', true, 'Strict'); 
  }

  getAuthToken(): string {
    return this.cookieService.get('authToken');
  }

  getRole(): string {
    return this.cookieService.get('role');
  }

  getUserId(): string {
    return this.cookieService.get('userId');
  }

  logout(): void {
    this.cookieService.delete('authToken');
    this.cookieService.delete('role');
    this.cookieService.delete('userId');
  }
}
