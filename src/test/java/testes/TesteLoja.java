package testes;

import entidades.Localizacao;
import entidades.Loja;
import entidades.Telefone;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
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


public class TesteLoja
{
    private static EntityManagerFactory emf;
    private EntityManager em;
    private EntityTransaction et;
    
    public TesteLoja()
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
//        et = em.getTransaction();
//        et.begin();
    }

    @After
    public void tearDown()
    {
//        try
//        {
//            et.commit();
//        } catch (Exception ex)
//        {
//            et.rollback();
//        } finally
//        {
        em.close();
        //}
    }    
    
 
    @Test
    public void testarTelefoneDaLoja(){
        
        TypedQuery<Telefone> query;
        query = em.createQuery("SELECT t FROM Telefone t Where t.id = 2 ", Telefone.class);
        Telefone telefoneEsperado = query.getSingleResult();
       
        query = em.createQuery("SELECT l.telefone FROM Loja l Where l.id = ?1 ", Telefone.class);
        query.setParameter(1, 1);
        Telefone telefoneAtual = query.getSingleResult();
        assertEquals(telefoneEsperado, telefoneAtual);
    }
    
    @Test
    public void testarLocalizacaoDaLoja(){
        
        TypedQuery<Localizacao> query;
        query = em.createQuery("SELECT l FROM Localizacao l Where l.id = 2 ", Localizacao.class);
        Localizacao localizacaoEsperada = query.getSingleResult();
       
        query = em.createQuery("SELECT l.localizacao FROM Loja l Where l.id = 1 ", Localizacao.class);
        Localizacao localizacaoAtual = query.getSingleResult();
        assertEquals(localizacaoEsperada, localizacaoAtual);
    }
  
    @Test
    public void testaQuantidadeDeLojas(){
    
        TypedQuery<Loja> query;
        query = em.createQuery("SELECT l FROM Loja l", Loja.class);
        List<Loja> lojas = query.getResultList();
        assertEquals(1, lojas.size());
    
    }
    
    @Test
    public void testaNomeDaLoja()
    {
        Loja l = new Loja("aaaaa", null, null);
        
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<Loja>> constraintViolations = validator.validate(l);
        
        ConstraintViolation<Loja> violation = constraintViolations.iterator().next();
        assertEquals("Deve possuir uma única letra maiúscula, seguida por letras minúsculas.", violation.getMessage());
    
    }
}
