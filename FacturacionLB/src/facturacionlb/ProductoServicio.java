/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facturacionlb;

import java.util.ArrayList;
import javafx.scene.control.Alert;

/**
 *
 * @author Emilsson Soler
 */
public class ProductoServicio {
    private final ArrayList<Producto> listaDeProducto;
    private int id=1;
    public ProductoServicio() {
        listaDeProducto = new ArrayList<>();
        crearDatosDePrueba();
    }

    public ArrayList<Producto> getListaDeProducto() {
        return listaDeProducto;
    }
    
    
    public void guardar(Producto producto){
        
        asignarId(producto);
       
        try {
          if(!producto.getNombre().trim().isEmpty()&&producto.getPrecio()!=0&&producto.getExistencia()!=0){
     listaDeProducto.add(producto);
     }else{
         Alert novalido= new Alert(Alert.AlertType.ERROR);
         novalido.setContentText(null);
         novalido.setTitle("Error");
         novalido.setHeaderText("No ingreso los datos correctamente o dejo algun espacio vacio");
         novalido.showAndWait();
     }  
        } catch (Exception e) {
           Alert novalido= new Alert(Alert.AlertType.ERROR);
         novalido.setContentText(null);
         novalido.setTitle("Error");
         novalido.setHeaderText("No ingreso los datos correctamente o dejo algun espacio vacio");
         novalido.showAndWait(); 
        }
     
     
    }
    
    public void asignarId(Producto producto){     
        if(producto.getId()==0){
            producto.setId(id);
            id++;
        }
        
    }
    
    public void eliminarPServicio(int eliminar){
        listaDeProducto.remove(eliminar);
    }
    
    public void modificarPServicio(int posicion, Producto editado){
        listaDeProducto.remove(posicion);
        listaDeProducto.set(posicion, editado);
        
    }
    
    
    private void crearDatosDePrueba() {
        Producto producto1 = new Producto();
        producto1.setId(0);
        producto1.setNombre("queso");
        producto1.setPrecio(12.5);
        producto1.setExistencia(4);
        
        Producto producto2 = new Producto();
        producto2.setId(0);
        producto2.setNombre("mantequilla");
        producto2.setPrecio(14.4);
        producto2.setExistencia(45);
        guardar(producto1);
        guardar(producto2);
    }

    
    
    
    
}
