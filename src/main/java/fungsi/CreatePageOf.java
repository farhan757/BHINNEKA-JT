/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fungsi;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BadPdfFormatException;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import javax.swing.JOptionPane;
import propert.*;

/**
 *
 * @author someone
 */
public class CreatePageOf extends PDFManipul {

    private String nmFIle;
    private String pass;
    PdfReader a5reader;
    boolean skp = false, ketums = false, ketuss = false, berkas = false, kertass = false, ringpoll = false;

    public CreatePageOf(String nmFile) throws DocumentException, FileNotFoundException {
        this.nmFIle = nmFile;
        this.a5writer = PdfWriter.getInstance(this.a5doc, new FileOutputStream(this.nmFIle));
        this.a5doc.open();
    }

    public CreatePageOf(String nmFile, String pass, boolean finis) throws DocumentException, FileNotFoundException, IOException {
        if (finis) {
            try {
                this.nmFIle = nmFile;
                this.pass = pass;
                this.pdfCopy = new PdfCopy(a4doc, new FileOutputStream(this.nmFIle));
                //pdfCopy.setEncryption(pass.getBytes(), "THEJAK".getBytes(), PdfWriter.ALLOW_PRINTING, PdfWriter.ENCRYPTION_AES_128);
                this.a4doc.open();
            } catch (Exception e) {
                //JOptionPane.showMessageDialog(parentComponent, e);
            }
        } else {
            try {
                this.nmFIle = nmFile;
                this.pass = pass;
                this.a5writer = PdfWriter.getInstance(a5doc, new FileOutputStream(this.nmFIle));
                //a5writer.setEncryption(pass.getBytes(), "THEJAK".getBytes(), PdfWriter.ALLOW_PRINTING, PdfWriter.ENCRYPTION_AES_128);
                this.a5doc.open();
            } catch (Exception e) {
                //JOptionPane.showMessageDialog(parentComponent, e);
            }
        }
    }

    public CreatePageOf(String nmFile, String pass, boolean finis, boolean project) throws DocumentException, FileNotFoundException, IOException {
        if (finis) {
            try {
                this.nmFIle = nmFile;
                this.pass = pass;
                this.pdfCopy = new PdfCopy(a4doc, new FileOutputStream(this.nmFIle));
                if (project == false) {
                    pdfCopy.setEncryption(pass.getBytes(), "THEJAK".getBytes(), PdfWriter.ALLOW_PRINTING, PdfWriter.ENCRYPTION_AES_128);
                }
                this.a4doc.open();
            } catch (Exception e) {
                //JOptionPane.showMessageDialog(parentComponent, e);
            }
        } else {
            try {
                this.nmFIle = nmFile;
                this.pass = pass;
                this.a5writer = PdfWriter.getInstance(a5doc, new FileOutputStream(this.nmFIle));
                //a5writer.setEncryption(pass.getBytes(), "THEJAK".getBytes(), PdfWriter.ALLOW_PRINTING, PdfWriter.ENCRYPTION_AES_128);
                this.a5doc.open();
            } catch (Exception e) {
                //JOptionPane.showMessageDialog(parentComponent, e);
            }
        }
    }

    public int lakukan(Data obj, HashMap<String, propert.Log> Log_bhineka) throws IOException, DocumentException {
        this.a5reader = new PdfReader(this.FilePdf);
        PdfContentByte a5cb = this.a5writer.getDirectContent();
        int totalPages = this.a5reader.getNumberOfPages();
        int pagof = totalPages - 1;
        int pages = 0;

        int totalP = 0, halori = 0, ring = 0, ketm = 0, kets = 0, skkp = 0, berrks = 0;
        halori = totalPages;
        for (int i = 1; i <= totalPages; ++i) {
            this.a5doc.newPage();
            PdfImportedPage page = this.a5writer.getImportedPage(this.a5reader, i);
            a5cb.addTemplate(page, 0.0F, 0.0F);
            //if (i > 1) {
            pages++;
            if (!obj.getProduk().equals("PROJECT PA")) {
                PdfPTable pt = new PdfPTable(1);
                pt.setTotalWidth(new float[]{500.0F});
                PdfPCell pc = new PdfPCell();
                pc.setBorder(0);
                pc.setPhrase(new Phrase(new Chunk("Halaman " + String.valueOf(pages) + " dari " + totalPages, GetArial(7.0F))));
                pc.setHorizontalAlignment(2);
                pt.addCell(pc);
                pt.writeSelectedRows(0, -1, 60.0F, 35.0F, a5cb);
            }
            if (!obj.getProduk().equals("BAKI") && !obj.getProduk().equals("PROJECT") && !obj.getProduk().equals("PROJECT PA")) {
                //this.addFooterPolis(a5cb, obj.getKode_KPPT(), obj.getKantor_cabang());
            }
            //}
        }

        this.kertas = new PdfReader("Local/Kertas.pdf");
        PdfImportedPage page2 = this.a5writer.getImportedPage(this.kertas, 1);
        kertass = true;

        /*if (!Tabel_Produk.get(obj.getProduk()).getFile_Other().equals("")) {
            ring = this.Ringpol(Tabel_Produk.get(obj.getProduk()).getFile_Other(), page2, obj);
            ringpoll = true;
        }

        if (!((T_Produk) Tabel_Produk.get(obj.getProduk())).getFile_Ketum().equals("")) {
            ketm = this.ketum(((T_Produk) Tabel_Produk.get(obj.getProduk())).getFile_Ketum(), page2);
            ketums = true;
        }

        if (!((T_Produk) Tabel_Produk.get(obj.getProduk())).getFile_Ketus().equals("")) {
            kets = this.ketus(((T_Produk) Tabel_Produk.get(obj.getProduk())).getFile_Ketus(), page2);
            ketuss = true;
        }

        if (!((T_Produk) Tabel_Produk.get(obj.getProduk())).getFile_SKP().equals("")) {
            skkp = this.SKP(((T_Produk) Tabel_Produk.get(obj.getProduk())).getFile_SKP(), page2);
            skp = true;
        }*/
        /*File cek = new File(scannan.toString() + "\\" + obj.getNomor_polis() + ".zip");
        if (cek.exists()) {
            berrks = this.berkas(scannan.toString() + "\\" + obj.getNomor_polis(), obj.getNomor_polis(), Log_bhineka, obj.getProduk());
            berkas = true;
        }*/

        totalP = halori + ring + ketm + kets + skkp + berrks;
        return totalP;
    }

    public int lakukan_last(Data obj, HashMap<String, propert.Log> Log_bhineka) throws IOException, BadPdfFormatException, DocumentException {
        this.a5reader = new PdfReader(this.FilePdf);
        //PdfContentByte a5cb = this.pdfCopy.getDirectContent();
        int totalPages = this.a5reader.getNumberOfPages();
        int pagof = totalPages - 1;
        int pages = 0;

        int totalP = 0, halori = 0, ring = 0, ketm = 0, kets = 0, skkp = 0, berrks = 0;
        halori = totalPages;

        for (int i = 1; i <= totalPages; ++i) {
            //this.a5doc.newPage();
            PdfImportedPage page = this.pdfCopy.getImportedPage(this.a5reader, i);
            this.pdfCopy.addPage(page);
            //a5cb.addTemplate(page, 0.0F, 0.0F);
        }

        /*File cek = new File(scannan.toString() + "\\" + obj.getNomor_polis() + ".zip");
        if (cek.exists()) {
            berrks = this.berkas(scannan.toString() + "\\" + obj.getNomor_polis(), obj.getNomor_polis(), Log_bhineka, obj.getProduk());
            berkas = true;
        }*/

        totalP = halori + ring + ketm + kets + skkp + berrks;
        return totalP;
    }

    public void selesai() {

        try {
            this.a5doc.close();
        } catch (Exception var7) {
            System.out.print(var7 + " a5doc");
        }

        try {
            this.a4doc.close();
        } catch (Exception var7) {
            System.out.print(var7 + " a5doc");
        }

        try {
            this.a5reader.close();
        } catch (Exception var6) {
            System.out.print(var6 + " a5reader");
        }

        if (kertass) {
            try {
                this.kertas.close();
            } catch (Exception var5) {
                System.out.print(var5 + " KERTAS");
            }
        }
        if (ketums) {
            try {
                this.ketum.close();
            } catch (Exception var4) {
                System.out.print(var4 + " KETUM");
            }
        }
        if (ketuss) {
            try {
                this.ketus.close();
            } catch (Exception var3) {
                System.out.print(var3 + " KETUS");
            }
        }
        if (skp) {
            try {
                this.SKP.close();
            } catch (Exception var2) {
                System.out.print(var2 + " SKP");
            }
        }
        if (berkas) {
            try {
                this.brks.close();
            } catch (Exception var2) {
                System.out.print(var2 + " brks");
            }
        }

        if (ringpoll) {
            try {
                this.ringpol.close();
            } catch (Exception var2) {
                System.out.print(var2 + " ringpoll");
            }
        }
    }
}
