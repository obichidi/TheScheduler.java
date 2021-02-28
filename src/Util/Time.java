package Util;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

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


        public static Calendar CalenderString(String startDate) throws ParseException {
            SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yy hh:mm:ss");
            java.util.Date date = sdf.parse(startDate);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            return calendar;
        }


        public static String StringDate(Calendar cal) {
            DateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            return sdf.format(cal.getTime());
        }


        public static String timeString(Calendar cal) {
            DateFormat sdf = new SimpleDateFormat("hh:mm a");
            return sdf.format(cal.getTime());
        }

        public static Timestamp generateStartTimestamp(DatePicker datePicker, ComboBox<String> startTime, ComboBox<String> startMinute, ComboBox<String> locationAdd) {
            LocalDate day = datePicker.getValue();
            Integer startHours = Integer.parseInt((startTime.getValue()).substring(0, (startTime.getValue().length() - 5)));

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

            Integer endHours = Integer.parseInt((endTime.getValue()).substring(0, (endTime.getValue().length() - 5)));

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


        //        /**

        public static Calendar convertToLocalTimezone (Calendar cal, String location){
            int offsetFromUtc;
            switch (location) {
                case "London, England":
                    offsetFromUtc = 1;
                    break;
                case "New York, New York":
                    offsetFromUtc = -4;
                    break;
                default:
                    offsetFromUtc = -7;
                    break;
            }

            cal.add(Calendar.HOUR, offsetFromUtc);

            return cal;
        }
//
//
        public static LocalDate localDatFromCalender(Calendar cal) {
            String date = new SimpleDateFormat("dd-MM-yyyy").format(cal.getTime());
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate local = LocalDate.parse(date, dateFormat);
            return local;
        }


        //
        public static String minutesCalenderString(Calendar cal) {
            String min;
            min = Integer.toString(cal.get(Calendar.MINUTE));
            if (min.equals("0")) min = "00";
            return min;
        }

//
        public static String hourCalenderString(Calendar cal) {
            String hour = Integer.toString(cal.get(Calendar.HOUR_OF_DAY));
            return hour;
        }
//
//
        public static String calToComboBoxHour(Calendar cal, String location){

            String hour = hourCalenderString(cal);
            String hourWithMeridium;
            switch (hour) {
                case "9":
                    hourWithMeridium = "9 a.m.";
                    break;
                case "10":
                    hourWithMeridium = "10 a.m.";
                    break;
                case "11":
                    hourWithMeridium = "11 a.m.";
                    break;
                case "12":
                    hourWithMeridium = "12 p.m.";
                    break;
                case "1":
                case "13":
                    hourWithMeridium = "1 p.m.";
                    break;
                case "2":
                case "14":
                    hourWithMeridium = "2 p.m.";
                    break;
                case "3":
                case "15":
                    hourWithMeridium = "3 p.m.";
                    break;
                case "4":
                case "16":
                    hourWithMeridium = "4 p.m.";
                    break;
                default:
                    hourWithMeridium = "5 p.m.";
                    break;
            }
            return (hourWithMeridium);
        }
    }

