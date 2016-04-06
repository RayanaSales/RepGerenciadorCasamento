/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;

import entidades.Buffet;
import entidades.Cerimonia;
import entidades.Convidado;
import entidades.Localizacao;
import entidades.Noivo;
import entidades.Presente;
import entidades.ProdutorDeMidia;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class TesteCerimonia
{
    EntityManagerFactory emf = null;
    EntityManager em = null;
    
    public TesteCerimonia()
    {
    }
    
    @BeforeClass
    public static void setUpClass()
    {
        DbUnitUtil.inserirDados();
    }
    
    @AfterClass
    public static void tearDownClass()
    {
    }
    
    @Before
    public void setUp()
    {// Antes que os testes rodem, processe algo barato nessa classe 
        emf = Persistence.createEntityManagerFactory("casamento");
        em = emf.createEntityManager();
    }
    
    @After
    public void tearDown()
    {// Depois que os testes rodarem, processe algo barato nessa classe 
        try
        {
            //emf.close(); //isso n se fecha
            em.close();
        } catch (Exception e)
        {e.getMessage();}

    }

    @Test // caso de teste, deve ser bem independente
    public void criarCerimonia()
    {   
        Localizacao l = Montar.montarLocal();
        Buffet b = Montar.montarBuffet();
       
        Cerimonia c = new Cerimonia();

        List<Presente> presentes = Montar.montarListaPresentes(c);
        List<Noivo> casal = Montar.montarCasal(c);
        List<Convidado> convidados = Montar.convidarPessoas(c);
        List<ProdutorDeMidia> produtoresDeMidia = Montar.montarProdutorMidia(c);
       
        c = Montar.montarCerimonia(c, l, b, produtoresDeMidia, presentes, casal, convidados);

        try
        {
            em.getTransaction().begin();
            em.persist(c);
            em.getTransaction().commit();
        } catch (Exception e)
        {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }
}
