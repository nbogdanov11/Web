import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { NgZorroAntdModule} from 'ng-zorro-antd';
import { IconDefinition } from '@ant-design/icons-angular';
import { NzIconModule, NZ_ICONS } from 'ng-zorro-antd/icon';
import { ReactiveFormsModule } from '@angular/forms';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NZ_I18N } from 'ng-zorro-antd/i18n';
import { en_US } from 'ng-zorro-antd/i18n';

import { registerLocaleData } from '@angular/common';
import en from '@angular/common/locales/en';
import { AppRoutingModule } from './app-routing.module';

import * as AllIcons from '@ant-design/icons-angular/icons';

import { HomeComponent } from './home/home.component';
import { LoginComponent } from './auth/login/login.component';
import { RegistrationComponent } from './auth/registration/registration.component';
import { ProjectionsComponent } from './home/lists/projections/projections.component';
import { CinemasComponent } from './home/lists/cinemas/cinemas.component';
import { CinemaComponent } from './home/profile/cinema/cinema.component';
import { UsersComponent } from './home/lists/users/users.component';
import { UserComponent } from './home/profile/user/user.component';
import { TheatersComponent } from './home/lists/theaters/theaters.component';
import { TheaterComponent } from './home/profile/theater/theater.component';
import { ViewersComponent } from './home/lists/viewers/viewers.component';
import { ProjectionComponent } from './home/profile/projection/projection.component';
import { ReservedComponent } from './home/lists/reserved/reserved.component';
import { WatchedComponent } from './home/lists/watched/watched.component';

const antDesignIcons = AllIcons as {
  [key: string]: IconDefinition;
};
const icons: IconDefinition[] = Object.keys(antDesignIcons).map(key => antDesignIcons[key])

registerLocaleData(en);

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    RegistrationComponent,
    ProjectionsComponent,
    CinemasComponent,
    CinemaComponent,
    UsersComponent,
    UserComponent,
    TheatersComponent,
    TheaterComponent,
    ViewersComponent,
    ProjectionComponent,
    ReservedComponent,
    WatchedComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    NgZorroAntdModule,
    ReactiveFormsModule,
    NzIconModule,
    AppRoutingModule
  ],
  providers: [{ provide: NZ_I18N, useValue: en_US }, {provide: NZ_ICONS, useValue: icons}],
  bootstrap: [AppComponent],
})
export class AppModule { }
