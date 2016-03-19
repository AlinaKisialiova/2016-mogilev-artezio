package by.artezio.hackathon.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by AlinaKisyalyova on 19.03.2016.
 */
public class DateFormatter {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");
    private static final SimpleDateFormat DATETIME_FORMAT = new SimpleDateFormat("dd.MM.yyyy H:mm");

    public static String formatDate(Date date) {
        return DATE_FORMAT.format(date);
    }

    public static String formatDateTime(Date date) {
        return DATETIME_FORMAT.format(date);
    }
}
