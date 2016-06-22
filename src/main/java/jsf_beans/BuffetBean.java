package jsf_beans;

import entidades.Buffet;
import entidades.Cerimonia;
import entidades.ComesBebes;
import excecao.ExcecaoNegocio;
import excecao.MensagemExcecao;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.validation.ConstraintViolationException;
import servico.BuffetServico;
import servico.CerimoniaServico;

@ManagedBean
@SessionScoped
public class BuffetBean implements Serializable {

    @EJB
    private BuffetServico buffetServico;

    @EJB
    private CerimoniaServico cerimoniaServico;

    public List<Buffet> buffets;
    public Buffet buffet;

    public BuffetBean() {
        buffet = new Buffet();
    }

    public List<ComesBebes> buscarComesEBebes(int idBuffet) {
        buffet = buffetServico.buscar(idBuffet); //trouxe a lista de comes e bebes        
        return buffet.getComesBebes();
    }

    public void salvar() {

        listar();

        try {
            buffetServico.salvar(buffet);
            adicionarMessagem(FacesMessage.SEVERITY_INFO, "Cadastro realizado com sucesso!");
        } catch (ExcecaoNegocio ex) {
            adicionarMessagem(FacesMessage.SEVERITY_WARN, ex.getMessage());
        } catch (EJBException ex) {
            if (ex.getCause() instanceof ConstraintViolationException) {
                MensagemExcecao mensagemExcecao = new MensagemExcecao(ex.getCause());
                adicionarMessagem(FacesMessage.SEVERITY_WARN, mensagemExcecao.getMensagem());
            }
        }

        //atualize a minha lista
        buffet = new Buffet(); //renove a instancia, para o proximo elemento        
    }

    public void editar(int id) {

        listar(); //atualize a minha lista   
        buffet.setId(id);
        try {

            buffetServico.atualizar(buffet);
            adicionarMessagem(FacesMessage.SEVERITY_INFO, "Alterado com sucesso!");
        } catch (ExcecaoNegocio ex) {
            adicionarMessagem(FacesMessage.SEVERITY_WARN, ex.getMessage());

        } catch (EJBException ex) {
            if (ex.getCause() instanceof ConstraintViolationException) {
                MensagemExcecao mensagemExcecao = new MensagemExcecao(ex.getCause());
                adicionarMessagem(FacesMessage.SEVERITY_WARN, mensagemExcecao.getMensagem());
            }
        }

        buffet = new Buffet();
    }

    public void remover(Buffet buffet) {
        listar(); //atualize a minha lista

        if (buffet.getComesBebes().isEmpty() && algumaCerimoniaMeTem(buffet) == false) //se nao tiver comida, pode apagar.
        {
            if (buffets.contains(buffet)) {
                buffetServico.remover(buffet);
                adicionarMessagem(FacesMessage.SEVERITY_INFO, "Removido com sucesso!");
            } else {
                adicionarMessagem(FacesMessage.SEVERITY_INFO, "Buffet não existe!");
            }
        }

        if (!buffet.getComesBebes().isEmpty())//tem roupas, nao pode excluir esse noivo
        {
            adicionarMessagem(FacesMessage.SEVERITY_INFO, "Esse buffet não pode ser excluido. Ele possui comes e bebes.");
        }
        if (algumaCerimoniaMeTem(buffet) == true) {
            adicionarMessagem(FacesMessage.SEVERITY_INFO, "Pertenço a uma cerimonia. Não me exclua.");
        }
    }

    protected void adicionarMessagem(FacesMessage.Severity severity, String mensagem) {
        FacesMessage message = new FacesMessage(severity, mensagem, "");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void setBuffetServico(BuffetServico buffetServico) {
        this.buffetServico = buffetServico;
    }

    public void listar() {
        buffets = buffetServico.listar();
    }

    public List<Buffet> getBuffets() {
        listar();
        return buffets;
    }

    public void setBuffets(List<Buffet> buffets) {
        this.buffets = buffets;
    }

    public Buffet getBuffet() {
        return buffet;
    }

    public void setBuffet(Buffet buffet) {
        this.buffet = buffet;
    }

    private boolean algumaCerimoniaMeTem(Buffet buffet) {
        List<Cerimonia> cerimonias = cerimoniaServico.listar();

        for (Cerimonia cerimonia : cerimonias) {
            if (cerimonia.getBuffet().getId().equals(buffet.getId())) {
                return true;
            }
        }
        return false;
    }
}
