package br.com.HotelFlow.service;

import br.com.HotelFlow.model.FolioTransactionModel;
import br.com.HotelFlow.repository.FolioTransactionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class FolioTransactionService {

    private final FolioTransactionRepository repository;

    public FolioTransactionService(FolioTransactionRepository repository){
        this.repository = repository;
    }

    //CREATE
    @Transactional
    public FolioTransactionModel register(FolioTransactionModel transaction){
        return repository.save(transaction);
    }

    //READ ALL
    @Transactional(readOnly = true)
    public List<FolioTransactionModel> findAll(){
        return repository.findAll();
    }

    //READ BY ID
    @Transactional(readOnly = true)
    public Optional<FolioTransactionModel> findById(Long id){
        return repository.findById(id);
    }

    //UPDATE
    @Transactional
    public FolioTransactionModel update(Long id, FolioTransactionModel data){
        FolioTransactionModel transactionExists = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Transação não encontrada"));

        if(data.getFolio() != null){
            transactionExists.setFolio(data.getFolio());
        }

        // Mock update amount se viesse no DTO, mas DTO só tem folioId
        if(data.getAmount() != null){
            transactionExists.setAmount(data.getAmount());
        }

        return repository.save(transactionExists);
    }

    //DELETE
    @Transactional
    public void delete(Long id){
        if(!repository.existsById(id)){
            throw new EntityNotFoundException("Transação não encontrada");
        }
        repository.deleteById(id);
    }
}