package testes_JUnit;

import entidades.Cerimonia;
import entidades.Localizacao;
import entidades.Noivo;
import entidades.Pessoa;
import entidades.Telefone;
import enumeracoes.EstadosDoBrasil;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import jsf_beans.TelefoneBean;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import servico.NoivoServico;
import servico.TelefoneServico;

public class TESTE_EJB
{

    public TESTE_EJB()
    {
    }

    @BeforeClass
    public static void setUpClass()
    {
    }

    @AfterClass
    public static void tearDownClass()
    {
    }

    @Before
    public void setUp()
    {
    }

    @After
    public void tearDown()
    {
    }
    
    @Test
    public void persistirTelefone() throws ParseException
    {
        Telefone telefone = montarTelefone(null);
        TelefoneServico ts = new TelefoneServico();
        
        ts.salvar(telefone);
        System.out.println("SALVOU TEL");
    }
    
    @Test
    public void persistirTelefone2() throws ParseException
    {
        TelefoneBean tb = new TelefoneBean();
        tb.telefone = montarTelefone(tb.telefone);
                               
        tb.salvar();
        System.out.println("SALVOU TEL");
    }
    
    private Telefone montarTelefone(Telefone t)
    {      
        t.setId(3);        
        t.setDdd("81");
        t.setNumero("99325015");
        return t;
    }

    @Test
    public void persistirNoivo() throws ParseException
    {
        Noivo n = montarNoivo();
        NoivoServico ns = new NoivoServico();  
        
        System.out.println("dados pessoa: " + " id: " + n.getId() + " Nome: " +  n.getNome() + " email: " + n.getEmail() + " cermimonia: " + n.getCerimonia().getId());
        System.out.println("dados noivo: " + n.getSenha());
        
        ns.salvar(n);       
        
    }
    
    private Noivo montarNoivo() throws ParseException
    {
        Cerimonia c = montarCerimonia();
        Noivo noivo = new Noivo("senha");
        noivo.setNome("Nati");
        noivo.setEmail("nati@gmail.com");
        noivo.setId(1);
        noivo.setCerimonia(c);
        
        List<Pessoa> pessoas = new ArrayList<>();
        pessoas.add(noivo);
        c.setPessoas(pessoas);
        
        return noivo;
    }
    
     private Cerimonia montarCerimonia() throws ParseException
    {
        DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = (Date) formatter.parse("2018/05/28 17:00:00");
        
        Localizacao l = new Localizacao(EstadosDoBrasil.ES, "Recife", "Encruzilhada", "rua lagoa azul", "b", "52040090", 50);

        Cerimonia c = new Cerimonia(date, l);
        c.setId(1);
        
        return c;
    }
}
