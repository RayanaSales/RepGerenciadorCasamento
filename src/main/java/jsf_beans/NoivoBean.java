package jsf_beans;

import entidades.Noivo;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import servico.NoivoServico;

@ManagedBean
@SessionScoped
public class NoivoBean implements Serializable
{
    @EJB
    private NoivoServico noivoServico;

    public List<Noivo> noivos;
    public Noivo noivo;

    public NoivoBean()
    {
        noivo = new Noivo();
    }
    
    public void listar()
    {        
        noivos = noivoServico.listar();
    }
    
    public void salvar()
    {
        listar(); //atualize a minha lista
        if (!noivos.contains(noivo))
        {
            noivoServico.salvar(noivo);
            adicionarMessagem(FacesMessage.SEVERITY_INFO, "Salvo com sucesso!");
        } else
        {
            adicionarMessagem(FacesMessage.SEVERITY_INFO, "Noivo já existe!");
        }        
        noivo = new Noivo(); //renove a instancia, para o proximo elemento
    }    
    
    public void editar(int id)
    {
        listar(); //atualize a minha lista
        noivo.setId(id);
        noivoServico.atualizar(noivo);
        adicionarMessagem(FacesMessage.SEVERITY_INFO, "Alterado com sucesso!");
        noivo = new Noivo();
    }

    public void remover(Noivo noivo)
    {
        listar(); //atualize a minha lista

        if (noivos.contains(noivo))
        {
            noivoServico.remover(noivo);
            adicionarMessagem(FacesMessage.SEVERITY_INFO, "Removido com sucesso!");
        } else
        {
            adicionarMessagem(FacesMessage.SEVERITY_INFO, "Noivo não existe!");
        }
    }
    
    public NoivoServico getNoivoServico()
    {
        return noivoServico;
    }

    public void setNoivoServico(NoivoServico noivoServico)
    {
        this.noivoServico = noivoServico;
    }

    public List<Noivo> getNoivos()
    {
        listar(); //atualize a minha lista
        return noivos;
    }

    public void setNoivos(List<Noivo> noivos)
    {
        this.noivos = noivos;
    }

    public Noivo getNoivo()
    {
        return noivo;
    }

    public void setNoivo(Noivo noivo)
    {
        this.noivo = noivo;
    }
    
    
    
    
    protected void adicionarMessagem(FacesMessage.Severity severity, String mensagem)
    {
        FacesMessage message = new FacesMessage(severity, mensagem, "");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
