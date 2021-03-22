package Util;

import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;


import java.sql.Timestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;



/**
 *
 * @author obi Chidi
 */

/**
 * This class is for the Time  manipulations functions
 *
 */
public class Time {


    /**This is the constructor for the time class*/
public Time(){}
    /**
     * This class is for the Time  manipulations functions
     * @param startTime the selected start time
     * @param startMinute  the selected  start minute
     * @param locationAdd   the selected location
     * @param datePicker the selected date
     * @param startAmPm  the am pm picker
     * @return  startTimestamp
     */
    public static Timestamp generateStartTimestamp(DatePicker datePicker, ComboBox<String> startTime, ComboBox<String> startMinute, ComboBox<String> locationAdd,ComboBox <String>startAmPm) {
        LocalDate day = datePicker.getValue();



        int startHours = Integer.parseInt((startTime.getValue()));
//
      int startMinutes = Integer.parseInt(startMinute.getValue());

      if(locationAdd.getValue().equals("New York, New York" ) ) {
    if (startHours == 12 && startAmPm.getValue().equals("Pm")) startHours = 16;
    if (startHours == 1 && startAmPm.getValue().equals("Pm") ) startHours = 17;
    if (startHours == 2 && startAmPm.getValue().equals("Pm") )startHours = 18;
    if (startHours == 3 && startAmPm.getValue().equals("Pm")) startHours = 19;
    if (startHours == 4 && startAmPm.getValue().equals("Pm")) startHours = 20;
    if (startHours == 5 && startAmPm.getValue().equals("Pm")) startHours = 21;
    if (startHours == 6 && startAmPm.getValue().equals("Pm")) startHours = 22;
    if (startHours == 7 && startAmPm.getValue().equals("Pm")) startHours = 23;
    if (startHours == 8 && startAmPm.getValue().equals("Pm")) startHours = 0;
    if (startHours == 9 && startAmPm.getValue().equals("Pm")) startHours = 1;

    if(startHours == 11 && startAmPm .getValue().equals("Am")) startHours  = 15;
    if(startHours == 10 && startAmPm .getValue().equals("Am")) startHours  = 14;
    if(startHours == 9 && startAmPm .getValue().equals("Am")) startHours  = 13;
    if(startHours == 8 && startAmPm .getValue().equals("Am"))  startHours  = 12;



}

if(locationAdd.getValue().equals("Paris, France")){

    if (startHours == 12 && startAmPm.getValue().equals("Pm")) startHours = 11;
    if (startHours == 1 && startAmPm.getValue().equals("Pm")) startHours = 12;
    if (startHours == 2 && startAmPm.getValue().equals("Pm")) startHours = 13;
    if (startHours == 3 && startAmPm.getValue().equals("Pm")) startHours = 14;
    if (startHours == 4 && startAmPm.getValue().equals("Pm")) startHours = 15;
    if (startHours == 5 && startAmPm.getValue().equals("Pm")) startHours = 16;
    if (startHours == 6 && startAmPm.getValue().equals("Pm")) startHours = 17;
    if (startHours == 7 && startAmPm.getValue().equals("Pm")) startHours = 18;
    if (startHours == 8 && startAmPm.getValue().equals("Pm")) startHours = 19;
    if (startHours == 9 && startAmPm.getValue().equals("Pm")) startHours = 20;

    if(startHours == 11 && startAmPm.getValue().equals("Am"))  startHours  = 15;
    if(startHours == 10 && startAmPm.getValue().equals("Am"))  startHours  = 9;
    if(startHours == 9 && startAmPm.getValue().equals("Am"))  startHours  =  8;
    if(startHours == 8 && startAmPm.getValue().equals("Am"))  startHours  = 7;

}

if(locationAdd.getValue().equals("London, England") ){
    if (startHours == 12 && startAmPm.getValue().equals("Pm")) startHours = 12;
    if (startHours == 1 && startAmPm.getValue().equals("Pm")) startHours = 13;
    if (startHours == 2 && startAmPm.getValue().equals("Pm")) startHours = 14;
    if (startHours == 3 && startAmPm.getValue().equals("Pm")) startHours = 15;
    if (startHours == 4 && startAmPm.getValue().equals("Pm")) startHours = 16;
    if (startHours == 5 && startAmPm.getValue().equals("Pm")) startHours = 17;
    if (startHours == 6 && startAmPm.getValue().equals("Pm")) startHours = 18;
    if (startHours == 7 && startAmPm.getValue().equals("Pm")) startHours = 19;
    if (startHours == 8 && startAmPm.getValue().equals("Pm")) startHours = 20;
    if (startHours == 9 && startAmPm.getValue().equals("Pm")) startHours = 21;

    if(startHours == 11 && startAmPm.getValue().equals("Am"))  startHours  = 11;
    if(startHours == 10 && startAmPm.getValue().equals("Am"))  startHours  = 10;
    if(startHours == 9 && startAmPm.getValue().equals("Am"))  startHours  =  9;
    if(startHours == 8 && startAmPm.getValue().equals("Am"))  startHours  = 8;

}



LocalDateTime startLocal = LocalDateTime.of(day.getYear(), day.getMonthValue(),
                day.getDayOfMonth(), (startHours ), startMinutes);
        Timestamp startTimestamp = Timestamp.valueOf(startLocal);


        return startTimestamp;
    }

   /**
     * Thi function  creates a timestamp for the End  date and time input data
     * @param datePicker  the datePicker data
     * @param locationAdd  the comboBox selection value of the location of the appointment
     * @param endMinute  the appointment start minutes
     * @param endTime the appointment start times
    * @param endAmPm   the am pm picker
     * @return  endTimestamp
     */

    public static Timestamp generateEndTimestamp(DatePicker datePicker, ComboBox<String> endTime, ComboBox<String> endMinute, ComboBox<String> locationAdd, ComboBox<String>endAmPm) {
        LocalDate day = datePicker.getValue();

        Integer endHours = Integer.parseInt((endTime.getValue()));


        Integer endMinutes = Integer.parseInt(endMinute.getValue());



        if(locationAdd.getValue().equals("New York, New York")   ) {

            if (endHours == 12 && endAmPm.getValue().equals("Pm")) endHours = 16;
            if (endHours == 1 && endAmPm.getValue().equals("Pm")) endHours = 17;
            if (endHours == 2 && endAmPm.getValue().equals("Pm")) endHours = 18;
            if (endHours == 3 && endAmPm.getValue().equals("Pm")) endHours = 19;
            if (endHours == 4 && endAmPm.getValue().equals("Pm")) endHours = 20;
            if (endHours == 5 && endAmPm.getValue().equals("Pm")) endHours = 21;
            if (endHours == 6 && endAmPm.getValue().equals("Pm")) endHours = 22;
            if (endHours == 7 && endAmPm.getValue().equals("Pm")) endHours = 23;
            if (endHours == 8 && endAmPm.getValue().equals("Pm")) endHours = 0;
            if (endHours == 9 && endAmPm.getValue().equals("Pm")) endHours = 1;

            if(endHours == 11 && endAmPm.getValue().equals("Am"))  endHours  = 15;
            if(endHours == 10 && endAmPm.getValue().equals("Am"))  endHours  = 14;
            if(endHours == 9 && endAmPm.getValue().equals("Am"))  endHours  = 13;
            if(endHours == 8 && endAmPm.getValue().equals("Am"))  endHours  = 12;


        }

        if(locationAdd.getValue().equals("Paris, France")){

            if (endHours == 12 && endAmPm.getValue().equals("Pm")) endHours = 11;
            if (endHours == 1 && endAmPm.getValue().equals("Pm")) endHours = 12;
            if (endHours == 2 && endAmPm.getValue().equals("Pm")) endHours = 13;
            if (endHours == 3 && endAmPm.getValue().equals("Pm")) endHours = 14;
            if (endHours == 4 && endAmPm.getValue().equals("Pm")) endHours = 15;
            if (endHours == 5 && endAmPm.getValue().equals("Pm")) endHours = 16;
            if (endHours == 6 && endAmPm.getValue().equals("Pm")) endHours = 17;
            if (endHours == 7 && endAmPm.getValue().equals("Pm")) endHours = 18;
            if (endHours == 8 && endAmPm.getValue().equals("Pm")) endHours = 19;
            if (endHours == 9 && endAmPm.getValue().equals("Pm"))endHours = 20;

            if(endHours == 11 && endAmPm.getValue().equals("Am"))  endHours  = 15;
            if(endHours == 10 && endAmPm.getValue().equals("Am"))  endHours  = 9;
            if(endHours == 9 && endAmPm.getValue().equals("Am")) endHours  =  8;
            if(endHours == 8 && endAmPm.getValue().equals("Am"))  endHours  = 7;

        }

        if(locationAdd.getValue().equals("London, England")){
            if (endHours == 12 && endAmPm.getValue().equals("Pm")) endHours = 12;
            if (endHours == 1 && endAmPm.getValue().equals("Pm")) endHours = 13;
            if (endHours == 2 && endAmPm.getValue().equals("Pm")) endHours = 14;
            if (endHours == 3 && endAmPm.getValue().equals("Pm")) endHours = 15;
            if (endHours == 4 && endAmPm.getValue().equals("Pm")) endHours = 16;
            if (endHours == 5 && endAmPm.getValue().equals("Pm")) endHours = 17;
            if (endHours == 6 && endAmPm.getValue().equals("Pm")) endHours = 18;
            if (endHours == 7 && endAmPm.getValue().equals("Pm")) endHours = 19;
            if (endHours == 8 && endAmPm.getValue().equals("Pm")) endHours = 20;
            if (endHours == 9 && endAmPm.getValue().equals("Pm")) endHours = 21;


            if(endHours == 11 && endAmPm.getValue().equals("Am"))  endHours  = 11;
            if(endHours == 10 && endAmPm.getValue().equals("Am"))  endHours  = 10;
            if(endHours == 9 && endAmPm.getValue().equals("Am")) endHours  =  9;
            if(endHours == 8 && endAmPm.getValue().equals("Am"))  endHours  = 8;

        }




            LocalDateTime endLocal = LocalDateTime.of(day.getYear(), day.getMonthValue(),
                    day.getDayOfMonth(), (endHours ), endMinutes);
            Timestamp endTimestamp = Timestamp.valueOf(endLocal);


            return endTimestamp;
        }




}