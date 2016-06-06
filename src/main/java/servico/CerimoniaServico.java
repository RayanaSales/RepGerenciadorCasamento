package servico;

import entidades.Cerimonia;
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
public class CerimoniaServico extends Servico
{
    public void salvar(Cerimonia cerimonia)
    {
        em.persist(cerimonia);
        em.flush();
    }

    public List<Cerimonia> listar()
    {     
        TypedQuery<Cerimonia> query = em.createQuery("SELECT c FROM Cerimonia c", Cerimonia.class);
        List<Cerimonia> cerimonias = query.getResultList();
        
        return cerimonias;
    }
    
    public void remover(Cerimonia cerimonia)
    {
        Cerimonia c = (Cerimonia) em.find(Cerimonia.class, cerimonia.getId()); //se n tiver isso, o jpa acha que n deatachou        
        em.remove(c);
        em.flush();
    }

    public void atualizar(Cerimonia cerimonia)
    {
        em.merge(cerimonia);
        em.flush();
    }

    public boolean existente(Cerimonia cerimonia)
    {
        em.flush();
        return listar().contains(cerimonia);
    }
}
