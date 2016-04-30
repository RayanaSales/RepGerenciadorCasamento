package entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class Telefone implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @NotNull
    @validadores.ValidaTelefoneCategoria
    @Size(min = 7, max = 11)
    @Column
    String categoria;
    
    @NotNull  
    @Size(min = 2, max = 3)
    @Pattern(regexp = "[0-9]+", message = "{entidades.Telefone.ddd}")
    @Column(name = "txt_ddd")
    private String ddd;
        
    @NotNull
    @Size(min = 8, max = 9)
    @Pattern(regexp = "[0-9]+", message = "{entidades.Telefone.numero}")
    @Column(name = "txt_numero")
    private String numero;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_pessoa", referencedColumnName = "id")
    private Pessoa pessoa;  

    public Telefone()
    {
    }
    
    public Telefone(String categoria, String ddd, String numero) //usado para loja
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

    public String getCategoria()
    {
        return categoria;
    }

    public void setCategoria(String categoria)
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
