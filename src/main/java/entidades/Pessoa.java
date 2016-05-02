package entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "disc_pessoa", discriminatorType = DiscriminatorType.STRING , length = 1)
public class Pessoa implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;  
    
    private String disc_pessoa; //para o jpql poder acessar, sem ele aq jpql n acessa o disc_pessoa
        
    @NotNull
    @Size(min = 2, max = 30)
    @Pattern(regexp = "\\p{Upper}{1}\\p{Lower}+", message = "{entidades.Pessoa.nome}")
    @Column(name = "txt_nome")
    private String nome;

    @NotNull 
    @Size(min = 5, max = 50)
    @Email
    @Column(name = "txt_email")
    private String email;

    @OneToMany(mappedBy = "pessoa", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Telefone> telefones;
    
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_cerimonia", referencedColumnName = "id")
    private Cerimonia cerimonia;    

    public Pessoa()
    {
        telefones = new ArrayList<>();
    }

    public Pessoa(String nome, String email, Cerimonia cerimonia)
    {
        this.nome = nome;
        this.email = email;
        this.cerimonia = cerimonia;
        telefones = new ArrayList<>();
    }

    public int getId()
    {
        return id;
    }
   
    public List<Telefone> getTelefones() {
        return telefones;
    }

    //PADRAO EXPERT
    public void setTelefones(List<Telefone> telefonesNovos)
    {
        if (telefones != null)
        {
           telefones = new ArrayList<>(); 
        }
        
        for (Telefone telefone : telefonesNovos)
        {
            if(!existente(telefone))
            {
                //System.out.println("Telefone nao existia, entao vou add");
                telefones.add(telefone);               
            }
        }       
    }
    
    public boolean existente(Object c)
    {
        //System.out.println("MEU CONTAINS (existente) SENDO EXECUTADO");
        
        Telefone t = (Telefone) c;
        boolean contem = false;
        
        for (int i = 0; i<telefones.size() ; i++)
        {
            if(telefones.get(i).getId() == t.getId())
            {
                contem = true;            
                break;
            }
        }        
        return contem;
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

    public Cerimonia getCerimonia()
    {
        return cerimonia;
    }

    public void setCerimonia(Cerimonia cerimonia)
    {
        this.cerimonia = cerimonia;
    }

    public String getDisc_pessoa()
    {
        return disc_pessoa;
    }

    public void setDisc_pessoa(String disc_pessoa)
    {
        this.disc_pessoa = disc_pessoa;
    }
    
    
}
