package com.skilloo.api.services.professor;

import com.skilloo.api.dto.TaskDTO;
import com.skilloo.api.entities.Task;
import com.skilloo.api.repositories.ProfessorRepository;
import com.skilloo.api.repositories.TaskRepository;
import com.skilloo.api.services.exceptions.DataNotFoundException;
import com.skilloo.api.services.exceptions.ForbiddenException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository repository;

    @Autowired
    private ProfessorRepository professorRepository;

    @Transactional
    public Page<TaskDTO> findAllTasks(Pageable pageable, Long idUser) {
        return repository.findAllByAutor(idUser, pageable).map(TaskDTO::new);
    }

    @Transactional
    public TaskDTO insertTask(TaskDTO dto, Long idUser) {
        Task entity = new Task();
        copyDtoToEntity(dto, entity, idUser);
        return new TaskDTO(repository.saveAndFlush(entity));
    }

    @Transactional
    public TaskDTO updateTask(TaskDTO dto, Long idTask, Long idUser) {

        try{
            Task entity = repository.getReferenceById(idTask);

            if (!Objects.equals(entity.getAutor().getId(), idUser)){
                throw new ForbiddenException("Você não pode atualizar task que não seja de sua autoria!");
            }

            copyDtoToEntity(dto, entity);

            return new TaskDTO(repository.save(entity));

        }
        catch (EntityNotFoundException e){
            throw new DataNotFoundException("Task não encontrada, ID: " + idTask);
        }
    }

    @Transactional
    public void deleteTask(Long idTask, Long idUser) {

        Optional<Task> entity = repository.findById(idTask);

        if (entity.isEmpty()){
            throw new DataNotFoundException("Task não encontrada! ID: "+ idTask);
        }

        if (!Objects.equals(entity.get().getAutor().getId(), idUser)){
            throw new ForbiddenException("Você não pode deletar uma task que não seja de sua autoria!");
        }

        repository.deleteById(idTask);
    }


    private void copyDtoToEntity(TaskDTO dto, Task entity, Long idUser){
        entity.setTitulo(dto.getTitulo());
        entity.setPrazo(dto.getPrazo());
        entity.setAnotacao(dto.getAnotacao());
        entity.setStatus(dto.getStatus());
        entity.setAutor(professorRepository.getReferenceById(idUser));
    }

    private void copyDtoToEntity(TaskDTO dto, Task entity){
        entity.setTitulo(dto.getTitulo());
        entity.setPrazo(dto.getPrazo());
        entity.setAnotacao(dto.getAnotacao());
        entity.setStatus(dto.getStatus());
    }
}
