package testes;

import entidades.Cerimonia;
import entidades.Pessoa;
import entidades.ProdutorDeMidia;
import enumeracoes.ProdutorDeMidiaCategoria;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TesteProdutorDeMidia
{

    private static EntityManagerFactory emf;
    private EntityManager em;
    // private EntityTransaction et;

    public TesteProdutorDeMidia()
    {
    }

    @BeforeClass
    public static void setUpClass()
    {
        emf = Persistence.createEntityManagerFactory("casamento");
        DbUnitUtil.inserirDados();
    }

    @AfterClass
    public static void tearDownClass()
    {
        emf.close();
    }

    @Before
    public void setUp()
    {
        em = emf.createEntityManager();
//        et = em.getTransaction();
//        et.begin();
    }

    @After
    public void tearDown()
    {
//        try
//        {
//            et.commit();
//        } catch (Exception ex)
//        {
//            et.rollback();
//        } finally
//        {
        em.close();
        //}
    }

//    @Test
//    public void testaPrecoValido()
//    {//espera 0, mas vem 2
//        
//            ProdutorDeMidia pm = em.find(ProdutorDeMidia.class, 6);
//            
//            ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
//            Validator validator = validatorFactory.getValidator();
//            Set<ConstraintViolation<ProdutorDeMidia>> constraintViolations = validator.validate(pm);
//
//            assertEquals(0, constraintViolations.size());           
//    }
    
//    @Test
//    public void testaPrecoInvalido()
//    { //espera um, mas vem 3
//        
//        Date dataCerimonia = Calendar.getInstance().getTime();
//        Cerimonia c = new Cerimonia();
//        c.setData(dataCerimonia);        
//        ProdutorDeMidia pm = new ProdutorDeMidia(ProdutorDeMidiaCategoria.fotografo, 0.0, dataCerimonia, "lalaalal");
//        pm.setNome("Luna");
//        pm.setEmail("lunabandeira@gmail.com");
//        pm.setCerimonia(c);
//        
//        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
//        Validator validator = validatorFactory.getValidator();
//        Set<ConstraintViolation<ProdutorDeMidia>> constraintViolations = validator.validate(pm);
//
//        assertEquals(1, constraintViolations.size());
//        ConstraintViolation<ProdutorDeMidia> violation = constraintViolations.iterator().next();
//        assertEquals("Deve ser maior que 0. Pode conter ponto.", violation.getMessage());
//    }
    
    @Test
    public void horaChegadaDiferenteQuehoraCerimonia()
    {
        ProdutorDeMidia pm = em.find(ProdutorDeMidia.class, 6);
             
        Date horaCerimonia = pm.getCerimonia().getData();
        Date horaChegadaProdutor = pm.getDataEHoraChegada();
        Assert.assertNotEquals(horaCerimonia, horaChegadaProdutor);
    }

//    @Test
//    public void urlValida()
//    { //espera 0, mas vem 2
//        Date dataCerimonia = Calendar.getInstance().getTime();
//        Cerimonia c = new Cerimonia();
//        c.setData(dataCerimonia);        
//        ProdutorDeMidia pm = new ProdutorDeMidia(ProdutorDeMidiaCategoria.filmografia, 7.000, dataCerimonia, "www.lunalunatica.com.br");
//        pm.setNome("Luna");
//        pm.setEmail("lunabandeira@gmail.com");
//        pm.setCerimonia(c);
//        
//        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
//        Validator validator = validatorFactory.getValidator();
//        Set<ConstraintViolation<ProdutorDeMidia>> constraintViolations = validator.validate(pm);
//
//        assertEquals(0, constraintViolations.size());    
//    }
}
