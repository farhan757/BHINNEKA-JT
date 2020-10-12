package fungsi;

import com.itextpdf.awt.geom.AffineTransform;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BadPdfFormatException;
import com.itextpdf.text.pdf.BarcodeQRCode;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.VerticalText;
import java.awt.Component;
import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import propert.Data;

public class PDFManipul {

    Component parentComponent;
    Document a5doc = new Document(PageSize.A4, 54.0F, 54.0F, 100.0F, 100.0F);
    Document a4doc = new Document();
    PdfCopy pdfCopy;
    PdfWriter a5writer;
    String FilePdf;
    PdfStamper pdfStamp;

    int pageall = 0;
    String cycle;
    PdfReader kertas;
    PdfReader kertasWL;
    PdfReader ketum;
    PdfReader ketus;
    PdfReader SKP;
    PdfReader tante;
    PdfReader ringpol;
    PdfReader brks;

    PdfCopy copy;

    Date date = new Date();
    Locale lokal = new Locale("id", "ID");
    SimpleDateFormat ft = new SimpleDateFormat("dd MMMM yyyy", lokal);

    public void setCycle(String dt) {
        cycle = dt;
    }

    public void PDFStream(String Pdf) {
        FilePdf = Pdf;
    }

    public void QRCODE(PdfContentByte a5cb, String text, float x, float y) throws BadElementException {
        PdfPTable pt = new PdfPTable(1);
        pt.setTotalWidth(150);
        pt.setLockedWidth(true);
        BarcodeQRCode barcodeQRCode = new BarcodeQRCode(text, 1000, 1000, null);
        Image codeQrImage = barcodeQRCode.getImage();
        codeQrImage.scaleAbsolute(50, 50);
        PdfPCell pc = new PdfPCell(codeQrImage);
        pc.setBorder(0);
        pt.addCell(pc);
        pt.writeSelectedRows(0, -1, x, (float) y, a5cb);
    }

    public int berkas(String fold, String nopol, HashMap<String, propert.Log> Log_bhineka, String Kode_produk) throws IOException, BadPdfFormatException {
        Unzip xtr = new Unzip();
        xtr.unzipz(fold + ".zip", fold);
        File dir = new File(fold);
        File pdf = new File(dir + "//" + dir.getName());
        File b2 = new File(pdf + "_B00002.pdf");
        File b7 = new File(pdf + "_B00007.pdf");
        int totalP = 0;
        if (b2.exists()) {
            brks = new PdfReader(b2.toString());
            //brks.get
            totalP = totalP + brks.getNumberOfPages();
            for (int i = 1; i <= brks.getNumberOfPages(); i++) {
                PdfImportedPage page = pdfCopy.getImportedPage(brks, i);
                PdfContentByte a5cb = pdfCopy.getDirectContent();
                pdfCopy.addPage(page);
                //a5doc.newPage();
                //a5cb.addTemplate(page, a5doc.getPageSize().getWidth() / brks.getPageSize(i).getWidth(), 0.0F, 0.0F, a5doc.getPageSize().getHeight() / brks.getPageSize(i).getHeight(), 15.0F, 0.0F);
            }
            Log_bhineka.get(nopol).setSPAJ("SPAJ OK");
        } else {
            Log_bhineka.get(nopol).setSPAJ("SPAJ GAGAL");
            System.out.print(b2.toPath() + " Not Found");
            //JOptionPane.showMessageDialog(parentComponent, b2.toPath() + " Not Found");
        }

        if (!Kode_produk.equals("ED016")) {
            if (b7.exists()) {
                brks = new PdfReader(b7.toString());
                totalP = totalP + brks.getNumberOfPages();
                for (int i = 1; i <= brks.getNumberOfPages(); i++) {

                    PdfImportedPage page = pdfCopy.getImportedPage(brks, i);
                    PdfContentByte a5cb = pdfCopy.getDirectContent();
                    pdfCopy.addPage(page);
                    //a5cb.addTemplate(page, 0.0F, 0.0F);
                    //a5doc.newPage();
                    //a5cb.addTemplate(page, a5doc.getPageSize().getWidth() / brks.getPageSize(i).getWidth(), 0.0F, 0.0F, a5doc.getPageSize().getHeight() / brks.getPageSize(i).getHeight(), 15.0F, 0.0F);
                }
                Log_bhineka.get(nopol).setIlustrasi("ILLUSTRASI OK");
            } else {
                Log_bhineka.get(nopol).setIlustrasi("ILLUSTRASI GAGAL");
                System.out.print(b7.toPath() + " Not Found");
                //JOptionPane.showMessageDialog(parentComponent, b7.toPath() + " Not Found");
            }
        } else {
            Log_bhineka.get(nopol).setIlustrasi("ILLUSTRASI GAGAL");
        }

        return totalP;
    }

    protected void rotate(PdfImportedPage page, int rota, Rectangle pagesize, PdfContentByte cb) {

        a5doc.setPageSize(pagesize);
        a5writer.setPageSize(pagesize);
        cb = a5writer.getDirectContent();
        a5doc.newPage();
        float oWidth = 595;
        float oHeight = 842;
        float scale = getScale(oWidth, oHeight);
        float scaledWidth = oWidth * scale;
        float scaledHeight = oHeight * scale;
        int rotation = rota;

        AffineTransform transform = new AffineTransform(scale, 0, 0, scale, 0, 0);
        switch (rotation) {
            case 0:
                cb.addTemplate(page, transform);
                break;
            case 90:
                AffineTransform rotate90 = new AffineTransform(0, -1f, 1f, 0, 0, pagesize.getHeight());
                rotate90.concatenate(transform);
                cb.addTemplate(page, rotate90);
                break;
            case 180:
                AffineTransform rotate180 = new AffineTransform(-1f, 0, 0, -1f, pagesize.getWidth(),
                        pagesize.getHeight());
                rotate180.concatenate(transform);
                cb.addTemplate(page, rotate180);
                break;
            case 270:
                AffineTransform rotate270 = new AffineTransform(0, 1f, -1f, 0, pagesize.getWidth(), 0);
                rotate270.concatenate(transform);
                cb.addTemplate(page, rotate270);
                break;
            default:
                cb.addTemplate(page, scale, 0, 0, scale, 0, 0);
        }
    }

    protected static float getScale(float width, float height) {
        float scaleX = PageSize.A4.getWidth() / width;
        float scaleY = PageSize.A4.getHeight() / height;
        return Math.min(scaleX, scaleY);
    }

    public int tante(PdfContentByte a5cb, Data obj, String F_tante) throws IOException, JSONException, DocumentException {
        String NOPOL = obj.getNomor_Polis();
        String NAMA_PEMEGANG = obj.getNama_Pemegang_Polis();
        tante = new PdfReader(F_tante);
        int totalP = 0;
        totalP = tante.getNumberOfPages();
        a5doc.newPage();
        PdfImportedPage page = a5writer.getImportedPage(tante, 1);
        a5cb.addTemplate(page, 0.0F, 0.0F);

        PdfPTable pt = new PdfPTable(1);
        pt.setTotalWidth(new float[]{340.0F});

        PdfPCell pc = new PdfPCell();
        pc.setBorder(0);
        pc.setPhrase(new Phrase(new Chunk(NOPOL, GetArial(11.0F))));
        pc.setHorizontalAlignment(0);
        pt.addCell(pc);

        pt.writeSelectedRows(0, -1, 184.0F, 699.0F, a5cb);

        pt = new PdfPTable(1);
        pt.setTotalWidth(new float[]{340.0F});

        pc = new PdfPCell();
        pc.setBorder(0);
        pc.setPhrase(new Phrase(new Chunk(NAMA_PEMEGANG, GetArial(11.0F))));
        pc.setHorizontalAlignment(0);
        pt.addCell(pc);

        pt.writeSelectedRows(0, -1, 184.0F, 687.0F, a5cb);

        return totalP;
    }

    public int ketum(String fileKTUM, PdfImportedPage kertas) throws IOException {
        ketum = new PdfReader(fileKTUM);
        int totalP = ketum.getNumberOfPages();
        for (int i = 1; i <= ketum.getNumberOfPages(); i++) {
            a5doc.newPage();
            PdfImportedPage page = a5writer.getImportedPage(ketum, i);
            PdfContentByte a5cb = a5writer.getDirectContent();
            a5cb.addTemplate(kertas, 0.0F, 0.0F);
            a5cb.addTemplate(page, a5doc.getPageSize().getWidth() / ketum.getPageSize(i).getWidth(), 0.0F, 0.0F, a5doc.getPageSize().getHeight() / ketum.getPageSize(i).getHeight(), 15.0F, 0.0F);
        }
        return totalP;
    }

    public int ketus(String fileKTUS, PdfImportedPage kertas) throws IOException {
        ketus = new PdfReader(fileKTUS);
        int totalP = ketus.getNumberOfPages();
        for (int i = 1; i <= ketus.getNumberOfPages(); i++) {
            a5doc.newPage();
            PdfImportedPage page = a5writer.getImportedPage(ketus, i);
            PdfContentByte a5cb = a5writer.getDirectContent();
            a5cb.addTemplate(kertas, 0.0F, 0.0F);
            a5cb.addTemplate(page, a5doc.getPageSize().getWidth() / ketus.getPageSize(i).getWidth(), 0.0F, 0.0F, a5doc.getPageSize().getHeight() / ketus.getPageSize(i).getHeight(), 15.0F, 0.0F);
        }
        return totalP;
    }

    public int SKP(String fileSKP, PdfImportedPage kertas) throws IOException {
        SKP = new PdfReader(fileSKP);
        int totalP = SKP.getNumberOfPages();
        for (int i = 1; i <= SKP.getNumberOfPages(); i++) {
            a5doc.newPage();
            PdfImportedPage page = a5writer.getImportedPage(SKP, i);
            PdfContentByte a5cb = a5writer.getDirectContent();
            a5cb.addTemplate(kertas, 0.0F, 0.0F);
            a5cb.addTemplate(page, a5doc.getPageSize().getWidth() / SKP.getPageSize(i).getWidth(), 0.0F, 0.0F, a5doc.getPageSize().getHeight() / SKP.getPageSize(i).getHeight(), 15.0F, 0.0F);
        }
        return totalP;
    }

    public void addFooterPolis(PdfContentByte a5cb, String KODE_KPPT, String KODE_CABANG)
            throws DocumentException {
        PdfPTable pt = new PdfPTable(1);
        pt.setTotalWidth(new float[]{500.0F});

        String KPPT = KODE_KPPT.substring(0, KODE_KPPT.indexOf("/") + 1) + KODE_CABANG + KODE_KPPT.substring(KODE_KPPT.indexOf("/"));
        PdfPCell pc = new PdfPCell();
        pc.setBorder(0);
        pc.setPhrase(new Phrase(new Chunk(KPPT, GetArial(7.0F, 1))));
        pc.setHorizontalAlignment(0);
        pt.addCell(pc);

        pt.writeSelectedRows(0, -1, 60.0F, 35.0F, a5cb);
    }

    public void addTextUL(PdfContentByte cb, String text, int x, int y, int hg, int maxline, float lead, int size) {
        VerticalText vt = new VerticalText(cb);
        vt.setVerticalLayout(x, y, hg, maxline, lead);
        Chunk chunk = new Chunk(text, GetArial(size, 4));
        chunk.setUnderline(2.0F, -3.0F);
        vt.addText(chunk);
        vt.go();
    }

    public void addText(PdfContentByte cb, String text, int x, int y, int hg, int maxline, float lead, int style, int size) {
        VerticalText vt = new VerticalText(cb);
        vt.setVerticalLayout(x, y, hg, maxline, lead);
        vt.addText(new Chunk(text, GetCalibri(size, style)));
        vt.go();
    }

    public void addTextWhite(PdfContentByte cb, String text, int x, int y, int hg, int maxline, float lead, int style, int size) {
        Font font = GetArial(size, style);
        font.setColor(BaseColor.WHITE);
        VerticalText vt = new VerticalText(cb);
        vt.setVerticalLayout(x, y, hg, maxline, lead);
        vt.addText(new Chunk(text, font));
        vt.go();
    }

    public void addBarcode(PdfContentByte cb, String text, int x, int y, int hg, int maxline, float lead) {
        VerticalText vt = new VerticalText(cb);
        vt.setVerticalLayout(x, y, hg, maxline, lead);
        vt.addText(new Chunk(text, GetFBarcode(14.0F)));
        vt.go();
    }

    static Font GetCalibri(float size) {
        String fontname = "Calibri";
        if (!FontFactory.isRegistered(fontname)) {
            Path fontPath = Paths.get(Value.FONT_LOCATION + "/calibri.ttf", new String[0]);
            FontFactory.register(String.valueOf(fontPath));
        }
        return FontFactory.getFont(fontname, "Identity-H", true, size, 0);
    }

    static Font GetCalibri(float size, int style) {
        String fontname = "Calibri";
        if (!FontFactory.isRegistered(fontname)) {
            URL font_path = Thread.currentThread().getContextClassLoader().getResource(Value.LOCAL_LOCATION + "/fonts/calibri.ttf");

            FontFactory.register(String.valueOf(font_path));
        }
        return FontFactory.getFont(fontname, "Identity-H", true, size, style);
    }

    static Font GetArial(float size) {
        Font font = FontFactory.getFont(Value.FONT_LOCATION + "/times.ttf", "Identity-H", true, size, 0);
        BaseFont baseFont = font.getBaseFont();

        return font;
    }

    static Font GetArial(float size, int style) {
        Font font = FontFactory.getFont(Value.FONT_LOCATION + "/times.ttf", "Identity-H", true, size, style);
        BaseFont baseFont = font.getBaseFont();

        return font;
    }

    static Font GetFBarcode(float size) {
        Font font = FontFactory.getFont(Value.FONT_LOCATION + "/C39P36DlTt.ttf", "Identity-H", true, size);
        BaseFont baseFont = font.getBaseFont();

        return font;
    }

    public static void ScalePdfToA4(String inPdf, String outPdf) throws DocumentException {
        try {
            Document a5doc = new Document(PageSize.A5);
            PdfReader read = new PdfReader(inPdf);
            PdfWriter writer = null;
            try {
                writer = PdfWriter.getInstance(a5doc, new FileOutputStream(outPdf));
            } catch (DocumentException ex) {
                Logger.getLogger(PDFManipul.class.getName()).log(Level.SEVERE, null, ex);
            }

            a5doc.open();
            for (int i = 1; i <= read.getNumberOfPages(); i++) {
                PdfContentByte direct = writer.getDirectContent();
                PdfImportedPage page = writer.getImportedPage(read, i);
                direct.addTemplate(page,
                        AffineTransform.getScaleInstance(a5doc.getPageSize().getWidth() / read.getPageSize(1).getWidth(), a5doc
                                .getPageSize().getHeight() / read.getPageSize(i).getHeight()));
                a5doc.newPage();
            }
            a5doc.close();
            read.close();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    public static void Password(String inPdf, String out, String user, String owner) throws IOException, DocumentException {
        PdfReader reader = new PdfReader(inPdf);
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(out));
        stamper.setEncryption(user.getBytes(), owner.getBytes(), 2052, 10);

        stamper.close();
        reader.close();
    }

    public static String getSesuatu(JSONArray objar, String key, String get, String srch) {
        String Sesuatu = "";

        for (int i = 0; i < objar.length(); i++) {
            JSONObject obj = objar.getJSONObject(i);
            //obj.getString(key)
            if (obj.getString(key).toUpperCase().trim().equals(srch.trim().toUpperCase())) {
                Sesuatu = obj.getString(get);
                break;
            }
        }

        return Sesuatu;
    }
}
