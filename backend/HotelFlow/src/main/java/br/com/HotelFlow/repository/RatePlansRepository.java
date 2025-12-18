package br.com.HotelFlow.repository;

import br.com.HotelFlow.entity.RatePlansEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatePlansRepository extends JpaRepository<RatePlansEntity, Long> {
}
