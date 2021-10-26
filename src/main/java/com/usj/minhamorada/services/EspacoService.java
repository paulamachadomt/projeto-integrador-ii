package com.usj.minhamorada.services;

import com.usj.minhamorada.models.Espaco;
import com.usj.minhamorada.models.dto.DTO;
import com.usj.minhamorada.repositories.EspacoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EspacoService {

    @Autowired
    private EspacoRepository espacoRepository;


    public DTO cadastrarEspaco(DTO request) {
        try {
            Espaco espaco = request.getEspaco();
            espaco = espacoRepository.save(espaco);
            return response(espaco);
        }
        catch (Exception e) {
            return response(e.getMessage());
        }
    }

    public DTO carregarDadosEspaco(Long id) {
        try {
            Espaco espaco = readEspacoById(id);
            return response(espaco);
        }
        catch (Exception e) {
            return response(e.getMessage());
        }
    }

    public DTO atualizarEspaco(Long id, DTO request) {
        try {
            Espaco espaco = readEspacoById(id);
            espaco.setNomeEspaco(request.getEspaco().getNomeEspaco());
            espacoRepository.save(espaco);
            return response(espaco);
        }
        catch (Exception e) {
            return response(e.getMessage());
        }
    }

    public DTO deletarEspaco(Long id) {
        try {
            espacoRepository.deleteById(id);
            return response("Espaço deletado!");
        }
        catch (Exception e) {
            return response(e.getMessage());
        }
    }

    public DTO listarEspacos() {
        try {
            List<Espaco> listaEspacos = espacoRepository.findAll();
            return response(listaEspacos);
        }
        catch (Exception e) {
            return response(e.getMessage());
        }
    }

    public Espaco readEspacoById(Long id) throws Exception {
        try {
            return espacoRepository.findById(id).get();
        }
        catch (Exception e) {
            throw new Exception("Verifique id do espaço!");
        }
    }

    DTO response(String mensagem) {
        return DTO.builder().mensagem(mensagem).build();
    }

    DTO response(Espaco espaco) {
        return DTO.builder().espaco(espaco).build();
    }

    DTO response(List<Espaco> listaEspacos) {
        return DTO.builder().listaEspacos(listaEspacos).build();
    }

}
