package com.usj.minhamorada.controllers;

import com.usj.minhamorada.models.dto.DTO;
import com.usj.minhamorada.services.EspacoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/espacos")
public class EspacoController {

    @Autowired
    EspacoService espacoService;

    @CrossOrigin
    @PostMapping
    public DTO create (@RequestBody DTO requestDTO) {
        return espacoService.cadastrarEspaco(requestDTO);
    }

    @CrossOrigin
    @GetMapping(value = "/{id}")
    public DTO read (@PathVariable Long id) throws Exception {
        return espacoService.carregarDadosEspaco(id);
    }

    @CrossOrigin
    @PutMapping(value = "/{id}")
    public DTO update(@PathVariable Long id, @RequestBody DTO requestDTO) {
        return espacoService.atualizarEspaco(id, requestDTO);
    }

    @CrossOrigin
    @DeleteMapping(value = "/{id}")
    public DTO delete(@PathVariable Long id) {
        return espacoService.deletarEspaco(id);
    }

    @CrossOrigin
    @GetMapping("/")
    public DTO findAll() throws Exception {
        return espacoService.listarEspacos();
    }

}
