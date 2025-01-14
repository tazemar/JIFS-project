import { Injectable } from '@angular/core';
import {map, Subject} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ErrorService {
  private errorSubject = new Subject<string>();
  error$ = this.errorSubject.asObservable().pipe(
    map((error: string | null) => error || '')
  );

  constructor() {}

  showError(message: string): void {
    this.errorSubject.next(message);
  }

  clearError(): void {
    this.errorSubject.next('');
  }
}
