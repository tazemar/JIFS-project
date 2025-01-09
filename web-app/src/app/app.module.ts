import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import { AuthModule } from './components/auth/auth.module';
import { MainLayoutModule } from './components/main-layout/main-layout.module';

@NgModule({
  declarations: [
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    AuthModule,
    MainLayoutModule,
  ],
  providers: [],
})
export class AppModule { }
