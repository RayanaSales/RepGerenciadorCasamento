/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes_JUnit;

import entidades.Cerimonia;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import jsf_beans.CerimoniaBean;
import org.junit.Ignore;
import org.junit.Test;
import servico.CerimoniaServico;

public class TesteCerimonia
{

    public TesteCerimonia()
    {
    }

    @Ignore
    public void helloCerimonia()
    {
        CerimoniaBean cb = new CerimoniaBean();
        cb.cerimonia = montarCerimonia();
        
        cb.cerimoniaServico = new CerimoniaServico();
        cb.listar();
        
        cb.salvar();
    }

    private Cerimonia montarCerimonia()
    {
        Cerimonia c = null;
        try
        {
            DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = (Date) formatter.parse("2018/05/28 17:00:00");

            c = new Cerimonia(date);

        } catch (ParseException e)
        {
            e.printStackTrace();
            System.out.println(e.getCause());
        }

        return c;
    }
}
