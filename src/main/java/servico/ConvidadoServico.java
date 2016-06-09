package servico;

import entidades.Convidado;
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
public class ConvidadoServico extends Servico
{
    public void salvar(Convidado convidado)
    {
        em.persist(convidado);
        em.flush();
    }

    public List<Convidado> listar()
    {     
        TypedQuery<Convidado> query = em.createQuery("SELECT c FROM Convidado c", Convidado.class);
        List<Convidado> convidado = query.getResultList();
        
        return convidado;
    }
    
    public void remover(Convidado convidado)
    {
        Convidado c = (Convidado) em.find(Convidado.class, convidado.getId()); //se n tiver isso, o jpa acha que n deatachou        
        em.remove(c);
        em.flush();
    }

    public void atualizar(Convidado convidado)
    {
        em.flush();
        em.merge(convidado);
    }

    public boolean existente(Convidado convidado)
    {
        em.flush();
        return listar().contains(convidado);
    }
}
