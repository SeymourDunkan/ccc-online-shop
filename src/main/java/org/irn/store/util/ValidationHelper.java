package org.irn.store.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class ValidationHelper<E> {
	
	public List<String> validate(E entity) {

        List<String> errors = new ArrayList<>();
        
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<E>> cvs = validator.validate(entity);

        if (!cvs.isEmpty()) {

            for (ConstraintViolation<E> cv : cvs) {
                errors.add(cv.getMessage());
            }
        }
        
        return errors;
    }
}
