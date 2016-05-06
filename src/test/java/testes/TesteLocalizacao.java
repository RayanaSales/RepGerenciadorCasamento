package testes;

import entidades.Localizacao;
import enumeracoes.EstadosDoBrasil;
import javax.persistence.Query;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 *
 * @author rayana
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TesteLocalizacao extends Teste
{

    @Test
    public void t02_deletarLocal()
    {
        Localizacao b = em.find(Localizacao.class, 11);
        em.remove(b);
        b = em.find(Localizacao.class, 11);
        assertNull(b);
    }

    @Test
    public void t01_atualizarLocal()
    {
        int id = 11;
        String bairroEsperada = "Campo grande";

        Query query = em.createQuery("UPDATE Localizacao AS n SET n.bairro = ?1 WHERE n.id = ?2");
        query.setParameter(1, bairroEsperada);
        query.setParameter(2, id);
        query.executeUpdate();
        Localizacao n = em.find(Localizacao.class, id);
        assertEquals(bairroEsperada, n.getBairro());
    }

    @Test
    public void testarEstadoValido()
    {
        Localizacao l = this.montarLocalizacao();
        l.setEstado(EstadosDoBrasil.CE);
        assertEquals(EstadosDoBrasil.CE, l.getEstado());
    }

    @Test
    public void testarCidadeValido()
    {
        Localizacao l = this.montarLocalizacao();
        assertEquals("Recife", l.getCidade());
    }
    
    @Test
    public void testarCidadeInvalido()
    {
        Localizacao l = this.montarLocalizacao();
        l.setCidade("Janga");
        assertNotEquals("Recife", l.getCidade());
    }
    
    @Test
    public void testarBairroValido()
    {
        Localizacao l = this.montarLocalizacao();
        assertEquals("Encruzilhada", l.getBairro());
    }
    
    @Test
    public void testarBairroInvalido()
    {
        Localizacao l = this.montarLocalizacao();
        l.setBairro("Jaboatao");
        assertNotEquals("Encruzilhada", l.getBairro());
    }
    
    @Test
    public void testarLogradouroValido()
    {
        Localizacao l = this.montarLocalizacao();
        assertEquals("rua lagoa azul", l.getLogradouro());
    }
    
    @Test
    public void testarLougradouroInvalido()
    {
        Localizacao l = this.montarLocalizacao();
        l.setLogradouro("rua azul");
        assertNotEquals("rua lagoa azul", l.getLogradouro());
    }
    
    @Test
    public void testarComplementoValido()
    {
        Localizacao l = this.montarLocalizacao();
        assertEquals("b", l.getComplemento());
    }
    
    @Test
    public void testarComplementoInvalido()
    {
        Localizacao l = this.montarLocalizacao();
        l.setComplemento("a");
        assertNotEquals("b", l.getComplemento());
    }
    
    @Test
    public void testarCepValido()
    {
        Localizacao l = this.montarLocalizacao();
        assertEquals("52040090", l.getCep());
    }
    
    @Test
    public void testarCepInvalido()
    {
        Localizacao l = this.montarLocalizacao();
        l.setCep("50000000");
        assertNotEquals("52040090", l.getCep());
    }
    
    @Test
    public void testarNumeroValido()
    {
        Localizacao l = this.montarLocalizacao();
        assertEquals(50, l.getNumero());
    }
    
    @Test
    public void testarNumeroInvalido()
    {
        Localizacao l = this.montarLocalizacao();
        l.setNumero(21);
        assertNotEquals(50, l.getNumero());
    }

    private Localizacao montarLocalizacao()
    {
        Localizacao l = new Localizacao(EstadosDoBrasil.ES, "Recife", "Encruzilhada", "rua lagoa azul", "b", "52040090", 50);
        return l;
    }
}
