package servico;

import entidades.Loja;
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
public class LojaServico extends Servico
{
    public void salvar(Loja loja)
    {
        em.persist(loja);
        em.flush();
    }

    public List<Loja> listar()
    {     
        TypedQuery<Loja> query = em.createQuery("SELECT c FROM Loja c", Loja.class);
        List<Loja> lojas = query.getResultList();
        
        return lojas;
    }
    
    public void remover(Loja loja)
    {
        Loja c = (Loja) em.find(Loja.class, loja.getId()); //se n tiver isso, o jpa acha que n deatachou        
        em.remove(c);
        em.flush();
    }

    public void atualizar(Loja loja)
    {
        em.flush();
        em.merge(loja);
    }

    public boolean existente(Loja loja)
    {
        em.flush();
        return listar().contains(loja);
    }
}
