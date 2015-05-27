package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.AdministradorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Administrador;
import domain.Curso;
import domain.Reto;
import forms.AdministradorForm;

@Transactional
@Service
public class AdministradorService extends ActorService {

	// Managed repository-----------------------

	@Autowired
	private AdministradorRepository administradorRepository;

	// Supporting Services-----------------------
	private Md5PasswordEncoder encoder;

	// Constructors --------------------------

	public AdministradorService() {
		super();
	}


	public Administrador findByPrincipal() {

		Administrador result;
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		result = findByUserAccount(userAccount);
		Assert.notNull(result);

		return result;
	}

	public Administrador findByUserAccount(UserAccount userAccount) {

		Assert.notNull(userAccount);
		Administrador result;
		result = administradorRepository.findByUserAccountId(userAccount
				.getId());

		return result;
	}


	public Administrador findOne(int administradorId) {
		return administradorRepository.findOne(administradorId);
	}

	
	public Administrador create() {
		Administrador admin = new Administrador();
		
		Authority auth= new Authority();
		Collection<Authority> lAuthorty= new ArrayList<Authority>();
		UserAccount usserA= new UserAccount();
		
		auth.setAuthority("ADMINISTRADOR");
		lAuthorty.add(auth);
		usserA.setAuthorities(lAuthorty);
		admin.setUserAccount(usserA);
		admin.setCursos(new ArrayList<Curso>());
		admin.setRetos(new ArrayList<Reto>());
		
		return admin;
	}


	public void save(Administrador administrador) {
		Assert.notNull(administrador);
		
		encoder = new Md5PasswordEncoder();
		
		String oldPassword = administrador.getUserAccount().getPassword();
		String newPassword=encoder.encodePassword(oldPassword, null);
		
		administrador.getUserAccount().setPassword(newPassword);
		
		administradorRepository.save(administrador);
	}


	
	public Administrador reconstruct(AdministradorForm administradorForm) {
		Administrador administrador = this.create();
		
		administrador.setEmail(administradorForm.getEmail());
		administrador.setId(administradorForm.getId());
		administrador.setName(administradorForm.getName());
		administrador.setSurname(administradorForm.getSurname());
		administrador.setVersion(administradorForm.getVersion());
		administrador.getUserAccount().setPassword(administradorForm.getPassword());
		administrador.getUserAccount().setUsername(administradorForm.getUsername());
			
		this.checkSamePasswords(administradorForm.getPassword(), administradorForm.getRepeatPassword());
		this.TOSAccepted(administradorForm.isTOSAccepted());
		
		return administrador;
	}
	
	public AdministradorForm constructNew(){
		
		Administrador administrador = this.create();
		
		AdministradorForm administradorForm = new AdministradorForm();
		
		administradorForm.setEmail(administrador.getEmail());
		administradorForm.setId(administrador.getId());
		administradorForm.setName(administrador.getName());
		administradorForm.setSurname(administrador.getSurname());
		administradorForm.setVersion(administrador.getVersion());
		administradorForm.setPassword(administrador.getUserAccount().getPassword());
		administradorForm.setUsername(administrador.getUserAccount().getUsername());
		
		return administradorForm;
		
	}
	
	
	//Assertions
			private void checkSamePasswords(String passwordOne, String passwordTwo){
				Assert.isTrue(passwordOne.equals(passwordTwo),"Password diferentes");
			}
			
			private void TOSAccepted(boolean tos){
				Assert.isTrue(tos,"TOS no marcada");
			}


			public Collection<Administrador> findAll() {
				// TODO Auto-generated method stub
				return administradorRepository.findAll();
			}
	
}
