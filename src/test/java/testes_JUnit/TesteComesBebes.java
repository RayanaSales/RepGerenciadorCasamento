package testes_JUnit;

import entidades.Buffet;
import entidades.ComesBebes;
import entidades.Loja;
import enumeracoes.ComesBebesCategoria;
import java.util.Set;
import javax.persistence.Query;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 *
 * @author rayana
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TesteComesBebes extends Teste
{
    @Test
    public void t01_atualizarComesBebes()
    {
        double senhaEsperada = 5000;
        int id = 11;

        Query query = em.createQuery("UPDATE ComesBebes AS n SET n.valor = ?1 WHERE n.id = ?2");
        query.setParameter(1, senhaEsperada);
        query.setParameter(2, id);
        query.executeUpdate();
        ComesBebes p = em.find(ComesBebes.class, id);
        assertEquals(senhaEsperada, p.getValor(), 0.001);
    }

    @Test
    public void t02_deletarComesBebes()
    {
        ComesBebes b = em.find(ComesBebes.class,11);
        b.setBuffet(em.find(Buffet.class, 3));
        b.setLoja(em.find(Loja.class, 4));
        
        em.remove(b);        
        b = em.find(ComesBebes.class, 11);
        assertNull(b);
    }

    @Test
    public void t03_testarCategoriaValida()
    {
        ComesBebes c = this.montarComesBebes();        
        assertEquals(ComesBebesCategoria.salgado, c.getCategoria());
    }

    @Test
    public void t04_testarCategoriaInvalida()
    {
        ComesBebes c = this.montarComesBebes();    
        assertNotEquals(ComesBebesCategoria.doce, c.getCategoria());
    }
    
    @Test
    public void t05_quantidadeValida()
    {
        ComesBebes c = this.montarComesBebes();    
        
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<ComesBebes>> constraintViolations = validator.validate(c);

        assertEquals(0, constraintViolations.size());
    }

    @Test
    public void t06_quantidadeInvalida()
    {
        ComesBebes c = this.montarComesBebes(); 
        c.setQuantidade(0);
        
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<ComesBebes>> constraintViolations = validator.validate(c);

        assertEquals(1, constraintViolations.size());
        ConstraintViolation<ComesBebes> violation = constraintViolations.iterator().next();
        assertEquals("A quantidade tem que ser maior que zero.", violation.getMessage());
    }
    
    @Test
    public void t07_valorValido()
    {
        ComesBebes c = this.montarComesBebes();    
        
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<ComesBebes>> constraintViolations = validator.validate(c);

        assertEquals(0, constraintViolations.size());
    }

    @Test
    public void t08_valorInvalido()
    {
        ComesBebes c = this.montarComesBebes(); 
        c.setValor(0);
        
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<ComesBebes>> constraintViolations = validator.validate(c);

        assertEquals(1, constraintViolations.size());
        ConstraintViolation<ComesBebes> violation = constraintViolations.iterator().next();
        assertEquals("Deve ser maior que 0. Pode conter ponto, ou virgula.", violation.getMessage());
    }

    private ComesBebes montarComesBebes()
    {
        Buffet b = em.find(Buffet.class, 5);
        Loja loja = em.find(Loja.class, 1);
        
        ComesBebes cb = new ComesBebes(b, "Camarao", loja, ComesBebesCategoria.salgado, 500, 2.500);
        
        return cb;
    }

}
