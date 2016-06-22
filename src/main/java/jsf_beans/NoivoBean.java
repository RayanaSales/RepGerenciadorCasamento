package jsf_beans;

import entidades.Cerimonia;
import entidades.Noivo;
import entidades.Pessoa;
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
import servico.NoivoServico;

@ManagedBean
@SessionScoped
public class NoivoBean implements Serializable {

    @EJB
    private NoivoServico noivoServico;

    public List<Noivo> noivos;
    public Noivo noivo;

    public String[] roupasSelecionadas;

    public NoivoBean() {
        noivo = new Noivo();
    }

    public void setRoupasSelecionadas(String[] roupasSelecionadas) {
        this.roupasSelecionadas = roupasSelecionadas;

    }

    public String[] getRoupasSelecionadas() {

        return roupasSelecionadas;
    }

    public void listar() {
        noivos = noivoServico.listar();
    }

    public void salvar() {
        listar(); //atualize a minha lista

        //seta o noivo na cerimonia
        Cerimonia cerimonia = noivo.getCerimonia();
        List<Pessoa> novasPessoas = new ArrayList<>();
        novasPessoas.add(noivo);
        if (cerimonia != null) {
            cerimonia.setPessoas(novasPessoas);
        }
        noivo.setCerimonia(cerimonia);

        try {
            noivoServico.salvar(noivo);
            adicionarMessagem(FacesMessage.SEVERITY_INFO, "Cadastro realizado com sucesso!");
        } catch (ExcecaoNegocio ex) {
            adicionarMessagem(FacesMessage.SEVERITY_WARN, ex.getMessage());
        } catch (EJBException ex) {
            if (ex.getCause() instanceof ConstraintViolationException) {
                MensagemExcecao mensagemExcecao = new MensagemExcecao(ex.getCause());
                adicionarMessagem(FacesMessage.SEVERITY_WARN, mensagemExcecao.getMensagem());
            }
        }

        noivo = new Noivo(); //renove a instancia, para o proximo elemento
    }

    public void editar(int id) {
        listar(); //atualize a minha lista
        noivo.setId(id);
        try {
            noivoServico.atualizar(noivo);
            adicionarMessagem(FacesMessage.SEVERITY_INFO, "Alterado com sucesso!");
        } catch (ExcecaoNegocio ex) {
            adicionarMessagem(FacesMessage.SEVERITY_WARN, ex.getMessage());
        } catch (EJBException ex) {
            if (ex.getCause() instanceof ConstraintViolationException) {
                MensagemExcecao mensagemExcecao = new MensagemExcecao(ex.getCause());
                adicionarMessagem(FacesMessage.SEVERITY_WARN, mensagemExcecao.getMensagem());
            }
        }

        noivo = new Noivo();
    }

    public void remover(Noivo noivo) {
        listar(); //atualize a minha lista

        if (noivo.getRoupaDosNoivos().isEmpty() && noivo.getTelefones().isEmpty()) //verifica se tem alguma roupa atribuida a esse noivo.
        { //nao tem, pode excluir
            if (noivos.contains(noivo)) {
                noivoServico.remover(noivo);
                adicionarMessagem(FacesMessage.SEVERITY_INFO, "Removido com sucesso!");
            } else {
                adicionarMessagem(FacesMessage.SEVERITY_INFO, "Noivo não existe!");
            }
        }

        if (!noivo.getRoupaDosNoivos().isEmpty()) //tem roupas, nao pode excluir esse noivo
        {
            adicionarMessagem(FacesMessage.SEVERITY_INFO, "Esse noivo não pode ser excluido. Ele possui roupas.");
        }

        if (!noivo.getTelefones().isEmpty()) //tem roupas, nao pode excluir esse noivo
        {
            adicionarMessagem(FacesMessage.SEVERITY_INFO, "Esse noivo não pode ser excluido. Ele possui celulares.");
        }
    }

    public NoivoServico getNoivoServico() {
        return noivoServico;
    }

    public void setNoivoServico(NoivoServico noivoServico) {
        this.noivoServico = noivoServico;
    }

    public List<Noivo> getNoivos() {
        listar(); //atualize a minha lista
        return noivos;
    }

    public void setNoivos(List<Noivo> noivos) {
        this.noivos = noivos;
    }

    public Noivo getNoivo() {
        return noivo;
    }

    public void setNoivo(Noivo noivo) {
        this.noivo = noivo;
    }

    protected void adicionarMessagem(FacesMessage.Severity severity, String mensagem) {
        FacesMessage message = new FacesMessage(severity, mensagem, "");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
