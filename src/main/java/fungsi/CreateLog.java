/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fungsi;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author someone
 */
public class CreateLog {

    public static void Tulis(String s) throws IOException {
        Path logg = Paths.get("LOG");
        File sett = new File(".");
        File log = new File(sett.getAbsolutePath().substring(0, sett.getAbsolutePath().length() - 1) + "/LOG");
        log.mkdir();
        LocalDate today = LocalDate.now();
        try (FileWriter file_surat = new FileWriter(log.getPath() + "/" + today.toString() + ".txt", true)) {
            file_surat.write(today.atTime(LocalTime.now()) + " : " + s + "\r\n");
        }
    }
}
