// src/app/components/signup/signup.component.ts
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { AuthService } from '../../services/auth.service';
import { Router, RouterLink } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-signup',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterLink],
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss']
})
export class SignupComponent implements OnInit {
  signupForm!: FormGroup;
  submitted = false;
  loading = false;
  errorMessage: string | null = null;
  successMessage: string | null = null;

  constructor(
    private formBuilder: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) {
    this.signupForm = this.formBuilder.group({
      fullName: ['', Validators.required],
      headline: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(8)]],
      dateOfBirth: [''], // Optional
      about: [''], // Optional
      location: [''], // Optional
      profilePictureUrl: [''], // Optional
      backgroundImageUrl: [''] // Optional
    });
  }

  ngOnInit(): void {
    // Any initialization logic can go here
  }

  // Convenience getter for easy access to form fields
  get f() { return this.signupForm.controls; }

  onSubmit(): void {
    this.submitted = true;
    this.errorMessage = null;
    this.successMessage = null;

    // Stop if the form is invalid
    if (this.signupForm.invalid) {
      return;
    }

    this.loading = true;

    const signupData = {
      fullName: this.f['fullName'].value,
      headline: this.f['headline'].value,
      email: this.f['email'].value,
      password: this.f['password'].value,
      dateOfBirth: this.f['dateOfBirth'].value,
      about: this.f['about'].value,
      location: this.f['location'].value,
      profilePictureUrl: this.f['profilePictureUrl'].value,
      backgroundImageUrl: this.f['backgroundImageUrl'].value
    };

    this.authService.signup(signupData).subscribe({
      next: (response) => {
        this.loading = false;
        this.successMessage = 'Signup successful! Redirecting to login...';
        // Optionally, redirect to login after a delay
        setTimeout(() => {
          this.router.navigate(['/login']);
        }, 2000);
      },
      error: (error) => {
        this.loading = false;
        if (error.error) {
          this.errorMessage = error.error;
        } else {
          this.errorMessage = 'An unexpected error occurred. Please try again.';
        }
      }
    });
  }
}