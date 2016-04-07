//package testes;
//
//import entidades.*;
//import java.util.List;
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.Persistence;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//public class Main
//{    
//    EntityManagerFactory emf = null;
//    EntityManager em = null;
//    
//    @Before
//    public void setUp()
//    {// Antes que os testes rodem, processe algo barato nessa classe 
//        emf = Persistence.createEntityManagerFactory("casamento");
//        em = emf.createEntityManager();
//    }
//    
//    @After
//    public void tearDown()
//    {// Depois que os testes rodarem, processe algo barato nessa classe 
//        try
//        {
//            //emf.close(); //isso n se fecha
//            em.close();
//        } catch (Exception e)
//        {e.getMessage();}
//
//    }
//    
//    @Test // caso de teste, deve ser bem independente
//    public void criarCerimonia()
//    {   
//        Localizacao l = Montar.montarLocal();
//        Buffet b = Montar.montarBuffet();
//       
//        Cerimonia c = new Cerimonia();
//
//        List<Presente> presentes = Montar.montarListaPresentes(c);
//        List<Noivo> casal = Montar.montarCasal(c);
//        List<Convidado> convidados = Montar.convidarPessoas(c);
//        List<ProdutorDeMidia> produtoresDeMidia = Montar.montarProdutorMidia(c);
//       
//        c = Montar.montarCerimonia(c, l, b, produtoresDeMidia, presentes, casal, convidados);
//
//        try
//        {
//            em.getTransaction().begin();
//            em.persist(c);
//            em.getTransaction().commit();
//        } catch (Exception e)
//        {
//            em.getTransaction().rollback();
//            e.printStackTrace();
//        }
//    }
//}
