package com.skilloo.api.services;

import com.skilloo.api.dto.ticketsuporte.TicketSuporteDTO;
import com.skilloo.api.dto.ticketsuporte.TicketSuporteInsertDTO;
import com.skilloo.api.entities.TicketSuporte;
import com.skilloo.api.entities.enuns.Role;
import com.skilloo.api.entities.enuns.TicketStatus;
import com.skilloo.api.repositories.ProfessorRepository;
import com.skilloo.api.repositories.TicketSuporteRepository;
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
public class SuporteService {

    @Autowired
    private TicketSuporteRepository repository;
    @Autowired
    private ProfessorRepository professorRepository;

    @Transactional(readOnly = true)
    public Page<TicketSuporteDTO> findAllTickets(Pageable pageable){
        return repository.findAll(pageable).map(TicketSuporteDTO::new);
    }



    @Transactional
    public TicketSuporteDTO createTicket(TicketSuporteInsertDTO dto, Long idUser) {
        TicketSuporte ticketSuporte = new TicketSuporte();
        ticketSuporte.setTitulo(dto.getTitulo());
        ticketSuporte.setDescricao(dto.getDescricao());
        ticketSuporte.setLab(dto.getLab());
        ticketSuporte.setStatus(TicketStatus.PARA_RESOLVER);
        ticketSuporte.setProfessor(professorRepository.getReferenceById(idUser));

        return new TicketSuporteDTO(repository.save(ticketSuporte));
    }

    @Transactional
    public TicketSuporteDTO updateTicket(Long idTicket, TicketSuporteInsertDTO dto, Long idUser) {

        try{
            TicketSuporte ticketSuporte = repository.getReferenceById(idTicket);

            //caso o id que esteja associado ao ticket não seja o mesmo que estava no token
            if((!Objects.equals(ticketSuporte.getProfessor().getId(), idUser))){
                if(professorRepository.getReferenceById(idUser).getRole().equals(Role.PROF)) {
                    //caso o user não seja um ADMIN
                    throw new ForbiddenException("Vocẽ não pode alterar um ticket que não seja de sua autoria.");
                }
            }

            ticketSuporte.setTitulo(dto.getTitulo());
            ticketSuporte.setLab(dto.getLab());
            ticketSuporte.setDescricao(dto.getDescricao());
            ticketSuporte.setStatus(dto.getStatus());

            return new TicketSuporteDTO(repository.save(ticketSuporte));
        }

        catch (EntityNotFoundException e){
            throw new DataNotFoundException("Id not found: " + idTicket);
        }
    }

    @Transactional
    public void deleteTicket(Long idTicket, Long idUser) {

        Optional<TicketSuporte> ticketSuporte = repository.findById(idTicket);

        if(ticketSuporte.isEmpty()){
            throw new DataNotFoundException("Id not Found: " + idTicket);
        }

        //verificaçao para ver se o usuario tem permissão para deletar o ticket
        //caso o id que esteja associado ao ticket não seja o mesmo que estava no token
        if((!Objects.equals(ticketSuporte.get().getProfessor().getId(), idUser))){
            //caso o user não seja um ADMIN
            if(professorRepository.getReferenceById(idUser).getRole().equals(Role.PROF)) {
                throw new ForbiddenException("Vocẽ não pode deletar um ticket que não seja de sua autoria.");
            }
        }

        repository.deleteById(idTicket);
    }

    @Transactional
    public Page<TicketSuporteDTO> findTicketsByProfessor(Long idUser, Pageable pageable) {
           return repository.findAllByProfessorId(idUser, pageable).map(TicketSuporteDTO::new);
    }
}
