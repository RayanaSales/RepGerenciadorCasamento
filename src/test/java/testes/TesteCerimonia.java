package testes;

import entidades.Buffet;
import entidades.Cerimonia;
import entidades.Localizacao;
import entidades.Noivo;
import entidades.Pessoa;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
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
        DbUnitUtil.inserirDados(); //insiro antes de cada teste, para torna-los independentes.
    }

    @AfterClass
    public static void tearDownClass()
    {
        emf.close();
    }

    @Before
    public void setUp()
    {
        //DbUnitUtil.inserirDados(); //para q os testes fiquem independentes (sempre reinsira tudo de novo antes de rodar um teste)
        em = emf.createEntityManager();
        et = em.getTransaction();
        et.begin();
    }

    @After
    public void tearDown()
    {
        try
        {
            if ( et != null && et.isActive() ) {
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

    @Test
    public void t01_buscarLocalizacao() throws Exception
    {
        TypedQuery<Localizacao> query = em.createQuery(
                "SELECT l FROM Localizacao l WHERE l.id = 1", Localizacao.class);
        Localizacao l = query.getSingleResult();
        assertEquals(1, l.getId());
    }

    @Test
    public void t02_buscarBuffet()
    {
        Buffet c = em.find(Buffet.class, 1);
        assertEquals(1, c.getId());
    }

    @Test
    public void t03_quantidadeCerimonias() throws Exception
    {
        TypedQuery<Long> query = em.createQuery(
                "SELECT COUNT(c) FROM Cerimonia c WHERE c.id IS NOT NULL", Long.class);
        Long resultado = query.getSingleResult();
        assertEquals(new Long(2), resultado);
    }

    @Test
    public void t04_deletarBuffet() throws Exception
    {
        Query query1 = em.createQuery(
                "DELETE FROM Buffet c WHERE c.id = 10");
        query1.executeUpdate();

        Buffet b = em.find(Buffet.class, 10);
        assertNull(b);
    }

    @Test
    public void t05_testeAtualizarCerimonia() throws Exception
    {
        Query query = em.createQuery("UPDATE Cerimonia AS c SET c.dataHora = ?1 WHERE c.id = ?2");        
        Date dataEsperada = Calendar.getInstance().getTime();
        query.setParameter(1, dataEsperada);
        query.setParameter(2, 1);
        query.executeUpdate();
        Cerimonia c = em.find(Cerimonia.class, 1);        
        assertEquals(dataEsperada, c.getData());
    }
    
    @Test
    public void t06_buscarPessoas() throws Exception
    {
        TypedQuery<Pessoa> query;
        query = em.createQuery("SELECT p FROM Pessoa p WHERE p.cerimonia.id = ?1 ORDER BY p.nome", Pessoa.class);
        query.setParameter(1, 1); //o id da cerimonia eh int entao n pode mandar string
        List<Pessoa> noivos = query.getResultList();
        
        assertEquals(9, noivos.size());
        
        List<String> nomes = new ArrayList<>();
        nomes.add("Rayana");
        nomes.add("Paulo");
        
        for (Pessoa noivo : noivos) { 
            nomes.contains(noivo.getNome());
        }        
    }
    
//    @Test CATEGORIA N EXISTE MAIS - MAS SERVE COMO EXEMPLO PARA VER COMO BUSCAR COISA DE UMA ENUM
//    public void t07_buscarCategoriaDePresentePorNome() throws Exception
//    {
//        TypedQuery<Presente> query = em.createQuery(
//                "SELECT p FROM Presente p WHERE p.categoria = :categoria ORDER BY p.id",
//                Presente.class); //nao usei o like pq ele so compara string, como ta comparando uma enum, o teste estava falhando       
//        query.setParameter("categoria", PresenteCategoria.camaMesaBanho); //n pode mandar como string, tem que mandar como um tipo da categoria
//        List<Presente> presentes = query.getResultList();
//
//        for (Presente presente : presentes) {
//            assertTrue(presente.getCategoria().toString().startsWith("cama"));
//        }
//    }
}
