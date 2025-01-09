import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {CreateAccountComponent} from './create-account/create-account.component';
import {AuthLayoutComponent} from './auth-layout/auth-layout.component';
import {RouterLink, RouterOutlet} from '@angular/router';

@NgModule({
  declarations: [CreateAccountComponent, AuthLayoutComponent],
  imports: [
    CommonModule,
    RouterOutlet,
    RouterLink
  ],
  exports: [AuthLayoutComponent]
})
export class AuthModule { }
