package br.com.HotelFlow.service;

import br.com.HotelFlow.model.BookingStatusModel;
import br.com.HotelFlow.repository.BookingStatusRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookingStatusService {

    private final BookingStatusRepository repository;

    public BookingStatusService(BookingStatusRepository repository){
        this.repository = repository;
    }

    //CREATE
    @Transactional
    public BookingStatusModel register(BookingStatusModel bookingStatus){
        if (repository.existsByStatus(bookingStatus.getStatus())){
            throw new IllegalArgumentException("Já existe esse status cadastrado");
        }

        return repository.save(bookingStatus);
    }

    //READ ALL
    @Transactional(readOnly = true)
    public List<BookingStatusModel> findAll(){
        return repository.findAll();
    }

    //READ BY ID
    @Transactional(readOnly = true)
    public Optional<BookingStatusModel> findById(Long id){
        return repository.findById(id);
    }

    //UPDATE
    @Transactional
    public BookingStatusModel update(Long id, BookingStatusModel data){
        BookingStatusModel statusExists = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Status não encontrado"));

        if (data.getStatus() != null){
            if(!statusExists.getStatus().equals(data.getStatus()) && repository.existsByStatus(data.getStatus())){
                throw new IllegalArgumentException("Status ja cadastrado");
            }
            statusExists.setStatus(data.getStatus());
        }

        return repository.save(statusExists);
    }

    //DELETE
    @Transactional
    public void Delete(Long id){
        if(!repository.existsById(id)){
            throw new EntityNotFoundException("Status não encontrado");
        }
        repository.deleteById(id);
    }
}
