package com.example.connections_service.service;


import com.example.connections_service.dto.ConnectionRequestDTO;
import com.example.connections_service.dto.ConnectionResponseDTO;
import com.example.connections_service.exception.ConnectionNotFoundException;
import com.example.connections_service.model.Connection;
import com.example.connections_service.model.ConnectionStatus;
import com.example.connections_service.repository.ConnectionRepository;
import com.example.connections_service.utils.ConnectionMapper;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class ConnectionService {

    private final ConnectionRepository connectionRepository;

    public ConnectionService(ConnectionRepository connectionRepository) {
        this.connectionRepository = connectionRepository;
    }

    public ConnectionResponseDTO createConnection(ConnectionRequestDTO request) {
        Connection connection = ConnectionMapper.toEntity(request);
        connection.setStatus(ConnectionStatus.PENDING);
        connection = connectionRepository.save(connection);
        return ConnectionMapper.toDTO(connection);
    }

    public List<ConnectionResponseDTO> getConnections(Long userId) {
        List<Connection> connections = connectionRepository.findByUserId1OrUserId2(userId, userId);
        return connections.stream()
                .map(ConnectionMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    
    /**
     * Updates the status of a connection.
     *
     * @param connectionId The ID of the connection to update.
     * @param status       The new status to set (ACCEPTED or REJECTED).
     * @return The updated connection as a ConnectionResponseDTO.
     * @throws ConnectionNotFoundException if the connection ID does not exist.
     */
    public ConnectionResponseDTO updateConnectionStatus(Long connectionId, ConnectionStatus status) {
        // Find the connection by ID
        Connection connection = connectionRepository.findById(connectionId)
                .orElseThrow(() -> new ConnectionNotFoundException("Connection not found with ID: " + connectionId));

        // Update the status
        connection.setStatus(status);

        // Save the updated connection
        connection = connectionRepository.save(connection);

        // Return the updated connection as a DTO
        return ConnectionMapper.toDTO(connection);
    }
}
