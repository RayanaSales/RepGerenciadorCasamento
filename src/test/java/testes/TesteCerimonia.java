package testes;

import entidades.Buffet;
import entidades.Cerimonia;
import entidades.ComesBebes;
import entidades.Localizacao;
import entidades.Loja;
import entidades.Pessoa;
import entidades.Telefone;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
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
        TypedQuery<Long> query = em.createQuery("SELECT COUNT(c) FROM Cerimonia c WHERE c.id IS NOT NULL", Long.class);
        Long resultado = query.getSingleResult();
        assertEquals(new Long(2), resultado);
    }

    @Test
    public void t04_deletarBuffet() throws Exception
    {
        Buffet b = em.find(Buffet.class, 10);
        em.remove(b);        
        b = em.find(Buffet.class, 10);
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
    public void t06_buscarNoivosDeUmaCerimonia() throws Exception
    {
        TypedQuery<Pessoa> query;
        query = em.createQuery("SELECT p FROM Pessoa p WHERE p.cerimonia.id = ?1 AND p.disc_pessoa like ?2", Pessoa.class);
        query.setParameter(1, 1); //o id da cerimonia eh int entao n pode mandar string
        query.setParameter(2, "N");
        List<Pessoa> noivos = query.getResultList();        
        assertEquals(2, noivos.size());        
    }
  
    @Test
    public void t07_testeAtualizarTelefone() throws Exception 
    {           
        String NovoNumeroTelefone = "998877665";               
        Telefone t = em.find(Telefone.class, 6);
        t.setNumero(NovoNumeroTelefone);        
        em.merge(t);        
        assertEquals(t.getNumero() , NovoNumeroTelefone);
    }    
  
    @Test
    public void t08_buscarTelefonesDoTipoCelular() throws Exception
    {
        TypedQuery<Telefone> query = em.createQuery("SELECT t FROM Telefone t WHERE t.categoria = :categoria",
                Telefone.class); //like compara string, nesse caso eh uma enum,logo usa o =    
       
        //query.setParameter("categoria", TelefoneCategoria.celular); //n pode mandar como string, manda como enum
        query.setParameter("categoria", "celular"); //pq n to usando string no momento
        
        List<Telefone> telefones = query.getResultList();

        for (Telefone telefone : telefones) 
            assertEquals("celular", telefone.getCategoria().toString());        
    } 
    
    @Test
    public void t09_buscarLoja() throws Exception
    {
        Loja c = em.find(Loja.class, 1);
        assertEquals(1, c.getId());
    }
    
    @Test 
    public void t10_qntComesBebesNoBuffet() throws Exception
    {
        TypedQuery<ComesBebes> query;
        query = em.createQuery("SELECT c FROM ComesBebes c WHERE c.buffet.id = ?1", ComesBebes.class);
        query.setParameter(1, 1);
        List<ComesBebes> comesBebes = query.getResultList();        
        assertEquals(5, comesBebes.size());         
    }
    
//    @Test
//    public void t11_valorTotalDasRoupasDosNoivos() throws Exception
//    {
//        TypedQuery<Long> query = 
//                em.createQuery("SELECT r.valor FROM RoupaDosNoivos r WHERE r.noivo.pessoa.cerimonia.id = ?1", Long.class);        
//        query.setParameter(1, 2);
//        
//        List<Long> valores = query.getResultList();        
//        Long somaTotal = new Long(0);        
//        for(Long s : valores)             
//            somaTotal += s;
//        
//        assertEquals(new Long(12), somaTotal);
//    }
    
    @Test
    public void t12_qntConvidadosCerimonia() throws Exception
    {
        TypedQuery<Integer> query = 
                em.createQuery("SELECT c.qntSenhas FROM Convidado c WHERE c.pessoa.cerimonia.id = ?1", Integer.class);
        query.setParameter(1, 2);
        
        List<Integer> senhas = query.getResultList();        
        int somaTotal = 0;        
        for(Integer s : senhas)             
            somaTotal += s;
        
        assertEquals(12, somaTotal);
    }
    
    @Test
    public void t13_lojasEmRecife() throws Exception
    {
        Query query = em.createNativeQuery("SELECT l FROM Loja l INNER JOIN Localizacao local ON l.id_localizacao = local.id AND local.txt_cidade LIKE ?1");
        query.setParameter(1, "recife%");
        List<Loja> lojaEmRecife = query.getResultList();
        assertEquals(2, lojaEmRecife.size());
    }
    
    
    @Test
    public void t14_criarTelefoneCategoriaValida() throws Exception
    {
        Telefone telefone = new Telefone("residencial", "81", "89764567");
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<Telefone>> constraintViolations = validator.validate(telefone);
        assertEquals(0, constraintViolations.size());
        
    }
    
    @Test
    public void t15_criarTelefoneCategoriaInvalida() throws Exception
    {
        Telefone telefone = new Telefone("residencia", "81", "89764567");
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<Telefone>> constraintViolations = validator.validate(telefone);
        assertEquals(1, constraintViolations.size());
        ConstraintViolation<Telefone> violation = constraintViolations.iterator().next();
        assertEquals("Categoria telefone inválido.", violation.getMessage());
        
        
    }
}
