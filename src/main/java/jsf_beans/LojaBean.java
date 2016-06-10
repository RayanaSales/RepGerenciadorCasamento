package jsf_beans;

import entidades.Loja;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import servico.LojaServico;

@ManagedBean
@SessionScoped
public class LojaBean implements Serializable
{
    @EJB
    private LojaServico noivoServico;

    public List<Loja> lojas;
    public Loja loja;

    public LojaBean()
    {
        loja = new Loja();
    }
    
    public void listar()
    {        
        lojas = noivoServico.listar();
        
    }
    
    public void salvar()
    {
        listar(); //atualize a minha lista
        if (!lojas.contains(loja))
        {
            System.out.println("Telefone : " + loja.getTelefone());
            System.out.println("Localizacao : " + loja.getLocalizacao());
            System.out.println("Presente : " + loja.getPresente() );
            noivoServico.salvar(loja);
            adicionarMessagem(FacesMessage.SEVERITY_INFO, "Salvo com sucesso!");
        } else
        {
            adicionarMessagem(FacesMessage.SEVERITY_INFO, "Loja já existe!");
        }        
        adicionarMessagem(FacesMessage.SEVERITY_INFO, "ESTOU NO SALVAR!");
        loja = new Loja(); //renove a instancia, para o proximo elemento
    }    
    
    public void editar(int id)
    {
        listar(); //atualize a minha lista
        loja.setId(id);
        noivoServico.atualizar(loja);
        adicionarMessagem(FacesMessage.SEVERITY_INFO, "Alterado com sucesso!");
        loja = new Loja();
    }

    public void remover(Loja loja)
    {
        listar(); //atualize a minha lista

        if (lojas.contains(loja))
        {
            noivoServico.remover(loja);
            adicionarMessagem(FacesMessage.SEVERITY_INFO, "Removido com sucesso!");
        } else
        {
            adicionarMessagem(FacesMessage.SEVERITY_INFO, "Loja não existe!");
        }
    }
  
    public List<Loja> getLojas()
    {
        listar(); //atualize a minha lista
        return lojas;
    }

    public void setNoivos(List<Loja> lojas)
    {
        this.lojas = lojas;
    }

    public Loja getLoja()
    {
        return loja;
    }

    public void setLoja(Loja loja)
    {
        this.loja = loja;
    }
       
    protected void adicionarMessagem(FacesMessage.Severity severity, String mensagem)
    {
        FacesMessage message = new FacesMessage(severity, mensagem, "");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    
}
