package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.ActorRepository;
import security.Authority;
import security.LoginService;
import domain.Actor;

@Transactional
@Service
public class ActorService {
	
	@Autowired
	private ActorRepository actorRepository;

	// Constructors --------------------------

	public ActorService() {
		super();
	}

	// Other business methods ----------------
	public boolean AmIMySelf(int userId) {
		return LoginService.getPrincipal().getId() == userId;
	}

//	public boolean IAmAParticipant() {
//		return checkRole(Authority.PARTICIPANT);
//	}
//
//	public boolean IAmAnAdmin() {
//		return checkRole(Authority.ADMINISTRATOR);
//	}

	// TODO
	public boolean AmIAGuest() {
		return false;
	}

	private boolean checkRole(String role) {
		Collection<Authority> authorities = LoginService.getPrincipal()
				.getAuthorities();

		boolean res = false;

		for (Authority auth : authorities)
			res = res || auth.getAuthority().toUpperCase().compareTo(role) == 0;

		return res;
	}

	public Collection<Actor> findActorByUsername(String username){
		return actorRepository.findActorByUsername(username);
	}
	
}
