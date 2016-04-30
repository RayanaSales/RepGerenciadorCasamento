package entidades;

import enumeracoes.ProdutorDeMidiaCategoria;
import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@DiscriminatorValue(value = "P")
@PrimaryKeyJoinColumn(name = "id_pessoa", referencedColumnName = "id")
public class ProdutorDeMidia extends Pessoa implements Serializable
{    
    @NotNull
    @Column(name = "numero_preco")
    private double preco;
        
    @NotNull
    @Future
    @Column(name = "dt_dataEHoraChegada")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataEHoraChegada; //mudar para date   

    @NotNull
    @Column(name = "txt_linkParaRedeSocial")
    @Pattern(regexp = "[A-Za-z0-9\\._-]+", message = "{entidades.ProdutorDeMidia.linkParaRedeSocial}")
    private String linkParaRedeSocial;
    
    @Enumerated(EnumType.STRING)
    ProdutorDeMidiaCategoria categoria;    
 
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "id_pessoa", referencedColumnName = "id")
    private Pessoa pessoa;
    
    public ProdutorDeMidia()
    {
        
    }

    public ProdutorDeMidia(ProdutorDeMidiaCategoria categoria, Pessoa p, double preco, Date horaChegada, String linkParaRedeSocial)
    {       
        this.categoria = categoria;
        this.pessoa = p;
        this.preco = preco;
        this.dataEHoraChegada = horaChegada;       
        this.linkParaRedeSocial = linkParaRedeSocial;     
    }

    public Pessoa getPessoa()
    {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa)
    {
        this.pessoa = pessoa;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public double getPreco()
    {
        return preco;
    }

    public void setPreco(double preco)
    {
        this.preco = preco;
    }

    public Date getDataEHoraChegada()
    {
        return dataEHoraChegada;
    }

    public void setDataEHoraChegada(Date dataEHoraChegada)
    {
        this.dataEHoraChegada = dataEHoraChegada;
    }

    public String getLinkParaRedeSocial()
    {
        return linkParaRedeSocial;
    }

    public void setLinkParaRedeSocial(String linkParaRedeSocial)
    {
        this.linkParaRedeSocial = linkParaRedeSocial;
    }

    public ProdutorDeMidiaCategoria getCategoria()
    {
        return categoria;
    }

    public void setCategoria(ProdutorDeMidiaCategoria categoria)
    {
        this.categoria = categoria;
    }
}
