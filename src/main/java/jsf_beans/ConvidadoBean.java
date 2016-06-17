package jsf_beans;

import entidades.Cerimonia;
import entidades.Convidado;
import entidades.Pessoa;
import enumeracoes.ConvidadoCategoria;
import java.io.Serializable;
import java.util.ArrayList;
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
import org.primefaces.event.RowEditEvent;
import servico.ConvidadoServico;

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

    public void onRowEdit(RowEditEvent event)
    {
        System.out.println("jsf_beans.ConvidadoBean.onRowEdit()");
        Convidado c = (Convidado) event.getObject();
        editar(c.getId());
        System.out.println("NOVO NOME CONVIDADO: " + c.getNome());
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
        listar(); //atualize a minha lista

        if (!convidados.contains(convidado))
        {
            //setar o produtor, na lista de novasPessoas em cerimonia.
            Cerimonia cerimonia = convidado.getCerimonia();
            List<Pessoa> novasPessoas = new ArrayList<>();
            novasPessoas.add(convidado);

            if (cerimonia != null)
            {
                cerimonia.setPessoas(novasPessoas);
            }
            convidado.setCerimonia(cerimonia);

            convidadoServico.salvar(convidado);
            adicionarMessagem(FacesMessage.SEVERITY_INFO, "Salvo com sucesso!");
        } else
        {
            adicionarMessagem(FacesMessage.SEVERITY_INFO, "Convidado já existe!");
        }

        convidado = new Convidado(); //renove a instancia, para o proximo elemento
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

        if (convidado.getTelefones().isEmpty()) //se ela nao tem telefones
        {
            if (convidados.contains(convidado))
            {
                convidadoServico.remover(convidado);
                adicionarMessagem(FacesMessage.SEVERITY_INFO, "Removido com sucesso!");
            } else
            {
                adicionarMessagem(FacesMessage.SEVERITY_INFO, "Convidado não existe!");
            }
        } else
        {
            adicionarMessagem(FacesMessage.SEVERITY_INFO, "Esse convidado não pode ser excluido. Ele possui celulares.");
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
        } else
        {
            System.out.println("LOCAL INVALIDOOOOOOOOOOOOOOOOOO");
        }

        return valido;
    }

    public void teste(RowEditEvent event)
    {
        System.out.println("aaaaaaaaaaaa");
        Convidado c = (Convidado) event.getObject();
        System.out.println("OBJETO: " + c.getNome());
    }
}
