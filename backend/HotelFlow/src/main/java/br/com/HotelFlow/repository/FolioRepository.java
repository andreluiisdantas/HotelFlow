package br.com.HotelFlow.repository;

import br.com.HotelFlow.entity.FolioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FolioRepository extends JpaRepository<FolioEntity, Long> {
}
