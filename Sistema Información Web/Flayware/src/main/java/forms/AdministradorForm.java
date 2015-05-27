package forms;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

public class AdministradorForm {

	private String name;
	private String surname;
	private String email;
	private String password;
	private String username;
	
	private int id;
	private int version;
	
	private boolean TOSAccepted; 
	private String repeatPassword;
	
	
	public AdministradorForm() {
		super();
	}
	
	
	public boolean isTOSAccepted() {
		return TOSAccepted;
	}


	public void setTOSAccepted(boolean tOSAccepted) {
		TOSAccepted = tOSAccepted;
	}

	@Size(min = 5, max = 32)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Size(min = 5, max = 32)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@NotBlank
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	@NotBlank
	public String getSurname() {
		return surname;
	}


	public void setSurname(String surname) {
		this.surname = surname;
	}


	@NotBlank
	@Email
	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getVersion() {
		return version;
	}

	
	public void setVersion(int version) {
		this.version = version;
	}

	@NotBlank
	public String getRepeatPassword() {
		return repeatPassword;
	}

	
	public void setRepeatPassword(String repeatPassword) {
		this.repeatPassword = repeatPassword;
	}
	
	
}
