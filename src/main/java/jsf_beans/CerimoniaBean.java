package jsf_beans;

import entidades.Cerimonia;
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
import servico.CerimoniaServico;

@ManagedBean
@SessionScoped
public class CerimoniaBean implements Serializable
{
    @EJB
    public CerimoniaServico cerimoniaServico;

    public List<Cerimonia> cerimonias;
    public Cerimonia cerimonia;

    public CerimoniaBean()
    {        
        cerimonia = new Cerimonia();
    }

    protected void adicionarMessagem(FacesMessage.Severity severity, String mensagem)
    {
        FacesMessage message = new FacesMessage(severity, mensagem, "");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void salvar()
    {
        if (validaObjeto(cerimonia) == true)
        {
            listar(); //atualize a minha lista
            if (!cerimonias.contains(cerimonia))
            {
                cerimoniaServico.salvar(cerimonia);
                adicionarMessagem(FacesMessage.SEVERITY_INFO, "Salvo com sucesso!");
            } else
            {
                adicionarMessagem(FacesMessage.SEVERITY_INFO, "Cerimonia já existe!");
            }
            cerimonia = new Cerimonia(); //renove a instancia, para o proximo elemento
            
        } else
        {
            adicionarMessagem(FacesMessage.SEVERITY_INFO, "Objeto invalido");
        }

    }

    public void editar(int id)
    {
        listar(); //atualize a minha lista
        cerimonia.setId(id);
        cerimoniaServico.atualizar(cerimonia);
        adicionarMessagem(FacesMessage.SEVERITY_INFO, "Alterado com sucesso!");
        cerimonia = new Cerimonia();
    }

    public void remover(Cerimonia cer)
    {
        listar(); //atualize a minha lista

        if (cerimonias.contains(cer))
        {
            cerimoniaServico.remover(cer);
            adicionarMessagem(FacesMessage.SEVERITY_INFO, "Removido com sucesso!");
        } else
        {
            adicionarMessagem(FacesMessage.SEVERITY_INFO, "Cerimonia não existe!");
        }
    }

    public void listar()
    {        
        cerimonias = cerimoniaServico.listar();
    }

    public List<Cerimonia> getCerimonias()
    {
        listar(); //atualize a minha lista

        return cerimonias;
    }

    public Cerimonia getCerimonia()
    {
        return cerimonia;
    }

    public CerimoniaServico getCerimoniaServico()
    {
        return cerimoniaServico;
    }

    public void setCerimonia(Cerimonia cerimonia)
    {
        this.cerimonia = cerimonia;
    }

    public void setTelefoneServico(CerimoniaServico cerimoniaServico)
    {
        this.cerimoniaServico = cerimoniaServico;
    }

    public boolean validaObjeto(Cerimonia c)
    {
        boolean valido = false;

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Cerimonia>> constraintViolations = validator.validate(c);
        if (constraintViolations.size() > 0)
        {
            Iterator<ConstraintViolation<Cerimonia>> iterator = constraintViolations.iterator();
            while (iterator.hasNext())
            {
                ConstraintViolation<Cerimonia> cv = iterator.next();
                System.out.println(cv.getMessage());
                System.out.println(cv.getPropertyPath());
            }
        }

        if (constraintViolations.isEmpty())
        {
            valido = true;
        }

        return valido;
    }
}
