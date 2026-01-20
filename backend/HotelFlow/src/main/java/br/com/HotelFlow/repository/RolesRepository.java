package br.com.HotelFlow.repository;

import br.com.HotelFlow.model.RolesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends JpaRepository<RolesModel, Long> {
    boolean existsByName(String name);
}
