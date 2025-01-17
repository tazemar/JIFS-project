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

  login(email: string, password: string): Observable<LoginResponse> {
    return this.http.post<LoginResponse>(this.apiUrl, { email, password })
  }

  createAccount(username: string, email: string, password: string) {
    return this.http.post(this.apiUrl + "/create", { username, email, password }, {responseType: 'text'})
  }

  isAuthenticated(): boolean {
    return !!sessionStorage.getItem('authToken');
  }

  storeUser(userData: LoginResponse ): void {
    sessionStorage.setItem('authToken', userData.token);
    sessionStorage.setItem('role', userData.role);
    sessionStorage.setItem('userId', userData.id);
  }

  logout(): void {
    sessionStorage.removeItem('authToken');
    sessionStorage.removeItem('role');
    sessionStorage.removeItem('userId');
  }
}
