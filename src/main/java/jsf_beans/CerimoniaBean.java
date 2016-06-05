//package jsf_beans;
//
//import entidades.Cerimonia;
//import java.io.Serializable;
//import java.util.List;
//import javax.ejb.EJB;
//import javax.faces.application.FacesMessage;
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.SessionScoped;
//import javax.faces.context.FacesContext;
//import servico.CerimoniaServico;
//
//@ManagedBean
//@SessionScoped
//public class CerimoniaBean implements Serializable
//{
//    @EJB
//    private CerimoniaServico cerimoniaServico;
//
//    public List<Cerimonia> cerimonias;
//    public Cerimonia cerimonia;
//    
//    public CerimoniaBean()
//    {
//    }
//        
//    protected void adicionarMessagem(FacesMessage.Severity severity, String mensagem)
//    {
//        FacesMessage message = new FacesMessage(severity, mensagem, "");
//        FacesContext.getCurrentInstance().addMessage(null, message);
//    }
//        
//    public void salvar()
//    {
//        listar(); //atualize a minha lista
//
//        if (!cerimonias.contains(cerimonia))
//        {
//            cerimoniaServico.salvar(cerimonia);
//            adicionarMessagem(FacesMessage.SEVERITY_INFO, "Salvo com sucesso!");
//        } else
//        {
//            adicionarMessagem(FacesMessage.SEVERITY_INFO, "Telefone já existe!");
//        }
//        
//        cerimonia = new Cerimonia(); //renove a instancia, para o proximo elemento
//    }
//
//    public void editar(Cerimonia t)
//    {
//        listar(); //atualize a minha lista
//
//        cerimoniaServico.atualizar(t);
//        adicionarMessagem(FacesMessage.SEVERITY_INFO, "Alterado com sucesso!");
//    }
//    
//    
//   public void remover(Cerimonia cer)
//    {
//        listar(); //atualize a minha lista
//
//        if (cerimonias.contains(cer))
//        {
//            cerimoniaServico.remover(cer);
//            adicionarMessagem(FacesMessage.SEVERITY_INFO, "Removido com sucesso!");
//        } else
//        {
//            adicionarMessagem(FacesMessage.SEVERITY_INFO, "Cerimonia não existe!");
//        }
//    } 
//    
//   public void listar()
//    {        
//        cerimonias = cerimoniaServico.listar();
//    }
//
//    public List<Cerimonia> getCerimonias()
//    {
//        listar(); //atualize a minha lista
//
//        return cerimonias;
//    }
//
//    public Cerimonia getCerimonia()
//    {
//        return cerimonia;
//    }
//
//    public CerimoniaServico getCerimoniaServico()
//    {
//        return cerimoniaServico;
//    }
//
//    public void setCerimonia(Cerimonia cerimonia)
//    {
//        this.cerimonia = cerimonia;
//    }
//
//    public void setTelefoneServico(CerimoniaServico cerimoniaServico)
//    {
//        this.cerimoniaServico = cerimoniaServico;
//    }
//}
