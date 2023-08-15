package com.skilloo.api.services.professor;

import com.skilloo.api.dto.lab.LabReservaDTO;
import com.skilloo.api.entities.Aula;
import com.skilloo.api.entities.Lab;
import com.skilloo.api.entities.Reserva;
import com.skilloo.api.entities.User;
import com.skilloo.api.repositories.AulaRepository;
import com.skilloo.api.repositories.LabRepository;
import com.skilloo.api.repositories.ReservaRepository;
import com.skilloo.api.services.exceptions.DataNotFoundException;
import com.skilloo.api.services.exceptions.ForbiddenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ReservaService {

    @Autowired
    private LabRepository labRepository;

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private AulaRepository aulaRepository;

    @Transactional
    public List<LabReservaDTO> buscarLabs(Pageable pageable, Long idAula) {

        Aula aula = aulaRepository.getReferenceById(idAula);

        //busca todos os labs
        List<LabReservaDTO> allLabs = new java.util.ArrayList<>(labRepository.findAll().stream().map(LabReservaDTO::new).toList());

        //busca os labs já reservados em determinado horario
        List<Object[]> query = labRepository.buscarLabsComReserva(aula.getDia(), aula.getHorario());

        //para cada lab reservado
        for (Object[] obj : query){
            System.out.println(Arrays.toString(obj));
            Long idLab = (Long) obj[0];
            String nomeLab = (String) obj[1];
            Long idReserva = (Long) obj[2];
            DayOfWeek dia = aula.getDia();
            LocalTime horario = aula.getHorario();

            //remove o lab reservado da lista de todos os labs
             allLabs.removeIf(l -> l.getLabId().equals(idLab));


             //adiciona o lab novamente na lista, porem marcado como reservado
             LabReservaDTO labReservaDTO = new LabReservaDTO(idLab, nomeLab, reservaRepository.getReferenceById(idReserva), dia, horario);
             allLabs.add(labReservaDTO);
        }

        return allLabs;
    }

    @Transactional
    public LabReservaDTO reservarLab(Long idAula, Long idLab) {

        Aula aula = aulaRepository.getReferenceById(idAula);
        Lab lab = labRepository.getReferenceById(idLab);

        if(verificarSeJaExisteReserva(idLab, aula.getDia(), aula.getHorario())){
            throw new DataIntegrityViolationException("Já existe uma reserva no "+ lab.getNome()+" neste dia: " + aula.getDia() + ", e horario: "+aula.getHorario());
        }

        Reserva reserva = new Reserva();
        reserva.setAula(aula);
        reserva.setLab(lab);
        reserva.setDia(aula.getDia());
        reserva.setHorario(aula.getHorario());

        reserva = reservaRepository.save(reserva);

        return new LabReservaDTO(lab, reserva);
    }

    private boolean verificarSeJaExisteReserva(Long idLab, DayOfWeek dia, LocalTime horario){
        List<Object[]> query = reservaRepository.verificarSeJaExisteReserva(idLab, dia, horario);


        for (Object[] obj : query){
            System.out.println(obj[0]);
        }

        if (query.isEmpty()){
            return false;
        }

        return true;
    }

    @Transactional
    public void cancelarReserva(Long idReserva, Long idUser) {

        Optional<Reserva> entity = reservaRepository.findById(idReserva);

        if(entity.isEmpty()){
            throw new DataNotFoundException("Id not Found: " + idReserva);
        }

        boolean isAutor = false;
        for (User user : entity.get().getAula().getProfessores()){
            if (Objects.equals(user.getId(), idUser)) {
                isAutor = true;
                break;
            }
        }

        if (!isAutor){
            throw new ForbiddenException("Vocẽ não pode cancelar uma reserva que não seja de sua autoria.");
        }

        reservaRepository.deleteById(idReserva);
    }
}
