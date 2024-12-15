package com.luxery.gestioninventario.Services;

import com.luxery.gestioninventario.Entitys.RoomEntity;
import com.luxery.gestioninventario.Repository.RoomRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    /**
     * Crea una nueva habitación y la guarda en la base de datos.
     *
     * @param roomNumber Número de habitación (debe ser único).
     * @param roomType   Tipo de habitación (ej: single, double, suite).
     * @param status     Estado inicial de la habitación (ej: disponible).
     * @return La entidad de la habitación creada.
     */
    @Transactional
    public RoomEntity createRoom(String roomNumber, String roomType, String status) {
        // Opcionalmente, podemos verificar que no exista ya una habitación con el mismo roomNumber
        roomRepository.findByRoomNumber(roomNumber).ifPresent(r -> {
             throw new IllegalArgumentException("Ya existe una habitación con el número " + roomNumber);
         });

        RoomEntity newRoom = new RoomEntity(roomNumber, roomType, status);
        return roomRepository.save(newRoom);
    }

    /**
     * Actualiza el estado de una habitación existente.
     *
     * @param roomId ID de la habitación a actualizar.
     * @param newStatus Nuevo estado (ej: mantenimiento, disponible, ocupado, etc.)
     * @return La entidad de la habitación actualizada.
     * @throws IllegalArgumentException si la habitación no existe.
     */
    @Transactional
    public RoomEntity updateRoomStatus(Long roomId, String newStatus) {
        Optional<RoomEntity> optionalRoom = roomRepository.findById(roomId);
        if (optionalRoom.isEmpty()) {
            throw new IllegalArgumentException("No se encontró la habitación con ID: " + roomId);
        }

        RoomEntity room = optionalRoom.get();
        room.setStatus(newStatus);
        return roomRepository.save(room);
    }
}