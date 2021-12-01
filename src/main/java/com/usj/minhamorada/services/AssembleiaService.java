package com.usj.minhamorada.services;

import com.usj.minhamorada.models.Assembleia;
import com.usj.minhamorada.models.Espaco;
import com.usj.minhamorada.models.dto.DTO;
import com.usj.minhamorada.repositories.AssembleiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssembleiaService {

    @Autowired
    private AssembleiaRepository assembleiaRepository;


    public DTO cadastrarAssembleia(DTO request) {
        try {
            Assembleia assembleia = request.getAssembleia();
            assembleia = assembleiaRepository.save(assembleia);
            return response(assembleia);
        }
        catch (Exception e) {
            return response(e.getMessage());
        }
    }

    public DTO carregarDadosAssembleia(Long id) {
        try {
            Assembleia assembleia = readAssembleiaById(id);
            return response(assembleia);
        }
        catch (Exception e) {
            return response(e.getMessage());
        }
    }

    public DTO atualizarAssembleia(Long id, DTO request) {
        try {
            Assembleia assembleia = readAssembleiaById(id);
            assembleia.setTitulo(request.getAssembleia().getTitulo());
            assembleia.setAta(request.getAssembleia().getAta());
            assembleia.setDataHora(request.getAssembleia().getDataHora());
            assembleiaRepository.save(assembleia);
            return response(assembleia);
        }
        catch (Exception e) {
            return response(e.getMessage());
        }
    }

    public DTO deletarAssembleia(Long id) {
        try {
            assembleiaRepository.deleteById(id);
            return response("Assembleia deletada!");
        }
        catch (Exception e) {
            return response(e.getMessage());
        }
    }

    public DTO listarAssembleias() {
        try {
            List<Assembleia> listaAssembleias = assembleiaRepository.findAll();
            return response(listaAssembleias);
        }
        catch (Exception e) {
            return response(e.getMessage());
        }
    }

    private Assembleia readAssembleiaById(Long id) throws Exception {
        try {
            return assembleiaRepository.findById(id).get();
        }
        catch (Exception e) {
            throw new Exception("Verifique id da assembleia!");
        }
    }

    DTO response(String mensagem) {
        return DTO.builder().mensagem(mensagem).build();
    }

    DTO response(Assembleia assembleia) {
        return DTO.builder().assembleia(assembleia).build();
    }

    DTO response(List<Assembleia> listaAssembleias) {
        return DTO.builder().listaAssembleias(listaAssembleias).build();
    }

}
