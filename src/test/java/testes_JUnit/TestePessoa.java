package testes_JUnit;

import entidades.Cerimonia;
import entidades.Pessoa;
import entidades.Telefone;
import enumeracoes.TelefoneCategoria;
import java.util.ArrayList;
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
        Pessoa p = this.montarPessoa();
        assertEquals("Lucia", p.getNome());
    }
    
    @Test
    public void testarPessoaDesconhecida()
    {
        Pessoa p = this.montarPessoa();
        assertNotEquals("Julia", p.getNome());
    }
    
    @Test
    public void testarEmailConhecido()
    {
        Pessoa p = this.montarPessoa();
        assertEquals("lucia@hotmail.com", p.getEmail());
    }
    
    @Test
    public void testarEmailDesconhecido()
    {
        Pessoa p = this.montarPessoa();
        assertNotEquals("letranafoto@hotmail.com", p.getEmail());
    }

    @Test
    public void testarQuantidadeTelefones()
    {
//        TypedQuery<Telefone> query;
//        query = em.createQuery("SELECT c FROM Telefone c WHERE c.pessoa.id = ?1", Telefone.class);
//        query.setParameter(1, 5);
//        List<Telefone> telefones = query.getResultList();
        
        Pessoa p = this.montarPessoa();        
        assertEquals(2, p.getTelefones().size());
    }

    @Test
    public void testaCerimoniaValida()
    {
        Pessoa p = this.montarPessoa();
        
        assertEquals(1, p.getCerimonia().getId());
    }

    @Test
    public void testaCerimoniaInvalida()
    {
        Pessoa p = this.montarPessoa();
        assertNotEquals(3, p.getCerimonia().getId());
    }
         
     @Test 
    public void atualizarPessoa()
    {
        Pessoa p = em.find(Pessoa.class, 11);
        p.setNome("Luana");
        em.merge(p);
//        em.flush();
//        em.clear();
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
    
    private Pessoa montarPessoa()
    {
        Cerimonia c = em.find(Cerimonia.class, 1);
        c.setId(1);
        Pessoa p = new Pessoa("Lucia", "lucia@hotmail.com", c);
        p.setId(1);
        
        Telefone t = new Telefone(TelefoneCategoria.celular, "81", "993250212");
        t.setId(1);
        t.setPessoa(p);
        Telefone t1 = new Telefone(TelefoneCategoria.celular, "81", "988846448");
        t1.setId(2);
        t1.setPessoa(p);
        
        List<Telefone> telefones = new ArrayList<>();
        telefones.add(t);
        telefones.add(t1);
        
        p.setTelefones(telefones);
        
        return p;
    }
}
