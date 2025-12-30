package br.com.HotelFlow.service;

import br.com.HotelFlow.model.GuestModel;
import br.com.HotelFlow.repository.GuestRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class GuestService {

    private final GuestRepository repository;

    public GuestService(GuestRepository repository){
        this.repository = repository;
    }

    //CREATE
    @Transactional
    public GuestModel register(GuestModel guest){
        if (repository.existsByCpf(guest.getCpf())){
            throw new IllegalArgumentException("Já existe um cadastro nesse CPF");
        }

        if (repository.existsByRg(guest.getRg())){
            throw new IllegalArgumentException("Já existe um cadastro nesse RG");
        }

        return repository.save(guest);
    }

    //READ ALL
    @Transactional(readOnly = true)
    public List<GuestModel> findAll(){
        return repository.findAll();
    }

    //READ BY ID
    @Transactional(readOnly = true)
    public Optional<GuestModel> findById(Long id){
        return repository.findById(id);
    }

    //UPDATE
    @Transactional
    public GuestModel update(Long id, GuestModel data){
        GuestModel guestExists = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Hóspede não encontrado"));

        if (data.getCpf() != null) {
            if (!guestExists.getCpf().equals(data.getCpf()) && repository.existsByCpf(data.getCpf())){
                throw new IllegalArgumentException("CPF já em uso");
            }
            guestExists.setCpf(data.getCpf());
        }

        if (data.getRg() != null){
            if (!guestExists.getRg().equals(data.getRg()) && repository.existsByRg(data.getRg())){
                throw new IllegalArgumentException("RG já em uso");
            }
            guestExists.setRg(data.getRg());
        }

        if (data.getName() != null){
            guestExists.setName(data.getName());
        }

        if (data.getEmail() != null){
            guestExists.setEmail(data.getEmail());
        }

        if (data.getAddress() != null){
            guestExists.setAddress(data.getAddress());
        }

        if (data.getDateBirth() != null){
            guestExists.setDateBirth(data.getDateBirth());
        }

        if (data.getPhone() != null){
            guestExists.setPhone(data.getPhone());
        }

        return repository.save(guestExists);
    }

    //DELETE
    @Transactional
    public void delete(Long id){
        if (!repository.existsById(id)){
            throw new EntityNotFoundException("Usuário não encontrado");
        }
        repository.deleteById(id);
    }
}
