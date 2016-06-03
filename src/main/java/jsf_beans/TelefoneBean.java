package jsf_beans;

import entidades.Telefone;
import enumeracoes.TelefoneCategoria;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import servico.TelefoneServico;

@ManagedBean
@SessionScoped
public class TelefoneBean implements Serializable
{
    @EJB
    public TelefoneServico telefoneServico;
    
    public Telefone telefone;

    public TelefoneBean()
    {
        telefone = new Telefone();
    }
    
        
    public void salvar()
    {
        telefoneServico.salvar(telefone);
    }

    public Telefone getTelefone()
    {
        return telefone;
    }

    public TelefoneServico getTelefoneServico()
    {
        return telefoneServico;
    }

    public void setTelefone(Telefone telefone)
    {
        this.telefone = telefone;
    }

    public void setTelefoneServico(TelefoneServico telefoneServico)
    {
        this.telefoneServico = telefoneServico;
    }
    
    public TelefoneCategoria[] getCategorias() {
        return TelefoneCategoria.values();
    }
}
