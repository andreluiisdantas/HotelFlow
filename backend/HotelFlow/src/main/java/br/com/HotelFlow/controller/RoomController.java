package br.com.HotelFlow.controller;

import br.com.HotelFlow.dto.room.RoomRequestDTO;
import br.com.HotelFlow.dto.room.RoomResponseDTO;
import br.com.HotelFlow.model.RoomModel;
import br.com.HotelFlow.repository.RoomStatusRepository;
import br.com.HotelFlow.repository.RoomTypeRepository;
import br.com.HotelFlow.service.RoomService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    private final RoomService service;
    private final RoomTypeRepository roomTypeRepository;
    private final RoomStatusRepository roomStatusRepository;

    public RoomController(RoomService service, RoomTypeRepository roomTypeRepository, RoomStatusRepository roomStatusRepository){
        this.service = service;
        this.roomTypeRepository = roomTypeRepository;
        this.roomStatusRepository = roomStatusRepository;
    }

    private RoomModel toEntity(RoomRequestDTO dto){
        RoomModel room = new RoomModel();
        room.setDoorCode(dto.doorCode());

        if (dto.roomTypeId() != null) {
            room.setRoomType(roomTypeRepository.findById(dto.roomTypeId())
                    .orElseThrow(() -> new EntityNotFoundException("Tipo de quarto não encontrado")));
        }

        if (dto.roomStatusId() != null) {
            room.setRoomStatus(roomStatusRepository.findById(dto.roomStatusId())
                    .orElseThrow(() -> new EntityNotFoundException("Status de quarto não encontrado")));
        }

        return room;
    }

    private RoomResponseDTO toDTO(RoomModel room){
        return new RoomResponseDTO(room);
    }

    //POST
    @PostMapping
    public ResponseEntity<RoomResponseDTO> register(@RequestBody RoomRequestDTO request){
        RoomModel toCreate = toEntity(request);
        RoomModel created = service.register(toCreate);
        RoomResponseDTO response = toDTO(created);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    //GET
    @GetMapping
    public ResponseEntity<List<RoomResponseDTO>> listAll(){
        List<RoomModel> list = service.findAll();
        List<RoomResponseDTO> listDTO = list.stream()
                .map(this::toDTO)
                .toList();

        return ResponseEntity.ok(listDTO);
    }

    //GET By ID
    @GetMapping("/{id}")
    public ResponseEntity<RoomResponseDTO> findById(@PathVariable Long id){
        return service.findById(id)
                .map(room -> {
                    RoomResponseDTO dto = toDTO(room);
                    return ResponseEntity.ok(dto);
                })
                .orElseThrow(() -> new EntityNotFoundException("Quarto não encontrado"));
    }

    //PUT
    @PutMapping("/{id}")
    public ResponseEntity<RoomResponseDTO> update(@PathVariable Long id, @RequestBody RoomRequestDTO request){
        RoomModel data = toEntity(request);
        RoomModel updated = service.update(id, data);
        RoomResponseDTO response = toDTO(updated);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //PATCH
    @PatchMapping("/{id}")
    public ResponseEntity<RoomResponseDTO> updatePartial(@PathVariable Long id, @RequestBody RoomRequestDTO request){
        RoomModel data = toEntity(request);
        RoomModel updated = service.update(id, data);
        RoomResponseDTO response = toDTO(updated);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}