package entidades;

import enumeracoes.ConvidadoCategoria;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@DiscriminatorValue(value = "C")
@PrimaryKeyJoinColumn(name = "id_pessoa", referencedColumnName = "id")
public class Convidado extends Pessoa implements Serializable
{
    @NotNull
    @Enumerated(EnumType.STRING)
    ConvidadoCategoria categoria;
      
    @NotNull
    @validadores.ValidaQuantidade   
    @Column(name = "numero_quantidadeSenhas")
    int quantidadeSenhas;
        
    public Convidado()
    {
      
    }

    public Convidado(ConvidadoCategoria cc, int quantidadeSenhas)
    {        
        categoria = cc;
        this.quantidadeSenhas = quantidadeSenhas;        
    }

    public int getQuantidadeSenhas()
    {
        return quantidadeSenhas;
    }

    public void setQuantidadeSenhas(int quantidadeSenhas)
    {
        this.quantidadeSenhas = quantidadeSenhas;
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
