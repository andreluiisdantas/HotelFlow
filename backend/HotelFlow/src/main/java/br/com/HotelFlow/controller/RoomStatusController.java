package br.com.HotelFlow.controller;

import br.com.HotelFlow.dto.roomStatus.RoomStatusRequestDTO;
import br.com.HotelFlow.dto.roomStatus.RoomStatusResponseDTO;
import br.com.HotelFlow.model.RoomStatusModel;
import br.com.HotelFlow.service.RoomStatusService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/room-status")
public class RoomStatusController {

    private final RoomStatusService service;

    public RoomStatusController(RoomStatusService service){
        this.service = service;
    }

    private RoomStatusModel toEntity(RoomStatusRequestDTO dto){
        RoomStatusModel roomStatus = new RoomStatusModel();

        roomStatus.setStatus(dto.status());
        return roomStatus;
    }

    private RoomStatusResponseDTO toDTO(RoomStatusModel roomStatus){
        return new RoomStatusResponseDTO(
                roomStatus.getId(),
                roomStatus.getStatus()
        );
    }

    //POST
    @PostMapping
    public ResponseEntity<RoomStatusResponseDTO> register(@RequestBody RoomStatusRequestDTO request){

        RoomStatusModel toCreateStatus = toEntity(request);
        RoomStatusModel createdStatus = service.register(toCreateStatus);
        RoomStatusResponseDTO response = toDTO(createdStatus);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    //GET ALL
    @GetMapping
    public ResponseEntity<List<RoomStatusResponseDTO>> listAll(){
        List<RoomStatusModel> roomStatusList = service.findAll();

        List<RoomStatusResponseDTO> listDTO = roomStatusList.stream()
                .map(this::toDTO)
                .toList();

        return ResponseEntity.ok(listDTO);
    }

    //GET By ID
    @GetMapping("/{id}")
    public ResponseEntity<RoomStatusResponseDTO> findById(@PathVariable Long id){
        return service.findById(id)
                .map(RoomStatusModel -> {
                    RoomStatusResponseDTO dto = toDTO(RoomStatusModel);
                    return ResponseEntity.ok(dto);
                })
                .orElseThrow(() -> new EntityNotFoundException("Status não encontrado"));
    }

    //PUT
    @PutMapping("/{id}")
    public ResponseEntity<RoomStatusResponseDTO> update(@PathVariable Long id, @RequestBody RoomStatusRequestDTO request){

        RoomStatusModel roomStatusData = toEntity(request);
        RoomStatusModel updatedData = service.update(id, roomStatusData);
        RoomStatusResponseDTO response = toDTO(updatedData);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //PATCH
    @PatchMapping("/{id}")
    public ResponseEntity<RoomStatusResponseDTO> updatePartial(@PathVariable Long id, @RequestBody RoomStatusRequestDTO request){

        RoomStatusModel roomStatusData = toEntity(request);
        RoomStatusModel updatedData = service.update(id, roomStatusData);
        RoomStatusResponseDTO response = toDTO(updatedData);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
