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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@DiscriminatorValue(value = "N")
@PrimaryKeyJoinColumn(name = "id_pessoa", referencedColumnName = "id")
public class Noivo extends Pessoa implements Serializable //botar superclasse pessoa
{     
    @NotNull
    @Size(min = 6, max = 16, message = "{entidades.Noivo.senha}")
    @Pattern(regexp = "[A-Za-z0-9\\._-]+@[A-Za-z]+\\.[A-Za-z]+", message = "{entidades.Noivo.senha}")
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
    
//    @Override
//    public boolean equals(Object o)
//    { System.out.println("EQUALS DO NOIVO SENDO EXECUTADO");
//        if (o != null)
//        {
//            if (o instanceof Noivo)
//            {
//                Noivo outra = (Noivo) o;
//
//                if (this.senha.equals(outra.senha)) //confere atributos
//                {  
//                    int tamanhoLista = outra.getRoupaDosNoivos().size();
//                    int loops = 0;
//                    
//                    for (RoupaDosNoivos cb1 : this.roupaDosNoivos)//confere itens da lista
//                    {
//                        for (RoupaDosNoivos cb2 : outra.roupaDosNoivos)
//                        {
//                            if(cb1.getNoivo().equals(cb2.getNoivo()) && cb1.getRoupa().equals(cb2.getRoupa())
//                                    && cb1.getValor()== cb2.getValor())
//                            {
//                                loops++;
//                            }
//                        }
//                    }
//                    if (loops == tamanhoLista)
//                    {
//                        System.out.println("iguais");
//                        return true;
//                    }
//                }
//            }
//        }
//        System.out.println("diferentes");
//        return false;
//    }
}
