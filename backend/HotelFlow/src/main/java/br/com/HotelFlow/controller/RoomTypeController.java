package br.com.HotelFlow.controller;

import br.com.HotelFlow.dto.roomType.RoomTypeRequestDTO;
import br.com.HotelFlow.dto.roomType.RoomTypeResponseDTO;
import br.com.HotelFlow.model.RoomTypeModel;
import br.com.HotelFlow.service.RoomTypeService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/room-types")
public class RoomTypeController {

    private final RoomTypeService service;

    public RoomTypeController(RoomTypeService service){
        this.service = service;
    }

    private RoomTypeModel toEntity(RoomTypeRequestDTO dto){
        RoomTypeModel roomType = new RoomTypeModel();
        roomType.setType(dto.type());
        roomType.setCapacityAdults(dto.capacityAdults());
        roomType.setCapacityChildren(dto.capacityChildren());
        return roomType;
    }

    private RoomTypeResponseDTO toDTO(RoomTypeModel roomType){
        return new RoomTypeResponseDTO(
                roomType.getId(),
                roomType.getType(),
                roomType.getCapacityAdults(),
                roomType.getCapacityChildren()
        );
    }

    //POST
    @PostMapping
    public ResponseEntity<RoomTypeResponseDTO> register(@RequestBody RoomTypeRequestDTO request){
        RoomTypeModel toCreate = toEntity(request);
        RoomTypeModel created = service.register(toCreate);
        RoomTypeResponseDTO response = toDTO(created);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    //GET
    @GetMapping
    public ResponseEntity<List<RoomTypeResponseDTO>> listAll(){
        List<RoomTypeModel> list = service.findAll();
        List<RoomTypeResponseDTO> listDTO = list.stream()
                .map(this::toDTO)
                .toList();

        return ResponseEntity.ok(listDTO);
    }

    //GET By ID
    @GetMapping("/{id}")
    public ResponseEntity<RoomTypeResponseDTO> findById(@PathVariable Long id){
        return service.findById(id)
                .map(roomType -> {
                    RoomTypeResponseDTO dto = toDTO(roomType);
                    return ResponseEntity.ok(dto);
                })
                .orElseThrow(() -> new EntityNotFoundException("Tipo de quarto não encontrado"));
    }

    //PUT
    @PutMapping("/{id}")
    public ResponseEntity<RoomTypeResponseDTO> update(@PathVariable Long id, @RequestBody RoomTypeRequestDTO request){
        RoomTypeModel data = toEntity(request);
        RoomTypeModel updated = service.update(id, data);
        RoomTypeResponseDTO response = toDTO(updated);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //PATCH
    @PatchMapping("/{id}")
    public ResponseEntity<RoomTypeResponseDTO> updatePartial(@PathVariable Long id, @RequestBody RoomTypeRequestDTO request){
        RoomTypeModel data = toEntity(request);
        RoomTypeModel updated = service.update(id, data);
        RoomTypeResponseDTO response = toDTO(updated);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}