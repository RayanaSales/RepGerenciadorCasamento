package jsf_beans;

import entidades.Noivo;
import entidades.RoupaDosNoivos;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import servico.NoivoServico;
import servico.RoupaDosNoivosServico;

@ManagedBean
@SessionScoped
public class RoupaDosNoivosBean implements Serializable
{
    @EJB
    private RoupaDosNoivosServico roupaServico;

    @EJB
    private NoivoServico noivoServico;
    
    public List<RoupaDosNoivos> roupas;
    public RoupaDosNoivos roupa;

    public RoupaDosNoivosBean()
    {
        roupa = new RoupaDosNoivos();
    }

    public void salvar()
    {
        listar(); //atualize a minha lista

        if (!roupas.contains(roupa))
        {
            roupaServico.salvar(roupa);
            adicionarMessagem(FacesMessage.SEVERITY_INFO, "Salvo com sucesso!");
        } else
        {
            adicionarMessagem(FacesMessage.SEVERITY_INFO, "Roupa já existe!");
        }
        
        roupa = new RoupaDosNoivos(); //renove a instancia, para o proximo elemento
    }

    public void editar(int id)
    {
        listar(); //atualize a minha lista      
        
        roupa.setId(id);
        roupaServico.atualizar(roupa);       
        adicionarMessagem(FacesMessage.SEVERITY_INFO, "Alterado com sucesso!");
        roupa = new RoupaDosNoivos();
    }

    public void remover(RoupaDosNoivos roupa)
    {
        listar(); //atualize a minha lista

        if (roupas.contains(roupa))
        {
            roupaServico.remover(roupa);
            adicionarMessagem(FacesMessage.SEVERITY_INFO, "Removido com sucesso!");
        } else
        {
            adicionarMessagem(FacesMessage.SEVERITY_INFO, "Telefone não existe!");
        }
    }

    public void listar()
    {        
        roupas = roupaServico.listar();
    }

    public List<RoupaDosNoivos> getRoupas()
    {
        listar(); //atualize a minha lista
        return roupas;
    }

    public RoupaDosNoivos getRoupa()
    {
        return roupa;
    }

    public RoupaDosNoivosServico getRoupaDosNoivosServico()
    {
        return roupaServico;
    }

    public void setRoupaDosNoivos(RoupaDosNoivos roupa)
    {
        this.roupa = roupa;
    }

    public void setRoupaDosNoivosServico(RoupaDosNoivosServico roupaServico)
    {
        this.roupaServico = roupaServico;
    }

    protected void adicionarMessagem(FacesMessage.Severity severity, String mensagem)
    {
        FacesMessage message = new FacesMessage(severity, mensagem, "");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public List<Noivo> listarNoivos(){
        return noivoServico.listar();
    }
}
