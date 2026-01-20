package br.com.HotelFlow.service;

import br.com.HotelFlow.model.StatusFolioModel;
import br.com.HotelFlow.repository.StatusFolioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class StatusFolioService {

    private final StatusFolioRepository repository;

    public StatusFolioService(StatusFolioRepository repository){
        this.repository = repository;
    }

    //CREATE
    @Transactional
    public StatusFolioModel register(StatusFolioModel statusFolio){
        if (repository.existsByStatus(statusFolio.getStatus())){
            throw new IllegalArgumentException("Já existe esse status cadastrado");
        }
        return repository.save(statusFolio);
    }

    //READ ALL
    @Transactional(readOnly = true)
    public List<StatusFolioModel> findAll(){
        return repository.findAll();
    }

    //READ BY ID
    @Transactional(readOnly = true)
    public Optional<StatusFolioModel> findById(Long id){
        return repository.findById(id);
    }

    //UPDATE
    @Transactional
    public StatusFolioModel update(Long id, StatusFolioModel data){
        StatusFolioModel statusExists = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Status não encontrado"));

        if(data.getStatus() != null){
            if(!statusExists.getStatus().equals(data.getStatus()) && repository.existsByStatus(data.getStatus())){
                throw new IllegalArgumentException("Status já cadastrado");
            }
            statusExists.setStatus(data.getStatus());
        }

        return repository.save(statusExists);
    }

    //DELETE
    @Transactional
    public void delete(Long id){
        if(!repository.existsById(id)){
            throw new EntityNotFoundException("Status não encontrado");
        }
        repository.deleteById(id);
    }
}