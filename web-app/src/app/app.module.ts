import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import { AuthModule } from './components/auth/auth.module';
import { MainLayoutModule } from './components/main-layout/main-layout.module';
import {AppComponent} from './app.component';

@NgModule({
  declarations: [
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    AuthModule,
    MainLayoutModule,
    AppComponent
  ],
  providers: [],
})
export class AppModule { }
