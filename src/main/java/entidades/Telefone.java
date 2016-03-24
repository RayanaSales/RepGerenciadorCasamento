package entidades;

import enumeracoes.TelefoneCategoria;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Telefone implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Enumerated(EnumType.STRING)
    TelefoneCategoria categoria;
    
    @Column(name = "txt_ddd")
    private String ddd;
        
    @Column(name = "txt_numero")
    private String numero;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_convidado", referencedColumnName = "id")
    private Convidado convidado;  
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_produtorDeMidia", referencedColumnName = "id")
    private ProdutorDeMidia produtorDeMidia;  

    public Telefone()
    {
    }
    
    public Telefone(TelefoneCategoria categoria, String ddd, String numero) //usado para loja
    {       
        this.categoria = categoria;
        this.ddd = ddd;
        this.numero = numero;
    }

    public Telefone(Convidado convidado, TelefoneCategoria categoria, String ddd, String numero)
    {       
        this.categoria = categoria;
        this.ddd = ddd;
        this.numero = numero;
        this.convidado = convidado;
    }
    
    public Telefone(ProdutorDeMidia p, TelefoneCategoria categoria, String ddd, String numero)
    {       
        this.categoria = categoria;
        this.ddd = ddd;
        this.numero = numero;
        this.produtorDeMidia = p;
    }
    
    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public TelefoneCategoria getCategoria()
    {
        return categoria;
    }

    public void setCategoria(TelefoneCategoria categoria)
    {
        this.categoria = categoria;
    }

    public String getDdd()
    {
        return ddd;
    }

    public void setDdd(String ddd)
    {
        this.ddd = ddd;
    }

    public String getNumero()
    {
        return numero;
    }

    public void setNumero(String numero)
    {
        this.numero = numero;
    }    

    public Convidado getConvidado()
    {
        return convidado;
    }

    public void setConvidado(Convidado convidado)
    {
        this.convidado = convidado;
    }

    public ProdutorDeMidia getProdutorMidia()
    {
        return produtorDeMidia;
    }

    public void setProdutorMidia(ProdutorDeMidia produtorMidia)
    {
        this.produtorDeMidia = produtorMidia;
    }

    
    
    
}
