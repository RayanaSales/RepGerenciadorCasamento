package testes;

import entidades.Buffet;
import entidades.Cerimonia;
import entidades.Convidado;
import entidades.Localizacao;
import entidades.Noivo;
import entidades.Presente;
import entidades.ProdutorDeMidia;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class TesteCerimonia
{

    private static EntityManagerFactory emf;
    private static final Logger logger = Logger.getGlobal();
    private EntityManager em;
    private EntityTransaction et;

    public TesteCerimonia()
    {
    }

    @BeforeClass
    public static void setUpClass()
    {
        logger.setLevel(Level.INFO);
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
            et.commit();
        } catch (Exception ex)
        {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            et.rollback();
        } finally
        {
            em.close();
        }
    }

    @Test
    public void t01_testeBuscarLocalizacao() throws Exception
    {
        TypedQuery<Localizacao> query = em.createQuery(
                "SELECT l FROM Localizacao l WHERE l.id = 1", Localizacao.class);
        Localizacao resultado = query.getSingleResult();
        assertEquals(1, resultado.getId());
    }

    @Test
    public void t02_testeBuscarBuffet()
    {
        Buffet c = em.find(Buffet.class, 1);
        assertEquals(1, c.getId());
    }

    @Test
    public void t03_testeQtdCamposCerimonia() throws Exception
    {
        TypedQuery<Long> query = em.createQuery(
                "SELECT COUNT(c) FROM Cerimonia c WHERE c.id IS NOT NULL", Long.class);
        Long resultado = query.getSingleResult();
        assertEquals(new Long(1), resultado);
    }

    @Test
    public void t04_testeDeletarBuffet() throws Exception
    {
        Query query1 = em.createQuery(
                "DELETE FROM Buffet c WHERE c.id = 10");
        query1.executeUpdate();

        Buffet oferta = em.find(Buffet.class, 10);
        assertNull(oferta);
    }

//    @Test
//    public void t05_testeAtualizarCerimonia() throws Exception
//    {
//        Query query = em.createQuery("UPDATE Noivo AS n SET n.txt_email = ?1 WHERE n.id = ?2");
//        query.setParameter(1, "raysls@gmail.com");
//        query.setParameter(2, new Long(1));
//        query.executeUpdate();
//
//        Noivo vendedor = em.find(Noivo.class, 1);
//
//        System.out.println("Nome do noivo retornado do bd: " + vendedor.getNome());
//
//        assertEquals("rayanasales.ifpe@gmail.com", vendedor.getEmail());
//    }
}
