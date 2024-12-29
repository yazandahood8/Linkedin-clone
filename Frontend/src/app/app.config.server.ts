import { importProvidersFrom } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { bootstrapApplication } from '@angular/platform-browser';
import { JwtHelperService, JWT_OPTIONS } from '@auth0/angular-jwt';

import { AppComponent } from './app.component';

// Function to retrieve the token from storage
export function tokenGetter() {
  return localStorage.getItem('access_token');
}

const appConfig = {
  providers: [
    // Provide JWT_OPTIONS and JwtHelperService
    { provide: JWT_OPTIONS, useValue: JWT_OPTIONS }, // Incorrect
    JwtHelperService,

    // Optionally, configure HttpClient with JWT settings
    importProvidersFrom(
      HttpClientModule,
      // If you want to use JwtModule.forRoot equivalent
      // However, since you're not using NgModule, you might need to set up interceptors manually
    ),

    // Add other global providers here
  ],
};

bootstrapApplication(AppComponent, appConfig)
  .catch((err) => console.error(err));
