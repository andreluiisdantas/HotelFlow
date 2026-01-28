package br.com.HotelFlow.controller;

import br.com.HotelFlow.dto.booking.BookingRequestDTO;
import br.com.HotelFlow.dto.booking.BookingResponseDTO;
import br.com.HotelFlow.model.BookingModel;
import br.com.HotelFlow.repository.*;
import br.com.HotelFlow.service.BookingService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService service;
    private final RoomRepository roomRepository;
    private final GuestRepository guestRepository;
    private final RatePlansRepository ratePlansRepository;
    private final BookingStatusRepository bookingStatusRepository;
    private final UserRepository userRepository;

    public BookingController(BookingService service,
                             RoomRepository roomRepository,
                             GuestRepository guestRepository,
                             RatePlansRepository ratePlansRepository,
                             BookingStatusRepository bookingStatusRepository,
                             UserRepository userRepository){
        this.service = service;
        this.roomRepository = roomRepository;
        this.guestRepository = guestRepository;
        this.ratePlansRepository = ratePlansRepository;
        this.bookingStatusRepository = bookingStatusRepository;
        this.userRepository = userRepository;
    }

    private BookingModel toEntity(BookingRequestDTO dto){
        BookingModel booking = new BookingModel();
        booking.setStartDate(dto.startDate());
        booking.setFinalDate(dto.finalDate());
        booking.setDescription(dto.description());

        if(dto.roomId() != null){
            booking.setRoom(roomRepository.findById(dto.roomId())
                    .orElseThrow(() -> new EntityNotFoundException("Quarto não encontrado")));
        }

        if(dto.guestId() != null){
            booking.setGuest(guestRepository.findById(dto.guestId())
                    .orElseThrow(() -> new EntityNotFoundException("Hóspede não encontrado")));
        }

        if(dto.ratePlansId() != null){
            booking.setRatePlan(ratePlansRepository.findById(dto.ratePlansId())
                    .orElseThrow(() -> new EntityNotFoundException("Plano de tarifa não encontrado")));
        }

        booking.setBookingStatus(bookingStatusRepository.findById(1L)
                .orElseThrow(() -> new EntityNotFoundException("Status de reserva padrão não encontrado")));


        if(dto.userId() != null){
            booking.setResponsible(userRepository.findById(dto.userId())
                    .orElseThrow(() -> new EntityNotFoundException("Usuário responsável não encontrado")));
        }

        return booking;
    }

    private BookingResponseDTO toDTO(BookingModel booking){
        return new BookingResponseDTO(booking);
    }

    //POST
    @PostMapping
    public ResponseEntity<BookingResponseDTO> register(@RequestBody BookingRequestDTO request){
        BookingModel toCreate = toEntity(request);
        // Calcular total price simples (mock) pois não há lógica no DTO
        toCreate.setTotalPrice(java.math.BigDecimal.ZERO);

        BookingModel created = service.register(toCreate);
        BookingResponseDTO response = toDTO(created);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    //GET
    @GetMapping
    public ResponseEntity<List<BookingResponseDTO>> listAll(){
        List<BookingModel> list = service.findAll();
        List<BookingResponseDTO> listDTO = list.stream()
                .map(this::toDTO)
                .toList();

        return ResponseEntity.ok(listDTO);
    }

    //GET By ID
    @GetMapping("/{id}")
    public ResponseEntity<BookingResponseDTO> findById(@PathVariable Long id){
        return service.findById(id)
                .map(booking -> {
                    BookingResponseDTO dto = toDTO(booking);
                    return ResponseEntity.ok(dto);
                })
                .orElseThrow(() -> new EntityNotFoundException("Reserva não encontrada"));
    }

    //PUT
    @PutMapping("/{id}")
    public ResponseEntity<BookingResponseDTO> update(@PathVariable Long id, @RequestBody BookingRequestDTO request){
        BookingModel data = toEntity(request);
        BookingModel updated = service.update(id, data);
        BookingResponseDTO response = toDTO(updated);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //PATCH
    @PatchMapping("/{id}")
    public ResponseEntity<BookingResponseDTO> updatePartial(@PathVariable Long id, @RequestBody BookingRequestDTO request){
        BookingModel data = toEntity(request);
        BookingModel updated = service.update(id, data);
        BookingResponseDTO response = toDTO(updated);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
