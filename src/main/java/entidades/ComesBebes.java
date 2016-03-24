package entidades;

import enumeracoes.ComidaBebidaCategoria;
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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name = "numero_qtd")
    private int qtd;
    
    @Enumerated(EnumType.STRING)
    ComidaBebidaCategoria categoria;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_buffet", referencedColumnName = "id")
    private Buffet buffet;
    
    //quero pedir o brigadeiro da minha vizinha, os doces finos de tal lugar, mas eu gosto da coxinha da padaria. Pede tudo de cada lugar
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "id_loja", referencedColumnName = "id")
    private Loja loja;
}
