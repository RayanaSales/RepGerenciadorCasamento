package entidades;

import enumeracoes.ConvidadoCategoria;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@DiscriminatorValue(value = "C")
@PrimaryKeyJoinColumn(name = "id_pessoa", referencedColumnName = "id")
public class Convidado extends Pessoa implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id; 
    
    @Enumerated(EnumType.STRING)
    ConvidadoCategoria categoria;
      
    @NotNull
    @Pattern(regexp = "[0-9]+", message = "{entidades.Convidado.qntSenhas}")
    @Column(name = "numero_qntsenhas")
    int qntSenhas;
    
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "id_pessoa", referencedColumnName = "id")
    private Pessoa pessoa;
    
    public Convidado()
    {
      
    }

    public Convidado(ConvidadoCategoria cc, Pessoa p, int quantidadeSenhas)
    {        
        categoria = cc;
        this.pessoa = p;
        this.qntSenhas = quantidadeSenhas;        
    }

    public int getQntSenhas()
    {
        return qntSenhas;
    }

    public void setQntSenhas(int qntSenhas)
    {
        this.qntSenhas = qntSenhas;
    }

    public Pessoa getPessoa()
    {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa)
    {
        this.pessoa = pessoa;
    }

    public ConvidadoCategoria getCategoria()
    {
        return categoria;
    }

    public void setCategoria(ConvidadoCategoria categoria)
    {
        this.categoria = categoria;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }
}
