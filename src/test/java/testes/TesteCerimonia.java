package testes;

import entidades.Buffet;
import entidades.Cerimonia;
import entidades.Convidado;
import entidades.Localizacao;
import entidades.Noivo;
import entidades.Pessoa;
import entidades.Presente;
import enumeracoes.ConvidadoCategoria;
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
public class TesteCerimonia extends Teste
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
        Date date = (Date) formatter.parse("2018/05/28 17:00:00");

        Cerimonia c = this.montarCerimonia();

        assertEquals(date, c.getData());
    }

    @Test
    public void dataInvalida() throws Exception
    {
        DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = (Date) formatter.parse("2013/10/15 21:00:00");

        Cerimonia c = this.montarCerimonia();

        assertNotEquals(date, c.getData());
    }

    @Test
    public void localizacaoDaCerimoniaValida() throws Exception
    {
        Cerimonia c = this.montarCerimonia();
        Localizacao l = new Localizacao(EstadosDoBrasil.ES, "Recife", "Encruzilhada", "rua lagoa azul", "b", "52040090", 50);

        //EM LOCALIZACAO,SOBRESCREVI,O EQUALS,PARA COMPARAR OBJETOS POR ATRIBUTOS, E NAO POR REFERENCIAS(COMO O EQUALS NORMAL FAZ)
        assertEquals(l, c.getLocalizacao());
    }

    @Test
    public void localizacaoDaCerimoniaInvalida() throws Exception
    {
        Cerimonia c = this.montarCerimonia();
        Localizacao local = new Localizacao(EstadosDoBrasil.DF, "olinda", "Jardim Atlantico", "Rua Serinhaen", "B", "53140010", 157);

        //EM LOCALIZACAO,SOBRESCREVI,O EQUALS,PARA COMPARAR OBJETOS POR ATRIBUTOS, E NAO POR REFERENCIAS(COMO O EQUALS NORMAL FAZ)
        assertNotEquals(local, c.getLocalizacao());
    }

    @Test
    public void buffetDaCerimoniaValido() throws Exception
    {
        Cerimonia c = this.montarCerimonia();

        //EM BUFFET,SOBRESCREVI,O EQUALS,PARA COMPARAR OBJETOS POR ATRIBUTOS, E NAO POR REFERENCIAS(COMO O EQUALS NORMAL FAZ)
        assertEquals(5000, c.getBuffet().getValorTotalGasto(), 0.0001);
    }

    @Test
    public void quantidadePresentes() throws Exception
    {
        Cerimonia c = this.montarCerimonia();

        assertEquals(3, c.getPresentes().size());
    }

    @Test
    public void quantidadePessoasQueRecebemSenhas() throws Exception
    {  //confere convidados (eles recebem as senhas)
        Cerimonia c = this.montarCerimonia();

        List<Pessoa> pessoas = new ArrayList<>();
        for (Pessoa p : c.getPessoas())
        {
            if (p instanceof Convidado)
            {
                pessoas.add(p);
            }
        }

        assertEquals(1, pessoas.size());
    }

    @Test
    public void quantidadeNoivos() throws Exception
    { // por que cerimonia, so pode haver 2 noivos
        Cerimonia c = this.montarCerimonia();

        List<Pessoa> noivos = new ArrayList<>();

        for (Pessoa p : c.getPessoas())
        {
            if (p instanceof Noivo)
            {
                noivos.add(p);
            }
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

    private Cerimonia montarCerimonia() throws ParseException
    {
        DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = (Date) formatter.parse("2018/05/28 17:00:00");
        Localizacao l = new Localizacao(EstadosDoBrasil.ES, "Recife", "Encruzilhada", "rua lagoa azul", "b", "52040090", 50);

        Cerimonia c = new Cerimonia(date, l);

        Noivo noivo = new Noivo("senha");
        noivo.setNome("Nati");
        noivo.setEmail("nati@gmail.com");
        noivo.setId(1);
        noivo.setCerimonia(c);

        Noivo noivo2 = new Noivo("senha2");
        noivo2.setNome("Clerron");
        noivo2.setEmail("clerron@gmail.com");
        noivo.setId(2);
        noivo2.setCerimonia(c);

        Convidado convidado = new Convidado(ConvidadoCategoria.amigo, 5);
        convidado.setId(10);
        convidado.setNome("Larissa");
        convidado.setEmail("larissa@gmail.com");
        noivo.setId(3);
        convidado.setCerimonia(c);

        List<Pessoa> pessoas = new ArrayList<>();
        pessoas.add(noivo);
        pessoas.add(noivo2);
        pessoas.add(convidado);      
        c.setPessoas(pessoas);

        Presente p1 = new Presente(c, "Celular", "moto g", "Hiper");
        p1.setId(1);
        Presente p2 = new Presente(c, "Cortina", "bege", "Casa de cor");
        p2.setId(2);
        Presente p3 = new Presente(c, "Jogo talheres", "tramontina", "Le biscuit");
        p3.setId(3);

        List<Presente> presentes = new ArrayList<>();
        presentes.add(p1);
        presentes.add(p2);
        presentes.add(p3);
        c.setPresentes(presentes);

        Buffet b = new Buffet(5000);
        b.setId(1);
        c.setBuffet(b);

        return c;
    }
}
