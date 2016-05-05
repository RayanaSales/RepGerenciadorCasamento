package testes;

import entidades.Cerimonia;
import entidades.Loja;
import entidades.Noivo;
import entidades.Pessoa;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 *
 * @author natalia
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
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
    
//     @Test 
    public void t01_atualizarNoivo()
    {
        Noivo p = em.find(Noivo.class, 5);
        p.setNome("Laaa");
        em.merge(p);
        p = em.find(Noivo.class, 5);
        assertEquals("Laaa", p.getNome());
    }
        
    @Test
    public void t02_deletarNoivo()
    {        
        Noivo b = em.find(Noivo.class, 10);
        em.remove(b);        
        b = em.find(Noivo.class, 10);
        assertNull(b);
        
    }

}
