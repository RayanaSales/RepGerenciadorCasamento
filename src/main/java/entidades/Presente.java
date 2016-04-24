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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class Presente implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Pattern(regexp = "\\p{Upper}{1}\\p{Lower}+", message = "{entidades.Presente.nome}")
    @Column(name = "txt_nome")
    private String nome;
    
    @Size(max = 50)
    @Pattern(regexp = "[A-Za-z0-9.,]+", message = "{entidades.Presente.descricao}")
    @Column(name = "txt_descricao")
    private String descricao;

    @NotNull
    @Size(max = 50)
    @Pattern(regexp = "[A-Za-z0-9.,]+", message = "{entidades.Presente.ondeEncontrar}")
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

    public Presente(Cerimonia c, String nome, String descricao, String ondeEncontrar)
    {       
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
