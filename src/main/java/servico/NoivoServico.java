package servico;

import entidades.Noivo;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import static javax.ejb.TransactionManagementType.CONTAINER;

@Stateless
@TransactionManagement(CONTAINER)
public class NoivoServico extends Servico
{
    public void salvar(Noivo noivo)
    {       
        em.flush();
        em.persist(noivo);   
        em.flush();
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
}
