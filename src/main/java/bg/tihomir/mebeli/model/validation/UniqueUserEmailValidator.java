package bg.tihomir.mebeli.model.validation;

import bg.tihomir.mebeli.repository.UserRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUserEmailValidator implements ConstraintValidator<UniqueUserEmail, String> {

    private final UserRepository userRepository;

    public UniqueUserEmailValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void initialize(UniqueUserEmail constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {


        return userRepository
                .findByEmail(value)
                .isEmpty();


    }
}
