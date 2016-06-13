package servico;

import entidades.Pessoa;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
public class PessoaServico extends Servico
{
     public List<Pessoa> listar()
    {
        return em.createQuery("select p from Pessoa AS p", Pessoa.class).getResultList();
    }
}
