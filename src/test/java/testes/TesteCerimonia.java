package testes;

import entidades.Buffet;
import entidades.ComesBebes;
import entidades.Localizacao;
import entidades.Loja;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TesteCerimonia extends Teste
{    
    //@Test
    public void t01_buscarLocalizacao() throws Exception
    {
        TypedQuery<Localizacao> query = em.createQuery(
                "SELECT l FROM Localizacao l WHERE l.id = 1", Localizacao.class);
        Localizacao l = query.getSingleResult();
        assertEquals(1, l.getId());
    }
    
    //@Test
    public void lojasEmRecife() throws Exception
    {
        Query query = em.createNativeQuery("SELECT l FROM Loja l INNER JOIN Localizacao local ON l.id_localizacao = local.id AND local.txt_cidade LIKE ?1");
        query.setParameter(1, "recife%");
        List<Loja> lojaEmRecife = query.getResultList();
        assertEquals(1, lojaEmRecife.size());
    }  

    //@Test
    public void t02_buscarBuffet()
    {
        Buffet c = em.find(Buffet.class, 1);
        assertEquals(1, c.getId());
    }   

  //  @Test
    public void t04_deletarBuffet() throws Exception
    {
        Buffet b = em.find(Buffet.class, 10);
        em.remove(b);        
        b = em.find(Buffet.class, 10);
        assertNull(b);
    }

//    @Test
//    public void t06_buscarNoivosDeUmaCerimonia() throws Exception
//    {
//        TypedQuery<Pessoa> query;
//        query = em.createQuery("SELECT p FROM Pessoa p WHERE p.cerimonia.id = ?1 AND p.disc_pessoa like ?2", Pessoa.class);
//        query.setParameter(1, 1); //o id da cerimonia eh int entao n pode mandar string
//        query.setParameter(2, "N");
//        List<Pessoa> noivos = query.getResultList();        
//        assertEquals(2, noivos.size());        
//    }
      
    //@Test
    public void t09_buscarLoja() throws Exception
    {
        Loja c = em.find(Loja.class, 1);
        assertEquals(1, c.getId());
    }
    
    //@Test 
    public void t10_qntComesBebesNoBuffet() throws Exception
    {
        TypedQuery<ComesBebes> query;
        query = em.createQuery("SELECT c FROM ComesBebes c WHERE c.buffet.id = ?1", ComesBebes.class);
        query.setParameter(1, 1);
        List<ComesBebes> comesBebes = query.getResultList();        
        assertEquals(5, comesBebes.size());         
    }
    
//    @Test
//    public void t11_valorTotalDasRoupasDosNoivos() throws Exception
//    {
//        TypedQuery<Long> query = 
//                em.createQuery("SELECT r.valor FROM RoupaDosNoivos r WHERE r.noivo.pessoa.cerimonia.id = ?1", Long.class);        
//        query.setParameter(1, 2);
//        
//        List<Long> valores = query.getResultList();        
//        Long somaTotal = new Long(0);        
//        for(Long s : valores)             
//            somaTotal += s;
//        
//        assertEquals(new Long(12), somaTotal);
//    }
    
      
}
