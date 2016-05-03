package testes;

import entidades.Telefone;
import enumeracoes.TelefoneCategoria;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author rayana
 */
public class TesteTelefone
{

    private static EntityManagerFactory emf;
    private EntityManager em;
   // private EntityTransaction et;

    public TesteTelefone()
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
    }

    @After
    public void tearDown()
    {
        em.close();
    }

    @Test
    public void testaDDDValido()
    {
        Telefone telefone = new Telefone(TelefoneCategoria.celular, "81", "989764567");

        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<Telefone>> constraintViolations = validator.validate(telefone);

        assertEquals(0, constraintViolations.size());
    }

    @Test
    public void testaDDDInvalido()
    {
        Telefone telefone = new Telefone(TelefoneCategoria.celular, "00", "989764567");

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
        Telefone telefone = new Telefone(TelefoneCategoria.residencial, "81", "32417065");

        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<Telefone>> constraintViolations = validator.validate(telefone);

        assertEquals(0, constraintViolations.size());
    }

    @Test
    public void testaNumeroInvalido()
    {
        Telefone telefone = new Telefone(TelefoneCategoria.celular, "81", "22224444");

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
        Telefone t = em.find(Telefone.class, 1);
        assertEquals(TelefoneCategoria.celular, t.getCategoria());
    }
    
    @Test
    public void testaCategoriaInvalida()
    {
        Telefone t = em.find(Telefone.class, 2);
        assertNotEquals("residencia", t.getCategoria());        
    }
}
