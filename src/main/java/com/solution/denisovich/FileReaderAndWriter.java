package com.solution.denisovich;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * class contains some method
 */
public class FileReaderAndWriter {

    /**
     * method create file
     */
    public void createFile(String path) {
        File file = new File(path);
        checkIfFileAvailable(file);

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write("I loved 1999, England is beautiful, especially February, 12th");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * method create file and writes data
     */
    public void createFileNIO(String path) {
        Path file = Paths.get(path);
        try (BufferedWriter writer = Files.newBufferedWriter(file)) {
            writer.append("I loved 1999, England is beautiful, especially February, 12th")
                    .write("\r\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method extract date from text
     *
     * @param path
     * @return
     */
    public String extractDateFromText(String path) {
        String result = "";
        try (BufferedReader br = Files.newBufferedReader(Paths.get(path), StandardCharsets.UTF_8)) {
            for (String line = null; (line = br.readLine()) != null; ) {
                String[] words = line.split(", || ||\"||\\.");

                for (String word : words) {
                    Pattern year = Pattern.compile("[a-z][a-z0-9+.-]+:\\/\\/");
                    Matcher matcherYear = year.matcher(word);
                    if (matcherYear.matches()) {
                        result = result + word;
                    }
                }
                for (String word : words) {
                    if (word.equals("")) {
                        result = result + word;
                    }
                }
                for (String word : words) {
                    Pattern day = Pattern.compile("");
                    Matcher matcherDay = day.matcher(word);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Method convert date to string with using complete format
     * @param date
     * @param language
     * @return
     */
    public String formatFull(Date date, String language) {
        ZoneId zoneId = TimeZone.getTimeZone("Europe/Kiev").toZoneId();
        SimpleDateFormat dateFormat = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss z", new Locale(language));
        return dateFormat.format(date);
    }

    /**
     * Method convert date to string with using complete format
     * @param date
     * @param language
     * @return
     */
    public String formatFullJava8(ZonedDateTime date, String language) {
        ZoneId zoneId = TimeZone.getTimeZone("Europe/Kiev").toZoneId();
        DateTimeFormatter localizedTimeFormatter = DateTimeFormatter
                .ofLocalizedDateTime(FormatStyle.FULL);
        return localizedTimeFormatter.format(ZonedDateTime.of(LocalDateTime.from(date), zoneId));
    }


    /**
     * Method check if created file is available
     */
    private static void checkIfFileAvailable(File file) {
        if (!file.exists()) {
            file.getParentFile().mkdirs();

            try {
                file.createNewFile();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}

