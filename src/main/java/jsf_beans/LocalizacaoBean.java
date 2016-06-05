package jsf_beans;

import entidades.Localizacao;
import enumeracoes.EstadosDoBrasil;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import servico.LocalizacaoServico;

@ManagedBean
@SessionScoped
public class LocalizacaoBean implements Serializable {

    @EJB
    public LocalizacaoServico localizacaoServico;

    public Localizacao localizacao;

    public LocalizacaoServico getLocalizacaoServico() {
        return localizacaoServico;
    }

    public void setLocalizacaoServico(LocalizacaoServico localizacaoServico) {
        this.localizacaoServico = localizacaoServico;
    }

    public Localizacao getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(Localizacao localizacao) {
        this.localizacao = localizacao;
    }
    
    public LocalizacaoBean() 
    {
        localizacao = new Localizacao();
    }
    
    public void salvar()
    {
      localizacaoServico.salvar(localizacao);
      adicionarMessagem(FacesMessage.SEVERITY_INFO, "Cadastro realizado com sucesso!");
   
    }
    
    protected void adicionarMessagem(FacesMessage.Severity severity, String mensagem) 
    {
        FacesMessage message = new FacesMessage(severity, mensagem, "");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public EstadosDoBrasil[] getEstados() {
        return EstadosDoBrasil.values();
    }
}
