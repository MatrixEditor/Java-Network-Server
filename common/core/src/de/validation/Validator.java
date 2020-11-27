package de.validation;

/**
 * Validator in validation (Java-Network-Server)
 * <p>
 * Class description...
 *
 * @author
 * @version ...
 * @date 26.11.2020
 **/
public abstract class Validator {

    protected ValidationResult result;

    public abstract ValidationResult validate();
}
