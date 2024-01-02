package br.com.fiap.techchallenge.controller;

import br.com.fiap.techchallenge.dto.ProdutoDTO;
import br.com.fiap.techchallenge.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<Collection<ProdutoDTO>> findAll() {
        var produtoDTO = produtoService.findAll();
        return ResponseEntity.ok(produtoDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDTO> findById(@PathVariable UUID id) {
        var produtoDTO = produtoService.findById(id);
        return ResponseEntity.ok(produtoDTO);
    }

    @PostMapping
    public ResponseEntity<ProdutoDTO> save(@RequestBody ProdutoDTO produtoDTO) {
        produtoDTO = produtoService.save(produtoDTO);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(produtoDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDTO> updateById(@PathVariable UUID id, @RequestBody ProdutoDTO produtoDTO) {
        produtoDTO = produtoService.updateById(id, produtoDTO);
        return ResponseEntity.ok(produtoDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
        produtoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
