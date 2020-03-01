/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facturacionlb;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleDoubleProperty;



/**
 *
 * @author Emilsson Soler
 */
public class Producto {
    
    private SimpleStringProperty nombre;
    private SimpleIntegerProperty id;
    private SimpleDoubleProperty precio;
    private SimpleIntegerProperty existencia;

    public Producto() {
        nombre = new SimpleStringProperty();
        id = new SimpleIntegerProperty();
        precio = new SimpleDoubleProperty();
        existencia= new SimpleIntegerProperty();
    }
     
    public SimpleStringProperty nombreProperty(){
    return nombre;
    }
    
    public SimpleIntegerProperty existenciaProperty(){
    return existencia;
    }
    
     public SimpleIntegerProperty idProperty(){
    return id;
    }
     
     public SimpleDoubleProperty precioProperty(){
     return precio;
     }
    

    public Integer getExistencia() {
        return existencia.get();
    }

    public void setExistencia(Integer existencia) {
        this.existencia.setValue(existencia);
    }

    public String getNombre() {
        return nombre.get();
    }

    public void setNombre(String Nombre) {
        this.nombre.set(Nombre);
    }

    public Integer getId() {
        return id.get();
    }

    public void setId(Integer id) {
        this.id.setValue(id);
    }

    public double getPrecio() {
        return precio.get();
    }

    public void setPrecio(double precio) {
        this.precio.set(precio); 
    }
    

   
     
    
}
