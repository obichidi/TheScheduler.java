package Util;

import java.io.*;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Calendar;
import java.util.Date;

import static Model.User.currentUser;

public class Login {
    Date loginTime = Calendar.getInstance().getTime();


    public static void recordLogin(){
        Date loginTime = Calendar.getInstance().getTime();

        File loginFile = new File(" login_activity.txt");
        if(!loginFile.exists()){
            try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(" login_activity.txt"), "utf-8"))) {
                writer.write(currentUser.getUsername() + " successfully signed in at " +
                        loginTime +  "\r\n");
            } catch (IOException ex) {
                System.out.println("IOEception: " + ex);
            }
        }
        else {
            try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(" login_activity.txt", true), "utf-8"))) {
                writer.write(currentUser.getUsername() + "  successfully signed in at " +
                        loginTime + "\r\n");
            } catch (IOException ex) {
                System.out.println("IOEception: " + ex);
            }
        }
    }
}
