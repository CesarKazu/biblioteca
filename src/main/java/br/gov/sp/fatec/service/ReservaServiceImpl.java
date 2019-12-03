package br.gov.sp.fatec.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.gov.sp.fatec.model.Reserva;
import br.gov.sp.fatec.repository.ReservaRepository;

@Service("reservaService")
@Transactional
public class ReservaServiceImpl implements ReservaService {

	@Autowired
	private ReservaRepository reservaRepo;

	public void setReservaRepo(ReservaRepository reservaRepo) {
		this.reservaRepo = reservaRepo;
	}

	@Override
	@PreAuthorize("isAuthenticated()")
	public Reserva cadastrar(Reserva reserva) {
		return reservaRepo.save(reserva);
	}

	@Override
	@PreAuthorize("isAuthenticated()")
	public boolean excluir(Long id) {
		Optional<Reserva> reserva =  reservaRepo.findById(id);
		if(reserva.isPresent()) {
			reservaRepo.delete(reserva.get());
			return true;
		}
		return false;
	}

	@Override
	@PreAuthorize("isAuthenticated()")
	public List<Reserva> findByUsuarioNome(String nome) {
		List<Reserva> retorno = new ArrayList<Reserva>();
		for(Reserva exemplar: reservaRepo.findByUsuarioNome(nome)) {
			retorno.add(exemplar);
		}
		return retorno;
	}
	
	@Override
	@PreAuthorize("isAuthenticated()")
	public List<Reserva> findByExemplarLivroNome(String nome) {
		List<Reserva> retorno = new ArrayList<Reserva>();
		for(Reserva exemplar: reservaRepo.findByExemplarLivroNomeContainsIgnoreCase(nome)) {
			retorno.add(exemplar);
		}
		return retorno;
	}
}