package com.usj.minhamorada.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "assembleia")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
@Builder
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class Assembleia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo", nullable = false)
    @NotNull
    private String titulo;

    @Column(name = "pautaAssembleia", nullable = false)
    @NotNull
    private String pauta;

    @Column(name = "ata")
    @NotNull
    private String ata;

    @Column(name = "dataHora", nullable = false)
    @NotNull
    @JsonFormat(pattern="dd/MM/yyyy HH:mm")
    private LocalDateTime dataHora;

}
