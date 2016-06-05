package servico;

import entidades.Telefone;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
public class TelefoneServico extends Servico
{
    public void salvar(Telefone telefone)
    {
        em.persist(telefone);
    }

    public List<Telefone> listar()
    {
        return em.createQuery("select t from Telefone AS t", Telefone.class).getResultList();
    }

    public void remover(Telefone telefone)
    {
        Telefone t = (Telefone) em.find(Telefone.class, telefone.getId()); //se n tiver isso, o jpa acha que n deatachou        
        em.remove(t);
    }

    public void atualizar(Telefone telefone)
    {
        em.merge(telefone);
    }

    public boolean existente(Telefone telefone)
    {
        return listar().contains(telefone);
    }
}
