package com.example.connections_service.utils;


import com.example.connections_service.dto.ConnectionRequestDTO;
import com.example.connections_service.dto.ConnectionResponseDTO;
import com.example.connections_service.model.Connection;

public class ConnectionMapper {

    /**
     * Maps a ConnectionRequestDTO to a Connection entity.
     */
    public static Connection toEntity(ConnectionRequestDTO dto) {
        Connection connection = new Connection();
        connection.setUserId1(dto.getUserId1());
        connection.setUserId2(dto.getUserId2());
        return connection;
    }

    /**
     * Maps a Connection entity to a ConnectionResponseDTO.
     */
    public static ConnectionResponseDTO toDTO(Connection connection) {
        ConnectionResponseDTO dto = new ConnectionResponseDTO();
        dto.setId(connection.getId());
        dto.setUserId1(connection.getUserId1());
        dto.setUserId2(connection.getUserId2());
        dto.setStatus(connection.getStatus());
        dto.setCreatedAt(connection.getCreatedAt());
        return dto;
    }
}
