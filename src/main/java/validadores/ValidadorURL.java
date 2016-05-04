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
        
        //achar funcao que use o pattern/ validar com .br tb
        if(link.startsWith("www.") && (link.endsWith(".com") || link.endsWith(".com.br")))
        {
            valido = true;            
        }
        
        return valido;
    }    
}
