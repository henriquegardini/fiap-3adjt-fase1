package br.com.fiap.techchallenge.controller;

import br.com.fiap.techchallenge.dto.UsuarioDTO;
import br.com.fiap.techchallenge.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<Page<UsuarioDTO>> findAll(@PageableDefault(size = 10, page = 0, sort = "nome") Pageable pageable) {
        Page<UsuarioDTO> usuarioDTO = usuarioService.findAll(pageable);
        return ResponseEntity.ok(usuarioDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> findById(@PathVariable Long id) {
        UsuarioDTO usuarioDTO = usuarioService.findById(id);
        return ResponseEntity.ok(usuarioDTO);
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> save(@Valid @RequestBody UsuarioDTO usuarioDTO) {
        usuarioDTO = usuarioService.save(usuarioDTO);
        return new ResponseEntity<>(usuarioDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> updateById(@PathVariable Long id, @RequestBody UsuarioDTO usuarioDTO) {
        usuarioDTO = usuarioService.updateById(id, usuarioDTO);
        return ResponseEntity.ok(usuarioDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        usuarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
