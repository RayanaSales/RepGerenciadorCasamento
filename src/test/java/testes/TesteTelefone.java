package testes;

import entidades.Telefone;
import enumeracoes.TelefoneCategoria;
import java.util.List;
import java.util.Set;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author rayana
 */
public class TesteTelefone extends Teste
{
    @Test
    public void testaDDDValido()
    {
        Telefone telefone = new Telefone(TelefoneCategoria.celular, "81", "989764567");

        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<Telefone>> constraintViolations = validator.validate(telefone);

        assertEquals(0, constraintViolations.size());
    }

    @Test
    public void testaDDDInvalido()
    {
        Telefone telefone = new Telefone(TelefoneCategoria.celular, "00", "989764567");

        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<Telefone>> constraintViolations = validator.validate(telefone);

        assertEquals(1, constraintViolations.size());
        ConstraintViolation<Telefone> violation = constraintViolations.iterator().next();
        assertEquals("Insira um ddd válido.", violation.getMessage());
    }

    @Test
    public void testaNumeroValido()
    {
        Telefone telefone = new Telefone(TelefoneCategoria.residencial, "81", "32417065");

        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<Telefone>> constraintViolations = validator.validate(telefone);

        assertEquals(0, constraintViolations.size());
    }

    @Test
    public void testaNumeroInvalido()
    {
        Telefone telefone = new Telefone(TelefoneCategoria.celular, "81", "22224444");

        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<Telefone>> constraintViolations = validator.validate(telefone);

        assertEquals(1, constraintViolations.size());
        ConstraintViolation<Telefone> violation = constraintViolations.iterator().next();
        assertEquals("Os números devem começar com 9, 8, 7 ou 3.", violation.getMessage());
    }

    @Test
    public void testaCategoriaValida()
    {
        Telefone t = em.find(Telefone.class, 1);
        assertEquals(TelefoneCategoria.celular, t.getCategoria());
    }
    
    @Test
    public void testaCategoriaInvalida()
    {
        Telefone t = em.find(Telefone.class, 2);
        assertNotEquals("residencia", t.getCategoria());        
    }
    
     @Test
    public void testeAtualizarTelefone() throws Exception 
    {           
        String NovoNumeroTelefone = "34267844";               
        Telefone t = em.find(Telefone.class, 6);
        t.setNumero(NovoNumeroTelefone);        
        em.merge(t);        
        assertEquals(t.getNumero() , NovoNumeroTelefone);
    }    
  
    @Test
    public void buscarTelefonesDoTipoCelular() throws Exception
    {
        TypedQuery<Telefone> query = em.createQuery("SELECT t FROM Telefone t WHERE t.categoria = :categoria",
                Telefone.class); //like compara string, nesse caso eh uma enum,logo usa o =          
        query.setParameter("categoria", TelefoneCategoria.celular); //n pode mandar como string, manda como enum        
        List<Telefone> telefones = query.getResultList();

        for (Telefone telefone : telefones) 
            assertEquals("celular", telefone.getCategoria().toString());        
    } 
}
