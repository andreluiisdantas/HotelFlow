package br.com.HotelFlow.repository;

import br.com.HotelFlow.model.RatePlansModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatePlansRepository extends JpaRepository<RatePlansModel, Long> {
}
