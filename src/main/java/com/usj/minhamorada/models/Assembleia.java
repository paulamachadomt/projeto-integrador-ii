package com.usj.minhamorada.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
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

    @Column(name = "ata")
    @NotNull
    private String ata;

    @Column(name = "data", nullable = false)
    @NotNull
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dataHora;

}
