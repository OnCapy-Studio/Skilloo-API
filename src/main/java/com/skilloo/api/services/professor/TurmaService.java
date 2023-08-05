package com.skilloo.api.services.professor;

import com.skilloo.api.dto.MateriaDTO;
import com.skilloo.api.dto.TurmaWithMateriaDTO;
import com.skilloo.api.entities.Materia;
import com.skilloo.api.entities.User;
import com.skilloo.api.repositories.AulaRepository;
import com.skilloo.api.repositories.ProfessorRepository;
import com.skilloo.api.repositories.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TurmaService {


    @Autowired
    private TurmaRepository repository;

    @Autowired
    private AulaRepository aulaRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @Transactional
    public Page<TurmaWithMateriaDTO> findAllTurmas(Pageable pageable, Long idUser) {

        //retora as turmas com suas respectivas materias em formato de object
        Page<Object[]> resultadoConsulta = repository.buscarTurmasEMateriasPorProfessor(idUser, pageable);

        return filtrarQuery(resultadoConsulta);
    }

    private Page<TurmaWithMateriaDTO> filtrarQuery(Page<Object[]> resultadoConsulta){

        List<TurmaWithMateriaDTO> turmas = new ArrayList<>();

        //para cada objeto(turma)
        // /*
        // [1, "3ºDS-B", "MATERIA": {}]
        // */
        for (Object[] row : resultadoConsulta){

            Long id = (Long) row[0];
            String nome = (String) row[1];
            Materia materia = (Materia) row[2];

            MateriaDTO materiaDTO = new MateriaDTO(materia);

            TurmaWithMateriaDTO turma = turmas.stream()
                    //verifica se já existe uma turma com o mesmo nome (turma com 2 materias)
                    .filter(t -> t.getNomeTurma().equals(nome))
                    //pega a entidade já existente
                    .findFirst()

                    //caso nao tenha nenhuma igual, cria uma nova instancia normalmente
                    .orElseGet(() -> {
                        TurmaWithMateriaDTO newTurma = new TurmaWithMateriaDTO();
                        newTurma.setIdTurma(id);
                        newTurma.setNomeTurma(nome);
                        turmas.add(newTurma);
                        return newTurma;
                    });

            //adciona a materia na turma
            turma.getMaterias().add(materiaDTO);
        }

        return new PageImpl<>(turmas);
    }
}
