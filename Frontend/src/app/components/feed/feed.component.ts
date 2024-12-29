import { Component, OnInit, ElementRef, ViewChild } from '@angular/core';
import { PostService, Post } from '../../services/post.service';
import { CommentService, Comment } from '../../services/comment.service';
import { Router } from '@angular/router';

import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { CommonModule } from '@angular/common';
import { AuthService, User } from '../../services/auth.service';
import { Subscription } from 'rxjs';

declare var bootstrap: any;

@Component({
  selector: 'app-feed',
  standalone:true,
    imports: [ReactiveFormsModule,CommonModule],
  
  templateUrl: './feed.component.html',
  styleUrls: ['./feed.component.scss'],
})
export class FeedComponent implements OnInit {
[x: string]: any;

  currentUser: User | null = null;
  private subscription: Subscription = new Subscription();


  posts: Array<{ post: Post; user: User | null }> = []; // Combine posts with user data
  postForm!: FormGroup;
  @ViewChild('postModal', { static: true }) postModal!: ElementRef;
  user: any = null; // To hold the authenticated user's data

  constructor(
    private commentService: CommentService,
    private router: Router,
    private postService: PostService,
     private authService: AuthService,
    private fb: FormBuilder,
    private toastr: ToastrService
  ) {}

  ngOnInit(): void {
    //console.log(this.user)

    this.subscription = this.authService.currentUser.subscribe(user => {
      this.currentUser = user;
    });
    if (this.authService.isAuthenticated()) {
      this.authService.getCurrentUser().subscribe();
    }
    //this.user = this.authService.userValue;
    this.getUserByEmail("dahood.yazan8@gmail.com");
    this.loadPosts();
    this.postForm = this.fb.group({
      author: ['', Validators.required],
      content: ['', [Validators.required, Validators.maxLength(500)]],
    });
  }

  goToProfile(email: string  | undefined): void {
    console.log(email)
    this.router.navigate(['/profile/', email]);
  }

  getUserByEmail(email: string): void {
    this.authService.getUserByEmail(email).subscribe({
      next: (user: User) => {
        this.user = user; // Assign the retrieved user to this.user
        console.log(this.user)
      },
      error: (err) => {
        console.error('Error fetching user by email:', err);
      },
    });
  }
  toggleLike(post: Post & { liked?: boolean; likes?: number }): void {
    if (post.liked) {
      post.likes = (post.likes || 0) - 1; // Decrease like count
    } else {
      post.likes = (post.likes || 0) + 1; // Increase like count
    }
    post.liked = !post.liked; // Toggle liked status
  }




  loadPosts(): void {
    this.postService.getPosts().subscribe({
      next: (postList) => {
        this.posts = [];
        postList.forEach((post) => {
          this.authService.getUserById(post.userId).subscribe({
            next: (user) => {
              this.posts.push({ post, user });
            },
            error: () => {
              this.posts.push({ post, user: null }); // Fallback if user not found
            },
          });


          this.postService.getLikeCount(post.id!).subscribe((likes) => {
            post.likes = likes; // Dynamically add likes to the post object
          })

         
        });
      },
      error: (err) => {
        console.error('Failed to load posts:', err);
      },
    });





    
  }
  getComments(post: Post): void {
    if (!post.comments) {
      // Initialize comments array if undefined
      post.comments = [];
    }
  
    this.commentService.getComments(post.id!).subscribe({
      next: (comments) => {
        post.comments = comments; // Assign fetched comments to the post
      },
      error: (err) => {
        console.error(`Failed to fetch comments for post ${post.id}:`, err);
      },
    });
  }
  
  createPost(): void {
    if (this.postForm.invalid) return;

    const newPost: Post = this.postForm.value;

    this.postService.createPost(newPost).subscribe({
      next: (post) => {
        this.toastr.success('Post created successfully');
      //  this.posts.unshift(post, user); // Add the new post to the top of the feed
        this.postForm.reset();
        this.closeModal();
      },
      error: () => {
        this.toastr.error('Failed to create post');
      },
    });
  }

  openModal(): void {
    const modal = new bootstrap.Modal(this.postModal.nativeElement);
    modal.show();
  }

  closeModal(): void {
    const modal = bootstrap.Modal.getInstance(this.postModal.nativeElement);
    modal.hide();
  }
}
