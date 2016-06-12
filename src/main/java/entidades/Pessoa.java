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
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "disc_pessoa", discriminatorType = DiscriminatorType.STRING, length = 1)
@SequenceGenerator(name = "PESSOA_SEQUENCE", sequenceName = "PESSOA_SEQUENCE", allocationSize = 1, initialValue = 1)
public class Pessoa implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PESSOA_SEQUENCE")
    protected int id;

    @NotNull
    @Size(min = 2, max = 30)
    @Pattern(regexp = "\\p{Upper}{1}\\p{Lower}+", message = "{entidades.Pessoa.nome}")
    @Column(name = "txt_nome")
    protected String nome;

    @Size(min = 5, max = 50)
    @Email
    @Column(name = "txt_email")
    protected String email;

    @OneToMany(mappedBy = "pessoa", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, orphanRemoval = true)
    protected List<Telefone> telefones;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_cerimonia", referencedColumnName = "id")
    protected Cerimonia cerimonia;

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

    public void setId(int id)
    {
        this.id = id;
    }

    public List<Telefone> getTelefones()
    {
        return telefones;
    }

    //PADRAO EXPERT
    public void setTelefones(List<Telefone> telefonesNovos)
    {
        if (telefones == null)
        {
            telefones = new ArrayList<>();
        }

        for (Telefone telefone : telefonesNovos)
        {
            if (!existente(telefone))
            {
                telefones.add(telefone);
            }
        }
    }

    public boolean existente(Object c)
    {
        Telefone t = (Telefone) c;
        boolean contem = false;

        for (int i = 0; i < telefones.size(); i++)
        {
            if (telefones.get(i).getId() == t.getId())
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

    @Override
    public boolean equals(Object o)
    {
        boolean valido = false;

        if (o != null)
        {
            if (o instanceof ProdutorDeMidia)
            {
                ProdutorDeMidia outra = (ProdutorDeMidia) o;
                if (this.id == outra.id && this.nome.equals(outra.nome))
                {
                    valido = true;
                }
            } else if (o instanceof Noivo)
            {
                Noivo outra = (Noivo) o;
                if (this.id == outra.id && this.nome.equals(outra.nome))
                {
                    valido = true;
                }
            } else if (o instanceof Convidado)
            {
                Convidado outra = (Convidado) o;
                if (this.id == outra.id && this.nome.equals(outra.nome))
                {
                    valido = true;
                }
            }
        }
        return valido;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 53 * hash + this.id;
        return hash;
    }
}
