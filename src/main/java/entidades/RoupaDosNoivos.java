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

@Entity
public class RoupaDosNoivos implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "id_noivo", referencedColumnName = "id")
    Noivo noivo;
    
    @Column
    String roupa;
    
    public RoupaDosNoivos()
    {
    
    }
    
    public RoupaDosNoivos( Noivo noivo, String roupa )
    {
        this.noivo = noivo;
        this.roupa = roupa;
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
}
