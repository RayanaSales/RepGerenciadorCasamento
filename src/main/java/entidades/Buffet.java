package entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class Buffet implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @validadores.ValidaPreco
    @Column(name = "numero_valorTotal")
    private double valorTotalGasto;

    @OneToMany(mappedBy = "buffet", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ComesBebes> comesBebes;
    
    public Buffet()
    {
        comesBebes = new ArrayList<>();
    }

    public Buffet(double valorTotalGasto)
    {
        this.valorTotalGasto = valorTotalGasto;
        comesBebes = new ArrayList<>();
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public double getValorTotalGasto()
    {
        return valorTotalGasto;
    }

    public void setValorTotalGasto(double valorTotalGasto)
    {
        this.valorTotalGasto = valorTotalGasto;
    }

    public void setComesBebes(List<ComesBebes> ComesBebesNovos)
    {

        for (ComesBebes comesBebe : ComesBebesNovos)
        {
            if (!comesBebes.contains(comesBebe))
            {
                comesBebes.add(comesBebe);
            }
        }
    }

    public List<ComesBebes> getComesBebes()
    {
        return comesBebes;
    }

    @Override
    public boolean equals(Object o)
    {
        if (o != null)
        {
            if (o instanceof Buffet)
            {
                Buffet outra = (Buffet) o;

                if (Objects.equals(this.valorTotalGasto, outra.valorTotalGasto)) //confere atributos
                {
                   /* int tamanhoLista = outra.getComesBebes().size();
                    int loops = 0;

                    for (ComesBebes cb1 : this.comesBebes)//confere itens da lista
                    {
                        for (ComesBebes cb2 : outra.comesBebes)
                        {
                            if (cb1.getProduto().equals(cb2.getProduto()) && cb1.getCategoria().equals(cb2.getCategoria())
                                    && cb1.getBuffet().getId() == cb2.getBuffet().getId() && cb1.getLoja().equals(cb2.getLoja())
                                    && cb1.getQuantidade() == cb2.getQuantidade() && cb1.getValor() == cb2.getValor())
                            {
                                loops++;
                            }
                        }
                    }
                    if (loops == tamanhoLista)
                    {
                        return true;
                    } */
                   
                   return true; //qd descomentar o de cima, tira essa linha
                }
            }
        }
        return false;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 29 * hash + this.id;
        return hash;
    }   
}
