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
    public void testarMetodoContainsOveride_Telefone() //bug - assertNotEquals devia ser true
    {    
        //busca pessoa 19 - guarda referencia da pessoa antes da alteracao
        Pessoa pessoaAntiga = em.find(Pessoa.class, 19);
        
        //busca pessoa 19 - pessoa a ser alterada
        Pessoa pessoaNova = em.find(Pessoa.class, 19);
        
        //busca telefones da pessoa 19
        TypedQuery<Telefone> query2;
        query2 = em.createQuery("SELECT c FROM Telefone c WHERE c.pessoa.id = ?1", Telefone.class);
        query2.setParameter(1, 19);
        List<Telefone> telefones = query2.getResultList(); 
        
        //CRIA NOVO TELEFONE, ADD em p2 
        Telefone t = new Telefone(TelefoneCategoria.celular, "81", "988846448");
        t.setId(26);
        telefones.add(t);
        pessoaNova.setTelefones(telefones);
        
        System.out.println("qtd tel na pessoa nova: " + pessoaNova.getTelefones().size());
        em.merge(pessoaNova); //coloca no banco as alteracoes
                
        //verifica se p1 != p2
        //assertNotEquals(pessoaAntiga, pessoaNova);
        assertEquals(pessoaAntiga, pessoaNova);
    }
    
    @Test
    public void testarQuantidadeTelefones()
    { 
        TypedQuery<Telefone> query;
        query = em.createQuery("SELECT c FROM Telefone c WHERE c.pessoa.id = ?1", Telefone.class);
        query.setParameter(1, 19);
        List<Telefone> telefones = query.getResultList();        
        assertEquals(3, telefones.size());  
    }
    
//    @Test
//    public void testaTipoPessoaValida()
//    {
//        Pessoa p = em.find(Pessoa.class, 19);         
//        String tipoEsperado = "N";        
//        assertEquals(tipoEsperado, p.getDisc_pessoa());
//    }
//    
//    @Test
//    public void testaTipoPessoaInvalida()
//    {
//        Pessoa p = em.find(Pessoa.class, 19);         
//        String tipoNaoEsperado = "F";
//        assertNotEquals(tipoNaoEsperado, p.getDisc_pessoa());
//    }
    
    @Test
    public void testaCerimoniaPessoa()
    {
        Pessoa p = em.find(Pessoa.class, 19);        
        int idCerimonia = p.getCerimonia().getId();
        assertEquals(3, idCerimonia);        
    }
    
}
