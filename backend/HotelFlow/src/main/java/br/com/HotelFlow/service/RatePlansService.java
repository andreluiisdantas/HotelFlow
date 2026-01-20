package br.com.HotelFlow.service;

import br.com.HotelFlow.model.RatePlansModel;
import br.com.HotelFlow.repository.RatePlansRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RatePlansService {

    private final RatePlansRepository repository;

    public RatePlansService(RatePlansRepository repository){
        this.repository = repository;
    }

    //CREATE
    @Transactional
    public RatePlansModel register(RatePlansModel ratePlan){
        return repository.save(ratePlan);
    }

    //READ ALL
    @Transactional(readOnly = true)
    public List<RatePlansModel> findAll(){
        return repository.findAll();
    }

    //READ BY ID
    @Transactional(readOnly = true)
    public Optional<RatePlansModel> findById(Long id){
        return repository.findById(id);
    }

    //UPDATE
    @Transactional
    public RatePlansModel update(Long id, RatePlansModel data){
        RatePlansModel planExists = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Plano de tarifa não encontrado"));

        if(data.getName() != null){
            planExists.setName(data.getName());
        }

        if(data.getStandardPrice() != null){
            planExists.setStandardPrice(data.getStandardPrice());
        }

        if(data.getRoomType() != null){
            planExists.setRoomType(data.getRoomType());
        }

        return repository.save(planExists);
    }

    //DELETE
    @Transactional
    public void delete(Long id){
        if(!repository.existsById(id)){
            throw new EntityNotFoundException("Plano de tarifa não encontrado");
        }
        repository.deleteById(id);
    }
}