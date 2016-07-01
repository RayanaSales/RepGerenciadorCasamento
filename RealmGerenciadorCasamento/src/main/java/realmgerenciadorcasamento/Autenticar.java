package realmgerenciadorcasamento;

import com.sun.appserv.security.AppservPasswordLoginModule;
import java.util.List;
import javax.security.auth.login.LoginException;

public class Autenticar extends AppservPasswordLoginModule
{
    @Override
    protected void authenticateUser() throws LoginException
    {
        RealmGerenciador realm = (RealmGerenciador) _currentRealm;
        if (realm.authenticateUser(_username, _password))
        {
            List<String> groupsList = realm.getGroupList(_username);
            String[] groups = new String[groupsList.size()];
            int i = 0;
            for (String group : groupsList)
            {
                groups[i++] = group;
                System.out.println("NO AUTENTTICA, GRUPO: " + group);
            }            
            commitUserAuthentication(groups);
        } else
        {
            throw new LoginException("Invalid login!");
        }

    }

}
