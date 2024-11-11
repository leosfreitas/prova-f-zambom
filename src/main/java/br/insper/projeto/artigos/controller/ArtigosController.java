package br.insper.projeto.artigos.controller;

import br.insper.projeto.artigos.dto.ArtigosDTO;
import br.insper.projeto.artigos.service.ArtigosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/artigo")
public class ArtigosController {

    @Autowired
    private ArtigosService artigosService;

    @PostMapping
    public ResponseEntity<?> adicionarArtigo(@RequestHeader("Authorization") String jwtToken, @RequestBody ArtigosDTO artigosDTO) {
        var h = artigosService.adicionarArtigo(jwtToken, artigosDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(h);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteArtigo(@RequestHeader("Authorization") String jwtToken, @PathVariable String id) {
        artigosService.deleteArtigo(jwtToken, id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<?> listarArtigos(@RequestHeader("Authorization") String jwtToken) {
        var artigos = artigosService.listarArtigos(jwtToken);
        return ResponseEntity.ok(artigos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarArtigo(@RequestHeader("Authorization") String jwtToken, @PathVariable String id) {
        var artigo = artigosService.getArtigo(jwtToken, id);
        return ResponseEntity.ok(artigo);
    }
}
