package jsf_beans;

import entidades.Noivo;
import java.io.Serializable;
import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import servico.NoivoServico;

@ManagedBean
@ViewScoped
public class Noivo_Bean implements Serializable
{
    @EJB
    private NoivoServico noivoServico;
    
    protected void salvarNoivo(Noivo noivo)
    {
        noivoServico.salvar(noivo);
    }
}
