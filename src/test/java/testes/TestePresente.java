package testes;

import entidades.Presente;
import org.junit.Test;


public class TestePresente extends Teste
{
    @Test
    public void testaNomeValido()
    {
        Presente p = new Presente(null, "Faqueiro", "Contém 100 peças", "Lojas Americanas");
        
        // testar se a cerimonia é válida.
        //
    }    
}
