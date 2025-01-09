import {RouterModule, Routes} from '@angular/router';
import {NgModule} from '@angular/core';
import {AuthLayoutComponent} from './components/auth/auth-layout/auth-layout.component';
import {MainLayoutComponent} from './components/main-layout/main-layout/main-layout.component';
import {CreateAccountComponent} from './components/auth/create-account/create-account.component';
import {LoginComponent} from './components/auth/login/login.component';
import {AuthGuard} from './core/guard/auth.guard';

export const routes: Routes = [
  {
    path: 'auth',
    component: AuthLayoutComponent,
    children: [
      {
        path: '',
        component: LoginComponent,
      },
      {
        path: 'create',
        component: CreateAccountComponent,
      },
    ]
  },
  {
    path: '',
    component: MainLayoutComponent,
    canActivate: [AuthGuard]
  },
  {
    path: '**',
    redirectTo: '/auth',
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
