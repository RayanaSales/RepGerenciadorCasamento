package testes;

import entidades.Buffet;
import entidades.Cerimonia;
import entidades.Convidado;
import entidades.Localizacao;
import entidades.Noivo;
import entidades.Pessoa;
import enumeracoes.EstadosDoBrasil;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.TypedQuery;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author rayana
 */
public class TesteCerimonia2 extends Teste
{

    @Test
    public void quantidadeCerimonias() throws Exception
    {
        TypedQuery<Long> query = em.createQuery("SELECT COUNT(c) FROM Cerimonia c WHERE c.id IS NOT NULL", Long.class);
        Long resultado = query.getSingleResult();
        assertEquals(new Long(3), resultado);
    }

    @Test
    public void pessoasTotaisNaCerimonia() throws Exception
    {
        TypedQuery<Integer> query = em.createQuery("SELECT c.quantidadeSenhas FROM Convidado c WHERE c.cerimonia.id = ?1", Integer.class);
        query.setParameter(1, 1);
        List<Integer> senhas = query.getResultList();

        int somaTotal = 0;
        for (Integer s : senhas)
        {
            somaTotal += s;
        }

        assertEquals(28, somaTotal);
    }

    @Test
    public void dataValida() throws Exception
    {
        DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = (Date) formatter.parse("2016/12/15 20:00:00");

        Cerimonia c = em.find(Cerimonia.class, 2);
        
        assertEquals(date, c.getData());
    }
    
    @Test
    public void dataInvalida() throws Exception
    {
        DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = (Date) formatter.parse("2013/10/15 21:00:00");

        Cerimonia c = em.find(Cerimonia.class, 2);
        
        assertNotEquals(date, c.getData());
    }
    
    @Test
    public void localizacaoDaCerimoniaValida() throws Exception
    {        
        Cerimonia c = em.find(Cerimonia.class, 1);
        Localizacao local = new Localizacao(EstadosDoBrasil.PE, "olinda", "Jardim Atlantico", "Rua Serinhaen", "B", "53140010", 157);
        
        //EM LOCALIZACAO,SOBRESCREVI,O EQUALS,PARA COMPARAR OBJETOS POR ATRIBUTOS, E NAO POR REFERENCIAS(COMO O EQUALS NORMAL FAZ)
        assertEquals(local, c.getLocalizacao());
    }
    
    @Test
    public void localizacaoDaCerimoniaInvalida() throws Exception
    {        
        Cerimonia c = em.find(Cerimonia.class, 1);
        Localizacao local = new Localizacao(EstadosDoBrasil.DF, "olinda", "Jardim Atlantico", "Rua Serinhaen", "B", "53140010", 157);
        
        //EM LOCALIZACAO,SOBRESCREVI,O EQUALS,PARA COMPARAR OBJETOS POR ATRIBUTOS, E NAO POR REFERENCIAS(COMO O EQUALS NORMAL FAZ)
        assertNotEquals(local, c.getLocalizacao());
    }
    
    @Test
    public void buffetDaCerimoniaValido() throws Exception
    {        
        Cerimonia c = em.find(Cerimonia.class, 1);
        Buffet b = new Buffet(3000);
                
        //EM BUFFET,SOBRESCREVI,O EQUALS,PARA COMPARAR OBJETOS POR ATRIBUTOS, E NAO POR REFERENCIAS(COMO O EQUALS NORMAL FAZ)
        assertEquals(b, c.getBuffet());
    }
    
    @Test
    public void quantidadePresentes() throws Exception
    {
        Cerimonia c = em.find(Cerimonia.class, 1);
        
        assertEquals(3, c.getPresentes().size());
    }
    
    @Test
    public void quantidadePessoasQueRecebemSenhas() throws Exception
    {  //confere convidados (eles recebem as senhas)
        
        Cerimonia c = em.find(Cerimonia.class, 1);
        
        List<Pessoa> pessoas = new ArrayList<>();
        
        for(Pessoa p : c.getPessoas())
        {
            if(p instanceof Convidado)
                pessoas.add(p);
        }
        
        assertEquals(4, pessoas.size());
    }
    
    @Test
    public void quantidadeNoivos() throws Exception
    { // por cerimonia, so pode haver 2 noivos
        
        Cerimonia c = em.find(Cerimonia.class, 1);
        
        List<Pessoa> noivos = new ArrayList<>();
        
        for(Pessoa p : c.getPessoas())
        {
            if(p instanceof Noivo)
                noivos.add(p);
        }
        
        assertEquals(2, noivos.size());
    }
    
     @Test 
    public void atualizarCerimonia() throws ParseException
    {
        //atualiza hora, era 2020-09-28 22:00:00, fica 2020-09-28 20:00:00
        DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = (Date) formatter.parse("2020/09/28 20:00:00");
        
        Cerimonia p = em.find(Cerimonia.class, 1);
        p.setData(date);
        em.merge(p);
        p = em.find(Cerimonia.class, 1);
        assertEquals(date, p.getData());
    }
        
    @Test
    public void deletarCerimonia()
    {        
        Cerimonia b = em.find(Cerimonia.class, 4);
        em.remove(b);        
        b = em.find(Cerimonia.class, 4);
        assertNull(b);
        
    }
}
