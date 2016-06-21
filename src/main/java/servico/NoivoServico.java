package servico;

import entidades.Convidado;
import entidades.Noivo;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import static javax.ejb.TransactionManagementType.CONTAINER;
import javax.persistence.TypedQuery;

@Stateless
@TransactionManagement(CONTAINER)
public class NoivoServico extends Servico
{
    public void salvar(Noivo noivo)
    {       
        em.flush();
        
        if(existente(noivo.getEmail()) == false)
            em.persist(noivo);   
        
    }
    
    public List<Noivo> listar()
    {
        return em.createQuery("select t from Noivo AS t", Noivo.class).getResultList();
    }
    
    public void remover(Noivo noivo)
    {
        if(existente(noivo) == true)
        {
            Noivo t = (Noivo) em.find(Noivo.class, noivo.getId()); //se n tiver isso, o jpa acha que n deatachou        
            em.remove(t); 
            em.flush();
        }
        else System.out.println("Noivo não existe");
    }

    public void atualizar(Noivo noivo)
    {
        em.flush();
        em.merge(noivo);        
    }
    
    public boolean existente(Noivo noivo)
    {
        em.flush();
        return listar().contains(noivo);
    }
    
    private boolean existente(String email)
    {       
        TypedQuery<Convidado> query;
        query = em.createQuery("select b from Pessoa b where b.email like ?1", Convidado.class);
        query.setParameter(1, email);
        List<Convidado> pessoas = query.getResultList();

        if (pessoas.isEmpty())
        {
            return false;
        }

        return true;
    }
}
