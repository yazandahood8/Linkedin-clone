// src/app/services/post.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'; 
import { Observable } from 'rxjs';
import { CommentService, Comment } from './comment.service';

export interface Post {
  id?: number;
  author: string;
  content: string;
  createdAt?: Date;
  userId:number;
  likes:number;
  liked:boolean;
  comments: Comment[];
  
}

@Injectable({
  providedIn: 'root'
})
export class PostService {
  private postsUrl = 'http://localhost:8082/api/posts'; // Replace with your API

  constructor(private http: HttpClient) { }

  getPosts(): Observable<Post[]> {
    
    return this.http.get<Post[]>(this.postsUrl);
  }

  createPost(post: Post): Observable<Post> {
    return this.http.post<Post>(this.postsUrl, post);
  }

 // Fetch like count for a specific post
 getLikeCount(postId: number): Observable<number> {
  const url = `${this.postsUrl}/${postId}/likes/count`;
  return this.http.get<number>(url);
}



// getCommentsCount(postId: number): Observable<number> {
//   const url = `${this.postsUrl}/${postId}/comments/count`;
//   return this.http.get<number>(url);
// }

}
