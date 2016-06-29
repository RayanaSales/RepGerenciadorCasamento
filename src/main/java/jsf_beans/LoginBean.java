package jsf_beans;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import org.hibernate.validator.constraints.NotBlank;

@ManagedBean
@RequestScoped
public class LoginBean implements Serializable
{
    @NotBlank
    String username;
    @NotBlank
    String senha;

    public String efetuarLogin()
    {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        try
        {
            request.login(username, senha); //chama indiretamente o codigo authent user  
            System.out.println("logado");
            adicionarMessagem(FacesMessage.SEVERITY_INFO, "Logado");
            return "sucesso";
        } catch (ServletException ex)
        {
            System.out.println("Causa do erro: " + ex.getCause());
            ex.printStackTrace();       
                     
            return "falha";
        }
    }

    protected void adicionarMessagem(FacesMessage.Severity severity, String mensagem)
    {
        FacesMessage message = new FacesMessage(severity, mensagem, "");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getSenha()
    {
        return senha;
    }

    public void setSenha(String senha)
    {
        this.senha = senha;
    }

}