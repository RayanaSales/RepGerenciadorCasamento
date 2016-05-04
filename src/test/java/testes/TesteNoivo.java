package testes;

import entidades.Cerimonia;
import entidades.Convidado;
import entidades.Noivo;
import entidades.Pessoa;
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
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TesteNoivo
{

    private static EntityManagerFactory emf;
    private EntityManager em;
    // private EntityTransaction et;

    public TesteNoivo()
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

    // @Test
    public void testarTamanhoDaSenhaInvalido()
    {
        Cerimonia c = em.find(Cerimonia.class, 1);
        Noivo noivo = new Noivo("senha");
        noivo.setNome("Nati");
        noivo.setEmail("nati@gmail.com");
        noivo.setCerimonia(c);

        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<Noivo>> constraintViolations = validator.validate(noivo);

        ConstraintViolation<Noivo> violation = constraintViolations.iterator().next();
        assertEquals("A senha deve possuir de 6 até 16 caracteres.", violation.getMessage());
    }

}
