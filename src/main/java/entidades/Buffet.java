package entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Buffet implements Serializable
{   
    //nome, valor, qtd, tipo - enum, loja
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;    
    
    @Column(name = "numero_valorTotal")
    private double valorTotalGasto;    
    
//    @OneToMany(mappedBy = "buffet", fetch = FetchType.LAZY,
//            cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<ComesBebes> comesEBebes;

    public Buffet()
    {
    }

    public Buffet(double valorTotalGasto)
    {
        this.valorTotalGasto = valorTotalGasto;
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
    
    

}
