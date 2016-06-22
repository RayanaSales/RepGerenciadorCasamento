package servico;

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
public class LocalizacaoServico extends Servico
{
    public void salvar(Localizacao localizacao) throws ExcecaoNegocio
    {
        em.flush();
        if (existente(localizacao.getLogradouro()) == false)        
            em.persist(localizacao);
        else throw new ExcecaoNegocio(ExcecaoNegocio.OBJETO_EXISTENTE);
    }

    public List<Localizacao> listar()
    {
        return em.createQuery("select t from Localizacao AS t", Localizacao.class).getResultList();
    }

    public void remover(Localizacao telefone)
    {
        Localizacao t = (Localizacao) em.find(Localizacao.class, telefone.getId()); //se n tiver isso, o jpa acha que n deatachou        
        em.remove(t);
        em.flush();
    }

    public void atualizar(Localizacao telefone)
    {
        em.merge(telefone);
        //       em.flush();
    }

    public boolean existente(Localizacao telefone)
    {
        em.flush();
        return listar().contains(telefone);
    }

    private boolean existente(String logradouro)
    {
        TypedQuery<Localizacao> query;
        query = em.createQuery("select b from Localizacao b where b.logradouro like ?1", Localizacao.class);
        query.setParameter(1, logradouro);
        List<Localizacao> locais = query.getResultList();

        if (locais.isEmpty())
        {
            return false;
        }

        return true;
    }
}
