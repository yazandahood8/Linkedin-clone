package com.example.connections_service.dto;

public class ConnectionRequestDTO {
    private Long userId1;
    private Long userId2;

    // Getters and Setters
    public Long getUserId1() {
        return userId1;
    }

    public void setUserId1(Long userId1) {
        this.userId1 = userId1;
    }

    public Long getUserId2() {
        return userId2;
    }

    public void setUserId2(Long userId2) {
        this.userId2 = userId2;
    }
}
