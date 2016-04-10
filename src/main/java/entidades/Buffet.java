package entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Buffet implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "numero_valorTotal")
    private double valorTotalGasto;

    @OneToMany(mappedBy = "buffet", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ComesBebes> comesBebes;

    public double calcularValorTotal()
    {
        for (ComesBebes produto : comesBebes)
        {
            valorTotalGasto += produto.getQtd() * produto.getValor();
        }
        return valorTotalGasto;
    }

    public Buffet()
    {
        comesBebes = new ArrayList<>();
    }

    public Buffet(double valorTotalGasto)
    {
        this.valorTotalGasto = valorTotalGasto;
        comesBebes = new ArrayList<>();
    }

    public int getId()
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
}
