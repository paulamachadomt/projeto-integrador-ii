package com.usj.minhamorada.models.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.usj.minhamorada.models.Apartamento;
import com.usj.minhamorada.models.Morador;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@JsonInclude(value = Include.NON_NULL)
public class RequestResponseDTO {
	
	private String statusCode;
	private Apartamento apartamento; 
	private Morador morador; 
	private String mensagem;
	private String error;
	
}
