package br.com.HotelFlow.repository;

import br.com.HotelFlow.model.RoomStatusModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomStatusRepository extends JpaRepository<RoomStatusModel, Long> {
    boolean existsByStatus(String status);
}
