// src/app/components/login/login.component.ts
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { AuthService } from '../../services/auth.service';
import { Router, RouterLink } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { CommonModule } from '@angular/common';
//import { console } from 'inspector';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  submitted = false;
  loading = false;
  errorMessage: string | null = null;

  constructor(
    private formBuilder: FormBuilder,
    private authService: AuthService, // Inject AuthService
    private router: Router
  ) {
    // Initialize the form with form controls and validators
    this.loginForm = this.formBuilder.group({
      email: ['kkk@gmail.com', [Validators.required, Validators.email]],
      password: ['123456789', Validators.required]
    });
  }

  ngOnInit(): void {
    // Any initialization logic can go here
  }

  // Getter for easy access to form fields
  get f() { return this.loginForm.controls; }

  onSubmit(): void {
    this.submitted = true;
    this.errorMessage = null;

    // If the form is invalid, stop the submission
    if (this.loginForm.invalid) {
      return;
    }
    //console.log("aaaa")

    this.loading = true;

    const loginData = {
      email: this.f['email'].value,
      password: this.f['password'].value
    };

    this.authService.login(loginData).subscribe({
      
      next: (response) => {

        this.loading = false;
        // Handle successful login, e.g., navigate to dashboard
        this.router.navigate(['/feed']); // Replace with your desired route
      },
      error: (error: Error) => {
        console.log('stop')

        this.loading = false;
        // Display error message to the user
        this.errorMessage = error.message || 'An unexpected error occurred. Please try again.';
      }
    });
  }
}