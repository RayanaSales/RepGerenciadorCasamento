package testes;

import entidades.Cerimonia;
import entidades.ProdutorDeMidia;
import enumeracoes.ProdutorDeMidiaCategoria;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class TesteProdutorDeMidia extends Teste
{

    @Test
    public void testaPrecoValido()
    {
        ProdutorDeMidia pm = this.montarProdutor();

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
        ProdutorDeMidia pm = this.montarProdutor();
        pm.setPreco(0);

        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<ProdutorDeMidia>> constraintViolations = validator.validate(pm);

        assertEquals(1, constraintViolations.size());

        ConstraintViolation<ProdutorDeMidia> violation = constraintViolations.iterator().next();
        assertEquals("Deve ser maior que 0. Pode conter ponto, ou virgula.", violation.getMessage());
    }

    @Test
    public void horaChegadaDiferenteQuehoraCerimonia()
    {
        ProdutorDeMidia pm = this.montarProdutor();

        Date horaCerimonia = pm.getCerimonia().getData();
        Date horaChegadaProdutor = pm.getDataEHoraChegada();
        assertNotEquals(horaCerimonia, horaChegadaProdutor);
    }

    @Test
    public void urlValida()
    {
        ProdutorDeMidia pm = this.montarProdutor();

        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<ProdutorDeMidia>> constraintViolations = validator.validate(pm);

        assertEquals(0, constraintViolations.size());
    }

    @Test
    public void urlInvalida()
    {
        ProdutorDeMidia pm = this.montarProdutor();
        pm.setLinkParaRedeSocial("www.meuSite");

        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<ProdutorDeMidia>> constraintViolations = validator.validate(pm);

        assertEquals(1, constraintViolations.size());
    }

    @Test
    public void atualizarProdutor()
    {
        ProdutorDeMidia p = em.find(ProdutorDeMidia.class, 15);
        p.setLinkParaRedeSocial("www.facebook.com");
        em.merge(p);
        p = em.find(ProdutorDeMidia.class, 15);
        assertEquals("www.facebook.com", p.getLinkParaRedeSocial());
    }

    @Test
    public void deletarProdutor()
    {
        ProdutorDeMidia b = em.find(ProdutorDeMidia.class, 16);
        em.remove(b);
        b = em.find(ProdutorDeMidia.class, 16);
        assertNull(b);
    }

    private ProdutorDeMidia montarProdutor()
    {
        DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = null;
        try
        {
            date = (Date) formatter.parse("2020-09-27 22:00:00");
        } catch (ParseException ex)
        {
            Logger.getLogger(TesteProdutorDeMidia.class.getName()).log(Level.SEVERE, null, ex);
        }
        Cerimonia c = em.find(Cerimonia.class, 1);
        c.setData(date);

        ProdutorDeMidia pm = new ProdutorDeMidia(ProdutorDeMidiaCategoria.filmografia, 7.000, c.getData(), "www.lunalunatica.com.br");
        pm.setNome("Luna");
        pm.setEmail("lunabandeira@gmail.com");

        DateFormat formatter2 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date2 = null;
        try
        {
            date2 = (Date) formatter2.parse("2020-09-27 19:00:00");
        } catch (ParseException ex)
        {
            Logger.getLogger(TesteProdutorDeMidia.class.getName()).log(Level.SEVERE, null, ex);
        }
        pm.setDataEHoraChegada(date2);

        pm.setCerimonia(c);
        pm.setId(1);

        return pm;

    }
}
