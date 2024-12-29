package com.example.connections_service.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.connections_service.model.Connection;

public interface ConnectionRepository extends JpaRepository<Connection, Long> {
    List<Connection> findByUserId1OrUserId2(Long userId1, Long userId2);
}