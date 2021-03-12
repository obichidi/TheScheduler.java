package Main;


import Controller.MainAppointmentController;
import Controller.ModifyAppointmentController;
import Database.AppointmentDatabase;
import Database.CustomerDatabase;
import Util.ConnectorDb;
import Util.TimestampToLocal;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.util.ResourceBundle;


public class Main extends Application {


    @Override
    public void start(Stage stage) throws Exception {
//      Locale frenchLocale = new Locale("fr", "FR");
//     Locale.setDefault(frenchLocale);
//       System.out.println(AppointmentDatabase.StartTimeList());

//   TimestampToLocal timestampToLocal = new TimestampToLocal();
//        timestampToLocal.setTime(13,27,6);
//        System.out.println(timestampToLocal.toString());

      AppointmentDatabase.getAppointmentsIn15Mins();


        ResourceBundle rb;
        ResourceBundle bundle = ResourceBundle.getBundle("Language/NAT");
        Parent root = FXMLLoader.load(getClass().getResource("/View/Login.fxml"));
        stage.setTitle("The Scheduler");
        Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.show();


    }


    public static void main(String[] args) throws Exception {

       ConnectorDb.connectDb();



        launch(args);

        ConnectorDb.disconnectDb();

    }


    }
