package entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@DiscriminatorValue(value = "N")
@PrimaryKeyJoinColumn(name = "id_pessoa", referencedColumnName = "id")
public class Noivo extends Pessoa implements Serializable //botar superclasse pessoa
{     
    @NotNull
    @Pattern(regexp = "[A-Za-z0-9\\._-]+@[A-Za-z]+\\.[A-Za-z]+", message = "{entidades.Buffet.valorTotalGasto}")
    @Column(name = "txt_senha")
    private String senha;      

    @OneToMany(mappedBy = "noivo", fetch = FetchType.LAZY,
    cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RoupaDosNoivos> roupaDosNoivos;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "id_pessoa", referencedColumnName = "id")
    private Pessoa pessoa;
    
    public Noivo()
    {
        roupaDosNoivos = new ArrayList<>();
    }

    public Noivo(String senha, Pessoa p)
    {
        this.senha = senha;
        this.pessoa = p;
        roupaDosNoivos = new ArrayList<>();        
    }

     public List<RoupaDosNoivos> getRoupaDosNoivos() {
        return roupaDosNoivos;
    }

    public void setRoupaDosNoivos(List<RoupaDosNoivos> roupaDosNoivosNovas) {
    
        for ( RoupaDosNoivos roupa : roupaDosNoivosNovas ) {
            
            if(!roupaDosNoivos.contains(roupa))
            {
                roupaDosNoivos.add(roupa);
            }
        }
    }
    
    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }
    
    public String getSenha()
    {
        return senha;
    }

    public void setSenha(String senha)
    {
        this.senha = senha;
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
