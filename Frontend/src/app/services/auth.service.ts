// src/app/services/auth.service.ts
import { Injectable, Inject, PLATFORM_ID } from '@angular/core';
import { HttpClient } from '@angular/common/http'; 
import { Observable, BehaviorSubject, throwError } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { JwtHelperService } from '@auth0/angular-jwt';
import { isPlatformBrowser } from '@angular/common';


export interface User {
  id?: number;
  fullName: string;
  headline: string;
  email: string;
  dateOfBirth: string;
  about: string;
  location: string;
  profilePictureUrl: string;
  backgroundImageUrl: string;
  experiences:string[];
  education: string[];
  followers: number;
  posts?: any[];
}


@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private authUrl = 'http://localhost:8080/api'; 
  private currentUserSubject: BehaviorSubject<any>;
  public currentUser: Observable<any>;

  constructor(
    private http: HttpClient, 
    private jwtHelper: JwtHelperService,
    @Inject(PLATFORM_ID) private platformId: Object
  ) { 
    let token: string | null = null;
    
    if (isPlatformBrowser(this.platformId)) {
      token = localStorage.getItem('token');
    }

    try {
      this.currentUserSubject = new BehaviorSubject<any>(token ? this.jwtHelper.decodeToken(token) : null);
      this.currentUser = this.currentUserSubject.asObservable();
    } catch (err) {
    //  console.error('Error decoding token:', err);
      this.currentUserSubject = new BehaviorSubject<any>(null);
      this.currentUser = this.currentUserSubject.asObservable();
    }
  }

  public get userValue() {
    console.log(this.currentUserSubject.value)
    return this.currentUserSubject.value;
  }

  getAllUsers(): Observable<any[]> {
    return this.http.get<any[]>(`${this.authUrl}/users`).pipe(
      catchError((error) => {
        console.error('Error fetching users:', error);
        throw error;
      })
    );
  }

  getUserById(userId: number): Observable<User> {
    return this.http.get<User>(`${this.authUrl}/users/${userId}`);
  }

  getUserByEmail(email: String): Observable<User> {
    return this.http.get<User>(`${this.authUrl}/users/email/${email}`);
  }



  login(credentials: {email: string, password: string}): Observable<any> {
   // console.log(this.authUrl)
    return this.http.post<any>(`${this.authUrl}/users/login`, credentials)
      .pipe(
        tap(response => {
          if (response.token) {
            if (isPlatformBrowser(this.platformId)) {
              localStorage.setItem('token', response.token);
            }
            this.currentUserSubject.next(this.jwtHelper.decodeToken(response.token));
          }
        })
      );
  }

  signup(data: { fullName: string, headline: string, email: string, password: string }): Observable<any> {
    console.log("Signup initiated with data:", data);
    return this.http.post<any>(`${this.authUrl}/users`, data)
      .pipe(
        tap(response => {
          console.log("Signup response:", response);
          // Handle response if needed, e.g., store token
        }),
        catchError(error => {
          console.error("Signup error:", error);
          throw error;
        })
      );
  }

  logout() {
    if (isPlatformBrowser(this.platformId)) {
      localStorage.removeItem('token');
    }
    this.currentUserSubject.next(null);
  }

  isAuthenticated(): boolean {
    let token: string | null = null;
    if (isPlatformBrowser(this.platformId)) {
      token = localStorage.getItem('token');
    }
    return token != null && !this.jwtHelper.isTokenExpired(token);
  }

  getToken(): string | null {
    if (isPlatformBrowser(this.platformId)) {
      return localStorage.getItem('token');
    }
    return null;
  }

  getCurrentUser(): Observable<User> {
    return this.http.get<User>(`${this.authUrl}/users/email/dahood.yazan8@gmail.com`)
      .pipe(
        tap((user: User) => {
          this.currentUserSubject.next(user);
        }),
        catchError((error) => {
          console.error('Error fetching current user:', error);
          return throwError(error);
        })
      );
  }

}
