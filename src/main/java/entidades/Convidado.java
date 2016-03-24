
package entidades;

import enumeracoes.ConvidadoCategoria;
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
public class Convidado implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    
    @Column(name = "txt_nome")
    String nome;
    
    @Column(name = "txt_email")
    String email;
    
    @Enumerated(EnumType.STRING)
    ConvidadoCategoria categoria;
           
    //muitos convidados vao a uma cerimonia
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_cerimonia", referencedColumnName = "id")
    private Cerimonia cerimonia;
    
    //um convidado, possui muitos telefones
    @OneToMany(mappedBy = "convidado", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Telefone> telefones;

    public Convidado()
    {
        telefones = new ArrayList<Telefone>();
    }

    public Convidado(Cerimonia c,String nome, String email, ConvidadoCategoria cc)
    {
        this.cerimonia = c;
        this.nome = nome;
        this.email = email;
        categoria = cc;
        
        telefones = new ArrayList<Telefone>();
    }

    public ConvidadoCategoria getCategoria()
    {
        return categoria;
    }

    public void setCategoria(ConvidadoCategoria categoria)
    {
        this.categoria = categoria;
    }

    public Cerimonia getCerimonia()
    {
        return cerimonia;
    }
    
    public void setCerimonia(Cerimonia cerimonia)
    {
        this.cerimonia = cerimonia;
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

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    //PADRAO EXPERT
    public void setTelefones(List<Telefone> telefonesNovos)
    {
        
        for (Telefone telefone : telefonesNovos)
        {
            if(!telefones.contains(telefone))
            {
                telefones.add(telefone);
            }
        }
    }
    
    public List<Telefone> getTelegones()
    {
        return telefones;
    }
}
