package org.irn.store.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

public class AuthCredentials {
	@NotNull
    @NotBlank
    @Email(message = "Please provide a valid email")
    private String email;
	
	@Length(min = 8, max = 20, message = "The password must be more the 8 and less then 20 charachters long")
    @Pattern(regexp = "[a-zA-Z0-9]*", message = "Password can contain only letters and numbers")
    private String password;
    
	public AuthCredentials(String email, String password) {

		this.email = email;
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
