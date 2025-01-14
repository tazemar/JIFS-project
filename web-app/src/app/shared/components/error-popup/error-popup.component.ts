import {Component, Input} from '@angular/core';
import {NgIf} from '@angular/common';
import {ErrorService} from './error.service';

@Component({
  selector: 'app-error-popup',
  imports: [
    NgIf
  ],
  templateUrl: './error-popup.component.html',
  styleUrl: './error-popup.component.scss'
})
export class ErrorPopupComponent  {
  @Input({
    transform: (value: string | null): string => {
      return value ?? '';
    }
  })
  errorMessage: string = '';

  constructor(private errorService: ErrorService) {}

  closePopup() {
    this.errorService.clearError();
  }
}
