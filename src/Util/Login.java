package Util;

import java.io.*;
import java.util.Calendar;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Calendar;
import static Model.User.currentUser;

public class Login {
    public static void recordLogin(){
        File loginFile = new File(" login_activity.txt");
        if(!loginFile.exists()){
            try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(" login_activity.txt"), "utf-8"))) {
                writer.write(currentUser.getUsername() + " signed in at " +
                        Calendar.getInstance().getTime() +  "\r\n");
            } catch (IOException ex) {
                System.out.println("IOEception: " + ex);
            }
        }
        else {
            try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(" login_activity.txt", true), "utf-8"))) {
                writer.write(currentUser.getUsername() + " signed in at " +
                        Calendar.getInstance().getTime() + "\r\n");
            } catch (IOException ex) {
                System.out.println("IOEception: " + ex);
            }
        }
    }
}
