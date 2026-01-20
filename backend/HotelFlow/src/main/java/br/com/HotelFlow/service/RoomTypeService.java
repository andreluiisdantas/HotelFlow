package br.com.HotelFlow.service;

import br.com.HotelFlow.model.RoomTypeModel;
import br.com.HotelFlow.repository.RoomTypeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RoomTypeService {

    private final RoomTypeRepository repository;

    public RoomTypeService(RoomTypeRepository repository){
        this.repository = repository;
    }

    //CREATE
    @Transactional
    public RoomTypeModel register(RoomTypeModel roomType){
        if (repository.existsByType(roomType.getType())){
            throw new IllegalArgumentException("Já existe um tipo de quarto com esse nome");
        }
        return repository.save(roomType);
    }

    //READ ALL
    @Transactional(readOnly = true)
    public List<RoomTypeModel> findAll(){
        return repository.findAll();
    }

    //READ BY ID
    @Transactional(readOnly = true)
    public Optional<RoomTypeModel> findById(Long id){
        return repository.findById(id);
    }

    //UPDATE
    @Transactional
    public RoomTypeModel update(Long id, RoomTypeModel data){
        RoomTypeModel typeExists = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tipo de quarto não encontrado"));

        if(data.getType() != null){
            if(!typeExists.getType().equals(data.getType()) && repository.existsByType(data.getType())){
                throw new IllegalArgumentException("Tipo de quarto já cadastrado");
            }
            typeExists.setType(data.getType());
        }

        if(data.getCapacityAdults() > 0){
            typeExists.setCapacityAdults(data.getCapacityAdults());
        }

        if(data.getCapacityChildren() >= 0){
            typeExists.setCapacityChildren(data.getCapacityChildren());
        }

        return repository.save(typeExists);
    }

    //DELETE
    @Transactional
    public void delete(Long id){
        if(!repository.existsById(id)){
            throw new EntityNotFoundException("Tipo de quarto não encontrado");
        }
        repository.deleteById(id);
    }
}