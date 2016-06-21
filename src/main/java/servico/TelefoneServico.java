package servico;

import entidades.Presente;
import entidades.Telefone;
import enumeracoes.TelefoneCategoria;
import java.util.ArrayList;
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
public class TelefoneServico extends Servico
{
    public void salvar(Telefone telefone)
    {
        em.flush();
        
        if(existente(telefone.getNumero()) == false)
            em.persist(telefone);
    }
    
    public List<Telefone> listarTelefonesPorCategoria(TelefoneCategoria categoria)
    {
        List<Telefone> telefonesTotais = listar();
        List<Telefone> telefonesEmpresariais = new ArrayList<>();

        for (Telefone telefone : telefonesTotais)
        {
            if (telefone.getCategoria() == categoria)
            {
                telefonesEmpresariais.add(telefone);
            }
        }
        return telefonesEmpresariais;
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
        em.flush();
        em.merge(telefone);
    }

    private boolean existente(String numero)
    {       
        TypedQuery<Telefone> query;
        query = em.createQuery("select b from Telefone b where b.numero like ?1", Telefone.class);
        query.setParameter(1, numero);
        List<Telefone> telefones = query.getResultList();

        if (telefones.isEmpty())
        {
            return false;
        }

        return true;
    }
}
