package validadores;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidadorQuantidade implements ConstraintValidator<ValidaQuantidade, Integer>
{
    @Override
    public void initialize(ValidaQuantidade a)
    {
        
    }

    @Override
    public boolean isValid(Integer valor, ConstraintValidatorContext cvc)
    {
        boolean valido = false;
        
        if(valor > 0)
            valido = true;
        
        return valido;
    }
}
