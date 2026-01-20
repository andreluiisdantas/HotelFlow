package br.com.HotelFlow.service;

import br.com.HotelFlow.model.RoomModel;
import br.com.HotelFlow.repository.RoomRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    private final RoomRepository repository;

    public RoomService(RoomRepository repository){
        this.repository = repository;
    }

    //CREATE
    @Transactional
    public RoomModel register(RoomModel room){
        if (repository.existsByDoorCode(room.getDoorCode())){
            throw new IllegalArgumentException("Código da porta já existe");
        }
        return repository.save(room);
    }

    //READ ALL
    @Transactional(readOnly = true)
    public List<RoomModel> findAll(){
        return repository.findAll();
    }

    //READ BY ID
    @Transactional(readOnly = true)
    public Optional<RoomModel> findById(Long id){
        return repository.findById(id);
    }

    //UPDATE
    @Transactional
    public RoomModel update(Long id, RoomModel data){
        RoomModel roomExists = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Quarto não encontrado"));

        if(data.getDoorCode() != null){
            if(!roomExists.getDoorCode().equals(data.getDoorCode()) && repository.existsByDoorCode(data.getDoorCode())){
                throw new IllegalArgumentException("Código da porta já em uso");
            }
            roomExists.setDoorCode(data.getDoorCode());
        }

        if(data.getRoomType() != null){
            roomExists.setRoomType(data.getRoomType());
        }

        if(data.getRoomStatus() != null){
            roomExists.setRoomStatus(data.getRoomStatus());
        }

        return repository.save(roomExists);
    }

    //DELETE
    @Transactional
    public void delete(Long id){
        if(!repository.existsById(id)){
            throw new EntityNotFoundException("Quarto não encontrado");
        }
        repository.deleteById(id);
    }
}