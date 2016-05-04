package validadores;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidadorPreco implements ConstraintValidator<ValidaPreco, Double>
{
    @Override
    public void initialize(ValidaPreco a)
    {
        
    }

    @Override
    public boolean isValid(Double valor, ConstraintValidatorContext cvc)
    {
        boolean valido = false;
        
        if(valor > 0)
            valido = true;
        
        return valido;
    }
    
}
