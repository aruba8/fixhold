package ca.fixhold.validator;

import ca.fixhold.form.UserProfileForm;
import ca.fixhold.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserProfileFormValidator implements Validator {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return UserProfileForm.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserProfileForm userProfileForm = (UserProfileForm) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "NotEmpty.userProfileForm.firstName");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "NotEmpty.userProfileForm.lastName");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty");

        if (userProfileForm.getFirstName().length() < 2 || userProfileForm.getFirstName().length() > 50) {
            errors.rejectValue("firstName", "Size.userProfileForm.firstName");
        }
        if (userProfileForm.getLastName().length() < 2 || userProfileForm.getLastName().length() > 50) {
            errors.rejectValue("firstName", "Size.userProfileForm.lastName");
        }
    }
}
