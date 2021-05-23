package org.irn.store.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public class User {
    private Integer id;
    
    @NotNull
    @NotBlank
    @Email(message = "Please provide a valid email")
    private String email;
    

    @Length(min = 8, max = 20, message = "The password must be more the 8 and less then 20 charachters long")
    @Pattern(regexp = "[a-zA-Z0-9]*", message = "Password can contain only letters and numbers")
    private String password;
    
    @NotBlank
    @NotNull
    @Pattern(regexp = "[A-Z][a-z]*", message = "First Name should start from a capital letter and can contain only letters")
    private String firstName;
    
    @NotBlank
    @NotNull
    @Pattern(regexp = "[A-Z][a-z]*", message = "Last Name should start from a capital letter and can contain only letters")
    private String lastName;
    
    @NotBlank
    @NotNull
    @Pattern(regexp = "customer|admin", message = "Role should either be customer or admin")
    private String role;
    
    @Pattern(regexp = "YES|NO", message = "Blocked status of the user should either be YES or NO")
    private String blocked;
    
    public User(Integer id, String email, String password, String firstName, String lastName,
			String role, String blocked) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;
		this.blocked = blocked;
	}
    
	public User(Integer id, String email, String password, String firstName, String lastName,
			String role) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;
		this.blocked = "NO";
	}
	
	public User(String email, String password, String firstName, String lastName,
			String role) {
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;
		this.blocked = "NO";
	}
    
	public User() {

	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getBlocked() {
		return blocked;
	}
	public void setBlocked(String blocked) {
		this.blocked = blocked;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", role=" + role + ", blocked=" + blocked + "]";
	}
    
	
    
    
}
