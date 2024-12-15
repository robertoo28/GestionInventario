package com.luxery.gestioninventario.Entitys;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Table(name = "rooms")
public class RoomEntity {
    // Constructores, vacío y con parámetros
    public RoomEntity() {
    }

    public RoomEntity(String roomNumber, String roomType, String status) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.status = status;
    }

    // Campos de la tabla
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id", nullable = false)
    private Long roomId;

    @Column(name = "room_number", nullable = false, unique = true)
    private String roomNumber;

    @Column(name = "room_type", nullable = false)
    private String roomType;

    @Column(name = "status", nullable = false)
    private String status;


    // Getters y Setters
    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

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