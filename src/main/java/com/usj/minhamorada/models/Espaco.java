package com.usj.minhamorada.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "espaco")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(exclude = {"agendamentoEspaco"})
@Builder
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class Espaco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nomeEspaco", nullable = false)
    @NotNull
    private String nomeEspaco;

    @OneToOne(mappedBy = "espaco", cascade = CascadeType.PERSIST)
    @JoinColumn (name = "agendamentoEspaco_id")
    @JsonIgnoreProperties("espaco")
    @ToString.Exclude
    private AgendamentoEspaco agendamentoEspaco;

}
