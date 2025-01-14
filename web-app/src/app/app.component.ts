import {Component} from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {ErrorPopupComponent} from './shared/components/error-popup/error-popup.component';
import {ErrorService} from './shared/components/error-popup/error.service';
import {AsyncPipe} from '@angular/common';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  imports: [
    RouterOutlet,
    ErrorPopupComponent,
    AsyncPipe
  ],
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'web-app';

  constructor(protected errorService: ErrorService) {}
}
