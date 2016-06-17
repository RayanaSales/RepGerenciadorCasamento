package entidades;

import enumeracoes.ProdutorDeMidiaCategoria;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

@Entity
@DiscriminatorValue(value = "P")
@PrimaryKeyJoinColumn(name = "id_pessoa", referencedColumnName = "id")
public class ProdutorDeMidia extends Pessoa implements Serializable
{
    @NotNull
    @validadores.ValidaPreco
    @Column(name = "numero_preco")
    private double preco;

    @NotNull
    @Future
    @Column(name = "dt_dataEHoraChegada")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataEHoraChegada;

    @NotNull
    @validadores.ValidaURL
    @Column(name = "txt_linkParaRedeSocial")
    private String linkParaRedeSocial;

    @NotNull
    @Enumerated(EnumType.STRING)
    ProdutorDeMidiaCategoria categoria;

    public ProdutorDeMidia()
    {

    }

    public ProdutorDeMidia(ProdutorDeMidiaCategoria categoria, double preco, Date horaChegada, String linkParaRedeSocial)
    {
        this.categoria = categoria;

        this.preco = preco;
        this.dataEHoraChegada = horaChegada;
        this.linkParaRedeSocial = linkParaRedeSocial;
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

    @Override
    public boolean equals(Object o)
    {
        super.equals(o);

        if (o != null)
        {
            if (o instanceof ProdutorDeMidia)
            {
                ProdutorDeMidia outra = (ProdutorDeMidia) o;
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
