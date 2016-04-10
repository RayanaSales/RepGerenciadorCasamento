package entidades;

import enumeracoes.ProdutorDeMidiaCategoria;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

@Entity
@DiscriminatorValue(value = "P")
@PrimaryKeyJoinColumn(name = "id_pessoa", referencedColumnName = "id")
public class ProdutorDeMidia extends Pessoa implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name = "numero_preco")
    private double preco;
        
    @Column(name = "dt_dataEHoraChegada")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataEHoraChegada; //mudar para date   

    @Column(name = "txt_linkParaRedeSocial")
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
