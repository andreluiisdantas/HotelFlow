package br.com.HotelFlow.repository;

import br.com.HotelFlow.entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingInterface extends JpaRepository<BookingEntity, Long> {
}
