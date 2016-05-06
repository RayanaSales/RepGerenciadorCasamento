package testes;

import entidades.Cerimonia;
import entidades.Noivo;
import java.util.Set;
import javax.persistence.Query;
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
        Noivo noivo = this.montarNoivo();

        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<Noivo>> constraintViolations = validator.validate(noivo);

        ConstraintViolation<Noivo> violation = constraintViolations.iterator().next();
        assertEquals("A senha deve possuir de 6 até 16 caracteres.", violation.getMessage());
    }
    
    @Test
    public void quantidadeRoupas()
    {
        Noivo n = em.find(Noivo.class, 1);
        assertEquals(3, n.getRoupaDosNoivos().size());
    }
    
     @Test 
    public void t01_atualizarNoivo()
    {       
        int id = 10;
        String senhaEsperada = "9876dcba";
        
        Query query = em.createQuery("UPDATE Noivo AS n SET n.senha = ?1 WHERE n.id = ?2");
        query.setParameter(1, senhaEsperada);
        query.setParameter(2, id);
        query.executeUpdate();        
        Noivo n = em.find(Noivo.class, id);               
        assertEquals(senhaEsperada, n.getSenha());
    }
        
    @Test
    public void t02_deletarNoivo()
    {        
        Noivo b = em.find(Noivo.class, 10);
        em.remove(b);        
        b = em.find(Noivo.class, 10);
        assertNull(b);
        
    }
    
    private Noivo montarNoivo()
    {
        Cerimonia c = em.find(Cerimonia.class, 1);
        Noivo noivo = new Noivo("senha");
        noivo.setNome("Nati");
        noivo.setEmail("nati@gmail.com");
        noivo.setCerimonia(c);
        
        return noivo;
    }

}
