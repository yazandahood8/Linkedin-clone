import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { AuthService, User } from '../../services/auth.service';

@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [CommonModule, HttpClientModule],
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss'],
})
export class ProfileComponent implements OnInit {
  email!: string; // Holds the email from route parameter
  user!: User; // Holds the fetched user data
  isLoading = true;

  constructor(private route: ActivatedRoute, private authService: AuthService) {}

  ngOnInit(): void {
    // Get the email from the route parameter
    this.email = this.route.snapshot.paramMap.get('email') || '';
    if (this.email) {
      this.fetchUserProfile();
    } else {
      console.error('Email is null or undefined in the route.');
    }
  }

  fetchUserProfile(): void {
    this.authService.getUserByEmail(this.email).subscribe({
      next: (user) => {
        this.user = user;
        console.log(this.user)
        this.isLoading = false;
      },
      error: (err) => {
        console.error('Failed to fetch user profile:', err);
        this.isLoading = false;
      },
    });
  }
}
