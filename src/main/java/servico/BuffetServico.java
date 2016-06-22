package servico;

import entidades.Buffet;
import excecao.ExcecaoNegocio;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.TypedQuery;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
public class BuffetServico extends Servico
{

    public void salvar(Buffet buffet) throws ExcecaoNegocio
    {
       

        if (existente(buffet.getValorTotalGasto()) == false)
        {
            em.persist(buffet);
        }
        else throw new ExcecaoNegocio(ExcecaoNegocio.OBJETO_EXISTENTE);

        
    }

    public List<Buffet> listar()
    {
        em.flush();
        return em.createQuery("select t from Buffet AS t", Buffet.class).getResultList();
    }
    
    public List<Buffet> listarComComesBebes()
    {
        em.flush(); 
        return em.createQuery("select t from Buffet AS t JOIN FETCH t.comesBebes", Buffet.class).getResultList();
    }


    public void remover(Buffet buffet)
    {
        Buffet t = (Buffet) em.find(Buffet.class, buffet.getId()); //se n tiver isso, o jpa acha que n deatachou        
        em.remove(t);
    }

    public void atualizar(Buffet buffet) throws ExcecaoNegocio
    {
         em.flush();

        if (existente(buffet.getValorTotalGasto()) == false)
        {
            em.merge(buffet);
        }
        else throw new ExcecaoNegocio(ExcecaoNegocio.OBJETO_EXISTENTE);
      
    }

    public Buffet buscar(int id)
    {
        return (Buffet) em.find(Buffet.class, id);
    }

    private boolean existente(double valor)
    {
        System.out.println("servico.BuffetServico.existente()");
        TypedQuery<Buffet> query;
        query = em.createQuery("select b from Buffet b where b.valorTotalGasto = ?1", Buffet.class);
        query.setParameter(1, valor);
        List<Buffet> buffets = query.getResultList();

        if (buffets.isEmpty())
        {
            return false;
        }

        return true;
    }

}
