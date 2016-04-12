package entidades;

import enumeracoes.TelefoneCategoria;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Telefone implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Enumerated(EnumType.STRING)
    TelefoneCategoria categoria;
    
    @Column(name = "txt_ddd")
    private String ddd;
        
    @Column(name = "txt_numero")
    private String numero;

//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "id_convidado", referencedColumnName = "id")
//    private Convidado convidado;  
//    
//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "id_noivo", referencedColumnName = "id")
//    private Noivo noivo;  
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_pessoa", referencedColumnName = "id")
    private Pessoa pessoa;  

    public Telefone()
    {
    }
    
    public Telefone(TelefoneCategoria categoria, String ddd, String numero) //usado para loja
    {       
        this.categoria = categoria;
        this.ddd = ddd;
        this.numero = numero;
    }
    
    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public TelefoneCategoria getCategoria()
    {
        return categoria;
    }

    public void setCategoria(TelefoneCategoria categoria)
    {
        this.categoria = categoria;
    }

    public String getDdd()
    {
        return ddd;
    }

    public void setDdd(String ddd)
    {
        this.ddd = ddd;
    }

    public String getNumero()
    {
        return numero;
    }

    public void setNumero(String numero)
    {
        this.numero = numero;
    }        

    public Pessoa getPessoa()
    {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa)
    {
        this.pessoa = pessoa;
    }
    
}
