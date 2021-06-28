package com.usj.minhamorada.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usj.minhamorada.models.Apartamento;
import com.usj.minhamorada.models.Morador;
import com.usj.minhamorada.models.dto.DTO;
import com.usj.minhamorada.repositories.ApartamentoRepository;

@Service
public class ApartamentoService {

	@Autowired
	private ApartamentoRepository apartamentoRepository;

	private String statuscode = "200";

	public DTO cadastrarApartamento(DTO request) {
		try {
			throwExceptionIfNumeroApartamentoIsNotPresent(request);
			Apartamento apartamento = request.getApartamento();
			apartamento = apartamentoRepository.save(apartamento);
			throwExceptionIfIdApartamentoIsNotPresent(apartamento);
			statuscode = "201";
			return response(apartamento, statuscode, "Apartamento cadastrado com sucesso!");

		} catch (Exception e) {
			return response(statuscode, e.getMessage());
		}

	}

	public DTO carregarDadosApartamento(Long id) throws Exception {
		try {
			Apartamento apartamento = readApartamentoById(id);
			return response(apartamento, "200", "Apartamento encontrado com sucesso!");
		} catch (Exception e) {
			return response(statuscode, e.getMessage());
		}
	}

	public DTO atualizarDadosApartamento(Long id, DTO request) {
		try {
			Apartamento apartamento = readApartamentoById(id);
			apartamento.setBlocoApto(request.getApartamento().getBlocoApto());
			apartamento.setNumeroApto(request.getApartamento().getNumeroApto());
			apartamento.setVagaGaragem(request.getApartamento().getVagaGaragem());
			apartamento.setMorador(request.getApartamento().getMorador());
			apartamentoRepository.save(apartamento);
			statuscode = "200";
			return response(apartamento, statuscode, "Apartamento atualizado com sucesso!");

		} catch (Exception e) {
			return response(statuscode, e.getMessage());
		}

	}

	public DTO deletarApartamento(Long id) throws Exception {
		try {
			Apartamento apartamento = readApartamentoById(id);
			throwExceptionIfResidentIsPresent(apartamento);
			apartamentoRepository.deleteById(id);
			statuscode = "200";
			return response(statuscode, "Apartamento deletado com sucesso!");
		} catch (Exception e) {
			return response(statuscode, e.getMessage());
		}
	}

	void throwExceptionIfResidentIsPresent(Apartamento apartamento) throws Exception {
		if (!(apartamento.getMorador() == null)) {
			statuscode = "401";
			throw new Exception("Alerta! Há um morador cadastrado para esse apartamento!");
		}
	}

	public Apartamento readApartamentoById(Long id) throws Exception {
		try {
			return apartamentoRepository.findById(id).get();
		} catch (Exception e) {
			statuscode = "404";
			throw new Exception("Verifique ID do apartamento!");
		}
	}

	void throwExceptionIfNumeroApartamentoIsNotPresent(DTO request) throws Exception {
		if (request.getApartamento().getNumeroApto() == null) {
			statuscode = "400";
			throw new Exception("Necessário inserir número do apartamento!");
		}
	}

	void throwExceptionIfIdApartamentoIsNotPresent(Apartamento apartamento) throws Exception {
		if (apartamento.getId() == null) {
			statuscode = "500";
			throw new Exception("Ocorreu uma falha. Tente novamente mais tarde!");
		}
	}

	DTO response(Apartamento apartamento, String statuscode, String mensagem) {
		return DTO.builder().apartamento(apartamento).statusCode(statuscode).mensagem(mensagem).build();
	}
	DTO response(List<Apartamento> listaApartamento, String statuscode, String mensagem) {
		return DTO.builder().listaApartamentos(listaApartamento).statusCode(statuscode).mensagem(mensagem).build();
	}

	DTO response(String statuscode, String mensagem) {
		return DTO.builder().statusCode(statuscode).mensagem(mensagem).build();
	}

	public DTO listarApartamentos() {
		try {
			List<Apartamento> listaApartamentos = new ArrayList<Apartamento>();
			listaApartamentos = apartamentoRepository.findAll();
			throwExceptionIfApartamentListIsEmpty(listaApartamentos);
			statuscode = "200";
			return response(listaApartamentos, statuscode, "Lista de moradores encontrada com sucesso!");
		} catch (Exception e) {
			return response(statuscode, e.getMessage());
		} 
	}
	
	
	void throwExceptionIfApartamentListIsEmpty(List<Apartamento> listaApartamentos) throws Exception {
		if(listaApartamentos.isEmpty()) {
			statuscode = "200";
			throw new Exception("Lista de apartamentos está vazia!");
		}
	}

}
