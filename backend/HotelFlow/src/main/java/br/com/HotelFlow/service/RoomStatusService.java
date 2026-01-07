package br.com.HotelFlow.service;

import br.com.HotelFlow.model.RoomStatusModel;
import br.com.HotelFlow.repository.RoomStatusRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RoomStatusService {

    private final RoomStatusRepository repository;

    public RoomStatusService(RoomStatusRepository repository){
        this.repository = repository;
    }

    //CREATE
    @Transactional
    public RoomStatusModel register(RoomStatusModel roomStatus){
        if (repository.existsByStatus(roomStatus.getStatus())){
            throw new IllegalArgumentException("Já existe esse status cadastrado");
        }
        return repository.save(roomStatus);
    }

    //READ ALL
    @Transactional(readOnly = true)
    public List<RoomStatusModel> findAll(){
        return repository.findAll();
    }

    //READ By ID
    @Transactional(readOnly = true)
    public Optional<RoomStatusModel> findById(Long id){
        return repository.findById(id);
    }

    //UPDATE
    @Transactional
    public RoomStatusModel update(Long id, RoomStatusModel data){
        RoomStatusModel statusExists = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Status não encontrado"));

        if(data.getStatus() != null){
            if(!statusExists.getStatus().equals(data.getStatus()) && repository.existsByStatus(data.getStatus())){
                throw new IllegalArgumentException("Status ja cadastrado");
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
