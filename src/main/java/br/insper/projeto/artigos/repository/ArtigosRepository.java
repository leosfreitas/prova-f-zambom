package br.insper.projeto.artigos.repository;

import br.insper.projeto.artigos.model.Artigos;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;

@Repository
public interface ArtigosRepository extends MongoRepository<Artigos, String> {
}
