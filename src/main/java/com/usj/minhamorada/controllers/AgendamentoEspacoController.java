package com.usj.minhamorada.controllers;

import com.usj.minhamorada.models.dto.DTO;
import com.usj.minhamorada.services.AgendamentoEspacoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/agendamento")
public class AgendamentoEspacoController {

    @Autowired
    AgendamentoEspacoService agendamentoEspacoService;

    @CrossOrigin
    @PostMapping
    public DTO create (@RequestBody DTO requestDTO) {
        return agendamentoEspacoService.cadastrarAgendamentoEspaco(requestDTO);
    }

    @CrossOrigin
    @GetMapping(value="/{id}")
    public DTO read(@PathVariable Long id) throws Exception {
        return agendamentoEspacoService.carregarDadosAgendamentoEspaco(id);
    }

    @CrossOrigin
    @PutMapping(value="/{id}")
    public DTO update(@PathVariable Long id, @RequestBody DTO request) throws Exception {
        return agendamentoEspacoService.atualizarDadosAgendamentoEspaco(id, request);
    }

    @CrossOrigin
    @DeleteMapping(value="/{id}")
    public DTO delete(@PathVariable Long id) throws Exception {
        return agendamentoEspacoService.deletarAgendamentoEspaco(id);
    }

    @CrossOrigin
    @GetMapping(value="/")
    public DTO findAll() {
        return agendamentoEspacoService.listarAgendamentosEspacos();
    }


}
