package testes;

import entidades.Cerimonia;
import entidades.Convidado;
import enumeracoes.ConvidadoCategoria;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import org.junit.Test;

/**
 *
 * @author rayana
 */
public class TesteConvidado extends Teste
{    
    @Test
    public void atualizarConvidado()
    {      
        Convidado p = em.find(Convidado.class, 8);
        p.setQuantidadeSenhas(8);
        em.merge(p);
        p = em.find(Convidado.class, 8);
        assertEquals(8, p.getQuantidadeSenhas());
    }
        
    @Test
    public void deletarConvidado()
    {        
        Convidado b = em.find(Convidado.class, 9);
        em.remove(b);        
        b = em.find(Convidado.class, 9);
        assertNull(b);        
    }
    
    @Test
    public void senhasValidas()
    {
        Convidado c = this.montarConvidado();

        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<Convidado>> constraintViolations = validator.validate(c);

        assertEquals(0, constraintViolations.size());
    }

    @Test
    public void senhasInvalidas()
    {
        Convidado c = this.montarConvidado();
        c.setQuantidadeSenhas(-2);

        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<Convidado>> constraintViolations = validator.validate(c);

        assertEquals(1, constraintViolations.size());
        ConstraintViolation<Convidado> violation = constraintViolations.iterator().next();
        assertEquals("A quantidade de senhas tem que ser maior que zero.", violation.getMessage());
    }
    
    @Test
    public void categoriaValida()
    {
        Convidado c = this.montarConvidado();        
        assertEquals(ConvidadoCategoria.amigo, c.getCategoria());
    }
    
    @Test
    public void categoriaInvalida()
    {
        Convidado c = this.montarConvidado();        
        assertNotEquals(ConvidadoCategoria.familia, c.getCategoria());
    }
    
    public Convidado montarConvidado()
    {
        Convidado c = new Convidado(ConvidadoCategoria.amigo, 5);
        c.setId(10);
        c.setNome("Larissa");
        c.setEmail("larissa@gmail.com");
        c.setCerimonia(em.find(Cerimonia.class, 1));
        
        return c;
    }
}
