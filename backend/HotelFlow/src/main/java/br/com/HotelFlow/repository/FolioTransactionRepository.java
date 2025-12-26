package br.com.HotelFlow.repository;

import br.com.HotelFlow.model.FolioTransactionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FolioTransactionRepository extends JpaRepository<FolioTransactionModel, Long> {
}
