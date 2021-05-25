package com.usj.minhamorada.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usj.minhamorada.models.Apartamento;
import com.usj.minhamorada.models.dto.RequestResponseDTO;
import com.usj.minhamorada.repositories.ApartamentoRepository;

@Service
public class ApartamentoService {

	@Autowired
	private ApartamentoRepository apartamentoRepository;	

	private String statuscode = "200";

	public RequestResponseDTO cadastrarApartamento(RequestResponseDTO requestDTO) {
		try {
			isPresentNumeroApartamento(requestDTO);
			Apartamento apartamento = requestDTO.getApartamento();
			apartamento = apartamentoRepository.save(apartamento);
			verificaCadastroApto(apartamento);
			return montaResposta(apartamento, "201", "Apartamento cadastrado com sucesso!");

		} catch (Exception e) {
			return RequestResponseDTO.builder().statusCode(statuscode).error(e.getMessage()).build();
		}

	}
	
	public RequestResponseDTO carregarDadosApartamento(Long id) throws Exception {
		try {
			Apartamento apartamento = readApartamentoById(id);			
			return montaResposta(apartamento, "200", "Apartamento encontrado com sucesso!");
		} catch (Exception e) {
			return RequestResponseDTO.builder().statusCode(statuscode).error(e.getMessage()).build();
		}		
	}
	
	public RequestResponseDTO atualizarDadosApartamento(Long id, RequestResponseDTO requestDTO) {
		try {
			Apartamento apartamento = readApartamentoById(id);
			apartamento.setBlocoApto(requestDTO.getApartamento().getBlocoApto());
			apartamento.setNumeroApto(requestDTO.getApartamento().getNumeroApto());
			apartamento.setVagaGaragem(requestDTO.getApartamento().getVagaGaragem());
			apartamento.setMorador(requestDTO.getApartamento().getMorador());
			apartamentoRepository.save(apartamento);
			return montaResposta(apartamento, "200", "Apartamento atualizado com sucesso!");			
			
		} catch (Exception e) {
			return RequestResponseDTO.builder().statusCode(statuscode).error(e.getMessage()).build();
		}
		
	}
		
	public RequestResponseDTO deletarApartamento(Long id) throws Exception {
		try {
			Apartamento apartamento = readApartamentoById(id);
			apartamentoRepository.deleteById(id);
			return montaResposta("200", "Apartamento deletado com sucesso!");			
		} catch (Exception e) {
			return RequestResponseDTO.builder().statusCode(statuscode).error(e.getMessage()).build();
		}
	}
	
	//Busca um apartamento na base de dados através do id
	Apartamento readApartamentoById(Long id) throws Exception {
		try {
			return apartamentoRepository.findById(id).get();
		} catch (Exception e) {
			statuscode = "404";
			throw new Exception("Verifique ID do apartamento!");
		}
	}

	//Verifica se o apartamento possui número, já que é um campo obrigatório
	boolean isPresentNumeroApartamento(RequestResponseDTO requestDTO) throws Exception {
		if (requestDTO.getApartamento().getNumeroApto() == null) {
			statuscode = "400";
			throw new Exception("Necessário inserir número do apartamento!");
		}
		return true;
	}

	//Verifica se o apartamento foi cadastrado com sucesso através do id
	boolean verificaCadastroApto(Apartamento apartamento) throws Exception {
		if (apartamento.getId() == null) {
			statuscode = "500";
			throw new Exception("Ocorreu uma falha. Tente novamente mais tarde!");
		}
		return true;
	}
	
	RequestResponseDTO montaResposta(Apartamento apartamento, String statuscode, String mensagem){
		return RequestResponseDTO.builder().apartamento(apartamento).statusCode(statuscode).mensagem(mensagem).build();
	}
	RequestResponseDTO montaResposta(String statuscode, String mensagem){
		return RequestResponseDTO.builder().statusCode(statuscode).mensagem(mensagem).build();
	}

	

	




}
