// src/app/interceptors/token.interceptor.spec.ts
import { TestBed } from '@angular/core/testing';
import { HTTP_INTERCEPTORS, HttpClient, HttpHandler, HttpRequest, HttpEvent } from '@angular/common/http';
import { of } from 'rxjs';
import { JwtInterceptor } from './token.interceptor';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';

describe('TokenInterceptor', () => {
  let interceptor: JwtInterceptor;
  let authService: jasmine.SpyObj<AuthService>;
  let router: jasmine.SpyObj<Router>;

  beforeEach(() => {
    const authServiceSpy = jasmine.createSpyObj('AuthService', ['isAuthenticated']);
    const routerSpy = jasmine.createSpyObj('Router', ['navigate']);

    TestBed.configureTestingModule({
      providers: [
        JwtInterceptor,
        { provide: AuthService, useValue: authServiceSpy },
        { provide: Router, useValue: routerSpy },
        {
          provide: HTTP_INTERCEPTORS,
          useClass: JwtInterceptor,
          multi: true
        }
      ]
    });

    interceptor = TestBed.inject(JwtInterceptor);
    authService = TestBed.inject(AuthService) as jasmine.SpyObj<AuthService>;
    router = TestBed.inject(Router) as jasmine.SpyObj<Router>;
  });

  it('should be created', () => {
    expect(interceptor).toBeTruthy();
  });

  it('should add Authorization header when token exists', () => {
    // Mock userValue getter to return a token
    Object.defineProperty(authService, 'userValue', {
      get: () => ({ token: 'sample-token' }),
      configurable: true
    });

    const req = new HttpRequest('GET', '/test');
    const next: HttpHandler = {
      handle: (request: HttpRequest<any>) => {
        expect(request.headers.get('Authorization')).toBe('Bearer sample-token');
        return of({} as HttpEvent<any>);
      }
    };

    interceptor.intercept(req, next).subscribe();
  });

  it('should not add Authorization header when token does not exist', () => {
    // Mock userValue getter to return null
    Object.defineProperty(authService, 'userValue', {
      get: () => null,
      configurable: true
    });

    spyOn(localStorage, 'getItem').and.returnValue(null);

    const req = new HttpRequest('GET', '/test');
    const next: HttpHandler = {
      handle: (request: HttpRequest<any>) => {
        expect(request.headers.has('Authorization')).toBeFalse();
        return of({} as HttpEvent<any>);
      }
    };

    interceptor.intercept(req, next).subscribe();
  });

  it('should add Authorization header from localStorage when userValue is null', () => {
    // Mock userValue getter to return null
    Object.defineProperty(authService, 'userValue', {
      get: () => null,
      configurable: true
    });

    const token = 'local-storage-token';
    spyOn(localStorage, 'getItem').and.returnValue(token);

    const req = new HttpRequest('GET', '/test');
    const next: HttpHandler = {
      handle: (request: HttpRequest<any>) => {
        expect(request.headers.get('Authorization')).toBe(`Bearer ${token}`);
        return of({} as HttpEvent<any>);
      }
    };

    interceptor.intercept(req, next).subscribe();
  });
});
