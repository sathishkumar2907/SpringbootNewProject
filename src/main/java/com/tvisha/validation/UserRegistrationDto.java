package com.tvisha.validation;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;




     @FieldMatch.List({
    @FieldMatch(first = "password", second = "confirmPassword", message = "The password fields must match"),
    @FieldMatch(first = "email", second = "confirmEmail", message = "The email fields must match")
})
/*@Entity
@Table(name = "user")*/


public class UserRegistrationDto{

	 @NotEmpty
	    private String name;


	    @NotEmpty
	    private String email;

	    @Email
	    @NotEmpty
	    private String confirmEmail;

	    @AssertTrue
	    private Boolean terms;	 

	    public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getEmail() {
	        return email;
	    }

	    public void setEmail(String email) {
	        this.email = email;
	    }

	    public String getConfirmEmail() {
	        return confirmEmail;
	    }

	    public void setConfirmEmail(String confirmEmail) {
	        this.confirmEmail = confirmEmail;
	    }

	    public Boolean getTerms() {
	        return terms;
	    }

	    public void setTerms(Boolean terms) {
	        this.terms = terms;
	    }    
	    
	    
	
}
