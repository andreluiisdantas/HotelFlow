package br.com.HotelFlow.repository;

import br.com.HotelFlow.model.StatusFolioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusFolioRepository extends JpaRepository<StatusFolioModel, Long> {
}
