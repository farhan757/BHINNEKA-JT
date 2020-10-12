/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fungsi;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCreationHelper;
import org.apache.poi.xssf.usermodel.XSSFHyperlink;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import propert.Data;

/**
 *
 * @author someone
 */
public class CreateSoftcopy {

    FileWriter soft;
    Workbook list;
    Sheet sheet;
    Row row;
    FileOutputStream fileout;

    public CreateSoftcopy(String nmFile) {
        File file = new File(nmFile);
        try {
            soft = new FileWriter(file, false);
        } catch (IOException ex) {
            Logger.getLogger(CreateSoftcopy.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public CreateSoftcopy() {

    }

    public void CreateList(String nmFile) throws FileNotFoundException {
        fileout = new FileOutputStream(nmFile);
        list = new XSSFWorkbook();
        sheet = list.createSheet("PDF Password");
    }

    public void HeadSoft() {
        try {
            soft.write("No|Nomor Polis|Nama Pemegang Polis|Kode Produk|Nama Produk|Tanggal Mulai Asuransi|Path Attachment|Email Nasabah|Pages|Password|No HP|Delivery polis|Kantor Cabang|Kantor Wilayah|Mulai Asuransi|No Surat|Jenis|Email Care|Telp Care|");
        } catch (IOException ex) {
            Logger.getLogger(CreateSoftcopy.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void HeadLogBhinneka() {
        try {
            soft.write("No;Nopolis;Status;Keterangan");
        } catch (Exception e) {
            Logger.getLogger(CreateSoftcopy.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void isiLogBhinneka(propert.Log obj, int cntr) {
        try {
            soft.write("\r\n" + String.valueOf(cntr) + ";" + obj.getNopol() + ";" + obj.getStatus() + ";" + obj.getSPAJ() + ", " + obj.getIlustrasi());
        } catch (Exception e) {
            Logger.getLogger(CreateSoftcopy.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void HeadListPass() {
        row = sheet.createRow(0);
        row.createCell(0).setCellValue("No.");
        row.createCell(1).setCellValue("Nomor Polis");
        row.createCell(2).setCellValue("File Pdf");
        row.createCell(3).setCellValue("Password");
    }

    public void isiSoft(Data obj, int totalPage, int cntr, String PathAttc, String Password) {
        try {
            switch (obj.getProduk()) {
                default: {
                    soft.write("\r\n" + String.valueOf(cntr) + "|" + obj.getNomor_Polis()+ "|" + obj.getNama_Pemegang_Polis()+ "|"
                            + "" + obj.getProduk() + "|" + obj.getNama_Produk()+ "|" + obj.getTgl_Jatuh_Tempo()+ "|" + PathAttc + "|"
                            + "|" + totalPage + "|" + Password + "|" + obj.getHP_pempol()+ "|" + obj.getJenis_cetakan()+ "||"
                            + "|" + obj.getTgl_Jatuh_Tempo() + "|"+obj.getNo_Surat()+"|JT|"
                            +""+obj.getEmail_Call_Center()+"|"+obj.getNotlp_call_center()+"|");
                    break;
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(CreateSoftcopy.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void isiList(Data obj, int totalPage, int cntr, String PathAttc, String Password) {

        //XSSFCreationHelper  ok =  (XSSFCreationHelper) list.getCreationHelper();
        //XSSFHyperlink  file_link = ok.createHyperlink(HyperlinkType.FILE);
        //file_link.setAddress(PathAttc);
        row = sheet.createRow(cntr);
        row.createCell(0).setCellValue(String.valueOf(cntr));
        row.createCell(1).setCellValue(obj.getNomor_Polis());
        row.createCell(2).setCellValue(PathAttc);
        row.createCell(3).setCellValue(Password);
    }

    public void tutupSoft() {
        try {
            soft.close();
        } catch (IOException ex) {
            Logger.getLogger(CreateSoftcopy.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void tutupList() throws IOException {
        list.write(fileout);
        fileout.close();
        list.close();
    }

    public void tutupLog() {

    }
}
