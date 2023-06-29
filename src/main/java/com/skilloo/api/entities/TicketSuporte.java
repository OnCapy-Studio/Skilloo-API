package com.skilloo.api.entities;

import com.skilloo.api.entities.enuns.TicketStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ticket_suporte")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TicketSuporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String lab;
    private String descricao;

    @Enumerated(EnumType.STRING)
    private TicketStatus status;

    @ManyToOne
    @JoinColumn(name = "professor")
    private User professor;
}
