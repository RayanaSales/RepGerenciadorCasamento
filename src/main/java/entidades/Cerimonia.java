package entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Cerimonia implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "dt_dataHora")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHora; 

    //Relacionamentos:
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "id_localizacao", referencedColumnName = "id")
    private Localizacao localizacao;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "id_buffet", referencedColumnName = "id")
    private Buffet buffet;

    //lista de presentes que o casal cria
    @OneToMany(mappedBy = "cerimonia", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Presente> presentes;

    //uma cerimonia to many pessoas (noivos, convidados, produtorDeMidia)
    @OneToMany(mappedBy = "cerimonia", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pessoa> pessoas;
    
    public Cerimonia()
    {
        presentes = new ArrayList<>();
        pessoas = new ArrayList<>();
    }

    public Date getData()
    {
        return dataHora;
    }

    public void setData(Date data)
    {
        this.dataHora = data;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public Localizacao getLocalizacao()
    {
        return localizacao;
    }

    public void setLocalizacao(Localizacao localizacao)
    {
        this.localizacao = localizacao;
    }

    public Buffet getBuffet()
    {
        return buffet;
    }

    public void setBuffet(Buffet buffet)
    {
        this.buffet = buffet;
    }

    public List<Presente> getPresentes()
    {
        return presentes;
    }

    public void setPresentes(List<Presente> presentesNovos)
    {
        for (Presente presente : presentesNovos)
        {
            if(!presentes.contains(presente))
            {
                presentes.add(presente);
            }
        }
    }

    public List<Pessoa> getPessoas()
    {
        return pessoas;
    }

    public void setPessoas(List<Pessoa> pessoasNovas)
    {
        for (Pessoa pessoa : pessoasNovas)
        {
            if(!pessoas.contains(pessoa))
            {
                pessoas.add(pessoa);
            }
        }
    }
}
