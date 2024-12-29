// app.config.ts
import { ApplicationConfig, importProvidersFrom } from '@angular/core';
import { provideRouter } from '@angular/router';
import { appRoutes } from './app.routes';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { provideHttpClient, HTTP_INTERCEPTORS } from '@angular/common/http';
import { JwtHelperService, JWT_OPTIONS } from '@auth0/angular-jwt';
import { HttpClientModule } from '@angular/common/http';
import { JwtInterceptor } from './jwt.interceptor'; // Ensure correct path
import { isPlatformBrowser } from '@angular/common';
import { PLATFORM_ID, Inject } from '@angular/core';

// Function to retrieve the token from storage safely
export function tokenGetter(platformId: Object) {
  if (isPlatformBrowser(platformId)) {
    return localStorage.getItem('access_token');
  }
  return null;
}

export const appConfig: ApplicationConfig = {
  providers: [
    provideRouter(appRoutes),
    provideAnimationsAsync(),
    provideHttpClient(),
    // JWT Configuration
    {
      provide: JWT_OPTIONS,
      useFactory: (platformId: Object) => ({
        tokenGetter: () => tokenGetter(platformId),
        // Add other configurations if needed
        // allowedDomains: ['your-api-domain.com'],
        // disallowedRoutes: ['your-api-domain.com/auth/'],
      }),
      deps: [PLATFORM_ID],
    },
    JwtHelperService,
    // HTTP Interceptor for JWT
    {
      provide: HTTP_INTERCEPTORS,
      useClass: JwtInterceptor,
      multi: true,
    },
    // Import additional modules if necessary
    importProvidersFrom(HttpClientModule),
  ],
};
