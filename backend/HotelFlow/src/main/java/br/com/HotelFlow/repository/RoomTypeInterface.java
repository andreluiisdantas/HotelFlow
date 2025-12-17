package br.com.HotelFlow.repository;

import br.com.HotelFlow.entity.RoomTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomTypeInterface extends JpaRepository<RoomTypeEntity, Long> {
}
