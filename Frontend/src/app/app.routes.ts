import { Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { SignupComponent } from './components/signup/signup.component';
import { FeedComponent } from './components/feed/feed.component';
import { ProfileComponent } from './components/profile/profile.component'; // Import ProfileComponent
import { AuthGuard } from './guards/auth.guard';
import { MyNetworkComponent } from './components/my-network/my-network.component'; // Import ProfileComponent
import { JobsComponent } from './components/jobs/jobs.component'; // Import ProfileComponent

export const appRoutes: Routes = [
  { path: '', redirectTo: '/feed', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'signup', component: SignupComponent },
  { path: 'feed', component: FeedComponent, canActivate: [AuthGuard] },
  { path: 'myNetWork', component: MyNetworkComponent, canActivate: [AuthGuard] },
  { path: 'jobs', component: JobsComponent, canActivate: [AuthGuard] },

  { path: 'profile/:email', component: ProfileComponent }, // Profile route
  // Wildcard route for a 404 page (optional)
  { path: '**', redirectTo: '/feed' },
];
