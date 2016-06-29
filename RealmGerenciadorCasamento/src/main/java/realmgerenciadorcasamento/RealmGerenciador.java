package realmgerenciadorcasamento;

import com.sun.appserv.connectors.internal.api.ConnectorRuntime;
import com.sun.appserv.security.AppservRealm;
import com.sun.enterprise.security.auth.realm.BadRealmException;
import com.sun.enterprise.security.auth.realm.InvalidOperationException;
import com.sun.enterprise.security.auth.realm.NoSuchRealmException;
import com.sun.enterprise.security.auth.realm.NoSuchUserException;
import com.sun.enterprise.security.common.Util;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.glassfish.hk2.api.ActiveDescriptor;
import org.glassfish.hk2.utilities.BuilderHelper;

public class RealmGerenciador extends AppservRealm
{

    private static String JTA_DATA_SOURCE = "jta-data-source";
    private static DataSource dataSource;

    @Override
    protected void init(Properties props) throws BadRealmException, NoSuchRealmException
    {
        super.setProperty(JTA_DATA_SOURCE, props.getProperty(JTA_DATA_SOURCE));
    }

    private Connection getConnection()
    {
        try
        {
            synchronized (this)
            {
                if (dataSource == null)
                {
                    ActiveDescriptor<ConnectorRuntime> cr = (ActiveDescriptor<ConnectorRuntime>) Util.getDefaultHabitat().getBestDescriptor(BuilderHelper.createContractFilter(ConnectorRuntime.class.getName()));
                    ConnectorRuntime connectorRuntime = Util.getDefaultHabitat().getServiceHandle(cr).getService();
                    dataSource = (DataSource) connectorRuntime.lookupNonTxResource(getJtaDataSource(), false);
                }
            }

            return dataSource.getConnection();
        } catch (NamingException | SQLException ex)
        {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public synchronized String getJAASContext()
    {
        return "realmGerenciador";
    }

    @Override
    public String getAuthType()
    {
        return "jdbc";
    }

    @Override
    public Enumeration getGroupNames(String username) throws InvalidOperationException, NoSuchUserException
    {
        List<String> groupsList = this.getGroupList(username);
        return Collections.enumeration(groupsList);
    }

    public List<String> getGroupList(String username)
    {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<String> groups = new ArrayList<>();

        try
        {
            conn = getConnection();
            stmt = conn.prepareStatement("SELECT g.nome FROM Pessoa p, Grupo g WHERE p.email like ?1");
            stmt.setString(1, username);
            rs = stmt.executeQuery();

            while (rs.next())
            {
                String group = rs.getString(1);
                groups.add(group);
            }
        } catch (SQLException ex)
        {
            System.out.println("Causa: " + ex.getCause());
            ex.getStackTrace();
        } finally
        {
            close(rs, stmt, conn);
        }

        return groups;
    }

    public boolean authenticateUser(String _username, String _password)
    {
        Encripta encripta = new Encripta();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean result = false;

        try
        {
            conn = getConnection();
            System.out.println("realm gerenciadorCasamento conectou com o banco");                        
            stmt = conn.prepareStatement("select txt_senha, numero_numeroAleatorio from Pessoa where txt_email like ?");
            System.out.println("realm gerenciadorCasamento preparei statement");    
            stmt.setString(1, _username);
            System.out.println("setei o parametro: " + _username +" na query");            
            rs = stmt.executeQuery();
            System.out.println("executei a query");

            if (rs.next())
            {
                System.out.println("entrei no if");
                
                String senhaAtual = rs.getString("txt_senha"); //senha no banco criptografada
                System.out.println("SENHA ATUAL: " + senhaAtual);
                System.out.println("SENHA Digitada pelo usuario: " + _password);

                int numeroAleatorio = rs.getInt("numero_numeroAleatorio");              
                System.out.println("NUMERO ALEATORIO NO BANCO: " + numeroAleatorio);

                String senhaDigitada = encripta.encriptar(_password, numeroAleatorio);
                System.out.println("Senha digitada encriptada: " + senhaDigitada);

                if (senhaDigitada.equals(senhaAtual))
                {
                    System.out.println("a senha digitada eh compativel com a do bd");
                    result = true;
                }
            }
        } catch (SQLException ex)
        {
            ex.getStackTrace();
        } finally
        {
            close(rs, stmt, conn);
        }
        return result;
    }

    private String getJtaDataSource()
    {
        return super.getProperty(JTA_DATA_SOURCE);
    }

    private void close(ResultSet resultSet, Statement statement, Connection connection)
    {
        try
        {
            if (resultSet != null)
            {
                resultSet.close();
            }

            if (statement != null)
            {
                statement.close();
            }

            if (connection != null)
            {
                connection.close();
            }
        } catch (SQLException ex)
        {
            System.out.println("Causa: " + ex.getCause());
            ex.getStackTrace();
        }
    }

}
