package br.com.HotelFlow.repository;

import br.com.HotelFlow.entity.BookingStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingStatusRepository extends JpaRepository<BookingStatusEntity, Long> {
}
