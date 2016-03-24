package testes;

import entidades.Buffet;
import entidades.Cerimonia;
import entidades.Convidado;
import entidades.ProdutorDeMidia;
import entidades.Localizacao;
import entidades.Loja;
import entidades.Presente;
import entidades.Noivo;
import entidades.Telefone;
import enumeracoes.ComidaBebidaCategoria;
import enumeracoes.ConvidadoCategoria;
import enumeracoes.EstadosDoBrasil;
import enumeracoes.PresenteCategoria;
import enumeracoes.ProdutorDeMidiaCategoria;
import enumeracoes.TelefoneCategoria;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Montar
{
    public static Localizacao montarLocal()
    {
        Localizacao l = new Localizacao(EstadosDoBrasil.AP,"lagoa dos gatos", "olinda", "b", "cajueiro", "52040090", 157);
        return l;
    }

    public static Buffet montarBuffet()
    {
        Telefone t1 = new Telefone(TelefoneCategoria.empresarial, "81", "87654328");          
        Localizacao local = new Localizacao(EstadosDoBrasil.PE,"barroso", "recife", "predio", "wee", "345679", 504);        
        Loja loja = new Loja("Delicata", t1, local);  
        
        Buffet b = new Buffet(200.0);
        return b;
    }
    
    public static List<Presente> montarListaPresentes(Cerimonia c)
    {
        Presente p = new Presente(c, "toalha", PresenteCategoria.camaMesaBanho, "capa vermelha", "amazon");
        List<Loja> lojas = montarListaLojas(p); //lugares onde posso encontrar esse presente
        p.setLojas(lojas);
        
        Presente p1 = new Presente(c, "jogo talheres", PresenteCategoria.cozinha, "capa metalica", "amazon");
        List<Loja> lojas2 = montarListaLojas(p1);
        p.setLojas(lojas2);
        
        List<Presente> presentes = new ArrayList<>();

        presentes.add(p);
        presentes.add(p1);

        return presentes;
    }
    
    public static List<Loja> montarListaLojas(Presente p)
    {
        List<Loja> lojas = new ArrayList<>();
        
        Telefone t1 = new Telefone(TelefoneCategoria.empresarial, "81", "32456298");          
        Localizacao local = new Localizacao(EstadosDoBrasil.BA,"cajueiro", "olinda", "b", "wee", "52040090", 456);        
        Loja loja = new Loja(p,"Casas bahia", t1, local);        
                
        Telefone t2 = new Telefone(TelefoneCategoria.empresarial, "21", "76543234");
        Localizacao local2 = new Localizacao(EstadosDoBrasil.PA, "morangueiro", "recife", "c", "yooo", "87654323", 456);       
        Loja loja2 = new Loja(p,"Americanas", t2, local2);   
        
        lojas.add(loja);
        lojas.add(loja2);        
        return lojas;
    }
    
    public static List<ProdutorDeMidia> montarProdutorMidia(Cerimonia c)
    {
        //transforma uma hora String em um date
        Date hora = new Date();
        java.sql.Timestamp timestamp = null;
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");       
        
        try
        {
            hora = df.parse("26/12/1920 10:45:53"); //parse: string pra date ; format: date para string
            timestamp = new java.sql.Timestamp(hora.getTime());
        
        } 
        catch (Exception ex)
        {
            ex.printStackTrace();
        }    
        
        ProdutorDeMidia f = new ProdutorDeMidia(c, ProdutorDeMidiaCategoria.fotografo, 2.000, hora,"paulo@hotmail.com", "https://www.flickr.com");
        f.setTelefones(montarTelefones(f));   
        
        ProdutorDeMidia f2 = new ProdutorDeMidia(c, ProdutorDeMidiaCategoria.filmografia, 1.000, hora, "yuri@hotmail.com", "https://www.flickr.com");
        f.setTelefones(montarTelefones(f2));   
        
        List<ProdutorDeMidia> produtores = new ArrayList<>();
        produtores.add(f);
        produtores.add(f2);
        
        return produtores;
    }

    public static List<Noivo> montarCasal(Cerimonia c)
    {
        Noivo u1 = new Noivo(c, "Paulo", "paulomenzs@gmail.com", "1234");
        Noivo u2 = new Noivo(c, "Rayana", "rayanasls@gmail.com", "5678");

        List<Noivo> usuarios = new ArrayList<Noivo>();

        usuarios.add(u1);
        usuarios.add(u2);

        return usuarios;
    }
    
    

    public static List<Convidado> convidarPessoas(Cerimonia c)
    {
        Convidado u1 = new Convidado(c, "Douglas", "paulomenzs@gmail.com", ConvidadoCategoria.familia);
        u1.setTelefones(montarTelefones(u1));
        
        Convidado u2 = new Convidado(c, "Edmilson", "rayanasls@gmail.com", ConvidadoCategoria.amigo);        
        u2.setTelefones(montarTelefones(u2));
        
        List<Convidado> convidados = new ArrayList<Convidado>();
        convidados.add(u1);
        convidados.add(u2);

        return convidados;
    }
    
    public static List<Telefone> montarTelefones(Convidado convidado)
    {
        Telefone t1 = new Telefone(convidado, TelefoneCategoria.celular, "81", "993250212");
        Telefone t2 = new Telefone(convidado, TelefoneCategoria.residencial, "81", "32417065");
        Telefone t3 = new Telefone(convidado, TelefoneCategoria.empresarial, "81", "34267844");
        
        List<Telefone> telefones = new ArrayList<Telefone>();
        telefones.add(t1);
        telefones.add(t2);
        telefones.add(t3);
        
        return telefones;
    }
    
    public static List<Telefone> montarTelefones(ProdutorDeMidia fotografo)
    {
        Telefone t1 = new Telefone(fotografo, TelefoneCategoria.celular, "81", "988389968");
        Telefone t2 = new Telefone(fotografo, TelefoneCategoria.residencial, "81", "32411608");
        Telefone t3 = new Telefone(fotografo, TelefoneCategoria.empresarial, "81", "35231286");
        
        List<Telefone> telefones = new ArrayList<Telefone>();
        telefones.add(t1);
        telefones.add(t2);
        telefones.add(t3);
        
        return telefones;
    }

    public static Cerimonia montarCerimonia(Cerimonia c, Localizacao l, Buffet b, List<ProdutorDeMidia> produtores, List<Presente> presentes, List<Noivo> casal, List<Convidado> convidados)
    {
        //transforma uma hora String em um date
        Date data = new Date();
        java.sql.Timestamp timestamp = null;
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");       
        
        try
        {
            data = df.parse("26/12/1920 12:45:53"); //parse: string pra date ; format: date para string
            timestamp = new java.sql.Timestamp(data.getTime());
        
        } 
        catch (Exception ex)
        {
            ex.printStackTrace();
        }       

        c.setData(timestamp);
        c.setConvidados(convidados);
        c.setBuffet(b);
        c.setEquipeDeMidia(produtores);
        c.setLocalizacao(l);
        c.setPresentes(presentes);
        c.setUsuarios(casal);
       
        return c;
    }
}
