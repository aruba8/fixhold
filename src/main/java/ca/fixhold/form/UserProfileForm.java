package ca.fixhold.form;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserProfileForm {

    @NotNull
    @Size(min = 2, max = 50, message = "First Name size must be between 2 and 50")
    private String firstName;
    @NotNull
    @Size(min = 2, max = 50, message = "Last Name size must be between 2 and 50")
    private String lastName;
    @NotNull
    @Email
    private String email;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
