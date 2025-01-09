import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {MainLayoutComponent} from './main-layout/main-layout.component';
import {RouterOutlet} from '@angular/router';


@NgModule({
  declarations: [MainLayoutComponent],
  imports: [
    CommonModule,
    RouterOutlet,
  ],
  exports: [MainLayoutComponent],
})
export class MainLayoutModule { }
