package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.JugadorRepository;
import security.LoginService;
import security.UserAccount;
import domain.Jugador;

@Transactional
@Service
public class JugadorService extends ActorService {

	// Managed repository-----------------------

	@Autowired
	private JugadorRepository jugadorRepository;

	// Supporting Services-----------------------

	// Constructors --------------------------

	public JugadorService() {
		super();
	}


	public Jugador findByPrincipal() {

		Jugador result;
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		result = findByUserAccount(userAccount);
		Assert.notNull(result);

		return result;
	}

	public Jugador findByUserAccount(UserAccount userAccount) {

		Assert.notNull(userAccount);
		Jugador result;
		result = jugadorRepository.findByUserAccountId(userAccount
				.getId());

		return result;
	}


	public Collection<Jugador> findAll() {
		// TODO Auto-generated method stub
		return jugadorRepository.findAll();
	}
	
	
	
	public void disable(int jugadorId) {
		Jugador jugador = this.findOne(jugadorId);
		jugador.getUserAccount().setActive(false);
		
		this.jugadorRepository.save(jugador);
	}


	public void enable(int jugadorId) {
		Jugador jugador = this.findOne(jugadorId);
		jugador.getUserAccount().setActive(true);
		
		this.jugadorRepository.save(jugador);
	}

	public Jugador findOne(int jugadorId) {
		// TODO Auto-generated method stub
		return jugadorRepository.findOne(jugadorId);
	}
	
	
	
	public Collection<Jugador> findBannerJugadores(){
		return this.jugadorRepository.findBannerJugadores();
	}
	



}
