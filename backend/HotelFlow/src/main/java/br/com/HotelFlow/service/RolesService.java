package br.com.HotelFlow.service;

import br.com.HotelFlow.model.RolesModel;
import br.com.HotelFlow.repository.RolesRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RolesService {

    private final RolesRepository repository;

    public RolesService(RolesRepository repository){
        this.repository = repository;
    }

    //CREATE
    @Transactional
    public RolesModel register(RolesModel role){
        if (repository.existsByName(role.getName())){
            throw new IllegalArgumentException("Já existe uma regra com esse nome");
        }

        return repository.save(role);
    }

    //READ ALL
    @Transactional(readOnly = true)
    public List<RolesModel> findAll(){
        return repository.findAll();
    }

    //READ BY ID
    @Transactional(readOnly = true)
    public Optional<RolesModel> findById(Long id){
        return repository.findById(id);
    }

    //UPDATE
    @Transactional
    public RolesModel update(Long id, RolesModel data){
        RolesModel roleExists = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Regra não encontrada"));

        if (data.getName() != null) {
            if (!roleExists.getName().equals(data.getName()) && repository.existsByName(data.getName())){
                throw new IllegalArgumentException("Nome de regra já em uso");
            }
            roleExists.setName(data.getName());
        }

        return repository.save(roleExists);
    }

    //DELETE
    @Transactional
    public void delete(Long id){
        if (!repository.existsById(id)){
            throw new EntityNotFoundException("Regra não encontrada");
        }
        repository.deleteById(id);
    }
}