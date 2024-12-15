package com.luxery.gestioninventario.Controllers;

import com.luxery.gestioninventario.Entitys.RoomEntity;
import com.luxery.gestioninventario.Services.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    /**
     * Endpoint para crear una nueva habitación.
     * Método: POST /rooms
     *
     * Ejemplo de JSON de entrada:
     * {
     *   "roomNumber": "101",
     *   "roomType": "single",
     *   "status": "disponible"
     * }
     */
    @PostMapping
    public ResponseEntity<RoomEntity> createRoom(@RequestBody CreateRoomRequest request) {
        // Crear la habitación usando el servicio
        RoomEntity createdRoom = roomService.createRoom(request.getRoomNumber(), request.getRoomType(), request.getStatus());
        return new ResponseEntity<>(createdRoom, HttpStatus.CREATED);
    }
    /**
     * Endpoint para actualizar el estado de una habitación.
     * Método: PATCH /rooms/{id}
     *
     * Ejemplo de JSON de entrada:
     * {
     *   "status": "mantenimiento"
     * }
     */
    @PatchMapping("/{id}")
    public ResponseEntity<RoomEntity> updateRoomStatus(@PathVariable Long id, @RequestBody UpdateRoomStatusRequest request) {
        try {
            RoomEntity updatedRoom = roomService.updateRoomStatus(id, request.getStatus());
            return ResponseEntity.ok(updatedRoom);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Clases internas para las peticiones (DTOs)
    // Debí haberlas separado, pero para simplicidad las creo aca mismo.

    public static class CreateRoomRequest {
        private String roomNumber;
        private String roomType;
        private String status;

        // Getters y Setters
        public String getRoomNumber() {
            return roomNumber;
        }

        public void setRoomNumber(String roomNumber) {
            this.roomNumber = roomNumber;
        }

        public String getRoomType() {
            return roomType;
        }

        public void setRoomType(String roomType) {
            this.roomType = roomType;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }

    public static class UpdateRoomStatusRequest {
        private String status;

        // Getter y Setter
        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
