package testes;

import entidades.Noivo;
import entidades.Pessoa;
import entidades.Telefone;
import java.util.List;
import javax.persistence.TypedQuery;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;

/**
 *
 * @author rayana
 */
public class TestePessoa extends Teste
{
    @Test
    public void testarPessoaConhecida()
    {
        Pessoa p = em.find(Pessoa.class, 5);        
        assertEquals("Marlene", p.getNome());
    }
    
    @Test
    public void testarPessoaDesconhecida()
    {
        Pessoa p = em.find(Pessoa.class, 5);        
        assertNotEquals("Julia", p.getNome());
    }
    
    @Test
    public void testarEmailConhecido()
    {
        Pessoa p = em.find(Pessoa.class, 4);        
        assertEquals("letranafoto@hotmail.com", p.getEmail());
    }
    
    @Test
    public void testarEmailDesconhecido()
    {
        Pessoa p = em.find(Pessoa.class, 4);        
        assertNotEquals("letranafotoooo@hotmail.com", p.getEmail());
    }

    @Test
    public void testarQuantidadeTelefones()
    {
        TypedQuery<Telefone> query;
        query = em.createQuery("SELECT c FROM Telefone c WHERE c.pessoa.id = ?1", Telefone.class);
        query.setParameter(1, 5);
        List<Telefone> telefones = query.getResultList();
        assertEquals(3, telefones.size());
    }

    @Test
    public void testaCerimoniaValida()
    {
        Pessoa p = em.find(Pessoa.class, 5);
        int idCerimonia = p.getCerimonia().getId();
        assertEquals(2, idCerimonia);
    }

    @Test
    public void testaCerimoniaInvalida()
    {
        Pessoa p = em.find(Pessoa.class, 5);
        int idCerimonia = p.getCerimonia().getId();
        assertNotEquals(3, idCerimonia);
    }
         
     @Test 
    public void atualizarPessoa()
    {
        Pessoa p = em.find(Pessoa.class, 11);
        p.setNome("Luana");
        em.merge(p);
        p = em.find(Pessoa.class, 11);
        assertEquals("Luana", p.getNome());
    }
        
    @Test
    public void deletarPessoa()
    {        
        Pessoa b = em.find(Pessoa.class, 12);
        em.remove(b);        
        b = em.find(Pessoa.class, 12);
        assertNull(b);
        
    }
}
