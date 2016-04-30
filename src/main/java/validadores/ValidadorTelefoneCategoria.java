package validadores;

import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidadorTelefoneCategoria implements ConstraintValidator<ValidaTelefoneCategoria, String>
{

    private List<String> categorias;

    @Override
    public void initialize(ValidaTelefoneCategoria validaEstado)
    {
        this.categorias = new ArrayList<>();
        this.categorias.add("celular");
        this.categorias.add("empresarial");
        this.categorias.add("residencial");
    }

    @Override
    public boolean isValid(String valor, ConstraintValidatorContext context)
    {
        return valor == null ? false : categorias.contains(valor);
    }
}
