package br.com.HotelFlow.controller;

import br.com.HotelFlow.dto.folio.FolioRequestDTO;
import br.com.HotelFlow.dto.folio.FolioResponseDTO;
import br.com.HotelFlow.model.FolioModel;
import br.com.HotelFlow.repository.BookingRepository;
import br.com.HotelFlow.repository.StatusFolioRepository;
import br.com.HotelFlow.service.FolioService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/folios")
public class FolioController {

    private final FolioService service;
    private final BookingRepository bookingRepository;
    private final StatusFolioRepository statusFolioRepository;

    public FolioController(FolioService service, BookingRepository bookingRepository, StatusFolioRepository statusFolioRepository){
        this.service = service;
        this.bookingRepository = bookingRepository;
        this.statusFolioRepository = statusFolioRepository;
    }

    private FolioModel toEntity(FolioRequestDTO dto){
        FolioModel folio = new FolioModel();

        if(dto.bookingId() != null){
            folio.setBooking(bookingRepository.findById(dto.bookingId())
                    .orElseThrow(() -> new EntityNotFoundException("Reserva não encontrada")));
        }

        if(dto.statusFolioId() != null){
            folio.setStatusFolio(statusFolioRepository.findById(dto.statusFolioId())
                    .orElseThrow(() -> new EntityNotFoundException("Status de folio não encontrado")));
        }

        return folio;
    }

    private FolioResponseDTO toDTO(FolioModel folio){
        return new FolioResponseDTO(folio);
    }

    //POST
    @PostMapping
    public ResponseEntity<FolioResponseDTO> register(@RequestBody FolioRequestDTO request){
        FolioModel toCreate = toEntity(request);
        FolioModel created = service.register(toCreate);
        FolioResponseDTO response = toDTO(created);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    //GET
    @GetMapping
    public ResponseEntity<List<FolioResponseDTO>> listAll(){
        List<FolioModel> list = service.findAll();
        List<FolioResponseDTO> listDTO = list.stream()
                .map(this::toDTO)
                .toList();

        return ResponseEntity.ok(listDTO);
    }

    //GET By ID
    @GetMapping("/{id}")
    public ResponseEntity<FolioResponseDTO> findById(@PathVariable Long id){
        return service.findById(id)
                .map(folio -> {
                    FolioResponseDTO dto = toDTO(folio);
                    return ResponseEntity.ok(dto);
                })
                .orElseThrow(() -> new EntityNotFoundException("Folio não encontrado"));
    }

    //PUT
    @PutMapping("/{id}")
    public ResponseEntity<FolioResponseDTO> update(@PathVariable Long id, @RequestBody FolioRequestDTO request){
        FolioModel data = toEntity(request);
        FolioModel updated = service.update(id, data);
        FolioResponseDTO response = toDTO(updated);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //PATCH
    @PatchMapping("/{id}")
    public ResponseEntity<FolioResponseDTO> updatePartial(@PathVariable Long id, @RequestBody FolioRequestDTO request){
        FolioModel data = toEntity(request);
        FolioModel updated = service.update(id, data);
        FolioResponseDTO response = toDTO(updated);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}