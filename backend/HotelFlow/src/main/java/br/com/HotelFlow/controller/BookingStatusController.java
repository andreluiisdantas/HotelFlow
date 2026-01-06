package br.com.HotelFlow.controller;

import br.com.HotelFlow.dto.bookingStatus.BookingStatusRequestDTO;
import br.com.HotelFlow.dto.bookingStatus.BookingStatusResponseDTO;
import br.com.HotelFlow.model.BookingStatusModel;
import br.com.HotelFlow.service.BookingStatusService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booking-status")
public class BookingStatusController {

    private final BookingStatusService service;

    public BookingStatusController(BookingStatusService service){
        this.service = service;
    }

    private BookingStatusModel toEntity(BookingStatusRequestDTO dto){
        BookingStatusModel bookingStatus = new BookingStatusModel();

        bookingStatus.setStatus(dto.status());
        return bookingStatus;
    }

    private BookingStatusResponseDTO toDTO(BookingStatusModel bookingStatus){
        return new BookingStatusResponseDTO(
                bookingStatus.getId(),
                bookingStatus.getStatus()
        );
    }

    //POST
    @PostMapping
    public ResponseEntity<BookingStatusResponseDTO> register(@RequestBody BookingStatusRequestDTO request){

        BookingStatusModel toCreateStatus = toEntity(request);
        BookingStatusModel createdStatus = service.register(toCreateStatus);
        BookingStatusResponseDTO response = toDTO(createdStatus);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    //GET
    @GetMapping
    public ResponseEntity<List<BookingStatusResponseDTO>> listAll(){
        List<BookingStatusModel> bookingStatusList = service.findAll();

        List<BookingStatusResponseDTO> listDTO = bookingStatusList.stream()
                .map(this::toDTO)
                .toList();

        return ResponseEntity.ok(listDTO);
    }

    //GET By ID
    @GetMapping("/{id}")
    public ResponseEntity<BookingStatusResponseDTO> findBydId(@PathVariable Long id){
        return service.findById(id)
                .map(BookingStatusModel -> {
                    BookingStatusResponseDTO dto = toDTO(BookingStatusModel);
                    return ResponseEntity.ok(dto);
                })
                .orElseThrow(() -> new EntityNotFoundException("Status não encontrado"));
    }

    //PUT
    @PutMapping("/{id}")
    public ResponseEntity<BookingStatusResponseDTO> update(@PathVariable Long id, @RequestBody BookingStatusRequestDTO request){

        BookingStatusModel bookingStatusData = toEntity(request);
        BookingStatusModel updatedData = service.update(id, bookingStatusData);
        BookingStatusResponseDTO response = toDTO(updatedData);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //PATCH
    @PatchMapping("/{id}")
    public ResponseEntity<BookingStatusResponseDTO> updatePartial(@PathVariable Long id, @RequestBody BookingStatusRequestDTO request){

        BookingStatusModel bookingStatusData = toEntity(request);
        BookingStatusModel updatedData = service.update(id, bookingStatusData);
        BookingStatusResponseDTO response = toDTO(updatedData);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
