package com.skilloo.api.services;

import com.skilloo.api.dto.user.UserDTO;
import com.skilloo.api.dto.user.UserInsertDTO;
import com.skilloo.api.dto.user.UserUpdateDTO;
import com.skilloo.api.entities.User;
import com.skilloo.api.repositories.UserRepository;
import com.skilloo.api.services.exceptions.DataNotFoundException;
import com.skilloo.api.services.exceptions.DatabaseException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public Page<UserDTO> findAll(Pageable pageable){

        Page<User> users = repository.findAll(pageable);
        return users.map(UserDTO::new);
    }

    @Transactional(readOnly = true)
    public UserDTO findById(Long id){

        Optional<User> user = repository.findById(id);

        //caso o optional volte vazio
        if (user.isEmpty()){
            throw new DataNotFoundException("Id not found: " + id);
        }

        return new UserDTO(user.get());
    }

    @Transactional
    public UserDTO insertUser(UserInsertDTO dto) {

        User user = new User();
        copyDtoToEntity(dto, user);

        user = repository.save(user);

        return new UserDTO(user);
    }

    @Transactional
    public UserDTO updateUser(Long id, UserUpdateDTO dto) {

        try{
            User user = repository.getReferenceById(id);
            System.out.println(dto);
            copyDtoToEntity(dto, user);
            user = repository.save(user);

            return new UserDTO(user);
        }
        //caso o .save() não ache a referencia pegada pelo getReferenceById(), é lançada uma exception
        catch (EntityNotFoundException e){
            throw new DataNotFoundException("Id not Found: " + id);
        }
    }

    @Transactional
    public void deleteUser(Long id) {

        try{
            Optional<User> user = repository.findById(id);

            if(user.isEmpty()){
                throw new DataNotFoundException("Id not Found: " + id);
            }
            repository.deleteById(id);
        }
        catch (DataIntegrityViolationException e){
            throw new DatabaseException("Integraty Violation");
        }
    }

    private void copyDtoToEntity(UserInsertDTO dto, User user) {
        user.setNome(dto.getNome());
        user.setEmail(dto.getEmail());
        user.setRole(dto.getRole());
        user.setArea(dto.getArea());
        user.setPontuacao(dto.getPontuacao());
        user.setDescricao(dto.getDescricao());
        user.setContrato(dto.getContrato());
        user.setSenha(passwordEncoder.encode(dto.getSenha()));
    }

    private void copyDtoToEntity(UserUpdateDTO dto, User user) {
        user.setNome(dto.getNome());
        user.setEmail(dto.getEmail());
        user.setRole(dto.getRole());
        user.setArea(dto.getArea());
        user.setPontuacao(dto.getPontuacao());
        user.setDescricao(dto.getDescricao());
        user.setContrato(dto.getContrato());
        user.setSenha(passwordEncoder.encode(dto.getSenha()));
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return repository.findByEmail(email);
    }
}
