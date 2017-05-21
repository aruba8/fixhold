package ca.fixhold.validator;

import ca.fixhold.form.UserProfileForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserProfileFormValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return UserProfileForm.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserProfileForm userProfileForm = (UserProfileForm) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty");

    }
}
