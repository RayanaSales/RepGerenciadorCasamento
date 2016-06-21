package jsf_beans;

import entidades.Cerimonia;
import entidades.Presente;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import servico.PresenteServico;

@ManagedBean
@SessionScoped
public class PresenteBean implements Serializable
{

    @EJB
    private PresenteServico presenteServico;

    public List<Presente> presentes;
    public Presente presente;

    public PresenteBean()
    {
        presente = new Presente();
    }

    public void setar()
    {

    }

    public void salvar()
    {
        listar(); //atualize a minha lista

//        if (!presentes.contains(presente))
//        {
            //seta os presentes la na cerimonia
            presentes.add(presente);
            Cerimonia c = presente.getCerimonia();
            if (c != null)
            {
                c.setPresentes(presentes);
                presente.setCerimonia(c);
            }
            presenteServico.salvar(presente);
//            adicionarMessagem(FacesMessage.SEVERITY_INFO, "Salvo com sucesso!");
//        } else
//        {
//            adicionarMessagem(FacesMessage.SEVERITY_INFO, "Presente já existe!");
//        }

        presente = new Presente(); //renove a instancia, para o proximo elemento
    }

    public void editar(int id)
    {
        listar(); //atualize a minha lista
        presente.setId(id);
        presenteServico.atualizar(presente);
        adicionarMessagem(FacesMessage.SEVERITY_INFO, "Alterado com sucesso!");
        presente = new Presente();
    }

    public void remover(Presente presente)
    {
        listar(); //atualize a minha lista

        if (presente.getLojas().isEmpty()) //se nao tiver lojas
        {
            if (presentes.contains(presente))
            {
                presenteServico.remover(presente);
                adicionarMessagem(FacesMessage.SEVERITY_INFO, "Removido com sucesso!");
            } else
            {
                adicionarMessagem(FacesMessage.SEVERITY_INFO, "Presente não existe!");
            }
        } else
        {
            adicionarMessagem(FacesMessage.SEVERITY_INFO, "Presente não pode ser excluido. Possui lojas.");
        }
    }

    public void listar()
    {
        presentes = presenteServico.listar();
    }

    public List<Presente> getPresentes()
    {
        listar(); //atualize a minha lista
        return presentes;
    }

    public Presente getPresente()
    {
        return presente;
    }

    public void setPresente(Presente presente)
    {
        this.presente = presente;
    }

    public void setPresenteServico(PresenteServico presenteServico)
    {
        this.presenteServico = presenteServico;
    }

    protected void adicionarMessagem(FacesMessage.Severity severity, String mensagem)
    {
        FacesMessage message = new FacesMessage(severity, mensagem, "");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

//    public List<Loja> listarLojas()
//    {
//        return lojaServico.listar();
//    }
//
//    public List<Loja> listarLojasPresente(int id)
//    {
//        int idPresenteGeral;
//        List<Loja> lojasGeral = listarLojas();
//        List<Loja> lojasPresentes = new ArrayList<>();
//
//        for (int i = 0; i < lojasGeral.size(); i++)
//        {
//            idPresenteGeral = lojasGeral.get(i).getPresente().getId();
//
//            if (idPresenteGeral == id)
//            {
//                lojasPresentes.add(lojasGeral.get(i));
//            }
//        }
//        return lojasPresentes;
//    }
}
