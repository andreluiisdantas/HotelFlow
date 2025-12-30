package br.com.HotelFlow.controller;

import br.com.HotelFlow.dto.guest.GuestRequestDTO;
import br.com.HotelFlow.dto.guest.GuestResponseDTO;
import br.com.HotelFlow.model.GuestModel;
import br.com.HotelFlow.service.GuestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/guests")
public class GuestController {

    private final GuestService service;

    public GuestController(GuestService service){
        this.service = service;
    }

    private GuestModel toEntity(GuestRequestDTO dto){
        GuestModel guest = new GuestModel();

        guest.setName(dto.name());
        guest.setCpf(dto.cpf());
        guest.setRg(dto.rg());
        guest.setAddress(dto.address());
        guest.setDateBirth(dto.dateBirth());
        guest.setPhone(dto.phone());
        guest.setEmail(dto.email());
        return guest;
    }

    private GuestResponseDTO toDTO(GuestModel guest){
        return new GuestResponseDTO(
                guest.getId(),
                guest.getName(),
                guest.getRg(),
                guest.getCpf(),
                guest.getDateBirth(),
                guest.getAddress(),
                guest.getPhone(),
                guest.getEmail()
        );
    }

    //POST
    @PostMapping
    public ResponseEntity<GuestResponseDTO> register(@RequestBody GuestRequestDTO request){

        GuestModel toCreateGuest = toEntity(request);
        GuestModel createdGuest = service.register(toCreateGuest);

        GuestResponseDTO response = toDTO(createdGuest);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    //GET
    @GetMapping
    public ResponseEntity<List<GuestResponseDTO>> listAll(){
        List<GuestModel> guestList = service.findAll();

        List<GuestResponseDTO> listDTO = guestList.stream()
                .map(this::toDTO)
                .toList();

        return ResponseEntity.ok(listDTO);
    }

    //GET By ID
    @GetMapping("/{id}")
    public ResponseEntity<GuestResponseDTO> findById(@PathVariable Long id){
        return service.findById(id)
                .map(GuestModel -> {
                    GuestResponseDTO dto = toDTO(GuestModel);
                    return ResponseEntity.ok(dto);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    //DELETE
    @DeleteMapping("/{ID}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
