package br.com.HotelFlow.controller;

import br.com.HotelFlow.dto.roles.RolesRequestDTO;
import br.com.HotelFlow.dto.roles.RolesResponseDTO;
import br.com.HotelFlow.model.RolesModel;
import br.com.HotelFlow.service.RolesService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RolesController {

    private final RolesService service;

    public RolesController(RolesService service){
        this.service = service;
    }

    private RolesModel toEntity(RolesRequestDTO dto){
        RolesModel role = new RolesModel();

        role.setName(dto.name());
        return role;
    }

    private RolesResponseDTO toDTO(RolesModel role){
        return new RolesResponseDTO(
                role.getId(),
                role.getName()
        );
    }

    //POST
    @PostMapping
    public ResponseEntity<RolesResponseDTO> register(@RequestBody RolesRequestDTO request){

        RolesModel toCreateRole = toEntity(request);
        RolesModel createdRole = service.register(toCreateRole);
        RolesResponseDTO response = toDTO(createdRole);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    //GET
    @GetMapping
    public ResponseEntity<List<RolesResponseDTO>> listAll(){
        List<RolesModel> rolesList = service.findAll();

        List<RolesResponseDTO> listDTO = rolesList.stream()
                .map(this::toDTO)
                .toList();

        return ResponseEntity.ok(listDTO);
    }

    //GET By ID
    @GetMapping("/{id}")
    public ResponseEntity<RolesResponseDTO> findById(@PathVariable Long id){
        return service.findById(id)
                .map(rolesModel -> {
                    RolesResponseDTO dto = toDTO(rolesModel);
                    return ResponseEntity.ok(dto);
                })
                .orElseThrow(() -> new EntityNotFoundException("Regra não encontrada"));
    }

    //PUT
    @PutMapping("/{id}")
    public ResponseEntity<RolesResponseDTO> update(@PathVariable Long id, @RequestBody RolesRequestDTO request){

        RolesModel roleData = toEntity(request);
        RolesModel updatedData = service.update(id, roleData);
        RolesResponseDTO response = toDTO(updatedData);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //PATCH
    @PatchMapping("/{id}")
    public ResponseEntity<RolesResponseDTO> updatePartial(@PathVariable Long id, @RequestBody RolesRequestDTO request){

        RolesModel roleData = toEntity(request);
        RolesModel updatedData = service.update(id, roleData);
        RolesResponseDTO response = toDTO(updatedData);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}