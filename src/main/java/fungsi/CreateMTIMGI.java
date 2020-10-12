/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fungsi;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.TabStop;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;
import fungsi.Value;
import java.text.ParseException;
import propert.Data;

/**
 *
 * @author farhan
 */
public class CreateMTIMGI extends PDFManipul {

    private String nmFIle;
    private String pass;

    public CreateMTIMGI(String nmFile) throws DocumentException, FileNotFoundException {

        this.nmFIle = nmFile;
        this.a5writer = PdfWriter.getInstance(this.a5doc, new FileOutputStream(this.nmFIle));
        this.a5doc.open();
    }

    public void lakukan(Data obj) throws DocumentException, IOException, ParseException {
        PdfContentByte a5cb = this.a5writer.getDirectContent();
        short y = 760;
        if (obj.getTipe_Jatuh_Tempo().equals("MTI")) {
            try {
                this.kertas = new PdfReader("Local/FORM-PPMPA-MTI.pdf");
            } catch (IOException var6) {
                Logger.getLogger(PDFManipul.class.getName()).log(Level.SEVERE, (String) null, var6);
            }
        } else if (obj.getTipe_Jatuh_Tempo().equals("MGI")) {
            try {
                this.kertas = new PdfReader("Local/FORM-PPMPA-MGI.pdf");
            } catch (IOException var6) {
                Logger.getLogger(PDFManipul.class.getName()).log(Level.SEVERE, (String) null, var6);
            }
        }

        try {
            this.kertasWL = new PdfReader("Local/KertasWL.pdf");
        } catch (IOException var6) {
            Logger.getLogger(PDFManipul.class.getName()).log(Level.SEVERE, (String) null, var6);
        }

        /*if (!((T_Produk) Tabel_Produk.get(obj.getProduk())).getFile_Tante().equals("")) {
            this.tante(a5cb, obj, ((T_Produk) Tabel_Produk.get(obj.getProduk())).getFile_Tante());
        }*/
        PdfImportedPage page2 = this.a5writer.getImportedPage(this.kertas, 1);
        PdfImportedPage page3 = this.a5writer.getImportedPage(this.kertasWL, 1);

        if (obj.getTipe_Jatuh_Tempo().equals("MTI")) {
            switch (obj.getTipe_ARO()) {
                case "2": {
                    this.MTI2(a5cb, obj, y, page2, page3);
                    break;
                }
                case "3": {
                    this.MTI3(a5cb, obj, y, page2, page3);
                    break;
                }
            }
        } else if (obj.getTipe_Jatuh_Tempo().equals("MGI")) {
            switch (obj.getTipe_ARO()) {
                case "1": {
                    this.MGI1(a5cb, obj, y, page2, page3);
                    break;
                }
                case "2": {
                    this.MGI2(a5cb, obj, y, page2, page3);
                    break;
                }
                case "3": {
                    this.MGI3(a5cb, obj, y, page2, page3);
                    break;
                }
            }
        }
    }

    private void MTI3(PdfContentByte a5cb, Data obj, int y, PdfImportedPage kertas, PdfImportedPage kertasWL) throws DocumentException, ParseException {
        this.a5doc.newPage();
        a5cb.addTemplate(kertasWL, 0.0F, 0.0F);

        PdfPTable tbl = new PdfPTable(3);
        tbl.setTotalWidth(new float[]{80f, 5f, 350f});
        PdfPCell pcell = new PdfPCell();
        pcell.setPhrase(new Phrase(new Chunk("Nomor", GetCalibri(10))));
        pcell.setBorder(0);
        tbl.addCell(pcell);
        pcell.setPhrase(new Phrase(new Chunk(":", GetCalibri(10))));
        pcell.setBorder(0);
        tbl.addCell(pcell);
        pcell.setPhrase(new Phrase(new Chunk(obj.getNo_Surat(), GetCalibri(10))));
        pcell.setBorder(0);
        tbl.addCell(pcell);
        pcell.setPhrase(new Phrase(new Chunk("Perihal", GetCalibri(10))));
        pcell.setBorder(0);
        tbl.addCell(pcell);
        pcell.setPhrase(new Phrase(new Chunk(":", GetCalibri(10))));
        pcell.setBorder(0);
        tbl.addCell(pcell);
        pcell.setPhrase(new Phrase(new Chunk("Pemberitahuan Jatuh Tempo Masa Target Investasi & Pengambilan Nilai Polis \r\nPolis No " + obj.getNomor_Polis() + " Atas nama " + obj.getNama_Pemegang_Polis(), GetCalibri(10))));
        pcell.setBorder(0);
        pcell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
        tbl.addCell(pcell);
        pcell.setPhrase(new Phrase(new Chunk("Lampiran", GetCalibri(10))));
        pcell.setBorder(0);
        tbl.addCell(pcell);
        pcell.setPhrase(new Phrase(new Chunk(":", GetCalibri(10))));
        pcell.setBorder(0);
        tbl.addCell(pcell);
        pcell.setPhrase(new Phrase(new Chunk("1 (satu)", GetCalibri(10))));
        pcell.setBorder(0);
        pcell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
        tbl.addCell(pcell);
        tbl.writeSelectedRows(0, -1, 60, y, a5cb);
        y -= tbl.getTotalHeight();
        y -= 10;

        tbl = new PdfPTable(1);
        tbl.setTotalWidth(new float[]{250});
        pcell = new PdfPCell();
        pcell.setPhrase(new Phrase(new Chunk("Jakarta, " + obj.getTgl_Surat() + "\r\n\n", GetCalibri(10))));
        pcell.setBorder(0);
        tbl.addCell(pcell);
        pcell.setPhrase(new Phrase(new Chunk("Kepada Yth,", GetCalibri(10))));
        pcell.setBorder(0);
        tbl.addCell(pcell);
        pcell.setPhrase(new Phrase(new Chunk("Bapak/Ibu " + obj.getNama_Pemegang_Polis(), GetCalibri(10))));
        pcell.setBorder(0);
        tbl.addCell(pcell);
        pcell.setPhrase(new Phrase(new Chunk("Di Tempat", GetCalibri(10))));
        pcell.setBorder(0);
        tbl.addCell(pcell);
        tbl.writeSelectedRows(0, -1, 60, y, a5cb);
        y -= tbl.getTotalHeight();
        y -= 15;

        tbl = new PdfPTable(1);
        tbl.setTotalWidth(new float[]{500f});
        pcell = new PdfPCell();
        pcell.setPhrase(new Phrase(new Chunk("Dengan hormat,\r\n\n", GetCalibri(10))));
        pcell.setBorder(0);
        tbl.addCell(pcell);
        pcell.setPhrase(new Phrase(new Chunk("Kami mengucapkan terima kasih atas kepercayaan Bapak/Ibu kepada PT Bhinneka Life Indonesia dalam pengelolaan perlindungan asuransi dan keuangan Bapak/Ibu beserta keluarga tercinta.\r\n\n", GetCalibri(10))));
        pcell.setBorder(0);
        pcell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
        tbl.addCell(pcell);
        pcell.setPhrase(new Phrase(new Chunk("Dengan ini kami sampaikan bahwa akhir Masa Target Investasi (MTI) Polis Asuransi "
                + obj.getNama_Produk() + " Bapak/Ibu akan jatuh tempo pada tanggal " + obj.getTgl_Jatuh_Tempo() + ". Berdasarkan pilihan Jatuh Tempo (MTI) yang tercantum pada Surat Pengajuan Asuransi Jiwa (SPAJ), Bapak/Ibu memilih untuk mengambil Nilai Tunai Polis yang Bapak/Ibu miliki."
                        + "\r\n\nAdapun perincian Nilai Polis Bapak/Ibu sebagai berikut :", GetCalibri(10))));
        pcell.setBorder(0);
        pcell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
        tbl.addCell(pcell);
        tbl.writeSelectedRows(0, -1, 60, y, a5cb);
        y -= tbl.getTotalHeight();
        y -= 10;

        PdfPTable pt = new PdfPTable(4);
        pt.setTotalWidth(new float[]{200.0F, 5f, 25f, 200.0F});
        PdfPCell pc = new PdfPCell();
        Phrase pr = new Phrase();
        pr.add(new Chunk("Nomor Polis", GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setHorizontalAlignment(Element.ALIGN_LEFT);
        pc.setBorder(0);
        pt.addCell(pc);
        pr = new Phrase();
        pr.add(new Chunk(": " + obj.getNomor_Polis(), GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setHorizontalAlignment(Element.ALIGN_LEFT);
        pc.setColspan(3);
        pc.setBorder(0);
        pt.addCell(pc);

        pc = new PdfPCell();
        pr = new Phrase();
        pr.add(new Chunk("Nama Pemegang Polis", GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setHorizontalAlignment(Element.ALIGN_LEFT);
        pc.setBorder(0);
        pt.addCell(pc);
        pr = new Phrase();
        pr.add(new Chunk(": " + obj.getNama_Pemegang_Polis(), GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setHorizontalAlignment(Element.ALIGN_LEFT);
        pc.setColspan(3);
        pc.setBorder(0);
        pt.addCell(pc);
        pt.writeSelectedRows(0, -1, 70.0F, (float) y, a5cb);
        y -= pt.getTotalHeight();
        
        pt = new PdfPTable(4);
        pt.setTotalWidth(new float[]{200.0F, 5f, 25f, 100.0F});        
        pc = new PdfPCell();
        pr = new Phrase();
        pr.add(new Chunk("Total Premi", GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setHorizontalAlignment(Element.ALIGN_LEFT);
        pc.setBorder(0);
        pt.addCell(pc);
        pr = new Phrase();
        pr.add(new Chunk(":", GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setHorizontalAlignment(Element.ALIGN_LEFT);
        pc.setBorder(0);
        pt.addCell(pc);
        pr = new Phrase();
        pr.add(new Chunk("Rp.", GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setHorizontalAlignment(Element.ALIGN_LEFT);
        pc.setBorder(0);
        pt.addCell(pc);
        pr = new Phrase();
        pr.add(new Chunk(obj.getTotal_Premi(), GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setHorizontalAlignment(Element.ALIGN_RIGHT);
        pc.setBorder(0);
        pt.addCell(pc);

        pc = new PdfPCell();
        pr = new Phrase();
        pr.add(new Chunk("Hasil Investasi (MTI " + obj.getPeriode_Jatuh_Tempo() + " bulan)", GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setHorizontalAlignment(Element.ALIGN_LEFT);
        pc.setBorder(0);
        pt.addCell(pc);
        pr = new Phrase();
        pr.add(new Chunk(":", GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setHorizontalAlignment(Element.ALIGN_LEFT);
        pc.setBorder(0);
        pt.addCell(pc);
        pr = new Phrase();
        pr.add(new Chunk("Rp.", GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setHorizontalAlignment(Element.ALIGN_LEFT);
        pc.setBorder(0);
        pt.addCell(pc);
        pr = new Phrase();
        pr.add(new Chunk(obj.getJumlah_Investasi(), GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setHorizontalAlignment(Element.ALIGN_RIGHT);
        pc.setBorder(0);
        pt.addCell(pc);

        pc = new PdfPCell();
        pr = new Phrase();
        pr.add(new Chunk("Nilai Polis/Saldo Dana Investasi", GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setHorizontalAlignment(Element.ALIGN_LEFT);
        pc.setBorder(0);
        pt.addCell(pc);
        pr = new Phrase();
        pr.add(new Chunk(":", GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setHorizontalAlignment(Element.ALIGN_LEFT);
        pc.setBorder(0);
        pt.addCell(pc);
        pr = new Phrase();
        pr.add(new Chunk("Rp.", GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setHorizontalAlignment(Element.ALIGN_LEFT);
        pc.setBorder(0);
        pt.addCell(pc);
        pr = new Phrase();
        pr.add(new Chunk(obj.getNilai_Polis(), GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setHorizontalAlignment(Element.ALIGN_RIGHT);
        pc.setBorder(0);
        pt.addCell(pc);
        pt.writeSelectedRows(0, -1, 70.0F, (float) y, a5cb);
        y -= pt.getTotalHeight();
        y -= 10;

        pt = new PdfPTable(1);
        pt.setTotalWidth(new float[]{500f});
        pr = new Phrase();
        pc = new PdfPCell();
        pr.add(new Chunk("Agar Pengambilan Nilai Tunai Polis Bapak/Ibu dapat segera "
                + "kami proses, mohon untuk melengkapi persyaratan dokumen sebagai berikut : ", GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setBorder(0);
        pc.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
        pt.addCell(pc);
        pt.writeSelectedRows(0, -1, 60.0F, (float) y, a5cb);
        y -= pt.getTotalHeight();
        y -= 10;

        pt = new PdfPTable(2);
        pt.setTotalWidth(new float[]{20f, 400f});
        pr = new Phrase();
        pc = new PdfPCell();
        pr.add(new Chunk("1.", GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setBorder(0);
        pc.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
        pt.addCell(pc);
        pr = new Phrase();
        pc = new PdfPCell();
        pr.add(new Chunk("Polis Asli", GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setBorder(0);
        pc.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
        pt.addCell(pc);
        pr = new Phrase();
        pc = new PdfPCell();
        pr.add(new Chunk("2.", GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setBorder(0);
        pc.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
        pt.addCell(pc);
        pr = new Phrase();
        pc = new PdfPCell();
        pr.add(new Chunk("Copy identitas diri Pemegang Polis yang masih berlaku", GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setBorder(0);
        pc.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
        pt.addCell(pc);
        pr = new Phrase();
        pc = new PdfPCell();
        pr.add(new Chunk("3.", GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setBorder(0);
        pc.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
        pt.addCell(pc);
        pr = new Phrase();
        pc = new PdfPCell();
        pr.add(new Chunk("Mengisi Formulir Pengajuan Pembayaran Manfaat Polis Asuransi (terlampir)", GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setBorder(0);
        pc.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
        pt.addCell(pc);
        pt.writeSelectedRows(0, -1, 70.0F, (float) y, a5cb);
        y -= pt.getTotalHeight();
        y -= 10;

        pt = new PdfPTable(1);
        pt.setTotalWidth(new float[]{500f});
        pr = new Phrase();
        pc = new PdfPCell();
        pr.add(new Chunk("Kelengkapan dokumen tersebut di atas dapat dikembalikan "
                + "ke kantor pemasaran PT Bhinneka Life Indonesia terdekat.", GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setBorder(0);
        pc.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
        pt.addCell(pc);
        pt.writeSelectedRows(0, -1, 60.0F, (float) y, a5cb);
        y -= pt.getTotalHeight();
        y -= 10;

        /*pt = new PdfPTable(1);
        pt.setTotalWidth(new float[]{500f});
        pr = new Phrase();
        pc = new PdfPCell();
        pr.add(new Chunk(obj.getAlamat_POS().replace("\n ", "\n"), GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setBorder(0);
        pc.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
        pt.addCell(pc);
        pt.writeSelectedRows(0, -1, 70.0F, (float) y, a5cb);
        y -= pt.getTotalHeight();
        y -= 10; */
        pt = new PdfPTable(1);
        pt.setTotalWidth(new float[]{500f});
        pr = new Phrase();
        pc = new PdfPCell();
        pr.add(new Chunk("Demikian kami sampaikan, jika Bapak/Ibu memiliki pertanyaan sehubungan dengan "
                + "Polis yang Bapak/Ibu miliki silahkan menghubungi Customer Care kami di nomor telepon "
                + obj.getNotlp_call_center() + ", atau melalui email : " + obj.getEmail_Call_Center() + " dengan senang hati kami akan membantu.", GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setBorder(0);
        pc.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
        pt.addCell(pc);
        pt.writeSelectedRows(0, -1, 60.0F, (float) y, a5cb);
        y -= (int) pt.getTotalHeight();
        y -= 25;

        pt = new PdfPTable(1);
        pt.setTotalWidth(new float[]{500f});
        pr = new Phrase();
        pc = new PdfPCell();
        pr.add(new Chunk("Hormat Kami,\r\n\n", GetCalibri(10)));
        pr.add(new Chunk("PT Bhinneka Life Indonesia\r\n\n\n", GetCalibri(10, 1)));
        pc.setPhrase(pr);
        pc.setBorder(0);
        pt.addCell(pc);
        pr = new Phrase();
        pc = new PdfPCell();
        pr.add(new Chunk("Surat ini dicetak secara komputerisasi, pencantuman tanda tangan tidak diperlukan", GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setBorder(0);
        pc.setHorizontalAlignment(Element.ALIGN_CENTER);
        pt.addCell(pc);
        pt.writeSelectedRows(0, -1, 60.0F, (float) y, a5cb);
        y -= pt.getTotalHeight();
        y -= 10;
        
        this.a5doc.newPage();
        a5cb.addTemplate(kertas, 0.0F, 0.0F);        
    }

    private void MTI2(PdfContentByte a5cb, Data obj, int y, PdfImportedPage kertas, PdfImportedPage kertasWL) throws DocumentException, ParseException {
        this.a5doc.newPage();
        a5cb.addTemplate(kertasWL, 0.0F, 0.0F);

        PdfPTable tbl = new PdfPTable(3);
        tbl.setTotalWidth(new float[]{80f, 5f, 350f});
        PdfPCell pcell = new PdfPCell();
        pcell.setPhrase(new Phrase(new Chunk("Nomor", GetCalibri(10))));
        pcell.setBorder(0);
        tbl.addCell(pcell);
        pcell.setPhrase(new Phrase(new Chunk(":", GetCalibri(10))));
        pcell.setBorder(0);
        tbl.addCell(pcell);
        pcell.setPhrase(new Phrase(new Chunk(obj.getNo_Surat(), GetCalibri(10))));
        pcell.setBorder(0);
        tbl.addCell(pcell);
        pcell.setPhrase(new Phrase(new Chunk("Perihal", GetCalibri(10))));
        pcell.setBorder(0);
        tbl.addCell(pcell);
        pcell.setPhrase(new Phrase(new Chunk(":", GetCalibri(10))));
        pcell.setBorder(0);
        tbl.addCell(pcell);
        pcell.setPhrase(new Phrase(new Chunk("Pemberitahuan Jatuh Tempo & Perpanjangan Masa Target Investasi \r\nPolis No " + obj.getNomor_Polis() + " Atas nama " + obj.getNama_Pemegang_Polis(), GetCalibri(10))));
        pcell.setBorder(0);
        pcell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
        tbl.addCell(pcell);
        tbl.writeSelectedRows(0, -1, 60, y, a5cb);
        y -= tbl.getTotalHeight();
        y -= 10;

        tbl = new PdfPTable(1);
        tbl.setTotalWidth(new float[]{250});
        pcell = new PdfPCell();
        pcell.setPhrase(new Phrase(new Chunk("Jakarta, " + obj.getTgl_Surat() + "\r\n\n", GetCalibri(10))));
        pcell.setBorder(0);
        tbl.addCell(pcell);
        pcell.setPhrase(new Phrase(new Chunk("Kepada Yth,", GetCalibri(10))));
        pcell.setBorder(0);
        tbl.addCell(pcell);
        pcell.setPhrase(new Phrase(new Chunk("Bapak/Ibu " + obj.getNama_Pemegang_Polis(), GetCalibri(10))));
        pcell.setBorder(0);
        tbl.addCell(pcell);
        pcell.setPhrase(new Phrase(new Chunk("Di Tempat", GetCalibri(10))));
        pcell.setBorder(0);
        tbl.addCell(pcell);
        tbl.writeSelectedRows(0, -1, 60, y, a5cb);
        y -= tbl.getTotalHeight();
        y -= 15;

        tbl = new PdfPTable(1);
        tbl.setTotalWidth(new float[]{500f});
        pcell = new PdfPCell();
        pcell.setPhrase(new Phrase(new Chunk("Dengan hormat,\r\n\n", GetCalibri(10))));
        pcell.setBorder(0);
        tbl.addCell(pcell);
        pcell.setPhrase(new Phrase(new Chunk("Kami mengucapkan terima kasih atas kepercayaan Bapak/Ibu kepada PT Bhinneka Life Indonesia "
                + "dalam pengelolaan perlindungan asuransi dan keuangan Bapak/Ibu beserta keluarga tercinta.\r\n\n", GetCalibri(10))));
        pcell.setBorder(0);
        pcell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
        tbl.addCell(pcell);
        pcell.setPhrase(new Phrase(new Chunk("Dengan ini kami sampaikan bahwa akhir Masa Target Investasi (MTI) Polis Asuransi "
                + obj.getNama_Produk() + " Bapak/Ibu akan jatuh tempo pada tanggal " + obj.getTgl_Jatuh_Tempo() + ". Berdasarkan pilihan Jatuh Tempo (MTI) yang tercantum pada Surat Pengajuan Asuransi Jiwa (SPAJ), "
                +" maka Polis Bapak/Ibu kami perpanjang untuk periode " + obj.getPeriode_Jatuh_Tempo() + " (" + fungsi.Value.angka(Integer.parseInt(obj.getPeriode_Jatuh_Tempo())) + ")"
                + " bulan berikutnya dengan perincian sebagai berikut :", GetCalibri(10))));
        pcell.setBorder(0);
        pcell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
        tbl.addCell(pcell);
        tbl.writeSelectedRows(0, -1, 60, y, a5cb);
        y -= tbl.getTotalHeight();
        //y -= 10;

        PdfPTable pt = new PdfPTable(4);
        pt.setTotalWidth(new float[]{200.0F, 5f, 25f, 200.0F});
        PdfPCell pc = new PdfPCell();
        Phrase pr = new Phrase();
        pr.add(new Chunk("Nomor Polis", GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setHorizontalAlignment(Element.ALIGN_LEFT);
        pc.setBorder(0);
        pt.addCell(pc);
        pr = new Phrase();
        pr.add(new Chunk(": " + obj.getNomor_Polis(), GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setHorizontalAlignment(Element.ALIGN_LEFT);
        pc.setColspan(3);
        pc.setBorder(0);
        pt.addCell(pc);

        pc = new PdfPCell();
        pr = new Phrase();
        pr.add(new Chunk("Nama Pemegang Polis", GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setHorizontalAlignment(Element.ALIGN_LEFT);
        pc.setBorder(0);
        pt.addCell(pc);
        pr = new Phrase();
        pr.add(new Chunk(": " + obj.getNama_Pemegang_Polis(), GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setHorizontalAlignment(Element.ALIGN_LEFT);
        pc.setColspan(3);
        pc.setBorder(0);
        pt.addCell(pc);
        pt.writeSelectedRows(0, -1, 70.0F, (float) y, a5cb);
        y -= pt.getTotalHeight();
        
        pt = new PdfPTable(4);
        pt.setTotalWidth(new float[]{200.0F, 5f, 25f, 100.0F});
        pc = new PdfPCell();
        pr = new Phrase();
        pr.add(new Chunk("Total Premi", GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setHorizontalAlignment(Element.ALIGN_LEFT);
        pc.setBorder(0);
        pt.addCell(pc);
        pr = new Phrase();
        pr.add(new Chunk(":", GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setHorizontalAlignment(Element.ALIGN_LEFT);
        pc.setBorder(0);
        pt.addCell(pc);
        pr = new Phrase();
        pr.add(new Chunk("Rp.", GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setHorizontalAlignment(Element.ALIGN_LEFT);
        pc.setBorder(0);
        pt.addCell(pc);
        pr = new Phrase();
        pr.add(new Chunk(obj.getTotal_Premi(), GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setHorizontalAlignment(Element.ALIGN_RIGHT);
        pc.setBorder(0);
        pt.addCell(pc);

        pc = new PdfPCell();
        pr = new Phrase();
        pr.add(new Chunk("Tanggal Mulai MTI", GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setHorizontalAlignment(Element.ALIGN_LEFT);
        pc.setBorder(0);
        pt.addCell(pc);
        pr = new Phrase();
        pr.add(new Chunk(": " + obj.getTanggal_Mulai_berikutnya(), GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setHorizontalAlignment(Element.ALIGN_LEFT);
        pc.setBorder(0);
        pc.setColspan(3);
        pt.addCell(pc);

        pc = new PdfPCell();
        pr = new Phrase();
        pr.add(new Chunk("Tingkat Target Investasi ", GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setHorizontalAlignment(Element.ALIGN_LEFT);
        pc.setBorder(0);
        pt.addCell(pc);
        pr = new Phrase();
        pr.add(new Chunk(": " + obj.getTingkat_Target_Investasi() + "% per tahun net", GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setHorizontalAlignment(Element.ALIGN_LEFT);
        pc.setBorder(0);
        pc.setColspan(3);
        pt.addCell(pc);
        pt.writeSelectedRows(0, -1, 70.0F, (float) y, a5cb);
        y -= pt.getTotalHeight();
        y -= 10;

        pt = new PdfPTable(1);
        pt.setTotalWidth(new float[]{500f});
        pr = new Phrase();
        pc = new PdfPCell();
        pr.add(new Chunk("Sebagai informasi hasil investasi atas Polis Bapak/Ibu "
                + "akan kami transfer ke rekening yang tercantum dalam SPAJ sebagai berikut :", GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setBorder(0);
        pc.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
        pt.addCell(pc);
        pt.writeSelectedRows(0, -1, 60.0F, (float) y, a5cb);
        y -= pt.getTotalHeight();

        pt = new PdfPTable(4);
        pt.setTotalWidth(new float[]{200.0F, 5f, 25f, 200.0F});
        pc = new PdfPCell();
        pr = new Phrase();
        pr.add(new Chunk("Nama Pemilik Rekening", GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setHorizontalAlignment(Element.ALIGN_LEFT);
        pc.setBorder(0);
        pt.addCell(pc);
        pr = new Phrase();
        pr.add(new Chunk(": " + obj.getNama_pemilik_rekening(), GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setHorizontalAlignment(Element.ALIGN_LEFT);
        pc.setColspan(3);
        pc.setBorder(0);
        pt.addCell(pc);

        pc = new PdfPCell();
        pr = new Phrase();
        pr.add(new Chunk("No. Rekening", GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setHorizontalAlignment(Element.ALIGN_LEFT);
        pc.setBorder(0);
        pt.addCell(pc);
        pr = new Phrase();
        pr.add(new Chunk(": " + obj.getNomor_rekening(), GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setHorizontalAlignment(Element.ALIGN_LEFT);
        pc.setColspan(3);
        pc.setBorder(0);
        pt.addCell(pc);

        pc = new PdfPCell();
        pr = new Phrase();
        pr.add(new Chunk("Nama Bank dan Cabang", GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setHorizontalAlignment(Element.ALIGN_LEFT);
        pc.setBorder(0);
        pt.addCell(pc);
        pr = new Phrase();
        pr.add(new Chunk(": " + obj.getNama_Bank() + " " + obj.getNama_Bank_Cabang(), GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setHorizontalAlignment(Element.ALIGN_LEFT);
        pc.setBorder(0);
        pc.setColspan(3);
        pt.addCell(pc);
        pt.writeSelectedRows(0, -1, 70.0F, (float) y, a5cb);
        y -= pt.getTotalHeight();
        
        pt = new PdfPTable(4);
        pt.setTotalWidth(new float[]{200.0F, 5f, 25f, 100.0F});        
        pc = new PdfPCell();
        pr = new Phrase();
        pr.add(new Chunk("Nominal", GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setHorizontalAlignment(Element.ALIGN_LEFT);
        pc.setBorder(0);
        pt.addCell(pc);
        pr = new Phrase();
        pr.add(new Chunk(":", GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setHorizontalAlignment(Element.ALIGN_LEFT);
        pc.setBorder(0);
        pt.addCell(pc);
        pr = new Phrase();
        pr.add(new Chunk("Rp.", GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setHorizontalAlignment(Element.ALIGN_LEFT);
        pc.setBorder(0);
        pt.addCell(pc);
        pr = new Phrase();
        pr.add(new Chunk(obj.getJumlah_Investasi(), GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setHorizontalAlignment(Element.ALIGN_RIGHT);
        pc.setBorder(0);
        pt.addCell(pc);
        pt.writeSelectedRows(0, -1, 70.0F, (float) y, a5cb);
        y -= pt.getTotalHeight();
        y -= 10;

        pt = new PdfPTable(1);
        pt.setTotalWidth(new float[]{500f});
        pr = new Phrase();
        pc = new PdfPCell();
        pr.add(new Chunk("Untuk memastikan korespondensi berjalan dengan baik, mohon Bapak/Ibu dapat selalu melakukan pengkinian data "
                + "atas alamat korespondensi, email dan nomor telepon (rumah, kantor dan HP) apabila ada perubahan."
                + "\r\n\n", GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setBorder(0);
        pc.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
        pt.addCell(pc);
        pr = new Phrase();
        pc = new PdfPCell();
        pr.add(new Chunk("Demikian kami sampaikan, jika Bapak/Ibu memiliki pertanyaan sehubungan dengan "
                + "Polis yang Bapak/Ibu miliki silahkan menghubungi Customer Care kami di nomor telepon "
                + obj.getNotlp_call_center() + ", atau melalui email : " + obj.getEmail_Call_Center() + " dengan senang hati kami akan membantu.", GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setBorder(0);
        pc.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
        pt.addCell(pc);
        pt.writeSelectedRows(0, -1, 60.0F, (float) y, a5cb);
        y -= (int) pt.getTotalHeight();
        y -= 25;

        pt = new PdfPTable(1);
        pt.setTotalWidth(new float[]{500f});
        pr = new Phrase();
        pc = new PdfPCell();
        pr.add(new Chunk("Hormat Kami,\r\n\n", GetCalibri(10)));
        pr.add(new Chunk("PT Bhinneka Life Indonesia\r\n\n\n", GetCalibri(10, 1)));
        pc.setPhrase(pr);
        pc.setBorder(0);
        pt.addCell(pc);
        pr = new Phrase();
        pc = new PdfPCell();
        pr.add(new Chunk("Surat ini dicetak secara komputerisasi, pencantuman tanda tangan tidak diperlukan", GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setBorder(0);
        pc.setHorizontalAlignment(Element.ALIGN_CENTER);
        pt.addCell(pc);
        pt.writeSelectedRows(0, -1, 60.0F, (float) y, a5cb);
        y -= pt.getTotalHeight();
        y -= 10;
    }

    private void MGI3(PdfContentByte a5cb, Data obj, int y, PdfImportedPage kertas, PdfImportedPage kertasWL) throws DocumentException, ParseException {
        this.a5doc.newPage();
        a5cb.addTemplate(kertasWL, 0.0F, 0.0F);

        PdfPTable tbl = new PdfPTable(3);
        tbl.setTotalWidth(new float[]{80f, 5f, 350f});
        PdfPCell pcell = new PdfPCell();
        pcell.setPhrase(new Phrase(new Chunk("Nomor", GetCalibri(10))));
        pcell.setBorder(0);
        tbl.addCell(pcell);
        pcell.setPhrase(new Phrase(new Chunk(":", GetCalibri(10))));
        pcell.setBorder(0);
        tbl.addCell(pcell);
        pcell.setPhrase(new Phrase(new Chunk(obj.getNo_Surat(), GetCalibri(10))));
        pcell.setBorder(0);
        tbl.addCell(pcell);
        pcell.setPhrase(new Phrase(new Chunk("Perihal", GetCalibri(10))));
        pcell.setBorder(0);
        tbl.addCell(pcell);
        pcell.setPhrase(new Phrase(new Chunk(":", GetCalibri(10))));
        pcell.setBorder(0);
        tbl.addCell(pcell);
        pcell.setPhrase(new Phrase(new Chunk("Pemberitahuan Jatuh Tempo Masa Garansi Investasi & Pengambilan Nilai Polis \r\nPolis No " + obj.getNomor_Polis() + " Atas nama " + obj.getNama_Pemegang_Polis(), GetCalibri(10))));
        pcell.setBorder(0);
        pcell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
        tbl.addCell(pcell);
        pcell.setPhrase(new Phrase(new Chunk("Lampiran", GetCalibri(10))));
        pcell.setBorder(0);
        tbl.addCell(pcell);
        pcell.setPhrase(new Phrase(new Chunk(":", GetCalibri(10))));
        pcell.setBorder(0);
        tbl.addCell(pcell);
        pcell.setPhrase(new Phrase(new Chunk("1 (satu)", GetCalibri(10))));
        pcell.setBorder(0);
        pcell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
        tbl.addCell(pcell);
        tbl.writeSelectedRows(0, -1, 60, y, a5cb);
        y -= tbl.getTotalHeight();
        y -= 10;

        tbl = new PdfPTable(1);
        tbl.setTotalWidth(new float[]{250});
        pcell = new PdfPCell();
        pcell.setPhrase(new Phrase(new Chunk("Jakarta, " + obj.getTgl_Surat() + "\r\n\n", GetCalibri(10))));
        pcell.setBorder(0);
        tbl.addCell(pcell);
        pcell.setPhrase(new Phrase(new Chunk("Kepada Yth,", GetCalibri(10))));
        pcell.setBorder(0);
        tbl.addCell(pcell);
        pcell.setPhrase(new Phrase(new Chunk("Bapak/Ibu " + obj.getNama_Pemegang_Polis(), GetCalibri(10))));
        pcell.setBorder(0);
        tbl.addCell(pcell);
        pcell.setPhrase(new Phrase(new Chunk("Di Tempat", GetCalibri(10))));
        pcell.setBorder(0);
        tbl.addCell(pcell);
        tbl.writeSelectedRows(0, -1, 60, y, a5cb);
        y -= tbl.getTotalHeight();
        y -= 15;

        tbl = new PdfPTable(1);
        tbl.setTotalWidth(new float[]{500f});
        pcell = new PdfPCell();
        pcell.setPhrase(new Phrase(new Chunk("Dengan hormat,\r\n\n", GetCalibri(10))));
        pcell.setBorder(0);
        tbl.addCell(pcell);
        pcell.setPhrase(new Phrase(new Chunk("Kami mengucapkan terima kasih atas kepercayaan Bapak/Ibu kepada PT Bhinneka Life Indonesia dalam pengelolaan perlindungan asuransi dan keuangan Bapak/Ibu beserta keluarga tercinta.\r\n\n", GetCalibri(10))));
        pcell.setBorder(0);
        pcell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
        tbl.addCell(pcell);
        pcell.setPhrase(new Phrase(new Chunk("Dengan ini kami sampaikan bahwa akhir Masa Garansi Investasi (MGI) Polis Asuransi "
                + obj.getNama_Produk() + " Bapak/Ibu akan jatuh tempo pada tanggal " + obj.getTgl_Jatuh_Tempo() + ". Berdasarkan pilihan Jatuh Tempo (MGI) yang tercantum di Surat Pengajuan Asuransi Jiwa (SPAJ), Bapak/Ibu memilih untuk "
                + obj.getNama_Tipe_ARO() + " yang Bapak/Ibu miliki.\r\n\nAdapun perincian Nilai Polis Bapak/Ibu sebagai berikut :", GetCalibri(10))));
        pcell.setBorder(0);
        pcell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
        tbl.addCell(pcell);
        tbl.writeSelectedRows(0, -1, 60, y, a5cb);
        y -= tbl.getTotalHeight();
        y -= 10;

        PdfPTable pt = new PdfPTable(4);
        pt.setTotalWidth(new float[]{200.0F, 5f, 25f, 200.0F});
        PdfPCell pc = new PdfPCell();
        Phrase pr = new Phrase();
        pr.add(new Chunk("Nomor Polis", GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setHorizontalAlignment(Element.ALIGN_LEFT);
        pc.setBorder(0);
        pt.addCell(pc);
        pr = new Phrase();
        pr.add(new Chunk(": " + obj.getNomor_Polis(), GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setHorizontalAlignment(Element.ALIGN_LEFT);
        pc.setColspan(3);
        pc.setBorder(0);
        pt.addCell(pc);

        pc = new PdfPCell();
        pr = new Phrase();
        pr.add(new Chunk("Nama Pemegang Polis", GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setHorizontalAlignment(Element.ALIGN_LEFT);
        pc.setBorder(0);
        pt.addCell(pc);
        pr = new Phrase();
        pr.add(new Chunk(": " + obj.getNama_Pemegang_Polis(), GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setHorizontalAlignment(Element.ALIGN_LEFT);
        pc.setColspan(3);
        pc.setBorder(0);
        pt.addCell(pc);
        pt.writeSelectedRows(0, -1, 70.0F, (float) y, a5cb);
        y -= pt.getTotalHeight();
        
        pt = new PdfPTable(4);
        pt.setTotalWidth(new float[]{200.0F, 5f, 25f, 100.0F});
        pc = new PdfPCell();
        pr = new Phrase();
        pr.add(new Chunk("Total Premi", GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setHorizontalAlignment(Element.ALIGN_LEFT);
        pc.setBorder(0);
        pt.addCell(pc);
        pr = new Phrase();
        pr.add(new Chunk(":", GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setHorizontalAlignment(Element.ALIGN_LEFT);
        pc.setBorder(0);
        pt.addCell(pc);
        pr = new Phrase();
        pr.add(new Chunk("Rp.", GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setHorizontalAlignment(Element.ALIGN_LEFT);
        pc.setBorder(0);
        pt.addCell(pc);
        pr = new Phrase();
        pr.add(new Chunk(obj.getTotal_Premi(), GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setHorizontalAlignment(Element.ALIGN_RIGHT);
        pc.setBorder(0);
        pt.addCell(pc);

        pc = new PdfPCell();
        pr = new Phrase();
        pr.add(new Chunk("Hasil Investasi MGI " + obj.getPeriode_Jatuh_Tempo() + " (" + fungsi.Value.angka(Integer.parseInt(obj.getPeriode_Jatuh_Tempo())) + ") bulan", GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setHorizontalAlignment(Element.ALIGN_LEFT);
        pc.setBorder(0);
        pt.addCell(pc);
        pr = new Phrase();
        pr.add(new Chunk(":", GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setHorizontalAlignment(Element.ALIGN_LEFT);
        pc.setBorder(0);
        pt.addCell(pc);
        pr = new Phrase();
        pr.add(new Chunk("Rp.", GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setHorizontalAlignment(Element.ALIGN_LEFT);
        pc.setBorder(0);
        pt.addCell(pc);
        pr = new Phrase();
        pr.add(new Chunk(obj.getJumlah_Investasi(), GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setHorizontalAlignment(Element.ALIGN_RIGHT);
        pc.setBorder(0);
        pt.addCell(pc);

        pc = new PdfPCell();
        pr = new Phrase();
        pr.add(new Chunk("Nilai Tunai", GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setHorizontalAlignment(Element.ALIGN_LEFT);
        pc.setBorder(0);
        pt.addCell(pc);
        pr = new Phrase();
        pr.add(new Chunk(":", GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setHorizontalAlignment(Element.ALIGN_LEFT);
        pc.setBorder(0);
        pt.addCell(pc);
        pr = new Phrase();
        pr.add(new Chunk("Rp.", GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setHorizontalAlignment(Element.ALIGN_LEFT);
        pc.setBorder(0);
        pt.addCell(pc);
        pr = new Phrase();
        pr.add(new Chunk(obj.getNilai_Polis(), GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setHorizontalAlignment(Element.ALIGN_RIGHT);
        pc.setBorder(0);
        pt.addCell(pc);
        pt.writeSelectedRows(0, -1, 70.0F, (float) y, a5cb);
        y -= pt.getTotalHeight();
        y -= 10;

        pt = new PdfPTable(1);
        pt.setTotalWidth(new float[]{500f});
        pr = new Phrase();
        pc = new PdfPCell();
        pr.add(new Chunk("Agar Pengambilan Nilai Tunai Polis Bapak/Ibu dapat segera "
                + "kami proses, mohon untuk melengkapi persyaratan dokumen sebagai berikut : ", GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setBorder(0);
        pc.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
        pt.addCell(pc);
        pt.writeSelectedRows(0, -1, 60.0F, (float) y, a5cb);
        y -= pt.getTotalHeight();
        y -= 10;

        pt = new PdfPTable(2);
        pt.setTotalWidth(new float[]{20f, 400f});
        pr = new Phrase();
        pc = new PdfPCell();
        pr.add(new Chunk("1.", GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setBorder(0);
        pc.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
        pt.addCell(pc);
        pr = new Phrase();
        pc = new PdfPCell();
        pr.add(new Chunk("Polis Asli", GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setBorder(0);
        pc.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
        pt.addCell(pc);
        pr = new Phrase();
        pc = new PdfPCell();
        pr.add(new Chunk("2.", GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setBorder(0);
        pc.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
        pt.addCell(pc);
        pr = new Phrase();
        pc = new PdfPCell();
        pr.add(new Chunk("Copy identitas diri Pemegang Polis yang masih berlaku", GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setBorder(0);
        pc.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
        pt.addCell(pc);
        pr = new Phrase();
        pc = new PdfPCell();
        pr.add(new Chunk("3.", GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setBorder(0);
        pc.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
        pt.addCell(pc);
        pr = new Phrase();
        pc = new PdfPCell();
        pr.add(new Chunk("Mengisi Formulir Pengajuan Pembayaran Manfaat Polis Asuransi (terlampir)", GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setBorder(0);
        pc.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
        pt.addCell(pc);
        pt.writeSelectedRows(0, -1, 70.0F, (float) y, a5cb);
        y -= pt.getTotalHeight();
        y -= 10;

        pt = new PdfPTable(1);
        pt.setTotalWidth(new float[]{500f});
        pr = new Phrase();
        pc = new PdfPCell();
        pr.add(new Chunk("Kelengkapan dokumen tersebut di atas dapat dikirimkan ke kantor pemasaran "
                + "PT Bhinneka Life Indonesia terdekat atau ke Kantor Pusat dengan alamat :", GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setBorder(0);
        pc.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
        pt.addCell(pc);
        pt.writeSelectedRows(0, -1, 60.0F, (float) y, a5cb);
        y -= pt.getTotalHeight();
        y -= 10;

        pt = new PdfPTable(1);
        pt.setTotalWidth(new float[]{500f});
        pr = new Phrase();
        pc = new PdfPCell();
        pr.add(new Chunk(obj.getAlamat_POS().replace("\n ", "\n"), GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setBorder(0);
        pc.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
        pt.addCell(pc);
        pt.writeSelectedRows(0, -1, 70.0F, (float) y, a5cb);
        y -= pt.getTotalHeight();
        y -= 10;

        pt = new PdfPTable(1);
        pt.setTotalWidth(new float[]{500f});
        pr = new Phrase();
        pc = new PdfPCell();
        pr.add(new Chunk("Demikian kami sampaikan, jika Bapak/Ibu memiliki pertanyaan sehubungan dengan "
                + "Polis yang Bapak/Ibu miliki silahkan menghubungi Customer Care kami di nomor telepon "
                + obj.getNotlp_call_center() + ", atau melalui email : " + obj.getEmail_Call_Center() + " dengan senang hati kami akan membantu.", GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setBorder(0);
        pc.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
        pt.addCell(pc);
        pt.writeSelectedRows(0, -1, 60.0F, (float) y, a5cb);
        y -= (int) pt.getTotalHeight();
        y -= 25;

        pt = new PdfPTable(1);
        pt.setTotalWidth(new float[]{500f});
        pr = new Phrase();
        pc = new PdfPCell();
        pr.add(new Chunk("Hormat Kami,\r\n\n", GetCalibri(10)));
        pr.add(new Chunk("PT Bhinneka Life Indonesia\r\n\n\n", GetCalibri(10, 1)));
        pc.setPhrase(pr);
        pc.setBorder(0);
        pt.addCell(pc);
        pr = new Phrase();
        pc = new PdfPCell();
        pr.add(new Chunk("Surat ini dicetak secara komputerisasi, pencantuman tanda tangan tidak diperlukan", GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setBorder(0);
        pc.setHorizontalAlignment(Element.ALIGN_CENTER);
        pt.addCell(pc);
        pt.writeSelectedRows(0, -1, 60.0F, (float) y, a5cb);
        y -= pt.getTotalHeight();
        y -= 10;
        this.a5doc.newPage();
        a5cb.addTemplate(kertas, 0.0F, 0.0F);           
    }

    private void MGI2(PdfContentByte a5cb, Data obj, int y, PdfImportedPage kertas, PdfImportedPage kertasWL) throws DocumentException, ParseException {
        this.a5doc.newPage();
        a5cb.addTemplate(kertasWL, 0.0F, 0.0F);

        PdfPTable tbl = new PdfPTable(3);
        tbl.setTotalWidth(new float[]{65f, 5f, 430f});
        PdfPCell pcell = new PdfPCell();
        pcell.setPhrase(new Phrase(new Chunk("Nomor", GetCalibri(10))));
        pcell.setBorder(0);
        tbl.addCell(pcell);
        pcell.setPhrase(new Phrase(new Chunk(":", GetCalibri(10))));
        pcell.setBorder(0);
        tbl.addCell(pcell);
        pcell.setPhrase(new Phrase(new Chunk(obj.getNo_Surat(), GetCalibri(10))));
        pcell.setBorder(0);
        tbl.addCell(pcell);
        pcell.setPhrase(new Phrase(new Chunk("Perihal", GetCalibri(10))));
        pcell.setBorder(0);
        tbl.addCell(pcell);
        pcell.setPhrase(new Phrase(new Chunk(":", GetCalibri(10))));
        pcell.setBorder(0);
        tbl.addCell(pcell);
        pcell.setPhrase(new Phrase(new Chunk("Pemberitahuan Jatuh Tempo, Perpanjangan Masa Garansi Investasi & Mengambil Hasil Investasi \r\nPolis No " + obj.getNomor_Polis() + " Atas nama " + obj.getNama_Pemegang_Polis(), GetCalibri(10))));
        pcell.setBorder(0);
        pcell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
        tbl.addCell(pcell);
        tbl.writeSelectedRows(0, -1, 60, y, a5cb);
        y -= tbl.getTotalHeight();
        y -= 10;

        tbl = new PdfPTable(1);
        tbl.setTotalWidth(new float[]{250});
        pcell = new PdfPCell();
        pcell.setPhrase(new Phrase(new Chunk("Jakarta, " + obj.getTgl_Surat() + "\r\n\n", GetCalibri(10))));
        pcell.setBorder(0);
        tbl.addCell(pcell);
        pcell.setPhrase(new Phrase(new Chunk("Kepada Yth,", GetCalibri(10))));
        pcell.setBorder(0);
        tbl.addCell(pcell);
        pcell.setPhrase(new Phrase(new Chunk("Bapak/Ibu " + obj.getNama_Pemegang_Polis(), GetCalibri(10))));
        pcell.setBorder(0);
        tbl.addCell(pcell);
        pcell.setPhrase(new Phrase(new Chunk("Di Tempat", GetCalibri(10))));
        pcell.setBorder(0);
        tbl.addCell(pcell);
        tbl.writeSelectedRows(0, -1, 60, y, a5cb);
        y -= tbl.getTotalHeight();
        y -= 15;

        tbl = new PdfPTable(1);
        tbl.setTotalWidth(new float[]{500f});
        pcell = new PdfPCell();
        pcell.setPhrase(new Phrase(new Chunk("Dengan hormat,\r\n\n", GetCalibri(10))));
        pcell.setBorder(0);
        tbl.addCell(pcell);
        pcell.setPhrase(new Phrase(new Chunk("Kami mengucapkan terima kasih atas kepercayaan Bapak/Ibu kepada PT Bhinneka Life Indonesia "
                + "dalam pengelolaan perlindungan asuransi dan keuangan Bapak/Ibu beserta keluarga tercinta.\r\n\n", GetCalibri(10))));
        pcell.setBorder(0);
        pcell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
        tbl.addCell(pcell);
        pcell.setPhrase(new Phrase(new Chunk("Dengan ini kami sampaikan bahwa akhir Masa Garansi Investasi (MGI) Polis Asuransi "
                + obj.getNama_Produk() + " Bapak/Ibu akan jatuh tempo pada tanggal " + obj.getTgl_Jatuh_Tempo() + ". Berdasarkan pilihan Jatuh Tempo (MGI) yang tercantum pada Surat Pengajuan Asuransi Jiwa (SPAJ), Bapak/Ibu memilih "
                + obj.getNama_Tipe_ARO() + ", maka Polis Bapak/Ibu kami perpanjang untuk periode " + obj.getPeriode_Jatuh_Tempo() + " (" + fungsi.Value.angka(Integer.parseInt(obj.getPeriode_Jatuh_Tempo())) + ")"
                + " bulan berikutnya dengan perincian sebagai berikut :", GetCalibri(10))));
        pcell.setBorder(0);
        pcell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
        tbl.addCell(pcell);
        tbl.writeSelectedRows(0, -1, 60, y, a5cb);
        y -= tbl.getTotalHeight();
        //y -= 10;

        PdfPTable pt = new PdfPTable(4);
        pt.setTotalWidth(new float[]{200.0F, 5f, 25f, 200.0F});
        PdfPCell pc = new PdfPCell();
        Phrase pr = new Phrase();
        pr.add(new Chunk("Nomor Polis", GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setHorizontalAlignment(Element.ALIGN_LEFT);
        pc.setBorder(0);
        pt.addCell(pc);
        pr = new Phrase();
        pr.add(new Chunk(": " + obj.getNomor_Polis(), GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setHorizontalAlignment(Element.ALIGN_LEFT);
        pc.setColspan(3);
        pc.setBorder(0);
        pt.addCell(pc);

        pc = new PdfPCell();
        pr = new Phrase();
        pr.add(new Chunk("Nama Pemegang Polis", GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setHorizontalAlignment(Element.ALIGN_LEFT);
        pc.setBorder(0);
        pt.addCell(pc);
        pr = new Phrase();
        pr.add(new Chunk(": " + obj.getNama_Pemegang_Polis(), GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setHorizontalAlignment(Element.ALIGN_LEFT);
        pc.setColspan(3);
        pc.setBorder(0);
        pt.addCell(pc);
        pt.writeSelectedRows(0, -1, 70.0F, (float) y, a5cb);
        y -= pt.getTotalHeight();
        
        pt = new PdfPTable(4);
        pt.setTotalWidth(new float[]{200.0F, 5f, 25f, 100.0F});
        pc = new PdfPCell();
        pr = new Phrase();
        pr.add(new Chunk("Total Premi", GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setHorizontalAlignment(Element.ALIGN_LEFT);
        pc.setBorder(0);
        pt.addCell(pc);
        pr = new Phrase();
        pr.add(new Chunk(":", GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setHorizontalAlignment(Element.ALIGN_LEFT);
        pc.setBorder(0);
        pt.addCell(pc);
        pr = new Phrase();
        pr.add(new Chunk("Rp.", GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setHorizontalAlignment(Element.ALIGN_LEFT);
        pc.setBorder(0);
        pt.addCell(pc);
        pr = new Phrase();
        pr.add(new Chunk(obj.getTotal_Premi(), GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setHorizontalAlignment(Element.ALIGN_RIGHT);
        pc.setBorder(0);
        pt.addCell(pc);
        
        pc = new PdfPCell();
        pr = new Phrase();
        pr.add(new Chunk("Tanggal Mulai MGI", GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setHorizontalAlignment(Element.ALIGN_LEFT);
        pc.setBorder(0);
        pt.addCell(pc);
        pr = new Phrase();
        pr.add(new Chunk(": " + obj.getTanggal_Mulai_berikutnya(), GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setHorizontalAlignment(Element.ALIGN_LEFT);
        pc.setBorder(0);
        pc.setColspan(3);
        pt.addCell(pc);

        pc = new PdfPCell();
        pr = new Phrase();
        pr.add(new Chunk("Tingkat Target Investasi ", GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setHorizontalAlignment(Element.ALIGN_LEFT);
        pc.setBorder(0);
        pt.addCell(pc);
        pr = new Phrase();
        pr.add(new Chunk(": " + obj.getTingkat_Target_Investasi() + "% per tahun net", GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setHorizontalAlignment(Element.ALIGN_LEFT);
        pc.setBorder(0);
        pc.setColspan(3);
        pt.addCell(pc);
        pt.writeSelectedRows(0, -1, 70.0F, (float) y, a5cb);
        y -= pt.getTotalHeight();
        y -= 10;

        pt = new PdfPTable(1);
        pt.setTotalWidth(new float[]{500f});
        pr = new Phrase();
        pc = new PdfPCell();
        pr.add(new Chunk("Sebagai informasi hasil investasi atas Polis Bapak/Ibu "
                + "akan kami transfer ke rekening yang tercantum dalam SPAJ sebagai berikut :", GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setBorder(0);
        pc.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
        pt.addCell(pc);
        pt.writeSelectedRows(0, -1, 60.0F, (float) y, a5cb);
        y -= pt.getTotalHeight();

        pt = new PdfPTable(4);
        pt.setTotalWidth(new float[]{200.0F, 5f, 25f, 200.0F});
        pc = new PdfPCell();
        pr = new Phrase();
        pr.add(new Chunk("Nama Pemilik Rekening", GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setHorizontalAlignment(Element.ALIGN_LEFT);
        pc.setBorder(0);
        pt.addCell(pc);
        pr = new Phrase();
        pr.add(new Chunk(": " + obj.getNama_pemilik_rekening(), GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setHorizontalAlignment(Element.ALIGN_LEFT);
        pc.setColspan(3);
        pc.setBorder(0);
        pt.addCell(pc);

        pc = new PdfPCell();
        pr = new Phrase();
        pr.add(new Chunk("No. Rekening", GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setHorizontalAlignment(Element.ALIGN_LEFT);
        pc.setBorder(0);
        pt.addCell(pc);
        pr = new Phrase();
        pr.add(new Chunk(": " + obj.getNomor_rekening(), GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setHorizontalAlignment(Element.ALIGN_LEFT);
        pc.setColspan(3);
        pc.setBorder(0);
        pt.addCell(pc);

        pc = new PdfPCell();
        pr = new Phrase();
        pr.add(new Chunk("Nama Bank dan Cabang", GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setHorizontalAlignment(Element.ALIGN_LEFT);
        pc.setBorder(0);
        pt.addCell(pc);
        pr = new Phrase();
        pr.add(new Chunk(": " + obj.getNama_Bank() + " " + obj.getNama_Bank_Cabang(), GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setHorizontalAlignment(Element.ALIGN_LEFT);
        pc.setBorder(0);
        pc.setColspan(3);
        pt.addCell(pc);
        pt.writeSelectedRows(0, -1, 70.0F, (float) y, a5cb);
        y -= pt.getTotalHeight();
        
        pt = new PdfPTable(4);
        pt.setTotalWidth(new float[]{200.0F, 5f, 25f, 100.0F});
        pc = new PdfPCell();
        pr = new Phrase();
        pr.add(new Chunk("Nominal", GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setHorizontalAlignment(Element.ALIGN_LEFT);
        pc.setBorder(0);
        pt.addCell(pc);
        pr = new Phrase();
        pr.add(new Chunk(":", GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setHorizontalAlignment(Element.ALIGN_LEFT);
        pc.setBorder(0);
        pt.addCell(pc);
        pr = new Phrase();
        pr.add(new Chunk("Rp.", GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setHorizontalAlignment(Element.ALIGN_LEFT);
        pc.setBorder(0);
        pt.addCell(pc);
        pr = new Phrase();
        pr.add(new Chunk(obj.getJumlah_Investasi(), GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setHorizontalAlignment(Element.ALIGN_RIGHT);
        pc.setBorder(0);
        pt.addCell(pc);
        pt.writeSelectedRows(0, -1, 70.0F, (float) y, a5cb);
        y -= pt.getTotalHeight();
        y -= 10;

        pt = new PdfPTable(1);
        pt.setTotalWidth(new float[]{500f});
        pr = new Phrase();
        pc = new PdfPCell();
        pr.add(new Chunk("Untuk memastikan korespondensi berjalan dengan baik, mohon Bapak/Ibu dapat "
                + "selalu melakukan pengkinian data atas alamat korespondensi, email dan nomor telepon "
                + "(rumah, kantor dan HP) apabila ada perubahan dan menginformasikannya melalui email ke "
                + obj.getEmail_Call_Center() + " atau whatsapp ke nomor " + obj.getNoWA_call_center() + "."
                + "\r\n\n", GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setBorder(0);
        pc.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
        pt.addCell(pc);
        pr = new Phrase();
        pc = new PdfPCell();
        pr.add(new Chunk("Demikian kami sampaikan, jika Bapak/Ibu memiliki pertanyaan sehubungan dengan "
                + "Polis yang Bapak/Ibu miliki silahkan menghubungi Customer Care kami di nomor telepon "
                + obj.getNotlp_call_center() + ", atau melalui email : " + obj.getEmail_Call_Center() + " dengan senang hati kami akan membantu.", GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setBorder(0);
        pc.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
        pt.addCell(pc);
        pt.writeSelectedRows(0, -1, 60.0F, (float) y, a5cb);
        y -= (int) pt.getTotalHeight();
        y -= 25;

        pt = new PdfPTable(1);
        pt.setTotalWidth(new float[]{500f});
        pr = new Phrase();
        pc = new PdfPCell();
        pr.add(new Chunk("Hormat Kami,\r\n\n", GetCalibri(10)));
        pr.add(new Chunk("PT Bhinneka Life Indonesia\r\n\n\n", GetCalibri(10, 1)));
        pc.setPhrase(pr);
        pc.setBorder(0);
        pt.addCell(pc);
        pr = new Phrase();
        pc = new PdfPCell();
        pr.add(new Chunk("Surat ini dicetak secara komputerisasi, pencantuman tanda tangan tidak diperlukan", GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setBorder(0);
        pc.setHorizontalAlignment(Element.ALIGN_CENTER);
        pt.addCell(pc);
        pt.writeSelectedRows(0, -1, 60.0F, (float) y, a5cb);
        y -= pt.getTotalHeight();
        y -= 10;
    }

    private void MGI1(PdfContentByte a5cb, Data obj, int y, PdfImportedPage kertas, PdfImportedPage kertasWL) throws DocumentException, ParseException {
        this.a5doc.newPage();
        a5cb.addTemplate(kertasWL, 0.0F, 0.0F);

        PdfPTable tbl = new PdfPTable(3);
        tbl.setTotalWidth(new float[]{80f, 5f, 350f});
        PdfPCell pcell = new PdfPCell();
        pcell.setPhrase(new Phrase(new Chunk("Nomor", GetCalibri(10))));
        pcell.setBorder(0);
        tbl.addCell(pcell);
        pcell.setPhrase(new Phrase(new Chunk(":", GetCalibri(10))));
        pcell.setBorder(0);
        tbl.addCell(pcell);
        pcell.setPhrase(new Phrase(new Chunk(obj.getNo_Surat(), GetCalibri(10))));
        pcell.setBorder(0);
        tbl.addCell(pcell);
        pcell.setPhrase(new Phrase(new Chunk("Perihal", GetCalibri(10))));
        pcell.setBorder(0);
        tbl.addCell(pcell);
        pcell.setPhrase(new Phrase(new Chunk(":", GetCalibri(10))));
        pcell.setBorder(0);
        tbl.addCell(pcell);
        pcell.setPhrase(new Phrase(new Chunk("Pemberitahuan Jatuh Tempo Perpanjangan Masa Garansi Investasi & Nilai Investasi \r\nPolis No " + obj.getNomor_Polis() + " Atas nama " + obj.getNama_Pemegang_Polis(), GetCalibri(10))));
        pcell.setBorder(0);
        pcell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
        tbl.addCell(pcell);
        tbl.writeSelectedRows(0, -1, 60, y, a5cb);
        y -= tbl.getTotalHeight();
        y -= 10;

        tbl = new PdfPTable(1);
        tbl.setTotalWidth(new float[]{250});
        pcell = new PdfPCell();
        pcell.setPhrase(new Phrase(new Chunk("Jakarta, " + obj.getTgl_Surat() + "\r\n\n", GetCalibri(10))));
        pcell.setBorder(0);
        tbl.addCell(pcell);
        pcell.setPhrase(new Phrase(new Chunk("Kepada Yth,", GetCalibri(10))));
        pcell.setBorder(0);
        tbl.addCell(pcell);
        pcell.setPhrase(new Phrase(new Chunk("Bapak/Ibu " + obj.getNama_Pemegang_Polis(), GetCalibri(10))));
        pcell.setBorder(0);
        tbl.addCell(pcell);
        pcell.setPhrase(new Phrase(new Chunk("Di Tempat", GetCalibri(10))));
        pcell.setBorder(0);
        tbl.addCell(pcell);
        tbl.writeSelectedRows(0, -1, 60, y, a5cb);
        y -= tbl.getTotalHeight();
        y -= 15;

        tbl = new PdfPTable(1);
        tbl.setTotalWidth(new float[]{500f});
        pcell = new PdfPCell();
        pcell.setPhrase(new Phrase(new Chunk("Dengan hormat,\r\n\n", GetCalibri(10))));
        pcell.setBorder(0);
        tbl.addCell(pcell);
        pcell.setPhrase(new Phrase(new Chunk("Kami mengucapkan terima kasih atas kepercayaan Bapak/Ibu kepada PT Bhinneka Life Indonesia dalam pengelolaan perlindungan asuransi dan keuangan Bapak/Ibu beserta keluarga tercinta.\r\n\n", GetCalibri(10))));
        pcell.setBorder(0);
        pcell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
        tbl.addCell(pcell);
        pcell.setPhrase(new Phrase(new Chunk("Dengan ini kami sampaikan bahwa akhir Masa Garansi Investasi (MGI) Polis Asuransi "
                + obj.getNama_Produk() + " Bapak/Ibu akan jatuh tempo pada tanggal " + obj.getTgl_Jatuh_Tempo() + ". Berdasarkan pilihan Jatuh Tempo (MGI) yang tercantum di Surat Pengajuan Asuransi Jiwa (SPAJ), Bapak/Ibu memilih "
                + obj.getNama_Tipe_ARO() + ", maka Polis Bapak/Ibu kami perpanjang untuk periode " + obj.getPeriode_Jatuh_Tempo() + " (" + fungsi.Value.angka(Integer.parseInt(obj.getPeriode_Jatuh_Tempo())) + ")"
                + " bulan berikutnya dengan perincian sebagai berikut :", GetCalibri(10))));
        pcell.setBorder(0);
        pcell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
        tbl.addCell(pcell);
        tbl.writeSelectedRows(0, -1, 60, y, a5cb);
        y -= tbl.getTotalHeight();
        y -= 10;

        PdfPTable pt = new PdfPTable(4);
        pt.setTotalWidth(new float[]{200.0F, 5f, 25f, 200.0F});
        PdfPCell pc = new PdfPCell();
        Phrase pr = new Phrase();
        pr.add(new Chunk("Nomor Polis", GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setHorizontalAlignment(Element.ALIGN_LEFT);
        pc.setBorder(0);
        pt.addCell(pc);
        pr = new Phrase();
        pr.add(new Chunk(": " + obj.getNomor_Polis(), GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setHorizontalAlignment(Element.ALIGN_LEFT);
        pc.setColspan(3);
        pc.setBorder(0);
        pt.addCell(pc);

        pc = new PdfPCell();
        pr = new Phrase();
        pr.add(new Chunk("Nama Pemegang Polis", GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setHorizontalAlignment(Element.ALIGN_LEFT);
        pc.setBorder(0);
        pt.addCell(pc);
        pr = new Phrase();
        pr.add(new Chunk(": " + obj.getNama_Pemegang_Polis(), GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setHorizontalAlignment(Element.ALIGN_LEFT);
        pc.setColspan(3);
        pc.setBorder(0);
        pt.addCell(pc);
        pt.writeSelectedRows(0, -1, 70.0F, (float) y, a5cb);
        y -= pt.getTotalHeight();
        
        pt = new PdfPTable(4);
        pt.setTotalWidth(new float[]{200.0F, 5f, 25f, 100.0F});        
        pc = new PdfPCell();
        pr = new Phrase();
        pr.add(new Chunk("Total Premi", GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setHorizontalAlignment(Element.ALIGN_LEFT);
        pc.setBorder(0);
        pt.addCell(pc);
        pr = new Phrase();
        pr.add(new Chunk(":", GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setHorizontalAlignment(Element.ALIGN_LEFT);
        pc.setBorder(0);
        pt.addCell(pc);
        pr = new Phrase();
        pr.add(new Chunk("Rp.", GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setHorizontalAlignment(Element.ALIGN_LEFT);
        pc.setBorder(0);
        pt.addCell(pc);
        pr = new Phrase();
        pr.add(new Chunk(obj.getTotal_Premi(), GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setHorizontalAlignment(Element.ALIGN_RIGHT);
        pc.setBorder(0);
        pt.addCell(pc);

        pc = new PdfPCell();
        pr = new Phrase();
        pr.add(new Chunk("Hasil Investasi MGI " + obj.getPeriode_Jatuh_Tempo() + " (" + fungsi.Value.angka(Integer.parseInt(obj.getPeriode_Jatuh_Tempo())) + ") bulan", GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setHorizontalAlignment(Element.ALIGN_LEFT);
        pc.setBorder(0);
        pt.addCell(pc);
        pr = new Phrase();
        pr.add(new Chunk(":", GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setHorizontalAlignment(Element.ALIGN_LEFT);
        pc.setBorder(0);
        pt.addCell(pc);
        pr = new Phrase();
        pr.add(new Chunk("Rp.", GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setHorizontalAlignment(Element.ALIGN_LEFT);
        pc.setBorder(0);
        pt.addCell(pc);
        pr = new Phrase();
        pr.add(new Chunk(obj.getJumlah_Investasi(), GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setHorizontalAlignment(Element.ALIGN_RIGHT);
        pc.setBorder(0);
        pt.addCell(pc);

        pc = new PdfPCell();
        pr = new Phrase();
        pr.add(new Chunk("Nilai Tunai Diinvestasikan Kembali", GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setHorizontalAlignment(Element.ALIGN_LEFT);
        pc.setBorder(0);
        pt.addCell(pc);
        pr = new Phrase();
        pr.add(new Chunk(":", GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setHorizontalAlignment(Element.ALIGN_LEFT);
        pc.setBorder(0);
        pt.addCell(pc);
        pr = new Phrase();
        pr.add(new Chunk("Rp.", GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setHorizontalAlignment(Element.ALIGN_LEFT);
        pc.setBorder(0);
        pt.addCell(pc);
        pr = new Phrase();
        pr.add(new Chunk(obj.getNilai_Polis(), GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setHorizontalAlignment(Element.ALIGN_RIGHT);
        pc.setBorder(0);
        pt.addCell(pc);

        pc = new PdfPCell();
        pr = new Phrase();
        pr.add(new Chunk("Tanggal Mulai MGI", GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setHorizontalAlignment(Element.ALIGN_LEFT);
        pc.setBorder(0);
        pt.addCell(pc);
        pr = new Phrase();
        pr.add(new Chunk(": " + obj.getTanggal_Mulai_berikutnya(), GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setHorizontalAlignment(Element.ALIGN_LEFT);
        pc.setBorder(0);
        pc.setColspan(3);
        pt.addCell(pc);

        pc = new PdfPCell();
        pr = new Phrase();
        pr.add(new Chunk("Tingkat Target Investasi ", GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setHorizontalAlignment(Element.ALIGN_LEFT);
        pc.setBorder(0);
        pt.addCell(pc);
        pr = new Phrase();
        pr.add(new Chunk(": " + obj.getTingkat_Target_Investasi() + "% per tahun net", GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setHorizontalAlignment(Element.ALIGN_LEFT);
        pc.setBorder(0);
        pc.setColspan(3);
        pt.addCell(pc);

        pt.writeSelectedRows(0, -1, 70.0F, (float) y, a5cb);
        y -= pt.getTotalHeight();
        y -= 10;

        pt = new PdfPTable(1);
        pt.setTotalWidth(new float[]{500f});
        pr = new Phrase();
        pc = new PdfPCell();
        pr.add(new Chunk("Untuk memastikan korespondensi berjalan dengan baik, mohon Bapak/Ibu dapat "
                + "selalu melakukan pengkinian data atas alamat korespondensi, email dan nomor telepon "
                + "(rumah, kantor dan HP) apabila ada perubahan dan menginformasikannya melalui email ke "
                + obj.getEmail_Call_Center() + " atau whatsapp ke nomor " + obj.getNoWA_call_center() + "."
                + "\r\n\n", GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setBorder(0);
        pc.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
        pt.addCell(pc);
        pr = new Phrase();
        pc = new PdfPCell();
        pr.add(new Chunk("Demikian kami sampaikan, jika Bapak/Ibu memiliki pertanyaan sehubungan dengan "
                + "Polis yang Bapak/Ibu miliki silahkan menghubungi Customer Care kami di nomor telepon "
                + obj.getNotlp_call_center() + ", atau melalui email : " + obj.getEmail_Call_Center() + " dengan senang hati kami akan membantu.", GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setBorder(0);
        pc.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
        pt.addCell(pc);
        pt.writeSelectedRows(0, -1, 60.0F, (float) y, a5cb);
        y -= (int) pt.getTotalHeight();
        y -= 25;

        pt = new PdfPTable(1);
        pt.setTotalWidth(new float[]{500f});
        pr = new Phrase();
        pc = new PdfPCell();
        pr.add(new Chunk("Hormat Kami,\r\n\n", GetCalibri(10)));
        pr.add(new Chunk("PT Bhinneka Life Indonesia\r\n\n\n", GetCalibri(10, 1)));
        pc.setPhrase(pr);
        pc.setBorder(0);
        pt.addCell(pc);
        pr = new Phrase();
        pc = new PdfPCell();
        pr.add(new Chunk("Surat ini dicetak secara komputerisasi, pencantuman tanda tangan tidak diperlukan", GetCalibri(10)));
        pc.setPhrase(pr);
        pc.setBorder(0);
        pc.setHorizontalAlignment(Element.ALIGN_CENTER);
        pt.addCell(pc);
        pt.writeSelectedRows(0, -1, 60.0F, (float) y, a5cb);
        y -= pt.getTotalHeight();
        y -= 10;
    }

    public void selesai() {
        try {
            this.a5doc.close();
            this.kertas.close();
            //this.tante.close();
            this.kertasWL.close();
        } catch (Exception var2) {
            System.out.print(var2);
        }

    }
}
