package br.com.HotelFlow.repository;

import br.com.HotelFlow.model.GuestModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestRepository extends JpaRepository<GuestModel, Long> {
    boolean existsByCpf(String cpf);
    boolean existsByRg(String rg);
}
