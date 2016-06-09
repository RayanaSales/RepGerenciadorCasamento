package entidades;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
public class RoupaDosNoivos implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "id_pessoa", referencedColumnName = "id")
    private Noivo noivo;
    
    @NotNull
    @Pattern(regexp = "\\p{Upper}{1}\\p{Lower}+", message = "{entidades.RoupaDosNoivos.roupa}")
    @Column(name = "txt_roupa")
    private String roupa;
    
    @NotNull
    @validadores.ValidaPreco
    @Column(name = "numero_valor")
    private double valor;
    
    public RoupaDosNoivos()
    {
    
    }
    
    public RoupaDosNoivos( Noivo noivo, String roupa, double valor)
    {
        this.noivo = noivo;
        this.roupa = roupa;
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Noivo getNoivo() {
        return noivo;
    }

    public void setNoivo(Noivo noivo) {
        this.noivo = noivo;
    }
    
    

    public String getRoupa()
    {
        return roupa;
    }

    public void setRoupa(String roupa)
    {
        this.roupa = roupa;
    }  

    public double getValor()
    {
        return valor;
    }

    public void setValor(double valor)
    {
        this.valor = valor;
    }
    
    @Override
    public boolean equals(Object o)
    {
        if (o != null)
        {
            if (o instanceof RoupaDosNoivos)
            {
                RoupaDosNoivos outra = (RoupaDosNoivos) o;
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
        int hash = 3;
        hash = 23 * hash + this.id;
        return hash;
    }
}
