package servico;

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
        em.persist(produtor);
        em.flush();
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
        em.merge(produtor);
        em.flush();
    }

    public boolean existente(ProdutorDeMidia produtor)
    {
        em.flush();
        return listar().contains(produtor);
    }
}
