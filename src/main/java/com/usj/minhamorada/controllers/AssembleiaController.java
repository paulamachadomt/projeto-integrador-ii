package com.usj.minhamorada.controllers;

import com.usj.minhamorada.models.dto.DTO;
import com.usj.minhamorada.services.AssembleiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/assembleia")
public class AssembleiaController {

    @Autowired
    AssembleiaService assembleiaService;

    @CrossOrigin
    @PostMapping
    public DTO create (@RequestBody DTO requestDTO) {
        return assembleiaService.cadastrarAssembleia(requestDTO);
    }

    @CrossOrigin
    @GetMapping(value = "/{id}")
    public DTO read (@PathVariable Long id) {
        return assembleiaService.carregarDadosAssembleia(id);
    }

    @CrossOrigin
    @PutMapping(value = "/{id}")
    public DTO update(@PathVariable Long id, @RequestBody DTO requestDTO) {
        return assembleiaService.atualizarAssembleia(id, requestDTO);
    }

    @CrossOrigin
    @DeleteMapping(value = "/{id}")
    public DTO delete(@PathVariable Long id) {
        return assembleiaService.deletarAssembleia(id);
    }

    @CrossOrigin
    @GetMapping("/")
    public DTO findAll() throws Exception {
        return assembleiaService.listarAssembleias();
    }

}
