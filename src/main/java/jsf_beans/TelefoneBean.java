package jsf_beans;

import entidades.Telefone;
import enumeracoes.TelefoneCategoria;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import servico.TelefoneServico;

@ManagedBean
@SessionScoped
public class TelefoneBean implements Serializable
{
    @EJB
    private TelefoneServico telefoneServico;

    public List<Telefone> telefones;
    public Telefone telefone;

    public TelefoneBean()
    {
        telefone = new Telefone();
    }

    public void salvar()
    {
        listar(); //atualize a minha lista

        if (!telefones.contains(telefone))
        {
            telefoneServico.salvar(telefone);
            adicionarMessagem(FacesMessage.SEVERITY_INFO, "Salvo com sucesso!");
        } else
        {
            adicionarMessagem(FacesMessage.SEVERITY_INFO, "Telefone já existe!");
        }
        
        telefone = new Telefone(); //renove a instancia, para o proximo elemento
    }

    public void editar(int id)
    {
        listar(); //atualize a minha lista      
        
        telefone.setId(id);
        telefoneServico.atualizar(telefone);       
        adicionarMessagem(FacesMessage.SEVERITY_INFO, "Alterado com sucesso!");
        telefone = new Telefone();
    }

    public void remover(Telefone tel)
    {
        listar(); //atualize a minha lista

        if (telefones.contains(tel))
        {
            telefoneServico.remover(tel);
            adicionarMessagem(FacesMessage.SEVERITY_INFO, "Removido com sucesso!");
        } else
        {
            adicionarMessagem(FacesMessage.SEVERITY_INFO, "Telefone não existe!");
        }
    }

    public void listar()
    {        
        telefones = telefoneServico.listar();
    }

    public List<Telefone> getTelefones()
    {
        listar(); //atualize a minha lista
        return telefones;
    }

    public Telefone getTelefone()
    {
        return telefone;
    }

    public void setTelefone(Telefone telefone)
    {
        this.telefone = telefone;
    }

    public void setTelefoneServico(TelefoneServico telefoneServico)
    {
        this.telefoneServico = telefoneServico;
    }

    public TelefoneCategoria[] getCategorias()
    {
        return TelefoneCategoria.values();
    }

    protected void adicionarMessagem(FacesMessage.Severity severity, String mensagem)
    {
        FacesMessage message = new FacesMessage(severity, mensagem, "");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}