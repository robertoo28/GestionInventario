package com.luxery.gestioninventario.Repository;

import com.luxery.gestioninventario.Entitys.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<RoomEntity, Long> {
    Optional<RoomEntity> findByRoomNumber(String roomNumber);
}
