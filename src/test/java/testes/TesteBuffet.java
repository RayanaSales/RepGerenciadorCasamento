package testes;

import entidades.Buffet;
import entidades.ComesBebes;
import entidades.Localizacao;
import entidades.Loja;
import entidades.Telefone;
import enumeracoes.ComesBebesCategoria;
import enumeracoes.EstadosDoBrasil;
import enumeracoes.TelefoneCategoria;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 *
 * @author rayana
 */
public class TesteBuffet extends Teste
{
    @Test
    public void atualizarBuffet()
    {
        Buffet p = em.find(Buffet.class, 10);
        p.setValorTotalGasto(3.500);
        em.merge(p);
        p = em.find(Buffet.class, 10);
        assertEquals(3.500, p.getValorTotalGasto(), 0.001);
    }

    @Test
    public void deletarBuffet()
    {
        Buffet b = em.find(Buffet.class, 9);
        em.remove(b);
        b = em.find(Buffet.class, 9);
        assertNull(b);
    }
    
//    @Test
//    public void testaPrecoValido()
//    {
//        Buffet b = this.montarBuffet();
//
//        if (b != null)
//        {
//            ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
//            Validator validator = validatorFactory.getValidator();
//            Set<ConstraintViolation<Buffet>> constraintViolations = validator.validate(b);
//            
//            assertEquals(0, constraintViolations.size());
//
//        }
//    }

//    @Test
//    public void testaPrecoInvalido()
//    {        
//        Buffet b = this.montarBuffet();
//        b.setValorTotalGasto(0);
//        
//        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
//        Validator validator = validatorFactory.getValidator();
//        Set<ConstraintViolation<Buffet>> constraintViolations = validator.validate(b);
//
//        assertEquals(1, constraintViolations.size()); 
//        
//        ConstraintViolation<Buffet> violation = constraintViolations.iterator().next();
//        assertEquals("Deve ser maior que 0. Pode conter ponto.", violation.getMessage());
//    }
    
    public void quantidadeComesBebes()
    {
        Buffet b = this.montarBuffet();        
        assertEquals(1, b.getComesBebes().size());
    }
    
    
    public Buffet montarBuffet()
    {
        Buffet b = new Buffet(5000);
        
        Telefone fone = new Telefone(TelefoneCategoria.empresarial, "81", "34267844");
        Localizacao local = new Localizacao(EstadosDoBrasil.ES, "Recife", "Encruzilhada", "rua lagoa azul", "b", "52040090", 50);
        Loja loja = new Loja("Delicata", fone, local);
        
        List<ComesBebes> comidas = new ArrayList<>();
        ComesBebes cb = new ComesBebes(b, "brigadeiro suico", loja, ComesBebesCategoria.salgado, 200, 400.0);
        comidas.add(cb);
        
        b.setComesBebes(comidas);
        
        return b;
    }

}
