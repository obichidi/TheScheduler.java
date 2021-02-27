module SoftwareII.theScheduler {
    requires javafx.fxml;
    requires javafx.controls;
    requires java.sql;
    requires java.desktop;

    opens View;
    opens Main;
    opens Controller;
    opens Database;
    opens Language;
    opens Util;
    opens Model;


}