import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CommonModule } from '@angular/common';

import { HomeComponent } from './home/home.component';
import { LoginComponent } from './auth/login/login.component';
import { RegistrationComponent } from './auth/registration/registration.component';
import { ProjectionsComponent } from './home/lists/projections/projections.component';
import { CinemasComponent } from './home/lists/cinemas/cinemas.component';
import { UsersComponent } from './home/lists/users/users.component';
import { CinemaComponent } from './home/profile/cinema/cinema.component';
import { UserComponent } from './home/profile/user/user.component';
import { TheatersComponent } from './home/lists/theaters/theaters.component';
import { TheaterComponent } from './home/profile/theater/theater.component';
import { ViewersComponent } from './home/lists/viewers/viewers.component';
import { ReservedComponent } from './home/lists/reserved/reserved.component';
import { WatchedComponent } from './home/lists/watched/watched.component';
import { ProjectionComponent } from './home/profile/projection/projection.component';

const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: '/login' },
  { path: 'login', component: LoginComponent },
  { path: 'registration', component: RegistrationComponent },
  {
    path: 'home', component: HomeComponent, children: [
      { path: 'cinemas', component: CinemasComponent },
      { path: 'cinemas/:id/manager', component: CinemasComponent },
      { path: 'users', component: UsersComponent },
      { path: 'viewers', component: ViewersComponent },
      { path: 'users/:id', component: UsersComponent },
      { path: 'inactive-managers', component: UsersComponent },
      { path: 'managers/:id/cinema', component: UsersComponent },
      { path: 'cinema/:id', component: CinemaComponent },
      { path: 'cinema', component: CinemaComponent },
      { path: 'user', component: UserComponent },
      { path: 'theaters/:id/cinema', component: TheatersComponent },
      { path: 'theater/:id', component: TheaterComponent },
      { path: 'theater', component: TheaterComponent },
      { path: 'projection', component: ProjectionComponent },
      { path: 'projections', component: ProjectionsComponent },
      { path: 'reserved-tickets', component: ReservedComponent },
      { path: 'graded-films/:id/viewer', component: WatchedComponent },
      { path: 'ungraded-films', component: WatchedComponent },
    ]
  }
]

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }