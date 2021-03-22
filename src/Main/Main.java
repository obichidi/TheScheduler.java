package Main;


import Lambda.getString;
import Util.ConnectorDb;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.util.Locale;
import java.util.ResourceBundle;

/**
 * This  class is the main class for the scheduler application
 *
 */
public class Main extends Application {

/**This is the constructor for the main class*/
    public Main(){}

    /**
     * This function  sets up the scene for when the program opens to the Login Page
     */

    @Override
    public void start(Stage stage) throws Exception {
//        Locale frenchLocale = new Locale("fr", "FR");
//        Locale.setDefault(frenchLocale);
        ResourceBundle rb = ResourceBundle.getBundle("Language/Nat", Locale.getDefault());


        Parent root = FXMLLoader.load(getClass().getResource("/View/Login.fxml"));
        stage.setTitle("The Scheduler");
        Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.show();
    }
    /**
     * This  is the main function  that executes all the controller database and fxml operations
     * @param args  the arguments of the program
     * @throws  Exception  throws an exception
     */
    public static void main(String[] args) throws Exception {

       ConnectorDb.connectDb();


        launch(args);

        ConnectorDb.disconnectDb();

    }


    }
