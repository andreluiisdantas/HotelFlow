package br.com.HotelFlow.controller;

import br.com.HotelFlow.dto.GuestDTO;
import br.com.HotelFlow.model.GuestModel;
import br.com.HotelFlow.repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/guests")
public class GuestController {

    @Autowired
    private GuestRepository guestRepository;

    //GET ALL
    @GetMapping
    public List<GuestModel> getAllGuests(){
        return guestRepository.findAll();
    }

    //POST GUEST
    @PostMapping
    public ResponseEntity<GuestModel> createGuest(@RequestBody GuestDTO dto) {

        GuestModel guest = new GuestModel();
        guest.setName(dto.name());
        guest.setRg(dto.rg());
        guest.setCpf(dto.cpf());
        guest.setDateBirth(dto.dateBirth());
        guest.setAddress(dto.address());
        guest.setPhone(dto.phone());
        guest.setEmail(dto.email());

        GuestModel saved = guestRepository.save(guest);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

}
