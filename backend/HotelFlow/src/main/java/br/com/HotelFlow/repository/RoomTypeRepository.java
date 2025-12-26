package br.com.HotelFlow.repository;

import br.com.HotelFlow.model.RoomTypeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomTypeRepository extends JpaRepository<RoomTypeModel, Long> {
}
