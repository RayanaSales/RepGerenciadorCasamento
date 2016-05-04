package testes;

import entidades.Pessoa;
import entidades.Telefone;
import enumeracoes.TelefoneCategoria;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 *
 * @author rayana
 */
public class TestePessoa
{
    private static EntityManagerFactory emf;
    private EntityManager em;
    private EntityTransaction et;
    
    public TestePessoa()
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
    public void testarMetodoContainsOveride_Telefone()
    {            
        Pessoa pessoaAntiga = em.find(Pessoa.class, 5); //guarda referencia           
        Pessoa pessoaNova = em.find(Pessoa.class, 5); //a ser alterado
        
        //busca telefones da pessoa 5
        TypedQuery<Telefone> query2;
        query2 = em.createQuery("SELECT c FROM Telefone c WHERE c.pessoa.id = ?1", Telefone.class);
        query2.setParameter(1, 5);
        List<Telefone> telefones = query2.getResultList(); 
        
        //CRIA NOVO TELEFONE, ADD em p2 
        Telefone t = new Telefone(TelefoneCategoria.celular, "81", "988846448");
        t.setId(6);
        telefones.add(t);
        pessoaNova.setTelefones(telefones);
        
        System.out.println("fones pessoaAntiga: " + pessoaAntiga.getTelefones().size() + "fones pessoaNova: "+ pessoaNova.getTelefones().size());
        
        assertNotEquals(pessoaAntiga, pessoaNova); //o equals compara referencia, se eu quiser q ele olhe pros campos, preciso sobrescrever o equals, la em pessoa
    }
    
    @Test
    public void testarQuantidadeTelefones()
    { 
        TypedQuery<Telefone> query;
        query = em.createQuery("SELECT c FROM Telefone c WHERE c.pessoa.id = ?1", Telefone.class);
        query.setParameter(1, 5);
        List<Telefone> telefones = query.getResultList();        
        assertEquals(3, telefones.size());  
    }
    
    @Test
    public void testaCerimoniaPessoa()
    {
        Pessoa p = em.find(Pessoa.class, 5);        
        int idCerimonia = p.getCerimonia().getId();
        assertEquals(2, idCerimonia);        
    }
    
}
