package entidades;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class Loja implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @NotNull
    @Size(min = 3)
    @Pattern(regexp = "\\p{Upper}{1}\\p{Lower}+", message = "{entidades.Loja.nome}")
    @Column(name = "txt_nome")
    private String nome;
        
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "id_telefone", referencedColumnName = "id")
    private Telefone telefone;
       
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "id_localizacao", referencedColumnName = "id")
    private Localizacao localizacao;
    
    //um presente contem uma lista de lojas
    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_presente", referencedColumnName = "id")
    private Presente presente;

    public Loja()
    {
    }

    public Loja(Presente p , String nome, Telefone telefone, Localizacao localizacao)
    {
        this.presente = p;
        this.nome = nome;
        this.telefone = telefone;
        this.localizacao = localizacao;
    }
    
    public Loja(String nome, Telefone telefone, Localizacao localizacao) //buffet usa esse constutor
    {
        
        this.nome = nome;
        this.telefone = telefone;
        this.localizacao = localizacao;
    }
        
    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public Telefone getTelefone()
    {
        return telefone;
    }

    public void setTelefone(Telefone telefone)
    {
        this.telefone = telefone;
    }

    public Localizacao getLocalizacao()
    {
        return localizacao;
    }

    public void setLocalizacao(Localizacao localizacao)
    {
        this.localizacao = localizacao;
    }

    public Presente getPresente()
    {
        return presente;
    }

    public void setPresente(Presente presente)
    {
        this.presente = presente;
    }
    
    @Override
    public boolean equals(Object o)
    {
        if (o != null)
        {
            if (o instanceof Loja)
            {
                Loja outra = (Loja) o;
                if (this.id == outra.id)
                { 
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public int hashCode()
    {
        int hash = 3;
        hash = 17 * hash + this.id;
        return hash;
    }    
}
