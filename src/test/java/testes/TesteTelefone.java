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
        Telefone telefone = this.montarTelefone();

        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<Telefone>> constraintViolations = validator.validate(telefone);

        assertEquals(0, constraintViolations.size());
    }

    @Test
    public void testaDDDInvalido()
    {
        Telefone telefone = this.montarTelefone();
        telefone.setDdd("00");

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
        Telefone telefone = this.montarTelefone();
        telefone.setNumero("9AAAAAAAA");

        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<Telefone>> constraintViolations = validator.validate(telefone);

        assertEquals(0, constraintViolations.size());
    }

    @Test
    public void testaNumeroInvalido()
    {
        Telefone telefone = this.montarTelefone();
        telefone.setNumero("22223333");

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
        Telefone t = this.montarTelefone();
        assertEquals(TelefoneCategoria.celular, t.getCategoria());
    }
    
    @Test
    public void testaCategoriaInvalida()
    {
        Telefone t = this.montarTelefone();
        assertNotEquals(TelefoneCategoria.empresarial, t.getCategoria());        
    }
    
    @Test
    public void testeAtualizarTelefone() throws Exception 
    {           
        String NovoNumeroTelefone = "34267844"; 
        
        Telefone t = this.montarTelefone();
        t.setNumero(NovoNumeroTelefone);        
        em.merge(t);        
        assertEquals(t.getNumero() , NovoNumeroTelefone);
    }  
    
    @Test
    public void testeDeletarTelefone() throws Exception 
    { 
        Telefone t = em.find(Telefone.class, 9);
        em.remove(t);        
        t = em.find(Telefone.class, 9);
        assertNull(t);
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
    
    
    private Telefone montarTelefone()
    {
        Telefone t = new Telefone(TelefoneCategoria.celular, "81", "993250212");
        t.setId(1);
        return t;
    }
}
