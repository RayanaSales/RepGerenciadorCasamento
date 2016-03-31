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

@Entity
public class Noivo implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name = "txt_nome")
    private String nome;
    
    @Column(name = "txt_email")
    private String email;
    
    @Column(name = "txt_senha")
    private String senha;      

    @OneToMany(mappedBy = "noivo", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Telefone> telefones;
    
    @OneToMany(mappedBy = "noivo", fetch = FetchType.LAZY,
    cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RoupaDosNoivos> roupaDosNoivos;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_cerimonia", referencedColumnName = "id")
    private Cerimonia cerimonia;

    public Noivo()
    {
        roupaDosNoivos = new ArrayList<>();
        telefones = new ArrayList<Telefone>();
    }

    public Noivo(Cerimonia c, String nome, String email, String senha)
    {
        this.cerimonia = c;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        roupaDosNoivos = new ArrayList<>();
        
        telefones = new ArrayList<Telefone>();
    }

     public List<RoupaDosNoivos> getRoupaDosNoivos() {
        return roupaDosNoivos;
    }

    public void setRoupaDosNoivos(List<RoupaDosNoivos> roupaDosNoivosNovas) {
    
        for ( RoupaDosNoivos roupa : roupaDosNoivosNovas ) {
            
            if(!roupaDosNoivos.contains(roupa))
            {
                roupaDosNoivos.add(roupa);
            }
        }
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

    public String getSenha()
    {
        return senha;
    }

    public void setSenha(String senha)
    {
        this.senha = senha;
    }

    public Cerimonia getCerimonia()
    {
        return cerimonia;
    }

    public void setCerimonia(Cerimonia cerimonia)
    {
        this.cerimonia = cerimonia;
    }

    
    public List<Telefone> getTelefones() {
        return telefones;
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
    
    
    
}
