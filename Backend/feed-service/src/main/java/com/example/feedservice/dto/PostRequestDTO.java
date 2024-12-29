package com.example.feedservice.dto;

import com.example.feedservice.model.PostVisibility;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostRequestDTO {
    private String content;
    private PostVisibility visibility;
    private Long userId;
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public PostVisibility getVisibility() {
		return visibility;
	}
	public void setVisibility(PostVisibility visibility) {
		this.visibility = visibility;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
    
    
    
    
    
}
