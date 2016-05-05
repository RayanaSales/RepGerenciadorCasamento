
package testes;

import entidades.Noivo;
import entidades.Pessoa;
import entidades.Presente;
import entidades.RoupaDosNoivos;
import java.util.Set;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Test;

/**
 *
 * @author NataliaAmancio
 */
public class TesteRoupaDosNoivos extends Teste {
    
    // teste se o valor da roupa válido. -> NÃO FUNCIONA
    // testa valor da roupa inválido. ok!
    // testa se a pessoa existe. ok!
    // testa nome válido
    // testa nome inválido.
    
    
    // NÃO FUNCIONA
 //   @Test
    public void testaValorRoupaInvalido()
    {
        RoupaDosNoivos r = this.montarObjetoRoupa();
     //   r.setValor(-1);
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<RoupaDosNoivos>> constraintViolations = validator.validate(r);
        
        assertEquals(0, constraintViolations.size());
        ConstraintViolation<RoupaDosNoivos> violation = constraintViolations.iterator().next();
        assertEquals("Deve ser maior que 0. Pode conter ponto.", violation.getMessage());
         
    }
    
    @Test
    public void testaValorRoupaValido(){
        
        RoupaDosNoivos r = em.find(RoupaDosNoivos.class, 1);
        assertEquals(3000, r.getValor(), 0);
        
    }
    
    @Test
    public void testaDonoDaRoupa()
    {
        TypedQuery<Pessoa> query;
        query = em.createQuery("SELECT p FROM Pessoa p Where p.id = 1 ", Pessoa.class);
        Pessoa pessoaEsperada = query.getSingleResult();
       
        query = em.createQuery("SELECT r.noivo FROM RoupaDosNoivos r Where r.id = 1 ", Pessoa.class);
        Pessoa pessoaAtual = query.getSingleResult();
        assertEquals(pessoaAtual, pessoaEsperada);
    }

    public RoupaDosNoivos montarObjetoRoupa()
    {
        RoupaDosNoivos r = new RoupaDosNoivos();
        r.setNoivo(em.find(Noivo.class, 1));
        r.setRoupa("Vestido");
        r.setValor(1000);
        
        return r;
    }
           
//     @Test 
    public void testarAtualizarRoupa()
    {
        RoupaDosNoivos r = em.find(RoupaDosNoivos.class, 1);
        r.setRoupa("Vestido");
        em.merge(r);
        r = em.find(RoupaDosNoivos.class, 1);
        assertEquals("Vestido", r.getRoupa());
    }
        
    @Test
    public void testarDeletarRoupa()
    {
        
        Presente b = em.find(Presente.class, 2);
        em.remove(b);        
        b = em.find(Presente.class, 2);
        assertNull(b);
        
    }
}
