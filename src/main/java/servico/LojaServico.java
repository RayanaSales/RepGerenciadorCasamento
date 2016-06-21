package servico;

import entidades.Convidado;
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
public class LojaServico extends Servico {

    public boolean salvar(Loja loja) {
        // Quando o objeto não existe no banco o getSingleResult() lança uma exceção, por isso
        // o código está dessa forma, ai entra no catch e salva.
        try {
            TypedQuery query = em.createQuery("SELECT c FROM Loja c WHERE c.cnpj = ?1 ", Loja.class);
            query.setParameter(1, loja.getCnpj());
            Loja l = (Loja) query.getSingleResult();

            return false;
        } catch (Exception e) {
            
            if(existente(loja.getCnpj()) == false)
                em.persist(loja);
            
            em.flush();
            return true;
        }

    }

    public List<Loja> listar() {
        TypedQuery<Loja> query = em.createQuery("SELECT c FROM Loja c", Loja.class);
        List<Loja> lojas = query.getResultList();

        return lojas;
    }

    public void remover(Loja loja) {
        Loja c = (Loja) em.find(Loja.class, loja.getId()); //se n tiver isso, o jpa acha que n deatachou        
        em.remove(c);
        em.flush();
    }

    public boolean atualizar(Loja loja) {

        try {
            TypedQuery query = em.createQuery("SELECT c FROM Loja c WHERE c.cnpj = ?1 ", Loja.class);
            query.setParameter(1, loja.getCnpj());
            Loja l = (Loja) query.getSingleResult();

            return false;
        } catch (Exception e) {
            em.flush();
            em.merge(loja);
            return true;
        }

    }
    
    private boolean existente(String cnpj)
    {       
        TypedQuery<Loja> query;
        query = em.createQuery("select b from Loja b where b.cnpj like ?1", Loja.class);
        query.setParameter(1, cnpj);
        List<Loja> lojas = query.getResultList();

        if (lojas.isEmpty())
        {
            return false;
        }

        return true;
    }
}
