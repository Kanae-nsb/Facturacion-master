/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facturacion.fx;




import facturacionlb.Producto;
import facturacionlb.ProductoServicio;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.security.auth.callback.TextOutputCallback;
/**
 * FXML Controller class
 *
 * @author Emilsson Soler
 */
public class FormProductoController implements Initializable {
    @FXML
    private TableView tableviewproductos;
    
    @FXML
    private TableColumn<Producto,Integer> colidproducto;
    
    @FXML
    private TableColumn<Producto,String> colnombreproducto;
    
    @FXML
    private TableColumn<Producto,Double> colprecioproducto;
    
    @FXML
    private TableColumn<Producto,Integer> colexistenciaproducto;
    
    @FXML 
    private TextField buscadorTextfield; 
    
    @FXML 
    private TableColumn colEditar;
    
    @FXML 
    private TableColumn colEliminar;
    public ObservableList<Producto> data;
   ProductoServicio servicio;

    /**
     * Initializes the controller class. 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        servicio = new ProductoServicio();
        
        colidproducto.setCellValueFactory(new PropertyValueFactory<>("id"));
        colnombreproducto.setCellValueFactory(new PropertyValueFactory("nombre") );
        colprecioproducto.setCellValueFactory(new PropertyValueFactory<>("precio"));
        colexistenciaproducto.setCellValueFactory(new PropertyValueFactory<>("existencia"));
        definirColumnaEditar();
        definirColumnaEliminar();
        cargardatos();
        
    }    
    
    public void  nuevoProducto() throws IOException{
        Producto producto=new Producto();
        abrirventanamodal(producto, "Nuevo Producto",false);
  
    }
    
    
    public void buscarProducto(){
        tableviewproductos.setItems(buscadordeProducto(buscadorTextfield.getText()));
        
    }
    
    private ObservableList<Producto> buscadordeProducto(String temp){
        if(temp.isEmpty()||temp.equals("")){
                return data;
            }else{
            ObservableList<Producto> databuscada = FXCollections.observableArrayList();
            for (Producto data1 : data) {
                if(data1.getNombre().toLowerCase().contains(temp.toLowerCase())){
                    databuscada.add(data1);
                }
            }
            return databuscada;
            
            }
            
        }
    
      
        
    private void abrirventanamodal(Producto producto,String titulo, boolean exist) throws IOException {
        FXMLLoader loader  = new FXMLLoader(getClass().getResource("NuevoEditarProducto.fxml"));
        Parent root =(Parent) loader.load();
        NuevoEditarProductoController controller = loader.getController();
        controller.setController(this);
        controller.setProducto(producto, exist);
        Stage stage = new Stage();
        Scene scene  = new Scene(root);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
         
        stage.setTitle(titulo);
        stage.show();
        
    }
    public void guardar(Producto producto, boolean exist){
        servicio.guardar(producto, exist);
        cargardatos();
    }
    
    public void eliminarProducto(Producto eliminar){
           for(int i=0; i<data.size();i++){
          if(data.get(i).getId().equals(eliminar.getId())){
             data.remove(i);
             servicio.eliminarPServicio(i);
              break;      
          }
        }
    }
     
    private void cargardatos() {
        data= FXCollections.observableArrayList(servicio.getListaDeProducto());
        tableviewproductos.setItems(data);
        
    }
        
           
    private void definirColumnaEditar() {
        colEditar.setCellFactory(param -> new TableCell<String, String>(){
        final Button btn= new Button("Editar");
        
        @Override
        public void updateItem(String item, boolean empty){
            super.updateItem(item, empty);
            if(empty){
                setGraphic(null);
                setText(null);
            }
            else{
                btn.setOnAction(event->{
                    Producto producto= (Producto) getTableRow().getItem();
                    try {
                        Producto ptemp= new Producto();
                        ptemp.setId(producto.getId());
                        ptemp.setExistencia(producto.getExistencia());
                        ptemp.setNombre(producto.getNombre());
                        ptemp.setPrecio(producto.getPrecio());
                        abrirventanamodal(ptemp, "Editar Producto",true);
                        
                    } catch (IOException ex) {
                        Logger.getLogger(FormProductoController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                });
                setGraphic(btn);
                setText(null);
            }
        }
        
        
         });
    }
        
    
    private void definirColumnaEliminar() {
        colEliminar.setCellFactory(param -> new TableCell<String, String>(){
        final Button btn= new Button("Eliminar");
        
        @Override
        public void updateItem(String item, boolean empty){
            super.updateItem(item, empty);
            if(empty){
                setGraphic(null);
                setText(null);
            }
            else{
                btn.setOnAction(event->{
                    Producto producto= (Producto) getTableRow().getItem();
                    eliminarProducto(producto);
                    tableviewproductos.refresh();
                    
                });
                setGraphic(btn);
                setText(null);
            }
        }
        
        
         });    }

    
     
}
