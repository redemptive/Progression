/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progression;

/**
 *
 * @author Computer
 */
public class TimeManager {

    public static int gTMinutes = 0;
    public static String timeFormatted;
    public static int gTDays;
    public static int gTHours = 12;
    private static int timeDelay = 0;

    public static void addTime() {
        if (timeDelay == 10) {
            timeDelay = 0;
            if (gTMinutes >= 60) {
                gTHours++;
                gTMinutes = 0;
            } else if (gTHours >= 24) {
                gTHours = 0;
            } else {
                gTMinutes++;
            }
        } else {
            timeDelay++;
        }
    }

    public static String getTime() {
        if (gTMinutes > 10) {
            timeFormatted = "Time: " + gTHours + ":" + gTMinutes;
            return timeFormatted;
        } else {
            timeFormatted = "Time: " + gTHours + ":0" + gTMinutes;
            return timeFormatted;
        }
    }
}
