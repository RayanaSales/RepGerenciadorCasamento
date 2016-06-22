package jsf_beans;

import entidades.Loja;
import entidades.Telefone;
import enumeracoes.TelefoneCategoria;
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
import servico.LojaServico;
import servico.TelefoneServico;

@ManagedBean
@SessionScoped
public class LojaBean implements Serializable
{
    @EJB
    private LojaServico lojaServico;

    @EJB
    private TelefoneServico telefoneServico;

    public List<Loja> lojas;
    public Loja loja;
    public List<Telefone> telefonesEmpresariais;

    public LojaBean()
    {
        loja = new Loja();
    }

    public void listar()
    {
        lojas = lojaServico.listar();
    }

    public void salvar()
    {
        listar(); //atualize a minha lista

        /* SE EU FIZER ASSIM, ELE SALVA CERTO. MAS ESSA MERDA VEM NUL, DO XHTML.        
        Presente p = presenteServico.buscar(2);
        System.out.println("PRESENTE NO BANCO: " + p.getNome());
        loja.setPresente(p);  */        
        
     /*   boolean salvou =  lojaServico.salvar(loja);
        if(salvou)
            adicionarMessagem(FacesMessage.SEVERITY_INFO, "Salvo com sucesso!");
        else
            adicionarMessagem(FacesMessage.SEVERITY_INFO, "Loja já existe!"); */
     
        try
        {
            lojaServico.salvar(loja);
            adicionarMessagem(FacesMessage.SEVERITY_INFO, "Cadastro realizado com sucesso!");
        } catch (ExcecaoNegocio ex)
        {
            adicionarMessagem(FacesMessage.SEVERITY_WARN, ex.getMessage());
        } catch (EJBException ex)
        {
            if (ex.getCause() instanceof ConstraintViolationException)
            {
                MensagemExcecao mensagemExcecao = new MensagemExcecao(ex.getCause());
                adicionarMessagem(FacesMessage.SEVERITY_WARN, mensagemExcecao.getMensagem());
            }
        }    
        loja = new Loja(); //renove a instancia, para o proximo elemento
    }

    public void editar(int id)
    {
        listar(); //atualize a minha lista
        loja.setId(id);
        boolean alterou = lojaServico.atualizar(loja);
        if(alterou)
            adicionarMessagem(FacesMessage.SEVERITY_INFO, "Alterado com sucesso!");
        else
            adicionarMessagem(FacesMessage.SEVERITY_INFO, "Loja já existe!");
        
        loja = new Loja();
    }

    public void remover(Loja loja)
    {
        listar(); //atualize a minha lista

        if (lojas.contains(loja))
        {
            lojaServico.remover(loja);
            adicionarMessagem(FacesMessage.SEVERITY_INFO, "Removido com sucesso!");
        } else
        {
            adicionarMessagem(FacesMessage.SEVERITY_INFO, "Loja não existe!");
        }
    }
    
    public List<Telefone> getTelefonesEmpresariais()
    {
        telefonesEmpresariais = telefoneServico.listarTelefonesPorCategoria(TelefoneCategoria.empresarial);
        
        return telefonesEmpresariais;
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
