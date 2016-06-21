package servico;

import entidades.Convidado;
import entidades.ProdutorDeMidia;
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
public class ProdutorDeMidiaServico extends Servico
{
    public void salvar(ProdutorDeMidia produtor)
    {
        em.flush();
        
        if(existente(produtor.getEmail()) == false)
            em.persist(produtor);
        
    }

    public List<ProdutorDeMidia> listar()
    {
        TypedQuery<ProdutorDeMidia> query = em.createQuery("SELECT c FROM ProdutorDeMidia c", ProdutorDeMidia.class);
        List<ProdutorDeMidia> produtores = query.getResultList();

        return produtores;
    }

    public void remover(ProdutorDeMidia produtor)
    {
        ProdutorDeMidia c = (ProdutorDeMidia) em.find(ProdutorDeMidia.class, produtor.getId()); //se n tiver isso, o jpa acha que n deatachou        
        em.remove(c);
        em.flush();
    }

    public void atualizar(ProdutorDeMidia produtor)
    {
        em.flush();
        em.merge(produtor);
    }

    public boolean existente(ProdutorDeMidia produtor)
    {
        em.flush();
        return listar().contains(produtor);
    }
    
     private boolean existente(String email)
    {       
        TypedQuery<ProdutorDeMidia> query;
        query = em.createQuery("select b from Pessoa b where b.email like ?1", ProdutorDeMidia.class);
        query.setParameter(1, email);
        List<ProdutorDeMidia> produtores = query.getResultList();

        if (produtores.isEmpty())
        {
            return false;
        }

        return true;
    }
}
