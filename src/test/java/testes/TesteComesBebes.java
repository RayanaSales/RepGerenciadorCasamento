package testes;

import entidades.ComesBebes;
import entidades.Loja;
import javax.persistence.Query;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Test;

/**
 *
 * @author rayana
 */
public class TesteComesBebes extends Teste
{
   // @Test    
    public void atualizarComesBebes()
    {
        ComesBebes p = em.find(ComesBebes.class, 1);
        p.setQuantidade(500);
        em.merge(p);
        p = em.find(ComesBebes.class, 1);
        assertEquals(500, p.getQuantidade());
    }
        
  //  @Test
    public void deletarComesBebes()
    {        
//        ComesBebes b = em.find(ComesBebes.class, 2);
//        em.remove(b);        
//        b = em.find(ComesBebes.class, 2);
//        assertNull(b);
        
        int id = 2;
       
        Query query = em.createQuery("DELETE ComesBebes AS c WHERE c.id = ?1");
        query.setParameter(1, id);
        query.executeUpdate();        
        ComesBebes cb = em.find(ComesBebes.class, id);  
        assertNull(cb);
        
    }
    
}
