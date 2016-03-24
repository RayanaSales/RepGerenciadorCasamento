package testes;

import entidades.Buffet;
import entidades.Cerimonia;
import entidades.Convidado;
import entidades.ProdutorDeMidia;
import entidades.Localizacao;
import entidades.Presente;
import entidades.Noivo;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main
{
//abre conexao com o banco
//cria as entidades
//operacoes no banco

    /*
        1- Cria local, buffet, fotografo 
        2- Cria lista de presentes, lista de usuarios 
        3- Cria cerimonia - seta tudo na cerimonia 
        4- salva cerimonia
     */
//fecha conexao
    public static void main(String[] args)
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("casamento");
        EntityManager em = emf.createEntityManager();
        
        Localizacao l = Montar.montarLocal();
        Buffet b = Montar.montarBuffet();

        Cerimonia c = new Cerimonia();

        List<Presente> presentes = Montar.montarListaPresentes(c);
        List<Noivo> casal = Montar.montarCasal(c);
        List<Convidado> convidados = Montar.convidarPessoas(c);
        List<ProdutorDeMidia> produtoresDeMidia = Montar.montarProdutorMidia(c);

        c = Montar.montarCerimonia(c, l, b, produtoresDeMidia, presentes, casal, convidados);

        try
        {
            em.getTransaction().begin();
            em.persist(c);
            em.getTransaction().commit();
        } catch (Exception e)
        {
            em.getTransaction().rollback();
            e.printStackTrace();
        }

        em.close();
    }
}
