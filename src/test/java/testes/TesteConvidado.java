package testes;

import entidades.Convidado;
import entidades.ProdutorDeMidia;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Test;

/**
 *
 * @author rayana
 */
public class TesteConvidado extends Teste
{
    @Test
    public void testarBuscarConvidado()
    {
        Convidado n = em.find(Convidado.class, 3);

        if (n != null)
        {
            System.out.println("Convidado dif nuuuuuuuuuuuuuuuuul - heranca funcionando");
        } else
        {
            System.out.println("Convidado nuuuuuuuuuuuuuuuuul");
        }
    }
    
    @Test
    public void atualizarProdutor()
    {
    /*    Pessoa p = em.find(Pessoa.class, 15);
        p.setNome("Linder");
        em.merge(p);
        p = em.find(Pessoa.class, 15);
        assertEquals("Linder", p.getNome()); 
        
        ProdutorDeMidia p = em.find(ProdutorDeMidia.class, 15);
        p.setEmail("spotty@yahoo.com");
        em.merge(p);
        p = em.find(ProdutorDeMidia.class, 15);
        assertEquals("spotty@yahoo.com", p.getEmail());*/
        
        Convidado p = em.find(Convidado.class, 8);
        p.setQuantidadeSenhas(8);
        em.merge(p);
        p = em.find(Convidado.class, 8);
        assertEquals(8, p.getQuantidadeSenhas());
    }
        
    @Test
    public void deletarConvidador()
    {        
        Convidado b = em.find(Convidado.class, 9);
        em.remove(b);        
        b = em.find(Convidado.class, 9);
        assertNull(b);        
    }
}
