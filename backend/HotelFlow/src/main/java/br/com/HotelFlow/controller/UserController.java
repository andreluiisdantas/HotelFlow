package br.com.HotelFlow.controller;

import br.com.HotelFlow.dto.user.UserRequestDTO;
import br.com.HotelFlow.dto.user.UserResponseDTO;
import br.com.HotelFlow.model.UserModel;
import br.com.HotelFlow.repository.RolesRepository;
import br.com.HotelFlow.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;
    private final RolesRepository rolesRepository;

    public UserController(UserService service, RolesRepository rolesRepository){
        this.service = service;
        this.rolesRepository = rolesRepository;
    }

    private UserModel toEntity(UserRequestDTO dto){
        UserModel user = new UserModel();
        user.setName(dto.name());
        user.setEmail(dto.email());
        user.setPassword(dto.password());

        if(dto.roleId() != null){
            user.setRole(rolesRepository.findById(dto.roleId())
                    .orElseThrow(() -> new EntityNotFoundException("Permissão não encontrada")));
        }

        return user;
    }

    private UserResponseDTO toDTO(UserModel user){
        return new UserResponseDTO(user);
    }

    //POST
    @PostMapping
    public ResponseEntity<UserResponseDTO> register(@RequestBody UserRequestDTO request){
        UserModel toCreate = toEntity(request);
        UserModel created = service.register(toCreate);
        UserResponseDTO response = toDTO(created);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    //GET
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> listAll(){
        List<UserModel> list = service.findAll();
        List<UserResponseDTO> listDTO = list.stream()
                .map(this::toDTO)
                .toList();

        return ResponseEntity.ok(listDTO);
    }

    //GET By ID
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> findById(@PathVariable Long id){
        return service.findById(id)
                .map(user -> {
                    UserResponseDTO dto = toDTO(user);
                    return ResponseEntity.ok(dto);
                })
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
    }

    //PUT
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> update(@PathVariable Long id, @RequestBody UserRequestDTO request){
        UserModel data = toEntity(request);
        UserModel updated = service.update(id, data);
        UserResponseDTO response = toDTO(updated);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //PATCH
    @PatchMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updatePartial(@PathVariable Long id, @RequestBody UserRequestDTO request){
        UserModel data = toEntity(request);
        UserModel updated = service.update(id, data);
        UserResponseDTO response = toDTO(updated);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}