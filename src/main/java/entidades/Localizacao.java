package entidades;

import enumeracoes.EstadosDoBrasil;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class Localizacao implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Size(min = 3, max = 40)
    @Pattern(regexp = "[A-Za-z]+", message = "{entidades.Localizacao.logradouro}")
    @Column(name = "txt_logradouro")
    private String logradouro;

    @NotNull
    @Size(min = 3, max = 40)
    @Pattern(regexp = "[A-Za-z]+", message = "{entidades.Localizacao.bairro}")
    @Column(name = "txt_bairro")
    private String bairro;

    @NotNull
    @Size(min = 3, max = 40)
    @Pattern(regexp = "[A-Za-z]+", message = "{entidades.Localizacao.cidade}")
    @Column(name = "txt_cidade")
    private String cidade;

    @NotNull
    @Size(min = 3, max = 40)
    @Pattern(regexp = "[A-Za-z]+", message = "{entidades.Localizacao.complemento}")
    @Column(name = "txt_complemento")
    private String complemento;

    @NotNull
    @Size(min = 3, max = 40)
    @Pattern(regexp = "[0-9.]", message = "{entidades.Convidado.cep}")
    @Column(name = "txt_cep")
    private String cep;

    @NotNull
    @Pattern(regexp = "[0-9]+", message = "{entidades.Convidado.numero}")
    @Column(name = "numero_numero")
    private int numero;

    @NotNull
    @Enumerated(EnumType.STRING)
    EstadosDoBrasil estado;

    public Localizacao()
    {
    }

    public Localizacao(EstadosDoBrasil estado, String cidade, String bairro, String logradouro, String complemento, String cep, int numero)
    {
        this.estado = estado;
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.cidade = cidade;
        this.complemento = complemento;
        this.cep = cep;
        this.numero = numero;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getRua()
    {
        return logradouro;
    }

    public void setRua(String logradouro)
    {
        this.logradouro = logradouro;
    }

    public String getBairro()
    {
        return bairro;
    }

    public void setBairro(String bairro)
    {
        this.bairro = bairro;
    }

    public String getCep()
    {
        return cep;
    }

    public void setCep(String cep)
    {
        this.cep = cep;
    }

    public int getNumero()
    {
        return numero;
    }

    public void setNumero(int numero)
    {
        this.numero = numero;
    }

    public String getLogradouro()
    {
        return logradouro;
    }

    public void setLogradouro(String logradouro)
    {
        this.logradouro = logradouro;
    }

    public String getCidade()
    {
        return cidade;
    }

    public void setCidade(String cidade)
    {
        this.cidade = cidade;
    }

    public String getComplemento()
    {
        return complemento;
    }

    public void setComplemento(String complemento)
    {
        this.complemento = complemento;
    }

    public EstadosDoBrasil getEstado()
    {
        return estado;
    }

    public void setEstado(EstadosDoBrasil estado)
    {
        this.estado = estado;
    }

    @Override
    public boolean equals(Object o)
    {
        if (o != null)
        {
            if (o instanceof Localizacao)
            {
                Localizacao outra = (Localizacao) o;

                if (this.logradouro.equals(outra.logradouro) && this.bairro.equals(outra.bairro) && this.cidade.equals(outra.cidade)
                        && this.complemento.equals(outra.complemento) && this.cep.equals(outra.cep) && this.numero == outra.numero
                        && this.estado.equals(outra.estado))
                {
                    return true;
                }
            }
        }
        return false;
    }
}
