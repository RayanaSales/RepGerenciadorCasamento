package testes_JUnit;

import entidades.Cerimonia;
import entidades.Presente;
import java.util.List;
import java.util.Set;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Test;


public class TestePresente extends Teste
{
    @Test
    public void testaNomeInvalido()
    {
        Presente p = this.montarObjetoPresente();
        p.setNome("?????");
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<Presente>> constraintViolations = validator.validate(p);
        
        assertEquals(1, constraintViolations.size());
        ConstraintViolation<Presente> violation = constraintViolations.iterator().next();
        assertEquals("Deve possuir uma única letra maiúscula, seguida por letras minúsculas.", violation.getMessage());
        
    }    
    
    @Test 
    public void testaCerimoniaDoPresente()
    {
    
        TypedQuery<Cerimonia> query;
        query = em.createQuery("SELECT c FROM Cerimonia c Where c.id = 1 ", Cerimonia.class);
        Cerimonia cerimoniaEsperada = query.getSingleResult();
       
        query = em.createQuery("SELECT p.cerimonia FROM Presente p Where p.id = 1 ", Cerimonia.class);
        Cerimonia cerimoniaoAtual = query.getSingleResult();
        assertEquals(cerimoniaEsperada, cerimoniaoAtual);
    
    }
    
    @Test
    public void testarQuantidadeDePresentesPorCerimonia()
    {
        TypedQuery<Presente> query;
        query = em.createQuery("SELECT p FROM Presente p Where p.cerimonia.id = 1 ", Presente.class);
        List<Presente> listaDePresentes = query.getResultList();
        assertEquals(3, listaDePresentes.size());
        
    }
    
    @Test 
    public void testarAtualizarPresente()
    {
        Presente p = em.find(Presente.class, 1);
        p.setNome("Televisao");
        em.merge(p);
        em.flush();
        em.clear();
        p = em.find(Presente.class, 1);
        assertEquals("Televisao", p.getNome());
    }
        
    @Test
    public void testarDeletarPresente()
    {
        
        Presente b = em.find(Presente.class, 2);
        em.remove(b);        
        b = em.find(Presente.class, 2);
        assertNull(b);
        
    }
    
    public Presente montarObjetoPresente() {
        Presente presente = new Presente();
        presente.setDescricao("Presente");
        presente.setCerimonia(em.find(Cerimonia.class, 1));
        presente.setNome("Nome Presente");
        presente.setOndeEncontrar("Encontrar");
        return presente;
    }
  
}
