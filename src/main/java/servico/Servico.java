package servico;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

public class Servico
{
  //  protected static EntityManagerFactory emf;
    @PersistenceContext(unitName = "casamento", type = PersistenceContextType.TRANSACTION)
    protected EntityManager em;
   // private EntityTransaction et;

    public Servico()
    {
        
    }
/*
    public static void encerrarServico()
    {
        emf.close();
    }

    public void abrirTransacao()
    {
        emf = Persistence.createEntityManagerFactory("casamento");
        //DbUnitUtil.inserirDados();
        
        em = emf.createEntityManager();
        et = em.getTransaction();
        et.begin();
    }

    public void fecharTransacao()
    {
        try
        {
            if (et != null && et.isActive())
            {
                et.commit();
            }
        } catch (Exception ex)
        {
            et.rollback();
        } finally
        {
            em.close();
        }
    } */
}
