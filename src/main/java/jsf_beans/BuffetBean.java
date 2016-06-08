package jsf_beans;

import entidades.Buffet;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import servico.BuffetServico;

@ManagedBean
@SessionScoped
public class BuffetBean implements Serializable
{

    @EJB
    private BuffetServico buffetServico;

    public List<Buffet> buffets;
    public Buffet buffet;

    public BuffetBean()
    {
        buffet = new Buffet();
    }

    public void salvar()
    {
        listar(); //atualize a minha lista

        if (!buffets.contains(buffet))
        {
            buffetServico.salvar(buffet);
            adicionarMessagem(FacesMessage.SEVERITY_INFO, "Salvo com sucesso!");
        } else
        {
            adicionarMessagem(FacesMessage.SEVERITY_INFO, "Buffet já existe!");
        }

        buffet = new Buffet(); //renove a instancia, para o proximo elemento
    }

    public void editar(int id)
    {
        listar(); //atualize a minha lista   
        buffet.setId(id);
        buffetServico.atualizar(buffet);
        adicionarMessagem(FacesMessage.SEVERITY_INFO, "Alterado com sucesso!");
        buffet = new Buffet();
    }

    public void remover(Buffet tel)
    {
        listar(); //atualize a minha lista

        if (buffets.contains(tel))
        {
            buffetServico.remover(tel);
            adicionarMessagem(FacesMessage.SEVERITY_INFO, "Removido com sucesso!");
        } else
        {
            adicionarMessagem(FacesMessage.SEVERITY_INFO, "Buffet não existe!");
        }
    }

    protected void adicionarMessagem(FacesMessage.Severity severity, String mensagem)
    {
        FacesMessage message = new FacesMessage(severity, mensagem, "");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void setBuffetServico(BuffetServico buffetServico)
    {
        this.buffetServico = buffetServico;
    }

    public void listar()
    {
        buffets = buffetServico.listar();
    }

    public List<Buffet> getBuffets()
    {
        listar();
        return buffets;
    }

    public void setBuffets(List<Buffet> buffets)
    {
        this.buffets = buffets;
    }

    public Buffet getBuffet()
    {
        return buffet;
    }

    public void setBuffet(Buffet buffet)
    {
        this.buffet = buffet;
    }
}
