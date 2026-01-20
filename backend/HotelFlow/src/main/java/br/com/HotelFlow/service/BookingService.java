package br.com.HotelFlow.service;

import br.com.HotelFlow.model.BookingModel;
import br.com.HotelFlow.repository.BookingRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    private final BookingRepository repository;

    public BookingService(BookingRepository repository){
        this.repository = repository;
    }

    //CREATE
    @Transactional
    public BookingModel register(BookingModel booking){
        return repository.save(booking);
    }

    //READ ALL
    @Transactional(readOnly = true)
    public List<BookingModel> findAll(){
        return repository.findAll();
    }

    //READ BY ID
    @Transactional(readOnly = true)
    public Optional<BookingModel> findById(Long id){
        return repository.findById(id);
    }

    //UPDATE
    @Transactional
    public BookingModel update(Long id, BookingModel data){
        BookingModel bookingExists = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reserva não encontrada"));

        if(data.getRoom() != null){
            bookingExists.setRoom(data.getRoom());
        }

        if(data.getGuest() != null){
            bookingExists.setGuest(data.getGuest());
        }

        if(data.getStartDate() != null){
            bookingExists.setStartDate(data.getStartDate());
        }

        if(data.getFinalDate() != null){
            bookingExists.setFinalDate(data.getFinalDate());
        }

        if(data.getRatePlan() != null){
            bookingExists.setRatePlan(data.getRatePlan());
        }

        if(data.getBookingStatus() != null){
            bookingExists.setBookingStatus(data.getBookingStatus());
        }

        if(data.getResponsible() != null){
            bookingExists.setResponsible(data.getResponsible());
        }

        if(data.getDescription() != null){
            bookingExists.setDescription(data.getDescription());
        }

        return repository.save(bookingExists);
    }

    //DELETE
    @Transactional
    public void delete(Long id){
        if(!repository.existsById(id)){
            throw new EntityNotFoundException("Reserva não encontrada");
        }
        repository.deleteById(id);
    }
}