package servico;

import entidades.Convidado;
import entidades.Noivo;
import excecao.ExcecaoNegocio;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import static javax.ejb.TransactionManagementType.CONTAINER;
import javax.persistence.TypedQuery;

@Stateless
@TransactionManagement(CONTAINER)
public class NoivoServico extends Servico
{

    public void salvar(Noivo noivo) throws ExcecaoNegocio
    {
       
        if (existente(noivo.getEmail()) == false)
        {
            em.persist(noivo);
        } else
        {
            throw new ExcecaoNegocio(ExcecaoNegocio.OBJETO_EXISTENTE);
        }
    }

    public List<Noivo> listar()
    {
        return em.createQuery("select t from Noivo AS t", Noivo.class).getResultList();
    }

    public void remover(Noivo noivo)
    {
        Noivo t = (Noivo) em.find(Noivo.class, noivo.getId()); //se n tiver isso, o jpa acha que n deatachou        
        em.remove(t);
    }

    public void atualizar(Noivo noivo) throws ExcecaoNegocio
    {
        em.flush();
        if (existente(noivo.getEmail()) == false)
        {
            em.merge(noivo);
        } else
        {
            throw new ExcecaoNegocio(ExcecaoNegocio.OBJETO_EXISTENTE);
        }
    }

    public boolean existente(Noivo noivo)
    {
        em.flush();
        return listar().contains(noivo);
    }

    private boolean existente(String email)
    {
        TypedQuery<Noivo> query;
        query = em.createQuery("select b from Pessoa b where b.email like ?1", Noivo.class);
        query.setParameter(1, email);
        List<Noivo> pessoas = query.getResultList();

        if (pessoas.isEmpty())
        {
            return false;
        }

        return true;
    }
}
