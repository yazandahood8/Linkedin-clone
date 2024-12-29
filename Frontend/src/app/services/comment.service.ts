import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, forkJoin } from 'rxjs';
import { map, switchMap } from 'rxjs/operators';
import { AuthService, User } from './auth.service';

export interface Comment {
  id?: number;
  content: string;
  userId: number;
  postId: number;
  createdAt?: Date;
  user?: User; // Include user details
}

@Injectable({
  providedIn: 'root',
})
export class CommentService {
  private commentsUrl = 'http://localhost:8082/api/posts'; // Base URL for comments

  constructor(private http: HttpClient, private userService: AuthService) {}

  getComments(postId: number): Observable<Comment[]> {
    const url = `${this.commentsUrl}/${postId}/comments`;

    return this.http.get<Comment[]>(url).pipe(
      map((comments) =>
        comments.map((comment) => ({
          ...comment,
          user: null, // Placeholder for user
        }))
      ),
      switchMap((comments) => {
        const userObservables = comments.map((comment) =>
          this.userService.getUserById(comment.userId).pipe(
            map((user) => ({
              ...comment,
              user, // Add user details to the comment
            }))
          )
        );
        return forkJoin(userObservables); // Combine all user-fetching observables
      })
    );
  }
}
