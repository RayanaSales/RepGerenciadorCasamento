//package servico;
//
//import entidades.Cerimonia;
//import java.util.List;
//import javax.ejb.Stateless;
//import javax.ejb.TransactionAttribute;
//import javax.ejb.TransactionAttributeType;
//import javax.ejb.TransactionManagement;
//import javax.ejb.TransactionManagementType;
//
//@Stateless
//@TransactionAttribute(TransactionAttributeType.REQUIRED)
//@TransactionManagement(TransactionManagementType.CONTAINER)
//public class CerimoniaServico extends Servico
//{
//    public void salvar(Cerimonia cerimonia)
//    {
//        em.persist(cerimonia);
//    }
//
//    public List<Cerimonia> listar()
//    {
//        return em.createQuery("select t from Cerimonia AS t", Cerimonia.class).getResultList();
//    }
//    
//    public void remover(Cerimonia cerimonia)
//    {
//        Cerimonia c = (Cerimonia) em.find(Cerimonia.class, cerimonia.getId()); //se n tiver isso, o jpa acha que n deatachou        
//        em.remove(c);
//    }
//
//    public void atualizar(Cerimonia cerimonia)
//    {
//        em.merge(cerimonia);
//    }
//
//    public boolean existente(Cerimonia cerimonia)
//    {
//        return listar().contains(cerimonia);
//    }
//}
