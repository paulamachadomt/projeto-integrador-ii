package com.usj.minhamorada.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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

@Entity
@Table(name = "apartamento")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(exclude = {"morador"})
@Builder
@JsonInclude(value = Include.NON_NULL)
public class Apartamento  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column (name = "numeroApto", nullable = false)
	@NotNull
	private String numeroApto;
	
	@Column (name = "blocoApto")
	private String blocoApto;
	
	@Column (name = "vagaGaragem")
	private String vagaGaragem;
	
	@OneToOne(mappedBy = "apartamento", cascade = CascadeType.PERSIST)
	@JoinColumn (name = "morador_id")
	@JsonIgnoreProperties("apartamento")
	@ToString.Exclude
	private Morador morador;


}
