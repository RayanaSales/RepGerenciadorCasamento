package entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@DiscriminatorValue(value = "N")
@PrimaryKeyJoinColumn(name = "id_pessoa", referencedColumnName = "id")
@SequenceGenerator(name = "PESSOA_SEQUENCE", sequenceName = "PESSOA_SEQUENCE", allocationSize = 1, initialValue = 1)
public class Noivo extends Pessoa implements Serializable //botar superclasse pessoa
{     
    @NotNull
    @Size(min = 6, max = 16, message = "{entidades.Noivo.senha}")
   // @Pattern(regexp = "[A-Za-z0-9\\._-]", message = "{entidades.Noivo.senha}")
    @Column(name = "txt_senha")
    private String senha;      

    @OneToMany(mappedBy = "noivo", fetch = FetchType.LAZY,
    cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RoupaDosNoivos> roupaDosNoivos;
    
    public Noivo()
    {
        roupaDosNoivos = new ArrayList<>();
    }

    public Noivo(String senha)
    {
        this.senha = senha;
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
        
    public String getSenha()
    {
        return senha;
    }

    public void setSenha(String senha)
    {
        this.senha = senha;
    }
    
     @Override
    public boolean equals(Object o)
    {
        if (o != null)
        {
            if (o instanceof Noivo)
            {
                Noivo outra = (Noivo) o;
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
        return super.hashCode(); //To change body of generated methods, choose Tools | Templates.
    }
}
