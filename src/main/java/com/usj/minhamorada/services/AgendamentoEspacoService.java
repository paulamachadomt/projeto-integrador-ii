package com.usj.minhamorada.services;

import com.usj.minhamorada.models.AgendamentoEspaco;
import com.usj.minhamorada.models.Apartamento;
import com.usj.minhamorada.models.Espaco;
import com.usj.minhamorada.models.Morador;
import com.usj.minhamorada.models.dto.DTO;
import com.usj.minhamorada.repositories.AgendamentoEspacoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Service
public class AgendamentoEspacoService {

    @Autowired
    private AgendamentoEspacoRepository agendamentoEspacoRepository;

    @Autowired
    private EspacoService espacoService;

    @Autowired
    private MoradorService moradorService;

    private String statuscode = "200";

    public DTO cadastrarAgendamentoEspaco(DTO request) {
        try {
            AgendamentoEspaco agendamentoEspaco = request.getAgendamentoEspaco();
            Espaco espaco = espacoService.readEspacoById(request.getAgendamentoEspaco().getEspaco().getId());
            Morador morador = moradorService.readMoradorById(request.getAgendamentoEspaco().getMorador().getId());
            //throwExceptionIfIdMoradorIsNotPresent(morador);
            agendamentoEspaco.setEspaco(espaco);
            agendamentoEspaco.setMorador(morador);
            agendamentoEspaco = agendamentoEspacoRepository.save(agendamentoEspaco);
            statuscode = "200";
            return response(agendamentoEspaco);
        }
        catch (Exception e) {
            statuscode = "400";
            return response(statuscode, e.getMessage());
        }
    }

    public DTO carregarDadosAgendamentoEspaco(Long id) {
        try {
            AgendamentoEspaco agendamentoEspaco = readAgendamentoEspacoById(id);
            agendamentoEspaco.setEspaco(espacoService.readEspacoById(agendamentoEspaco.getEspaco().getId()));
            return response(agendamentoEspaco);
        }
        catch (Exception e) {
            return response(e.getMessage());
        }
    }

    public DTO atualizarDadosAgendamentoEspaco(Long id, DTO request) {
        try {
            AgendamentoEspaco agendamentoEspaco = readAgendamentoEspacoById(id);
            agendamentoEspaco.setEspaco(request.getEspaco());
            agendamentoEspaco.setDataHoraAgendamento(request.getAgendamentoEspaco().getDataHoraAgendamento());
            agendamentoEspacoRepository.save(agendamentoEspaco);
            return response(agendamentoEspaco);
        } catch (Exception e) {
            return response(e.getMessage());
        }
    }

    public DTO deletarAgendamentoEspaco(Long id) {
       try {
           agendamentoEspacoRepository.deleteById(id);
           return response("Agendamento deletado!");
       }
       catch (Exception e) {
           return response(e.getMessage());
       }

    }

    public DTO listarAgendamentosEspacos() {
        try {
            List<AgendamentoEspaco> listaAgendamentosEspacos = new ArrayList<>();
            listaAgendamentosEspacos = agendamentoEspacoRepository.findAll();
            return response(listaAgendamentosEspacos);
        }
        catch (Exception e) {
            return response(e.getMessage());
        }
    }



    private AgendamentoEspaco readAgendamentoEspacoById(Long id) throws Exception {
        try {
            return agendamentoEspacoRepository.findById(id).get();
        } catch (Exception e) {
            throw new Exception("Verifique id do agendamento!", e);
        }
    }

    void throwExceptionIfIdMoradorIsNotPresent(Morador morador) throws Exception {
        if (morador.getId() == null) {
            throw new Exception("Morador não encontrado! Verifique e tente novamente!");
        }
    }

    DTO response(AgendamentoEspaco agendamentoEspaco) { return DTO.builder().agendamentoEspaco(agendamentoEspaco).build(); }

    DTO response(String mensagem) { return DTO.builder().mensagem(mensagem).build(); }

    DTO response(String statuscode, String mensagem) { return DTO.builder().statusCode(statuscode).mensagem(mensagem).build(); }

    DTO response(List<AgendamentoEspaco> listaAgendamentosEspacos) {
        return DTO.builder().listaAgendamentosEspacos(listaAgendamentosEspacos).build();
    }

}
