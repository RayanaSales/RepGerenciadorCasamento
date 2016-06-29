package servico;

import entidades.Grupo;
import entidades.Localizacao;
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
public class GrupoServico extends Servico
{
    public void salvar(Grupo grupo) throws ExcecaoNegocio
    {
        em.flush();
        if (existente(grupo.getNome()) == false)        
            em.persist(grupo);
        else throw new ExcecaoNegocio(ExcecaoNegocio.OBJETO_EXISTENTE);
    }
    
    private boolean existente(String nome)
    {
        TypedQuery<Grupo> query;
        query = em.createQuery("select b from Grupo b where b.nome like ?1", Grupo.class);
        query.setParameter(1, nome);
        List<Grupo> grupos = query.getResultList();

        if (grupos.isEmpty())
        {
            return false;
        }

        return true;
    }

    public List<Grupo> listar()
    {
       return em.createQuery("select g from Grupo AS g", Grupo.class).getResultList();   
    }
}
