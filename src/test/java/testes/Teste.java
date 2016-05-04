package testes;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *
 * @author rayana
 */
public class Teste
{
    protected static EntityManagerFactory emf;   
    protected EntityManager em;
    
    public Teste()
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
}
