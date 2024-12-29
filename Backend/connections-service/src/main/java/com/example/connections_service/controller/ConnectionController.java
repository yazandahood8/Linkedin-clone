package com.example.connections_service.controller;


import com.example.connections_service.dto.ConnectionRequestDTO;
import com.example.connections_service.dto.ConnectionResponseDTO;
import com.example.connections_service.model.ConnectionStatus;
import com.example.connections_service.service.ConnectionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/connections")
public class ConnectionController {

    private final ConnectionService connectionService;

    public ConnectionController(ConnectionService connectionService) {
        this.connectionService = connectionService;
    }

    @PostMapping
    public ResponseEntity<ConnectionResponseDTO> createConnection(@RequestBody ConnectionRequestDTO request) {
        ConnectionResponseDTO response = connectionService.createConnection(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<ConnectionResponseDTO>> getConnections(@PathVariable Long userId) {
        List<ConnectionResponseDTO> response = connectionService.getConnections(userId);
        return ResponseEntity.ok(response);
    }
    
    @PutMapping("/{connectionId}/status")
    public ResponseEntity<ConnectionResponseDTO> updateStatus(
            @PathVariable Long connectionId,
            @RequestParam ConnectionStatus status) {
        ConnectionResponseDTO response = connectionService.updateConnectionStatus(connectionId, status);
        return ResponseEntity.ok(response);
    }
    
    
    
}