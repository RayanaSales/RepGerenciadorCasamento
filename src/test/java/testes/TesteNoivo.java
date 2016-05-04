package testes;

import entidades.Cerimonia;
import entidades.Noivo;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author natalia
 */
public class TesteNoivo extends Teste
{
     @Test
    public void testarTamanhoDaSenhaInvalido()
    {
        Cerimonia c = em.find(Cerimonia.class, 1);
        Noivo noivo = new Noivo("senha");
        noivo.setNome("Nati");
        noivo.setEmail("nati@gmail.com");
        noivo.setCerimonia(c);

        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<Noivo>> constraintViolations = validator.validate(noivo);

        ConstraintViolation<Noivo> violation = constraintViolations.iterator().next();
        assertEquals("A senha deve possuir de 6 até 16 caracteres.", violation.getMessage());
    }

}
