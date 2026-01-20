package br.com.HotelFlow.controller;

import br.com.HotelFlow.dto.ratePlans.RatePlansRequestDTO;
import br.com.HotelFlow.dto.ratePlans.RatePlansResponseDTO;
import br.com.HotelFlow.model.RatePlansModel;
import br.com.HotelFlow.repository.RoomTypeRepository;
import br.com.HotelFlow.service.RatePlansService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rate-plans")
public class RatePlansController {

    private final RatePlansService service;
    private final RoomTypeRepository roomTypeRepository;

    public RatePlansController(RatePlansService service, RoomTypeRepository roomTypeRepository){
        this.service = service;
        this.roomTypeRepository = roomTypeRepository;
    }

    private RatePlansModel toEntity(RatePlansRequestDTO dto){
        RatePlansModel ratePlan = new RatePlansModel();
        ratePlan.setName(dto.name());
        ratePlan.setStandardPrice(dto.standardPrice());

        if (dto.roomTypeId() != null){
            ratePlan.setRoomType(roomTypeRepository.findById(dto.roomTypeId())
                    .orElseThrow(() -> new EntityNotFoundException("Tipo de quarto não encontrado")));
        }

        return ratePlan;
    }

    private RatePlansResponseDTO toDTO(RatePlansModel ratePlan){
        return new RatePlansResponseDTO(ratePlan);
    }

    //POST
    @PostMapping
    public ResponseEntity<RatePlansResponseDTO> register(@RequestBody RatePlansRequestDTO request){
        RatePlansModel toCreate = toEntity(request);
        RatePlansModel created = service.register(toCreate);
        RatePlansResponseDTO response = toDTO(created);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    //GET
    @GetMapping
    public ResponseEntity<List<RatePlansResponseDTO>> listAll(){
        List<RatePlansModel> list = service.findAll();
        List<RatePlansResponseDTO> listDTO = list.stream()
                .map(this::toDTO)
                .toList();

        return ResponseEntity.ok(listDTO);
    }

    //GET By ID
    @GetMapping("/{id}")
    public ResponseEntity<RatePlansResponseDTO> findById(@PathVariable Long id){
        return service.findById(id)
                .map(ratePlan -> {
                    RatePlansResponseDTO dto = toDTO(ratePlan);
                    return ResponseEntity.ok(dto);
                })
                .orElseThrow(() -> new EntityNotFoundException("Plano de tarifa não encontrado"));
    }

    //PUT
    @PutMapping("/{id}")
    public ResponseEntity<RatePlansResponseDTO> update(@PathVariable Long id, @RequestBody RatePlansRequestDTO request){
        RatePlansModel data = toEntity(request);
        RatePlansModel updated = service.update(id, data);
        RatePlansResponseDTO response = toDTO(updated);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //PATCH
    @PatchMapping("/{id}")
    public ResponseEntity<RatePlansResponseDTO> updatePartial(@PathVariable Long id, @RequestBody RatePlansRequestDTO request){
        RatePlansModel data = toEntity(request);
        RatePlansModel updated = service.update(id, data);
        RatePlansResponseDTO response = toDTO(updated);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}