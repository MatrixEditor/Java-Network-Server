package de.jns.core.validation;

import java.util.ArrayList;

public class ValidationResult {

    private static final int STOP = 0;
    private static final int CONTINUE = 1;
    private final ArrayList<ValidationError> errors = new ArrayList<>();

    public void add(ValidationError error) {
        this.errors.add(error);
    }

    public int isValidated() {
        if (hasErrors()) {
            return STOP;
        } else return CONTINUE;
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    public boolean hasError(ValidationError error) {
        return this.errors.contains(error);
    }

    public ValidationResult addAll(ValidationResult result) {
        this.errors.addAll(result.errors);
        return this;
    }

}
