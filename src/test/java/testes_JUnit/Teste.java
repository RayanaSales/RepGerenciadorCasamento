package testes_JUnit;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

public class Teste
{
    protected static EntityManagerFactory emf;
    protected EntityManager em;
    private EntityTransaction et;

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
        et = em.getTransaction();
        et.begin();
    }

    @After
    public void tearDown()
    {
        try
        {
            if (et != null && et.isActive())
            {
                et.commit();
            }
        } catch (Exception ex)
        {
            et.rollback();
        } finally
        {
            em.close();
        }
    }
}
