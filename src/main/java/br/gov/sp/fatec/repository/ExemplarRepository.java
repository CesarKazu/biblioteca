package br.gov.sp.fatec.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import br.gov.sp.fatec.model.Exemplar;

public interface ExemplarRepository extends CrudRepository<Exemplar, Long> {

	public List<Exemplar> findByReservadoTrue();

	public List<Exemplar> findByReservadoFalse();
}