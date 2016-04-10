package entidades;

import enumeracoes.ComesBebesCategoria;
import java.io.Serializable;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToOne;

@Entity
public class ComesBebes implements Serializable
{
    @Column(name = "txt_produto")
    private String produto;
    
    @Enumerated(EnumType.STRING)
    ComesBebesCategoria categoria;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_buffet", referencedColumnName = "id")
    private Buffet buffet;
    
    //quero pedir o brigadeiro da minha vizinha, os doces finos de tal lugar, mas eu gosto da coxinha da padaria. Pede tudo de cada lugar
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "id_loja", referencedColumnName = "id")
    private Loja loja;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name = "numero_qtd")
    private int qtd;
    
    @Column(name = "numero_valor")
    private double valor;
    
    public ComesBebes()
    {
        
    }
    
    public ComesBebes(Buffet buffet, String produto, Loja loja, ComesBebesCategoria categoria, int qtd, double valor){
        
        this.qtd = qtd;
        this.produto = produto;
        this.valor = valor;
        this.loja = loja;
        this.buffet = buffet;
        this.categoria = categoria;      
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public ComesBebesCategoria getCategoria() {
        return categoria;
    }

    public void setCategoria(ComesBebesCategoria categoria) {
        this.categoria = categoria;
    }

    public Buffet getBuffet() {
        return buffet;
    }

    public void setBuffet(Buffet buffet) {
        this.buffet = buffet;
    }

    public Loja getLoja() {
        return loja;
    }

    public void setLoja(Loja loja) {
        this.loja = loja;
    }

    public double getValor()
    {
        return valor;
    }

    public void setValor(double valor)
    {
        this.valor = valor;
    }

    public String getProduto()
    {
        return produto;
    }

    public void setProduto(String produto)
    {
        this.produto = produto;
    }
    
  
}
