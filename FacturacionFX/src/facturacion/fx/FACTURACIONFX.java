/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facturacion.fx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Emilsson Soler
 */
public class FACTURACIONFX extends Application {
    public static Boolean isSplashLoaded = false;
    static Stage stage;
    public static Stage getStage(){
        return stage;
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        FACTURACIONFX.stage=stage;
        Parent root = FXMLLoader.load(getClass()
                .getResource("/facturacion/fx/Menu/main.fxml"));      
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setTitle ("Facturacion");
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
