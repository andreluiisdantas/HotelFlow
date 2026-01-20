package br.com.HotelFlow.service;

import br.com.HotelFlow.model.FolioModel;
import br.com.HotelFlow.repository.FolioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class FolioService {

    private final FolioRepository repository;

    public FolioService(FolioRepository repository){
        this.repository = repository;
    }

    //CREATE
    @Transactional
    public FolioModel register(FolioModel folio){
        return repository.save(folio);
    }

    //READ ALL
    @Transactional(readOnly = true)
    public List<FolioModel> findAll(){
        return repository.findAll();
    }

    //READ BY ID
    @Transactional(readOnly = true)
    public Optional<FolioModel> findById(Long id){
        return repository.findById(id);
    }

    //UPDATE
    @Transactional
    public FolioModel update(Long id, FolioModel data){
        FolioModel folioExists = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Folio não encontrado"));

        if(data.getBooking() != null){
            folioExists.setBooking(data.getBooking());
        }

        if(data.getStatusFolio() != null){
            folioExists.setStatusFolio(data.getStatusFolio());
        }

        return repository.save(folioExists);
    }

    //DELETE
    @Transactional
    public void delete(Long id){
        if(!repository.existsById(id)){
            throw new EntityNotFoundException("Folio não encontrado");
        }
        repository.deleteById(id);
    }
}