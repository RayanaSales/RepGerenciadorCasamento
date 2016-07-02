package servico;

import entidades.Convidado;
import entidades.Grupo;
import entidades.Noivo;
import entidades.ProdutorDeMidia;
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
public class GrupoServico extends Servico
{
    public void associarGrupoNoivo(Noivo pessoa)
    {
        List<Grupo> gruposDaPessoa = new ArrayList<>();
        Grupo grupoPessoa = buscar(1);
        gruposDaPessoa.add(grupoPessoa);
        
        pessoa.setGrupos(gruposDaPessoa);     
        em.merge(pessoa);
    }
    
    public void associarGrupoConvidado(Convidado pessoa)
    {
        List<Grupo> gruposDaPessoa = new ArrayList<>();
        Grupo grupoPessoa = buscar(3);
        gruposDaPessoa.add(grupoPessoa);
        
        pessoa.setGrupos(gruposDaPessoa);     
        em.merge(pessoa);
    }
    
    public void associarGrupoProdutor(ProdutorDeMidia pessoa)
    {
        List<Grupo> gruposDaPessoa = new ArrayList<>();
        Grupo grupoPessoa = buscar(2);
        gruposDaPessoa.add(grupoPessoa);
        
        pessoa.setGrupos(gruposDaPessoa);     
        em.merge(pessoa);
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

    public Grupo buscar(int id)
    {
        return em.find(Grupo.class, id);
    }
}
