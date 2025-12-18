package br.com.HotelFlow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusFolioRepository extends JpaRepository<StatusFolioRepository, Long> {
}
