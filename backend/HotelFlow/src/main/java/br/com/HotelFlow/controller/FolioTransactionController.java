package br.com.HotelFlow.controller;

import br.com.HotelFlow.dto.folioTransaction.FolioTransactionRequestDTO;
import br.com.HotelFlow.dto.folioTransaction.FolioTransactionResponseDTO;
import br.com.HotelFlow.model.FolioTransactionModel;
import br.com.HotelFlow.repository.FolioRepository;
import br.com.HotelFlow.service.FolioTransactionService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/folio-transactions")
public class FolioTransactionController {

    private final FolioTransactionService service;
    private final FolioRepository folioRepository;

    public FolioTransactionController(FolioTransactionService service, FolioRepository folioRepository){
        this.service = service;
        this.folioRepository = folioRepository;
    }

    private FolioTransactionModel toEntity(FolioTransactionRequestDTO dto){
        FolioTransactionModel transaction = new FolioTransactionModel();

        if(dto.folioId() != null){
            transaction.setFolio(folioRepository.findById(dto.folioId())
                    .orElseThrow(() -> new EntityNotFoundException("Folio não encontrado")));
        }
        // Amount não vem no RequestDTO, setando 0 ou mock
        transaction.setAmount(BigDecimal.ZERO);

        return transaction;
    }

    private FolioTransactionResponseDTO toDTO(FolioTransactionModel transaction){
        return new FolioTransactionResponseDTO(
                transaction.getId(),
                transaction.getFolio() != null ? transaction.getFolio().getId() : null,
                transaction.getAmount()
        );
    }

    //POST
    @PostMapping
    public ResponseEntity<FolioTransactionResponseDTO> register(@RequestBody FolioTransactionRequestDTO request){
        FolioTransactionModel toCreate = toEntity(request);
        FolioTransactionModel created = service.register(toCreate);
        FolioTransactionResponseDTO response = toDTO(created);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    //GET
    @GetMapping
    public ResponseEntity<List<FolioTransactionResponseDTO>> listAll(){
        List<FolioTransactionModel> list = service.findAll();
        List<FolioTransactionResponseDTO> listDTO = list.stream()
                .map(this::toDTO)
                .toList();

        return ResponseEntity.ok(listDTO);
    }

    //GET By ID
    @GetMapping("/{id}")
    public ResponseEntity<FolioTransactionResponseDTO> findById(@PathVariable Long id){
        return service.findById(id)
                .map(transaction -> {
                    FolioTransactionResponseDTO dto = toDTO(transaction);
                    return ResponseEntity.ok(dto);
                })
                .orElseThrow(() -> new EntityNotFoundException("Transação não encontrada"));
    }

    //PUT
    @PutMapping("/{id}")
    public ResponseEntity<FolioTransactionResponseDTO> update(@PathVariable Long id, @RequestBody FolioTransactionRequestDTO request){
        FolioTransactionModel data = toEntity(request);
        FolioTransactionModel updated = service.update(id, data);
        FolioTransactionResponseDTO response = toDTO(updated);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //PATCH
    @PatchMapping("/{id}")
    public ResponseEntity<FolioTransactionResponseDTO> updatePartial(@PathVariable Long id, @RequestBody FolioTransactionRequestDTO request){
        FolioTransactionModel data = toEntity(request);
        FolioTransactionModel updated = service.update(id, data);
        FolioTransactionResponseDTO response = toDTO(updated);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}