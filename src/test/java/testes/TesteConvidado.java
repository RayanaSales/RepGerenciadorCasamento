/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;

import entidades.Convidado;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author rayana
 */
public class TesteConvidado
{
    private static EntityManagerFactory emf;
    private EntityManager em;
    // private EntityTransaction et;

    public TesteConvidado()
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
    public void testarBuscarConvidado()
    {
        Convidado n = em.find(Convidado.class, 3);

        if (n != null)
        {
            System.out.println("Convidado dif nuuuuuuuuuuuuuuuuul - heranca funcionando");
        } else
        {
            System.out.println("Convidado nuuuuuuuuuuuuuuuuul");
        }
    }
}
