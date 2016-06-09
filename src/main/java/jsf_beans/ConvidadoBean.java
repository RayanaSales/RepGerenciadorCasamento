package jsf_beans;

import entidades.Convidado;
import entidades.Localizacao;
import enumeracoes.ConvidadoCategoria;
import enumeracoes.EstadosDoBrasil;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import servico.ConvidadoServico;
import servico.LocalizacaoServico;

@ManagedBean
@SessionScoped
public class ConvidadoBean implements Serializable
{
    @EJB
    private ConvidadoServico convidadoServico;

    public List<Convidado> convidados;
    public Convidado convidado;

    public ConvidadoBean()
    {
        convidado = new Convidado();        
    }

    public void listar()
    {
        convidados = convidadoServico.listar();
    }

    public List<Convidado> getConvidados()
    {
        listar(); //atualize a minha lista
        return convidados;
    }

    public void salvar()
    {
//        if (validaObjeto(convidado) == true)
//        {
            listar(); //atualize a minha lista

            if (!convidados.contains(convidado))
            {
                convidadoServico.salvar(convidado);
                adicionarMessagem(FacesMessage.SEVERITY_INFO, "Salvo com sucesso!");
            } else
            {
                adicionarMessagem(FacesMessage.SEVERITY_INFO, "Convidado já existe!");
            }

            convidado = new Convidado(); //renove a instancia, para o proximo elemento

//        } else
//        {
//            adicionarMessagem(FacesMessage.SEVERITY_INFO, "Objeto invalido");
//        }
    }

    public void editar(int id)
    {
        listar(); //atualize a minha lista
        convidado.setId(id);
        convidadoServico.atualizar(convidado);
        adicionarMessagem(FacesMessage.SEVERITY_INFO, "Alterado com sucesso!");
        convidado = new Convidado();
    }

    public void remover(Convidado convidado)
    {
        listar(); //atualize a minha lista

        if (convidados.contains(convidado))
        {
            convidadoServico.remover(convidado);
            adicionarMessagem(FacesMessage.SEVERITY_INFO, "Removido com sucesso!");
        } else
        {
            adicionarMessagem(FacesMessage.SEVERITY_INFO, "Convidado não existe!");
        }
    }

    protected void adicionarMessagem(FacesMessage.Severity severity, String mensagem)
    {
        FacesMessage message = new FacesMessage(severity, mensagem, "");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public Convidado getConvidado()
    {
        return convidado;
    }

    public ConvidadoServico getConvidadoServico()
    {
        return convidadoServico;
    }
    
    public ConvidadoCategoria[] getCategorias()
    {
        return ConvidadoCategoria.values();
    }

    public void setConvidado(Convidado convidado)
    {
        this.convidado = convidado;
    }

    public void setConvidadoServico(ConvidadoServico convidadoServico)
    {
        this.convidadoServico = convidadoServico;
    }

    public boolean validaObjeto(Convidado c)
    {
        boolean valido = false;

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Convidado>> constraintViolations = validator.validate(c);
        if (constraintViolations.size() > 0)
        {
            Iterator<ConstraintViolation<Convidado>> iterator = constraintViolations.iterator();
            while (iterator.hasNext())
            {
                ConstraintViolation<Convidado> cv = iterator.next();
                System.out.println(cv.getMessage());
                System.out.println(cv.getPropertyPath());
            }
        }

        if (constraintViolations.isEmpty())
        {
            valido = true;
            System.out.println("LOCAL VALIDO");
        }
        else System.out.println("LOCAL INVALIDOOOOOOOOOOOOOOOOOO");

        return valido;
    }
}