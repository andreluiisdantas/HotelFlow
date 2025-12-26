package br.com.HotelFlow.repository;

import br.com.HotelFlow.model.BookingStatusModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingStatusRepository extends JpaRepository<BookingStatusModel, Long> {
}
