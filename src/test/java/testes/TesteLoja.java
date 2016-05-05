package testes;

import entidades.Localizacao;
import entidades.Loja;
import entidades.Telefone;
import java.util.List;
import java.util.Set;
import javax.persistence.TypedQuery;
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
public class TesteLoja extends Teste
{
    @Test
    public void testarTelefoneDaLoja(){
        
        TypedQuery<Telefone> query;
        query = em.createQuery("SELECT t FROM Telefone t Where t.id = 2 ", Telefone.class);
        Telefone telefoneEsperado = query.getSingleResult();
       
        query = em.createQuery("SELECT l.telefone FROM Loja l Where l.id = ?1 ", Telefone.class);
        query.setParameter(1, 1);
        Telefone telefoneAtual = query.getSingleResult();
        assertEquals(telefoneEsperado, telefoneAtual);
    }
    
    @Test
    public void testarLocalizacaoDaLoja(){
        
        TypedQuery<Localizacao> query;
        query = em.createQuery("SELECT l FROM Localizacao l Where l.id = 2 ", Localizacao.class);
        Localizacao localizacaoEsperada = query.getSingleResult();
       
        query = em.createQuery("SELECT l.localizacao FROM Loja l Where l.id = 1 ", Localizacao.class);
        Localizacao localizacaoAtual = query.getSingleResult();
        assertEquals(localizacaoEsperada, localizacaoAtual);
    }
  
    @Test
    public void t01_QuantidadeDeLojas(){
    
        TypedQuery<Loja> query;
        query = em.createQuery("SELECT l FROM Loja l", Loja.class);
        List<Loja> lojas = query.getResultList();
        assertEquals(2, lojas.size());
    
    }
    
    @Test
    public void testaNomeDaLoja()
    {
        Telefone fone = em.find(Telefone.class, 7);
        Localizacao local = em.find(Localizacao.class, 4);
        Loja loja = new Loja("aaaaa", fone, local);
        
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<Loja>> constraintViolations = validator.validate(loja);
        
        ConstraintViolation<Loja> violation = constraintViolations.iterator().next();
        assertEquals("Deve possuir uma única letra maiúscula, seguida por letras minúsculas.", violation.getMessage());
    
    }
    
     @Test 
    public void testarAtualizarLoja()
    {
        Loja p = em.find(Loja.class, 1);
        p.setNome("Amend");
        em.merge(p);
        p = em.find(Loja.class, 1);
        assertEquals("Amend", p.getNome());
    }
        
    @Test
    public void t02_deletarLoja()
    {        
        Loja b = em.find(Loja.class, 2);
        em.remove(b);        
        b = em.find(Loja.class, 2);
        assertNull(b);
        
    }
    
}
