import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {CreateAccountComponent} from './create-account/create-account.component';
import {AuthLayoutComponent} from './auth-layout/auth-layout.component';
import {RouterModule} from '@angular/router';
import {LoginComponent} from './login/login.component';

@NgModule({
  declarations: [CreateAccountComponent, LoginComponent],
  imports: [
    CommonModule,
    RouterModule
  ],
  exports: []
})
export class AuthModule { }
