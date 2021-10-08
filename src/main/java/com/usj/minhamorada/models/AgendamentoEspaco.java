package com.usj.minhamorada.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "AgendamentoEspaco")
@NoArgsConstructor
@AllArgsConstructor
@Data
//@EqualsAndHashCode(exclude = {"apartamento"})
@Builder
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class AgendamentoEspaco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "dataHoraAgendamento", nullable = false)
    @NotNull
    private LocalDate dataHoraAgendamento;


//    //espaço
//    @OneToOne(mappedBy = "agendamentoEspaco", cascade = CascadeType.PERSIST)
//    @JoinColumn (name = "espaco_id")
//    @JsonIgnoreProperties("agendamentoEspaco")
//    @ToString.Exclude
//    private Espaco espaco;
//
//
//    //morador
//    @OneToOne(mappedBy = "agendamentoEspaco", cascade = CascadeType.PERSIST)
//    @JoinColumn (name = "morador_id")
//    @JsonIgnoreProperties("agendamentoEspaco")
//    @ToString.Exclude
//    private Morador morador;

}
