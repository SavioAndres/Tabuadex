package tabuadex;

import java.io.File;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author Sávio Andres
 */
public class Tabuadex extends Application {
    
    private static Stage stage;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        
        primaryStage.setTitle("Tabuadex");
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("iconTabuada.png")));
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public void janelaSalvarArquivo(String arquivo) {
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Arquivo Tabuadex (*.tbdx)", "*.tbdx"));
        File file = chooser.showSaveDialog(stage);
        /*
        try {
            System.out.println("Foi salvo");
        } catch (Exception e) {
            System.out.println("Não foi salvo");
        }
        */
        Dados.EscreverArquivo("" + file, arquivo);
    }
    
    public File janelaAbrirArquivo() {
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Arquivo Tabuadex (*.tbdx)", "*.tbdx"));
        File file = chooser.showOpenDialog(stage);
        
        return file;
    }

    public void fecharPrograma(){
        stage.close();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
