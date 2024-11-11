package br.insper.projeto.historico.repository;

import br.insper.projeto.historico.model.Historico;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;


import java.util.List;

@Repository
public interface HistoricoRepository extends MongoRepository<Historico, Integer> {
    List<Historico> findByEmail(String email);

}
