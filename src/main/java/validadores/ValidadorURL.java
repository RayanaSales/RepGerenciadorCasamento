package validadores;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class ValidadorURL implements ConstraintValidator<ValidaURL, String>
{
    @Override
    public void initialize(ValidaURL a)
    {
    }

    @Override
    public boolean isValid(String link, ConstraintValidatorContext cvc)
    {
        boolean valido = false;
        
        if(link.startsWith("www.") && link.matches("a-zA-Z0-9_-/") && (link.endsWith(".com") || link.endsWith(".com.br")))
        {
            valido = true;            
        }
        
        return valido;
    }    
}
