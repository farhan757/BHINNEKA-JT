/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.panca.bhinneka.jt;

import com.itextpdf.text.DocumentException;
import fungsi.MySqlConnection;
import fungsi.CreateMTIMGI;
import fungsi.CreateLog;
import fungsi.CreatePageOf;
import fungsi.CreateSoftcopy;
import fungsi.HttpReq;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import org.json.JSONArray;
import propert.Log;

/**
 *
 * @author someone
 */
public class proses {

    public static void main(String[] args) throws IOException, ParseException, DocumentException {        
        LocalDate today = LocalDate.now();
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();        
        String cycle = dateFormat.format(date);
        String tnggal = cycle, jenis = "JATUHTEMPO", part = "";
        String pathData = "DATA//JATUHTEMPO//"+cycle+".json";
        pathData = "DATA//JATUHTEMPO//"+cycle+".json";
        Path fileJson = Paths.get(pathData);
        //File sett = new File(".");
        //System.out.println(sett.getAbsolutePath());        
        CreateLog.Tulis("Mulai");

        Logger logger = Logger.getLogger("JATUH TEMPO BHINNEKA");
        logger.log(Level.INFO, "\r\n\r\nStart Proses ...");

        File tmp = new File("TMP");
        if (!tmp.exists()) {
            tmp.mkdir();
        } else {
            tmp.delete();
            tmp.mkdir();
        }
        File ok = new File("bhinnekalife");
        if (!ok.exists()) {
            ok.mkdir();
        }
        File ok2 = new File(ok.getPath() + "/" + tnggal);
        if (!ok2.exists()) {
            ok2.mkdir();
        }

        CreateSoftcopy soft = new CreateSoftcopy(ok2.getPath() + "/SoftCopy-All-" + tnggal + jenis.trim() + part.trim() + ".txt");
        soft.HeadSoft();
        String softall = ok2.getPath() + "/SoftCopy-All-" + tnggal + jenis.trim() + part.trim() + ".txt";
        CreateSoftcopy list = new CreateSoftcopy();
        list.CreateList(ok2.getPath() + "/List-PasswordPDF-" + tnggal + jenis.trim() + part.trim() + ".xlsx");
        list.HeadListPass();

        HashMap<String, propert.Log> Log_bhineka = new HashMap<String, propert.Log>();
        String br = "";
        HashMap<String, propert.Data> listData = null;
        Map<String, String> Sorter = null;
        br = new String(Files.readAllBytes(fileJson));
        JSONArray jsonarray = new JSONArray(br);
        listData = fungsi.InsertTabel.InsertData(jsonarray);
        Sorter = fungsi.InsertTabel.Sorter(jsonarray);

        logger.log(Level.INFO, "{0} Found Item Polis...", String.valueOf(Sorter.size()));
        int cntr = 0;
        int e_email = 0;
        for (Map.Entry<String, String> entry : Sorter.entrySet()) {

            propert.Log data_log = new propert.Log();
            String key = entry.getKey();

            propert.Data data = listData.get(key);

            String PasswordPDF = "";
            logger.log(Level.INFO, data.getNomor_Polis());
            cntr++;

            data_log.setStatus("OK");
            data_log.setSPAJ("SPAJ GAGAL");
            data_log.setIlustrasi("ILLUSTRASI GAGAL");
            data_log.setNopol(data.getNomor_Polis());
            Log_bhineka.put(data_log.getNopol(), data_log);
            PasswordPDF = data.getTgl_Lahir_pempol().replace("-", "").replace("/", "");//fungsi.Value.PlusDay(data.getTanggal_lahir_pemegang_polis(), 0).replace("-", "");

            File ok3 = new File(ok2.getPath() + "/" + data.getTipe_Jatuh_Tempo());
            if (!ok3.exists()) {
                ok3.mkdir();
            }

            String nmFilePdf = tmp.getPath() + "/" + data.getTipe_Jatuh_Tempo() + " " + data.getNomor_Polis() + ".BAK";
            int totalPages = 0;
            String PathAttch = "";
            String PathAttch2 = "";
            String nmFilePdfof = "";
            String PathAttchOK = "";

            try {
                PathAttchOK = ok3 + "/" + data.getTipe_Jatuh_Tempo() + " " + data.getNomor_Polis() + ".pdf";
                PathAttch2 = "/" + data.getTipe_Jatuh_Tempo() + "/" + data.getTipe_Jatuh_Tempo() + " " + data.getNomor_Polis() + ".pdf";
                //PathAttch = tmp_pdf + "/" + data.getProduk() + " " + data.getNomor_polis() + ".pdf";
            } catch (Exception e) {
                logger.log(Level.INFO, e.toString());
                //JOptionPane.showMessageDialog(parentComponent, e);
            }

            CreateMTIMGI creates = null;
            try {
                creates = new CreateMTIMGI(nmFilePdf);
            } catch (DocumentException | FileNotFoundException e) {
                logger.log(Level.INFO, e.toString());
                //JOptionPane.showMessageDialog(parentComponent, e);
            }
            creates.lakukan(data);
            creates.selesai();
            
            CreatePageOf createofo = null;
            
            nmFilePdfof = new File(PathAttchOK).getPath();
            createofo = null;
            createofo = new CreatePageOf(nmFilePdfof, PasswordPDF, true);
            createofo.PDFStream(new File(nmFilePdf).getPath());
            try {
                totalPages = createofo.lakukan_last(data, Log_bhineka);
            } catch (IOException | DocumentException ex) {
                logger.log(Level.INFO, "{0}Pageof", ex.toString());
                //JOptionPane.showMessageDialog(parentComponent, ex);
            }
            createofo.selesai();
            soft.isiSoft(data, totalPages, cntr, PathAttch2, PasswordPDF);
            list.isiList(data, totalPages, cntr, PathAttch2, PasswordPDF);
            logger.log(Level.INFO, "Selesai Proses " + data.getNomor_Polis());
        }
        soft.tutupSoft();
        list.tutupList();
        logger.log(Level.INFO, "Selesai ...Proses");

        String[] entries = tmp.list();
        for (String s : entries) {
            File currentFile = new File(tmp.getPath(), s);
            currentFile.delete();
        }

        if (Log_bhineka.size() > 0) {
            CreateSoftcopy Log_Bhinneka = new CreateSoftcopy(ok2.getPath() + "/" + tnggal + "_log" + jenis + ".txt");
            Log_Bhinneka.HeadLogBhinneka();
            int urut = 0;
            for (Map.Entry<String, Log> entry : Log_bhineka.entrySet()) {
                urut++;
                Log_Bhinneka.isiLogBhinneka(entry.getValue(), urut);
            }
            Log_Bhinneka.tutupSoft();
        }

        if (e_email > 0) {
            //JOptionPane.showMessageDialog(parentComponent, "MOHON CEK FILE LOG !!!!"
            //+ "\r\n Karena Ada Nomor Polis Yang Tidak Memiliki Email");
        }

        if (tmp.exists()) {
            tmp.delete();
        }
        CreateLog.Tulis("Create order in webbase");
        //File uploadFile1 = new File("e:/Test/PIC1.JPG");
        File uploadFile2 = new File(softall);
        //String requestURL = "http://175.106.11.75/cms_dev/api/createorder/bhinnekalife/api/v1";
        String requestURL = "";
        URL url = new URL(requestURL);
        URLConnection con = url.openConnection();
        HttpURLConnection http = (HttpURLConnection) con;
        http.setRequestMethod("POST"); // PUT is another valid option
        http.setDoOutput(true);
        String boundary = UUID.randomUUID().toString();
        byte[] boundaryBytes
                = ("--" + boundary + "\r\n").getBytes(StandardCharsets.UTF_8);
        byte[] finishBoundaryBytes
                = ("--" + boundary + "--").getBytes(StandardCharsets.UTF_8);
        http.setRequestProperty("Content-Type",
                "multipart/form-data; charset=UTF-8; boundary=" + boundary);

        http.setChunkedStreamingMode(0);
        try (OutputStream out = http.getOutputStream()) {            
            out.write(boundaryBytes);

            // Send our first field
            sendField(out, "selectProjects", "15");

            // Send a seperator
            out.write(boundaryBytes);

            // Send our second field
            sendField(out, "inputCycle", cycle);

            // Send another seperator
            out.write(boundaryBytes);

            sendField(out, "selectPart", "1");

            // Send another seperator
            out.write(boundaryBytes);            
            // Send our file
            try (InputStream file = new FileInputStream(uploadFile2)) {
                sendFile(out, "input_file", file, uploadFile2.getName());
            }

            // Finish the request
            out.write(finishBoundaryBytes);            
            System.out.println(http.getResponseCode());
            CreateLog.Tulis(String.valueOf(http.getResponseCode()));
        }catch(Exception e){
            CreateLog.Tulis(e.toString());
        }
    }

    private static void sendFile(OutputStream out, String name, InputStream in, String fileName) throws UnsupportedEncodingException, IOException {
        String o = "Content-Disposition: form-data; name=\"" + URLEncoder.encode(name, "UTF-8")
                + "\"; filename=\"" + URLEncoder.encode(fileName, "UTF-8") + "\"\r\n\r\n";
        out.write(o.getBytes(StandardCharsets.UTF_8));
        byte[] buffer = new byte[2048];
        for (int n = 0; n >= 0; n = in.read(buffer)) {
            out.write(buffer, 0, n);
        }
        out.write("\r\n".getBytes(StandardCharsets.UTF_8));
    }

    private static void sendField(OutputStream out, String name, String field) throws UnsupportedEncodingException, IOException {
        String o = "Content-Disposition: form-data; name=\""
                + URLEncoder.encode(name, "UTF-8") + "\"\r\n\r\n";
        out.write(o.getBytes(StandardCharsets.UTF_8));
        out.write(URLEncoder.encode(field, "UTF-8").getBytes(StandardCharsets.UTF_8));
        out.write("\r\n".getBytes(StandardCharsets.UTF_8));
    }
}
