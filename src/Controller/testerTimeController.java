package Controller;

import Database.AppointmentDatabase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

import java.net.URL;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class testerTimeController implements Initializable {

    @FXML private Label timeTesterText;
    @FXML private Label dateTesterText;
    @FXML private Label timeConversionText;
    @FXML private ComboBox<String> timeTesterBox;
    @FXML private ComboBox<String> hourBox;
    @FXML private ComboBox<String> minuteBox;
    @FXML private ComboBox<String> secondBox;
    @FXML private ComboBox<String> amPm;
    @FXML private DatePicker datePicker;

    private int hour;
    private int minute;
    private int second;



    public void setTime(int h, int m, int s){

        hour = ((h>0 && h<24) ? h : 0);
        minute = ((m>=0 && m<60) ? m : 0);
        second  = ((s>=0 && s<60) ? s : 0);

    }

    public  String toMilitary(){


        return String.format("%02d:%02d:%02d,hour,minute,second ");
    }

    public String toStringBack(){

        return String.format("%d:%02d:%02d %s", ((hour==0||hour==12)?12:hour%12), minute, second, (hour<12? "AM": "PM"));

    }

    @Override
    public void initialize(URL url, ResourceBundle rb){
     timeTesterBox.setItems(AppointmentDatabase.AllStartTimeList());
//        timeTesterBox.setPromptText(String.valueOf(AppointmentDatabase.StartTimeList()));
        hourBox.setItems(AppointmentDatabase.AllStartTimeList());

//        hourBox.setPromptText(toStringBack());
        ObservableList<String> months = FXCollections.observableArrayList();
        ObservableList<String> hours = FXCollections.observableArrayList();
        ObservableList<String>  minutes = FXCollections.observableArrayList();

        ObservableList<String> AmPms = FXCollections.observableArrayList();
        AmPms.addAll("AM","PM");
        amPm.setItems(AmPms);

        hours.addAll("9", "10", "11", "12", "1", "2", "3", "4", "5");
        hourBox.setItems(minutes);

        months.addAll("January", "February", "March", "April", "May" ,"June" ,"July" ,"August" ,"September" ,"October" ,"November" ,"December");
    }

    @FXML
    void showHour(ActionEvent event) {
        String dateTimeStamp = hourBox.getValue();
        String year =   (dateTimeStamp.substring(0,4)) ;
        String month = (dateTimeStamp.substring(5,7)) ;
        String day = (dateTimeStamp.substring(8,10)) ;

        String hour = (dateTimeStamp.substring(11,13)) ;
        String minute = (dateTimeStamp.substring(14,16));
        String second = (dateTimeStamp.substring(17,19));

        int yearTry = Integer.parseInt(year);
        int monthTry = Integer.parseInt(month);
        int dayTry = Integer.parseInt(day);
        int hourTry = Integer.parseInt(hour);
        int minuteTry = Integer.parseInt(minute);
        int secondTry = Integer.parseInt(second);

        String Timestamp = year +"-"+ month +"-"+day +" "+ hour+ ":"+minute +":"+ second +".000";

        timeTesterText.setText(second);


        setTime(hourTry,minuteTry,secondTry);
        timeConversionText.setText(toStringBack());

    }





    @FXML
    void convertDate(ActionEvent event) {
//          System.out.println( datePicker.getValue().atTime());

//        SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
//        try{
//            Date date = DateFor.parse("08/07/2019");
//            System.out.println("Date : "+date);
//        }catch (ParseException e) {e.printStackTrace();}
//    }
//}

    }

    @FXML
    void showMinute(ActionEvent event) {


    }

    @FXML
    void  AmPmConvert(ActionEvent event) {


    }

    @FXML
    void showSecond(ActionEvent event) {


    }

    @FXML
    void timeString(ActionEvent event) {



        String dateTimeStamp = timeTesterBox.getValue();

//      LocalDateTime today = LocalDateTime.parse(Vampa);
        timeTesterText.setText(dateTimeStamp);


       String year =   (timeTesterText.getText().substring(0,4)) ;
       String month = (timeTesterText.getText().substring(5,7)) ;
       String day = (timeTesterText.getText().substring(8,10)) ;

       String hour = (timeTesterText.getText().substring(11,13)) ;
       String minute = (timeTesterText.getText().substring(14,16));
       String second = (timeTesterText.getText().substring(17,19));


//        setTime(hour,minute,second);


        String Timestamp = year +"-"+ month +"-"+day +" "+ hour+ ":"+minute +":"+ second +".000";

//        LocalDateTime now = LocalDateTime.now();
//        ZoneId zoneId = ZoneId.systemDefault();
//        ZonedDateTime zoneNow = now.atZone(zoneId);
//        DateTimeFormatter timeFormatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT);
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
//        LocalDateTime dateTime = LocalDateTime.parse(Timestamp, formatter);
//        LocalDateTime localDateTime = LocalDateTime.parse(dateTime.toString());
//        ZonedDateTime startZonedTime = localDateTime.atZone(ZoneId.of("UTC"));
//        ZonedDateTime localStart = startZonedTime.withZoneSameInstant(zoneId);

        int yearTry = Integer.parseInt(year);
        int monthTry = Integer.parseInt(month);
        int dayTry = Integer.parseInt(day);
        int hourTry = Integer.parseInt(hour);
        int minuteTry = Integer.parseInt(minute);
        int secondTry = Integer.parseInt(second);

                   setTime(hourTry,minuteTry,secondTry);

        System.out.println( "something:"+datePicker.getValue().atTime(hourTry ,minuteTry,secondTry ));
      timeConversionText.setText(toStringBack());
        hourBox.setPromptText(timeConversionText.getText());

//                LocalDateTime startTime = conver.toLocalDateTime();
//        timeConversionText.setText(conver);
//        LocalDateTime localDateTime = LocalDateTime.parse(startTime.toString())
//
////        LocalDateTime startTime = startTimeStamp.toLocalDateTime();
////        LocalDateTime localDateTime = LocalDateTime.parse(startTime.toString());
////        ZonedDateTime startZonedTime = localDateTime.atZone(ZoneId.of("UTC"));
////        ZonedDateTime localStart = startZonedTime.withZoneSameInstant(zoneId);
//
//        LocalDateTime now = LocalDateTime.now();
//        ZoneId zoneId = ZoneId.systemDefault();
//        ZonedDateTime zoneNow = now.atZone(zoneId);
//        LocalDateTime localNow = zoneNow.withZoneSameInstant(ZoneId.of("UTC")).toLocalDateTime();
//        LocalDateTime localInFifteen = localNow.plusMinutes(15);
//        DateTimeFormatter timeFormatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT);



    }

}
