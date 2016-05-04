package testes;

import entidades.Presente;
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
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class TestePresente
{

    private static EntityManagerFactory emf;
    private EntityManager em;
    private EntityTransaction et;

    public TestePresente()
    {
    }

    @BeforeClass
    public static void setUpClass()
    {
        emf = Persistence.createEntityManagerFactory("casamento");
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
    public void testaNomeValido()
    {
        Presente p = new Presente(null, "Faqueiro", "Contém 100 peças", "Lojas Americanas");
        
        // testar se a cerimonia é válida.
        //
    }    
}
