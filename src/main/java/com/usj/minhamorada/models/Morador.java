package com.usj.minhamorada.models;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "morador")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(exclude = {"apartamento"})
@Builder
@JsonInclude(value = Include.NON_NULL)
public class Morador  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column (name = "cpf", nullable = false)
	@NotNull
	private String cpf;
	
	@Column (name = "nome", nullable = false)
	@NotNull
	private String nome;
	
	@Column (name = "email")
	private String email;
	
	@Column (name = "telefone", nullable = false)
	@NotNull
	private String telefone;
	
	@Column (name = "dataNascimento")
	private String dataNascimento;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn (name = "apartamento_id")
	@JsonIgnoreProperties("morador")
	@ToString.Exclude
	private Apartamento apartamento;

	@OneToMany(mappedBy = "morador", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	@JoinColumn (name = "agendamentoEspaco_id")
	@JsonIgnoreProperties("morador")
	@ToString.Exclude
	private List<AgendamentoEspaco> agendamentoEspaco;

	@JsonIgnore
	public boolean isEmptyCPF() {
		return (cpf == null) ? true : false;			
	}
	
	@JsonIgnore
	public boolean isEmptyNome() {
		return (nome == null) ? true : false;			
	}
	
	@JsonIgnore
	public boolean isEmptyTelefone() {
		return (telefone == null) ? true : false;			
	}

}
