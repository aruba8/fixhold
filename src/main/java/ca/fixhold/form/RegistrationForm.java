package ca.fixhold.form;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

public class RegistrationForm {

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    @Size(min = 6, max = 50)
    private String password;

    @NotEmpty
    @Size(min = 6, max = 50)
    private String passwordConfirm;

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

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }
}
