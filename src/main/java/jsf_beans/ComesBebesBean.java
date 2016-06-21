package jsf_beans;

import entidades.Buffet;
import entidades.ComesBebes;
import enumeracoes.ComesBebesCategoria;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import servico.ComesBebesServico;

@ManagedBean
@SessionScoped
public class ComesBebesBean implements Serializable
{

    @EJB
    private ComesBebesServico comesBebesServico;

    public List<ComesBebes> cbs;
    public ComesBebes cb;

    public ComesBebesBean()
    {
        cb = new ComesBebes();
    }

    public void salvar()
    {
        listar(); //atualize a minha lista

//        if (!cbs.contains(cb))
//        {
//            //sete no buffet, a lista de come e bebes
//            cbs.add(cb);
            Buffet b = cb.getBuffet();
            if (b != null)
            {
                b.setComesBebes(cbs);
                cb.setBuffet(b);
            }
            comesBebesServico.salvar(cb);
            
//            adicionarMessagem(FacesMessage.SEVERITY_INFO, "Salvo com sucesso!");
//        } else
//        {
//            adicionarMessagem(FacesMessage.SEVERITY_INFO, "Comes e bebes já existe!");
//        }

        cb = new ComesBebes(); //renove a instancia, para o proximo elemento
    }

    public void editar(int id)
    {
        listar(); //atualize a minha lista   
        cb.setId(id);
        comesBebesServico.atualizar(cb);
        adicionarMessagem(FacesMessage.SEVERITY_INFO, "Alterado com sucesso!");
        cb = new ComesBebes();
    }

    public void remover(ComesBebes cb)
    {
        listar(); //atualize a minha lista

        if (cbs.contains(cb))
        {
            comesBebesServico.remover(cb);
            adicionarMessagem(FacesMessage.SEVERITY_INFO, "Removido com sucesso!");
        } else
        {
            adicionarMessagem(FacesMessage.SEVERITY_INFO, "Comes e bebes não existe!");
        }
    }

    protected void adicionarMessagem(FacesMessage.Severity severity, String mensagem)
    {
        FacesMessage message = new FacesMessage(severity, mensagem, "");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    @PostConstruct
    public void listar()
    {
        cbs = comesBebesServico.listar();
    }

    public List<ComesBebes> getCbs()
    {
        listar();
        return cbs;
    }

    public void setCbs(List<ComesBebes> comesBebes)
    {
        this.cbs = comesBebes;
    }

    public ComesBebes getCb()
    {
        return cb;
    }

    public void setCb(ComesBebes cb)
    {
        this.cb = cb;
    }

    public ComesBebesCategoria[] getCategorias()
    {
        return ComesBebesCategoria.values();
    }
}
