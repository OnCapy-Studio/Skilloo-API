package com.skilloo.api.services;

import com.skilloo.api.dto.user.UserDTO;
import com.skilloo.api.dto.user.UserInsertDTO;
import com.skilloo.api.dto.user.UserUpdateDTO;
import com.skilloo.api.entities.User;
import com.skilloo.api.entities.enuns.Role;
import com.skilloo.api.repositories.AulaRepository;
import com.skilloo.api.repositories.ProfessorRepository;
import com.skilloo.api.services.exceptions.DataNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ProfessorService implements UserDetailsService {

    @Autowired
    private ProfessorRepository repository;

    @Autowired
    private AulaRepository aulaRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public Page<UserDTO> findAllProfessores(Pageable pageable){

        Page<User> users = repository.findAllByRole(Role.PROF, pageable);

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
    public UserDTO insertProfessor(UserInsertDTO dto) {

        User user = new User();
        copyDtoToEntity(dto, user);

        user = repository.save(user);

        return new UserDTO(user);
    }

    @Transactional
    public UserDTO updateProfessor(Long id, UserUpdateDTO dto) {

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
    public void deleteProfessor(Long id) {

        Optional<User> user = repository.findById(id);

        if(user.isEmpty()){
            throw new DataNotFoundException("Id not Found: " + id);
        }

        aulaRepository.deleteByProfessorId(id);
        repository.deleteById(id);

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
