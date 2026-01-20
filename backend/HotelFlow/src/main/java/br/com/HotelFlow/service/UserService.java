package br.com.HotelFlow.service;

import br.com.HotelFlow.model.UserModel;
import br.com.HotelFlow.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository){
        this.repository = repository;
    }

    //CREATE
    @Transactional
    public UserModel register(UserModel user){
        if(repository.existsByEmail(user.getEmail())){
            throw new IllegalArgumentException("Já existe um usuário com esse email");
        }
        user.setActive(true);
        return repository.save(user);
    }

    //READ ALL
    @Transactional(readOnly = true)
    public List<UserModel> findAll(){
        return repository.findAll();
    }

    //READ BY ID
    @Transactional(readOnly = true)
    public Optional<UserModel> findById(Long id){
        return repository.findById(id);
    }

    //UPDATE
    @Transactional
    public UserModel update(Long id, UserModel data){
        UserModel userExists = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        if(data.getEmail() != null){
            if(!userExists.getEmail().equals(data.getEmail()) && repository.existsByEmail(data.getEmail())){
                throw new IllegalArgumentException("Email já em uso");
            }
            userExists.setEmail(data.getEmail());
        }

        if(data.getName() != null){
            userExists.setName(data.getName());
        }

        if(data.getPassword() != null){
            userExists.setPassword(data.getPassword());
        }

        if(data.getRole() != null){
            userExists.setRole(data.getRole());
        }

        return repository.save(userExists);
    }

    //DELETE
    @Transactional
    public void delete(Long id){
        if(!repository.existsById(id)){
            throw new EntityNotFoundException("Usuário não encontrado");
        }
        repository.deleteById(id);
    }
}