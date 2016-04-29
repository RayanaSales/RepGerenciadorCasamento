package validadores;

import enumeracoes.TelefoneCategoria;
import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidadorTelefoneCategoria implements ConstraintValidator<ValidaTelefoneCategoria, String> 
{
    
    
    @Override
    public void initialize(ValidaTelefoneCategoria validaTelefoneCategoria) {
              
    }

    @Override
    public boolean isValid(String valor, ConstraintValidatorContext context) {
        
        boolean verificador = false;
        
        for(TelefoneCategoria categoria : TelefoneCategoria.values()){
            if(valor.equals(categoria.name()))
                verificador = true;
        }
        
        return verificador;
    }  
}
