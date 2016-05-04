package testes;

import entidades.Cerimonia;
import entidades.ProdutorDeMidia;
import enumeracoes.ProdutorDeMidiaCategoria;
import java.util.Date;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author rayana
 */
public class TesteProdutorDeMidia extends Teste
{
    @Test
    public void testaPrecoValido()
    {
        ProdutorDeMidia pm = em.find(ProdutorDeMidia.class, 4);

        if (pm != null)
        {
            ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
            Validator validator = validatorFactory.getValidator();
            Set<ConstraintViolation<ProdutorDeMidia>> constraintViolations = validator.validate(pm);
            
            assertEquals(0, constraintViolations.size());

        }
    }

    @Test
    public void testaPrecoInvalido()
    {        
        Cerimonia c = em.find(Cerimonia.class, 1);
        ProdutorDeMidia pm = new ProdutorDeMidia(ProdutorDeMidiaCategoria.fotografo, 0.0, c.getData(), "www.lala.com");
        pm.setNome("Luna");
        pm.setEmail("lunabandeira@gmail.com");
        pm.setCerimonia(c);
        
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<ProdutorDeMidia>> constraintViolations = validator.validate(pm);

        assertEquals(1, constraintViolations.size()); 
        
        ConstraintViolation<ProdutorDeMidia> violation = constraintViolations.iterator().next();
        assertEquals("Deve ser maior que 0. Pode conter ponto.", violation.getMessage());
    }
    
   @Test
    public void horaChegadaDiferenteQuehoraCerimonia() 
    {
        ProdutorDeMidia pm = em.find(ProdutorDeMidia.class, 4);
             
        Date horaCerimonia = pm.getCerimonia().getData();
        Date horaChegadaProdutor = pm.getDataEHoraChegada();
        Assert.assertNotEquals(horaCerimonia, horaChegadaProdutor);
    }
    
    @Test
    public void urlValida()
    {        
        Cerimonia c = em.find(Cerimonia.class, 1);
             
        ProdutorDeMidia pm = new ProdutorDeMidia(ProdutorDeMidiaCategoria.filmografia, 7.000, c.getData(), "www.lunalunatica.com.br");
        pm.setNome("Luna");
        pm.setEmail("lunabandeira@gmail.com");
        pm.setCerimonia(c);
        
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<ProdutorDeMidia>> constraintViolations = validator.validate(pm);

        assertEquals(0, constraintViolations.size());    
    }
    
    @Test
    public void urlInvalida()
    {        
        Cerimonia c = em.find(Cerimonia.class, 1);
             
        ProdutorDeMidia pm = new ProdutorDeMidia(ProdutorDeMidiaCategoria.filmografia, 7.000, c.getData(), "www.lunalunatica");
        pm.setNome("Luna");
        pm.setEmail("lunabandeira@gmail.com");
        pm.setCerimonia(c);
        
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<ProdutorDeMidia>> constraintViolations = validator.validate(pm);

        assertEquals(1, constraintViolations.size());    
    }
}
