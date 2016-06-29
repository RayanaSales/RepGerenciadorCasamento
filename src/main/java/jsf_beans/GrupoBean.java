package jsf_beans;

import entidades.Grupo;
import excecao.ExcecaoNegocio;
import excecao.MensagemExcecao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.validation.ConstraintViolationException;
import servico.GrupoServico;

@ManagedBean
@SessionScoped
public class GrupoBean implements Serializable
{
    @EJB
    private GrupoServico grupoServico;
    public List<Grupo> grupos;
    public Grupo grupo;
    
    public GrupoBean()
    {
        grupo = new Grupo();
        grupos = new ArrayList<>();
    }
    
    public void salvar()
    {
        listar(); //atualize a minha lista

        try
        {
            grupoServico.salvar(grupo);
            adicionarMessagem(FacesMessage.SEVERITY_INFO, "Cadastro realizado com sucesso!");
        } catch (ExcecaoNegocio ex)
        {
            adicionarMessagem(FacesMessage.SEVERITY_WARN, ex.getMessage());
        } catch (EJBException ex)
        {
            if (ex.getCause() instanceof ConstraintViolationException)
            {
                MensagemExcecao mensagemExcecao = new MensagemExcecao(ex.getCause());
                adicionarMessagem(FacesMessage.SEVERITY_WARN, mensagemExcecao.getMensagem());
            }
        }
        grupo = new Grupo(); //renove a instancia, para o proximo elemento
    }

    public GrupoServico getGrupoServico()
    {
        return grupoServico;
    }

    public void setGrupoServico(GrupoServico grupoServico)
    {
        this.grupoServico = grupoServico;
    }

    public List<Grupo> getGrupos()
    {
        listar();
        return grupos;
    }

    public void setGrupos(List<Grupo> grupos)
    {
        this.grupos = grupos;
    }

    public Grupo getGrupo()
    {
        return grupo;
    }

    public void setGrupo(Grupo grupo)
    {
        this.grupo = grupo;
    }
        
    public void listar()
    {
        grupos = grupoServico.listar();
    }
    
     protected void adicionarMessagem(FacesMessage.Severity severity, String mensagem)
    {
        FacesMessage message = new FacesMessage(severity, mensagem, "");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
