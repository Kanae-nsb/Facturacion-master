/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facturacion.bl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author darkg
 */
public class FacturaServicio {
    
    public void guardar(Factura Factura){

        //Abriendo sesion y contectando con la base de datos.
         Session session = HibernateUtil.getSessionFactory().openSession();
        //Iniciando la transaccion.
         Transaction tx = session.beginTransaction();

         try {
            //Guardando y actualizando los productos.
             session.saveOrUpdate(Factura);
             
             for(FacturaDetalle detalle: Factura.getFacturaDetalle()){
                 Integer id = detalle.getProducto().getCodigo();
                 
                 Criteria query= session.createCriteria(Producto.class);
                 query.add(Restrictions.eq("id", id));
                 query.setMaxResults(1);
                 
                 Producto productoExistente=(Producto) query.uniqueResult();
                        
                 Integer nuevaExistencia= productoExistente.getExistencia()-
                         detalle.getCantidad();
                 
                 productoExistente.setExistencia(nuevaExistencia);
                 
                 session.saveOrUpdate(productoExistente);
             }
             
             tx.commit();

            } catch (Exception e) {
                tx.rollback();
                System.out.println(e.getMessage());
            } finally {
             //Cerrando sesion y desconectando con la base de datos.
             session.close();
            }
       
    }
}