package testes;

import entidades.Convidado;
import org.junit.Test;

/**
 *
 * @author rayana
 */
public class TesteConvidado extends Teste
{
    @Test
    public void testarBuscarConvidado()
    {
        Convidado n = em.find(Convidado.class, 3);

        if (n != null)
        {
            System.out.println("Convidado dif nuuuuuuuuuuuuuuuuul - heranca funcionando");
        } else
        {
            System.out.println("Convidado nuuuuuuuuuuuuuuuuul");
        }
    }
}
