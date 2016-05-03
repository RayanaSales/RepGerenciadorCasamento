package testes;

import entidades.ProdutorDeMidia;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TesteProdutorDeMidia
{
    private static EntityManagerFactory emf;
    private EntityManager em;
    private EntityTransaction et;

    public TesteProdutorDeMidia()
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

//    @Test
//    public void testaPrecoValido()
//    { //não traz entidade nenhuma do banco
//        
//            //ProdutorDeMidia pm = em.find(ProdutorDeMidia.class, 1);
//            TypedQuery<ProdutorDeMidia> query = em.createQuery("SELECT l FROM ProdutorDeMidia l WHERE l.id = 1", ProdutorDeMidia.class);
//            ProdutorDeMidia pm = query.getSingleResult();
//
//            ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
//            Validator validator = validatorFactory.getValidator();
//            Set<ConstraintViolation<ProdutorDeMidia>> constraintViolations = validator.validate(pm);
//
//            assertEquals(0, constraintViolations.size());           
//    }
//    @Test
//    public void testaPrecoInvalido()
//    { //espera um, mas vem 5 .----.        
//        Date dataCerimonia = Calendar.getInstance().getTime();
//        Cerimonia c = new Cerimonia();
//        c.setData(dataCerimonia);
//        Pessoa p = new Pessoa("Luna", "lunabandeira@gmail.com", c);
//        ProdutorDeMidia pm = new ProdutorDeMidia(ProdutorDeMidiaCategoria.fotografo, p, 0.0, dataCerimonia, "lalaalal");
//        
//        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
//        Validator validator = validatorFactory.getValidator();
//        Set<ConstraintViolation<ProdutorDeMidia>> constraintViolations = validator.validate(pm);
//
//        System.out.println("invalidos: " + constraintViolations.toString());
//        
//        assertEquals(1, constraintViolations.size());
//        ConstraintViolation<ProdutorDeMidia> violation = constraintViolations.iterator().next();
//        assertEquals("Deve ser maior que 0. Pode conter ponto.", violation.getMessage());
//    }
    
//    @Test
//    public void horaChegadaDiferenteQuehoraCerimonia()
//    { // nao traz nda do banco
//        
//        ProdutorDeMidia pm = em.find(ProdutorDeMidia.class, 2);       
//        System.out.println("Produtor midia vindo do banco: " + pm.getNome());
//        
//        Date horaCerimonia = pm.getPessoa().getCerimonia().getData();
//        Date horaChegadaProdutor = pm.getDataEHoraChegada();
//        
//        Assert.assertNotEquals(horaCerimonia, horaChegadaProdutor);
//    }
    
//    @Test
//    public void urlValida()
//    { //espera 0, mas vem 5
//        Date dataCerimonia = Calendar.getInstance().getTime();
//        Cerimonia c = new Cerimonia();
//        c.setData(dataCerimonia);
//        Pessoa p = new Pessoa("Luna", "lunabandeira@gmail.com", c);
//        ProdutorDeMidia pm = new ProdutorDeMidia(ProdutorDeMidiaCategoria.filmografia, p, 7.000, dataCerimonia, "www.lunalunatica.com.br");
//   
//        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
//        Validator validator = validatorFactory.getValidator();
//        Set<ConstraintViolation<ProdutorDeMidia>> constraintViolations = validator.validate(pm);
//
//        assertEquals(0, constraintViolations.size());    
//    }
}
