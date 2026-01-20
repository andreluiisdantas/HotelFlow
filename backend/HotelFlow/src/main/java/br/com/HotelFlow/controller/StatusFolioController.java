package br.com.HotelFlow.controller;

import br.com.HotelFlow.dto.statusFolio.StatusFolioRequestDTO;
import br.com.HotelFlow.dto.statusFolio.StatusFolioResponseDTO;
import br.com.HotelFlow.model.StatusFolioModel;
import br.com.HotelFlow.service.StatusFolioService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/status-folio")
public class StatusFolioController {

    private final StatusFolioService service;

    public StatusFolioController(StatusFolioService service){
        this.service = service;
    }

    private StatusFolioModel toEntity(StatusFolioRequestDTO dto){
        StatusFolioModel statusFolio = new StatusFolioModel();
        statusFolio.setStatus(dto.status());
        return statusFolio;
    }

    private StatusFolioResponseDTO toDTO(StatusFolioModel statusFolio){
        return new StatusFolioResponseDTO(
                statusFolio.getStatus()
        );
    }

    //POST
    @PostMapping
    public ResponseEntity<StatusFolioResponseDTO> register(@RequestBody StatusFolioRequestDTO request){
        StatusFolioModel toCreateStatus = toEntity(request);
        StatusFolioModel createdStatus = service.register(toCreateStatus);
        StatusFolioResponseDTO response = toDTO(createdStatus);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    //GET
    @GetMapping
    public ResponseEntity<List<StatusFolioResponseDTO>> listAll(){
        List<StatusFolioModel> statusList = service.findAll();
        List<StatusFolioResponseDTO> listDTO = statusList.stream()
                .map(this::toDTO)
                .toList();

        return ResponseEntity.ok(listDTO);
    }

    //GET By ID
    @GetMapping("/{id}")
    public ResponseEntity<StatusFolioResponseDTO> findById(@PathVariable Long id){
        return service.findById(id)
                .map(statusFolio -> {
                    StatusFolioResponseDTO dto = toDTO(statusFolio);
                    return ResponseEntity.ok(dto);
                })
                .orElseThrow(() -> new EntityNotFoundException("Status não encontrado"));
    }

    //PUT
    @PutMapping("/{id}")
    public ResponseEntity<StatusFolioResponseDTO> update(@PathVariable Long id, @RequestBody StatusFolioRequestDTO request){
        StatusFolioModel statusData = toEntity(request);
        StatusFolioModel updatedData = service.update(id, statusData);
        StatusFolioResponseDTO response = toDTO(updatedData);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //PATCH
    @PatchMapping("/{id}")
    public ResponseEntity<StatusFolioResponseDTO> updatePartial(@PathVariable Long id, @RequestBody StatusFolioRequestDTO request){
        StatusFolioModel statusData = toEntity(request);
        StatusFolioModel updatedData = service.update(id, statusData);
        StatusFolioResponseDTO response = toDTO(updatedData);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}