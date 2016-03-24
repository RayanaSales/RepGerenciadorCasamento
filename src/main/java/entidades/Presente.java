package entidades;

import enumeracoes.PresenteCategoria;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
import javax.persistence.OneToMany;

@Entity
public class Presente implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "txt_nome")
    private String nome;

    @Enumerated(EnumType.STRING)
    PresenteCategoria categoria;

    @Column(name = "txt_descricao")
    private String descricao;

    @Column(name = "txt_ondeEncontrar")
    private String ondeEncontrar;

    //uma cerimonia contem uma lista de presentes
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_cerimonia", referencedColumnName = "id")
    private Cerimonia cerimonia;

    //Um presente pode ser encontrado em uma lista de lojas
    @OneToMany(mappedBy = "presente", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Loja> lojas;

    public Presente()
    {
    }

    public Presente(Cerimonia c, String nome, PresenteCategoria categoria, String descricao, String ondeEncontrar)
    {
        this.categoria = categoria;
        this.cerimonia = c;
        this.nome = nome;
        this.descricao = descricao;
        this.ondeEncontrar = ondeEncontrar;
        
        lojas = new ArrayList<>();
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public PresenteCategoria getCategoria()
    {
        return categoria;
    }

    public void setCategoria(PresenteCategoria categoria)
    {
        this.categoria = categoria;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    public String getOndeEncontrar()
    {
        return ondeEncontrar;
    }

    public void setOndeEncontrar(String ondeEncontrar)
    {
        this.ondeEncontrar = ondeEncontrar;
    }

    public Cerimonia getCerimonia()
    {
        return cerimonia;
    }

    public void setCerimonia(Cerimonia cerimonia)
    {
        this.cerimonia = cerimonia;
    }

    //PADRAO EXPERT
    public void setLojas(List<Loja> novaslojasOndeEncontrar)
    {

        for (Loja loja : novaslojasOndeEncontrar)
        {
            if (!lojas.contains(loja))
            {
                lojas.add(loja);
            }
        }
    }

    public List<Loja> getLojas()
    {
        return lojas;
    }
}
