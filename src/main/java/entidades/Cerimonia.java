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

    //uma cerimonia to many usuarios, pois o casal pode editar a cerimonia
    @OneToMany(mappedBy = "cerimonia", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Noivo> usuarios;

    //uma cerimonia to many convidados
    @OneToMany(mappedBy = "cerimonia", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Convidado> convidados;

    //uma cerimonia tem uma equipe de midia (varios produtores de midia - fotografos, filmagens)
    @OneToMany(mappedBy = "cerimonia", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProdutorDeMidia> equipeDeMidia;    
    
    public Cerimonia()
    {
        presentes = new ArrayList<>();
        usuarios = new ArrayList<>();
        convidados = new ArrayList<>();
        equipeDeMidia = new ArrayList<>();
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

    public List<Noivo> getUsuarios()
    {
        return usuarios;
    }

    public void setUsuarios(List<Noivo> usuariosNovos)
    {
        for (Noivo noivo : usuariosNovos)
        {
            if(!usuarios.contains(noivo))
            {
                usuarios.add(noivo);
            }
        }
    }

    public List<Convidado> getConvidados()
    {
        return convidados;
    }

    public void setConvidados(List<Convidado> convidadosNovos)
    {
        //Marcos andre mandou fazer assim: percorre a lista nova
        //se a lista antiga nao contem tal usuario da lista nova: adicione-o na lista antiga
        for (Convidado convidado : convidadosNovos)
        {
            if(!convidados.contains(convidado))
            {
                convidados.add(convidado);
            }
        }
    }
    
    public void setEquipeDeMidia(List<ProdutorDeMidia> novaEquipe)
    {
        for (ProdutorDeMidia produtor : novaEquipe)
        {
            if(!equipeDeMidia.contains(produtor))
            {
                equipeDeMidia.add(produtor);
            }
        }
    }
    
    public List<ProdutorDeMidia> getEquipeDeMidia()
    {
        return equipeDeMidia;
    }
}
