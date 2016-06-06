package jsf_beans;

import entidades.Cerimonia;
import entidades.ProdutorDeMidia;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import servico.ProdutorDeMidiaServico;

@ManagedBean
@SessionScoped
public class ProdutorDeMidiaBean implements Serializable
{

    @EJB
    public ProdutorDeMidiaServico produtorServico;

    public List<ProdutorDeMidia> produtores;
    public ProdutorDeMidia produtor;

    public ProdutorDeMidiaBean()
    {
        produtor = new ProdutorDeMidia();
    }

    protected void adicionarMessagem(FacesMessage.Severity severity, String mensagem)
    {
        FacesMessage message = new FacesMessage(severity, mensagem, "");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void listar()
    {
        produtores = produtorServico.listar();
    }

    public void salvar()
    {
        listar(); //atualize a minha lista
        if (!produtores.contains(produtor))
        {
            produtorServico.salvar(produtor);
            adicionarMessagem(FacesMessage.SEVERITY_INFO, "Salvo com sucesso!");
        } else
        {
            adicionarMessagem(FacesMessage.SEVERITY_INFO, "Produtor já existe!");
        }
        produtor = new ProdutorDeMidia(); //renove a instancia, para o proximo elemento
    }

    public void editar(ProdutorDeMidia t)
    {
        listar(); //atualize a minha lista

        produtorServico.atualizar(t);
        adicionarMessagem(FacesMessage.SEVERITY_INFO, "Alterado com sucesso!");
    }

    public void remover(ProdutorDeMidia cer)
    {
        listar(); //atualize a minha lista

        if (produtores.contains(cer))
        {
            produtorServico.remover(cer);
            adicionarMessagem(FacesMessage.SEVERITY_INFO, "Removido com sucesso!");
        } else
        {
            adicionarMessagem(FacesMessage.SEVERITY_INFO, "Produtor não existe!");
        }
    }

    public ProdutorDeMidiaServico getProdutorServico()
    {
        return produtorServico;
    }

    public List<ProdutorDeMidia> getProdutores()
    {
        listar(); //atualize a minha lista
        return produtores;
    }

    public ProdutorDeMidia getProdutor()
    {
        return produtor;
    }

    public void setProdutor(ProdutorDeMidia produtor)
    {
        this.produtor = produtor;
    }
}
