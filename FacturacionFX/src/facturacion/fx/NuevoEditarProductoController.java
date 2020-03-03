/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facturacion.fx;

import facturacionlb.Producto;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

/**
 * FXML Controller class
 *
 * @author Emilsson Soler
 */
public class NuevoEditarProductoController implements Initializable {
    
    @FXML
    Button bntcancelarproducto;
    
    @FXML
    TextField nuevoidprodcuto;
    
    @FXML
    TextField nuevoprecioproducto;
    
    
    @FXML
    TextField nuevaexistenciaproducto;
    
    @FXML
    TextField nuevonombreporoducto;
    
    
    
    private FormProductoController controller;
    private Producto producto;
    private boolean exist;

    public void setProducto(Producto producto, boolean existe) {
        exist=existe;
        nuevoidprodcuto.textProperty().bindBidirectional(producto.idProperty(),new NumberStringConverter());
        nuevaexistenciaproducto.textProperty().bindBidirectional(producto.existenciaProperty(),new NumberStringConverter());
        nuevonombreporoducto.textProperty().bindBidirectional(producto.nombreProperty());
        nuevoprecioproducto.textProperty().bindBidirectional(producto.precioProperty(),new NumberStringConverter());
        
        this.producto = producto;
        
    }
   
    
    public void setController(FormProductoController controller) {
        this.controller = controller;
    }
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        
      
    }    
    public void aceptar(){
        
Alert alert = new Alert(AlertType.CONFIRMATION);
alert.setTitle("Confirmar");
alert.setHeaderText("Seguro desea procesar: ");
alert.setContentText(nuevonombreporoducto.getText());

Optional<ButtonType> result = alert.showAndWait();
if (result.get() == ButtonType.OK){
   
    if(exist){
        
        controller.eliminarProducto(producto);
    }
     controller.guardar(producto,exist);
    cerrar();
} else {
    
}
        
        
        
        
    }
    
    public void cancelar(){
     cerrar();
    }
    

    private void cerrar() {
        Stage stage = (Stage) bntcancelarproducto.getScene().getWindow();
        stage.close();
    }
}
