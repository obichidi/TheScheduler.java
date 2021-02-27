package Lambda;
import javafx.collections.ObservableList;
import Model.Appointment;


public interface Reports {

    ObservableList<Appointment> filter(ObservableList<Appointment> all, String s);
}
