package br.insper.projeto.artigos.service;

import br.insper.projeto.artigos.dto.ArtigosDTO;
import br.insper.projeto.artigos.dto.PlanoUsuarioDTO;

import br.insper.projeto.artigos.model.Artigos;
import br.insper.projeto.artigos.repository.ArtigosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class ArtigosService {

    @Autowired
    private ArtigosRepository artigosRepository;

    private PlanoUsuarioDTO achaUsuario(String jwtToken) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", jwtToken);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        String url = "http://184.72.80.215/usuario/validate";

        try {
            ResponseEntity<PlanoUsuarioDTO> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    PlanoUsuarioDTO.class
            );

            if (response.getStatusCode().is2xxSuccessful()) {
                PlanoUsuarioDTO planoUsuario = response.getBody();
                return planoUsuario;
            }
            else {
                throw new RuntimeException("Usuário não encontrado. Status code: " + response.getStatusCode());
            }
        } catch (HttpClientErrorException e) {
            throw new RuntimeException("Erro de cliente ao verificar o papel do usuário: " + e.getStatusCode(), e);
        } catch (HttpServerErrorException e) {
            throw new RuntimeException("Erro de servidor ao verificar o papel do usuário: " + e.getStatusCode(), e);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao verificar papel do usuário", e);
        }
    }

    public Artigos adicionarArtigo(String jwtToken, ArtigosDTO artigosDTO) {
        String email = achaUsuario(jwtToken) != null ? achaUsuario(jwtToken).getEmail() : null;

        if (email == null) {
            throw new IllegalArgumentException("Token JWT inválido ou usuário não encontrado.");
        }

        String papel = achaUsuario(jwtToken).getPapel();
        if (!"ADMIN".equals(papel)) {
            throw new IllegalArgumentException("Usuário sem permissão para adicionar artigos.");
        }

        Artigos artigo = new Artigos();

        artigo.setAutor(achaUsuario(jwtToken).getEmail());
        artigo.setTitulo(artigosDTO.getTitulo());
        artigo.setConteudo(artigosDTO.getConteudo());

        artigosRepository.save(artigo);
        return artigo;
    }

    public Optional<Artigos> deleteArtigo(String jwtToken, String id) {
        String email = achaUsuario(jwtToken) != null ? achaUsuario(jwtToken).getEmail() : null;

        if (email == null) {
            throw new IllegalArgumentException("Token JWT inválido ou usuário não encontrado.");
        }

        String papel = achaUsuario(jwtToken).getPapel();
        if (!"ADMIN".equals(papel)) {
            throw new IllegalArgumentException("Usuário sem permissão para deletar artigos.");
        }

        Optional<Artigos> artigo = artigosRepository.findById(id);
        if (artigo.isEmpty()) {
            throw new IllegalArgumentException("Artigo não encontrado.");
        }

        artigosRepository.deleteById(id);
        return artigo;
    }

    public Optional<Artigos> getArtigo(String jwtToken, String id) {
        String email = achaUsuario(jwtToken) != null ? achaUsuario(jwtToken).getEmail() : null;

        if (email == null) {
            throw new IllegalArgumentException("Token JWT inválido ou usuário não encontrado.");
        }

        return artigosRepository.findById(id);
    }

    public List<Artigos> listarArtigos(String jwtToken) {
        String email = achaUsuario(jwtToken) != null ? achaUsuario(jwtToken).getEmail() : null;

        if (email == null) {
            throw new IllegalArgumentException("Token JWT inválido ou usuário não encontrado.");
        }

        return artigosRepository.findAll();
    }



}
