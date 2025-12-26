package br.com.HotelFlow.repository;

import br.com.HotelFlow.model.FolioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FolioRepository extends JpaRepository<FolioModel, Long> {
}
