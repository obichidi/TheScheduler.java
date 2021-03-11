package Main;


import Controller.MainAppointmentController;
import Controller.ModifyAppointmentController;
import Database.AppointmentDatabase;
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


//        System.out.println(timestampToLocal.toMilitary());

        ResourceBundle rb;
        ResourceBundle bundle = ResourceBundle.getBundle("Language/NAT");
        Parent root = FXMLLoader.load(getClass().getResource("/View/Login.fxml"));
        stage.setTitle("The Scheduler");
        Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.show();


    }


    public static void main(String[] args) {


        launch(args);
//        ZoneId.getAvailableZoneIds().stream().forEach(System.out::println);
//        ZoneId.getAvailableZoneIds().stream().filter(c -> c.contains("Asia")).forEach(System.out::println);

//        LocalDate parisDate = LocalDate.of(2019, 10, 26);
//        LocalTime parisTime = LocalTime.of(01,00);
//        ZoneId   parisZoneId = ZoneId.of("Europe/Paris");
//        ZonedDateTime  ParisZDT = ZonedDateTime.of(parisDate, parisTime, parisZoneId);
//        ZoneId localZoneId =  ZoneId.of(TimeZone.getDefault().getID());
//        Instant parisToGmt =  ParisZDT.toInstant();
//        ZonedDateTime parisLocalZdt = ParisZDT.withZoneSameLocal(localZoneId);
//        LocalDateTime  gmttoLocacZDT


    }


    }
