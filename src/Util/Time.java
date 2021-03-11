package Util;

import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

/**
     *
     * @author mjenk
     */
    public class Time {

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

    public String toString(){

        return String.format("%d:%02d:%02d %s", ((hour==0||hour==12)?12:hour%12), minute, second, (hour<12? "AM": "PM"));

    }



        public static Timestamp generateStartTimestamp(DatePicker datePicker, ComboBox<String> startTime, ComboBox<String> startMinute, ComboBox<String> locationAdd) {
            LocalDate day = datePicker.getValue();

            Integer startHours = Integer.parseInt((startTime.getValue()));
//            Integer startHours = Integer.parseInt((startTime.getValue()).substring(0, (startTime.getValue().length() - 5)));

            if (startHours < 8) startHours += 12;

            Integer startMinutes = Integer.parseInt(startMinute.getValue());

            int utcOffset;
            switch (locationAdd.getValue()) {
                case "London, England":
                    utcOffset = -1;
                    break;
                case "New York, New York":
                    utcOffset = 5;
                    break;
                case "Paris, France":
                    utcOffset = 1;
                    break;
                default:
                    utcOffset = 7;
                    break;
            }
            LocalDateTime startLocal = LocalDateTime.of(day.getYear(), day.getMonthValue(),
                    day.getDayOfMonth(), (startHours + utcOffset), startMinutes);
            Timestamp startTimestamp = Timestamp.valueOf(startLocal);


            return startTimestamp;
        }



        public static Timestamp generateEndTimestamp(DatePicker datePicker, ComboBox<String> endTime, ComboBox<String> endMinute, ComboBox<String> locationAdd) {
            LocalDate day = datePicker.getValue();

            Integer endHours = Integer.parseInt((endTime.getValue()));



            if (endHours < 8) endHours += 12;
            Integer endMinutes = Integer.parseInt(endMinute.getValue());

            int utcOffset;
            switch (locationAdd.getValue()) {
                case "London, England":
                    utcOffset = -1;
                    break;
                case "New York, New York":
                    utcOffset = 5;
                    break;
                case "Paris, France":
                    utcOffset = 1;
                    break;
                default:
                    utcOffset = 7;
                    break;
            }


            LocalDateTime endLocal = LocalDateTime.of(day.getYear(), day.getMonthValue(),
                    day.getDayOfMonth(), (endHours + utcOffset), endMinutes);
            Timestamp endTimestamp = Timestamp.valueOf(endLocal);


            return endTimestamp;
        }



    public static Timestamp generateStartTimestampModify(DatePicker datePickerModify, ComboBox<String> startTimeModify, ComboBox<String> startMinuteModify, ComboBox<String> locationModify) {
        LocalDate day = datePickerModify.getValue();
        Integer startHours = Integer.parseInt((startTimeModify.getValue()));

        if (startHours < 8) startHours += 12;

        Integer startMinutes = Integer.parseInt(startMinuteModify.getValue());

        int utcOffset;
        switch (locationModify.getValue()) {
            case "London, England":
                utcOffset = -1;
                break;
            case "New York, New York":
                utcOffset = 5;
                break;
            case "Paris, France":
                utcOffset = 1;
                break;
            default:
                utcOffset = 7;
                break;
        }
        LocalDateTime startLocal = LocalDateTime.of(day.getYear(), day.getMonthValue(),
                day.getDayOfMonth(), (startHours + utcOffset), startMinutes);
        Timestamp startTimestamp = Timestamp.valueOf(startLocal);


        return startTimestamp;
    }


    public static Timestamp generateEndTimestampModify(DatePicker datePickerModify, ComboBox<String> endTimeModify, ComboBox<String> endMinuteModify, ComboBox<String> locationModify) {
        LocalDate day = datePickerModify.getValue();

        Integer endHours = Integer.parseInt((endTimeModify.getValue()));

        if (endHours < 8) endHours += 12;
        Integer endMinutes = Integer.parseInt(endMinuteModify.getValue());

        int utcOffset;
        switch (locationModify.getValue()) {
            case "London, England":
                utcOffset = -1;
                break;
            case "New York, New York":
                utcOffset = 5;
                break;
            case "Paris, France":
                utcOffset = 1;
                break;
            default:
                utcOffset = 7;
                break;
        }
        LocalDateTime endLocal = LocalDateTime.of(day.getYear(), day.getMonthValue(),
                day.getDayOfMonth(), (endHours + utcOffset), endMinutes);
        Timestamp endTimestamp = Timestamp.valueOf(endLocal);


        return endTimestamp;

    }




        public static String hourCalenderString(Calendar cal) {
            String hour = Integer.toString(cal.get(Calendar.HOUR_OF_DAY));
            return hour;
        }

    }

