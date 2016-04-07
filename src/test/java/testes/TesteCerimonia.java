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
    private EntityManager em;
    private EntityTransaction et;

    public TesteCerimonia()
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
            et.commit();
        } catch (Exception ex)
        {            
            et.rollback();
        } finally
        {
            em.close();
        }
    }

    @Test
    public void t01_BuscarLocalizacao() throws Exception
    {
        TypedQuery<Localizacao> query = em.createQuery(
                "SELECT l FROM Localizacao l WHERE l.id = 1", Localizacao.class);
        Localizacao resultado = query.getSingleResult();
        assertEquals(1, resultado.getId());
    }

    @Test
    public void t02_BuscarBuffet()
    {
        Buffet c = em.find(Buffet.class, 1);
        assertEquals(1, c.getId());
    }

    @Test
    public void t03_QuantidadeCerimonias() throws Exception
    {
        TypedQuery<Long> query = em.createQuery(
                "SELECT COUNT(c) FROM Cerimonia c WHERE c.id IS NOT NULL", Long.class);
        Long resultado = query.getSingleResult();
        assertEquals(new Long(2), resultado);
    }

    @Test
    public void t04_DeletarBuffet() throws Exception
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
//        Query query = em.createQuery("UPDATE Cerimonia AS c SET c.dt_dataHora = ?1 WHERE c.id = ?2");
//        query.setParameter(1, "2001-09-27 22:00:00");
//        query.setParameter(2, new Long(1));
//        query.executeUpdate();
//
//        Cerimonia vendedor = em.find(Cerimonia.class, 1);
//        System.out.println("Cerimonia retornado do bd: " + vendedor.getId());
//        assertEquals("2001-09-27 22:00:00", vendedor.getData());
//    }
    
//    @Test
//    public void t06_buscarNoivosDeUmaCerimonia() throws Exception
//    {
//        TypedQuery<Noivo> query;
//        query = em.createQuery("SELECT n FROM Noivo n WHERE n.id_cerimonia = ?1 ORDER BY n.nome", Noivo.class);
//        query.setParameter(1, "1");       
//        List<Noivo> noivos = query.getResultList();
//        
//        for (Noivo noivo : noivos) {            
//            System.out.println("Noivo: " + noivo.getNome());
//            assertTrue(noivo.getCerimonia().getId() == 1);
//        }        
//    }
    
//    @Test
//    public void t07_buscarCategoriaDePresentePorNome() throws Exception
//    {
//        TypedQuery<Presente> query = em.createQuery(
//                "SELECT p FROM Presente p WHERE p.categoria LIKE :categoria ORDER BY p.id",
//                Presente.class);        
//        query.setParameter("categoria", "cama%");
//        List<Presente> presentes = query.getResultList();
//
//        for (Presente presente : presentes) {
//            assertTrue(presente.getCategoria().toString().startsWith("cama"));
//        }
//    }
}
