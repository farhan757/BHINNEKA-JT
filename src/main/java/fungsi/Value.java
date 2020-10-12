/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fungsi;

import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.pdf.BaseFont;
import java.math.BigDecimal;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 *
 * @author someone
 */
public class Value {

    public static String FONT_LOCATION = "Local/Fonts";
    public static String LOCAL_LOCATION = "Local";

    public static String angka(int satuan) {

        String[] huruf = {"", "Satu", "Dua", "Tiga", "Empat", "Lima", "Enam", "Tujuh", "Delapan", "Sembilan", "Sepuluh", "Sebelas"};
        String hasil = "";
        if (satuan < 12) {
            hasil = hasil + huruf[satuan];
        } else if (satuan < 20) {
            hasil = hasil + angka(satuan - 10) + " Belas";
        } else if (satuan < 100) {
            hasil = hasil + angka(satuan / 10) + " Puluh " + angka(satuan % 10);
        } else if (satuan < 200) {
            hasil = hasil + "Seratus " + angka(satuan - 100);
        } else if (satuan < 1000) {
            hasil = hasil + angka(satuan / 100) + " Ratus " + angka(satuan % 100);
        } else if (satuan < 2000) {
            hasil = hasil + "Seribu " + angka(satuan - 1000);
        } else if (satuan < 1000000) {
            hasil = hasil + angka(satuan / 1000) + " Ribu " + angka(satuan % 1000);
        } else if (satuan < 1000000000) {
            hasil = hasil + angka(satuan / 1000000) + " Juta " + angka(satuan % 1000000);
        } else if (satuan >= 1000000000) {
            hasil = "Angka terlalu besar, harus kurang dari 1 milyar!";
        }
        return hasil;
    }

    public static String toRoman(int num) {
        String[] romanCharacters = {"M", "CM", "D", "C", "XC", "L", "X", "IX", "V", "I"};
        int[] romanValues = {1000, 900, 500, 100, 90, 50, 10, 9, 5, 1};
        String result = "";

        for (int i = 0; i < romanValues.length; i++) {
            int numberInPlace = num / romanValues[i];
            if (numberInPlace == 0) {
                continue;
            }
            result += numberInPlace == 4 && i > 0 ? romanCharacters[i] + romanCharacters[i - 1]
                    : new String(new char[numberInPlace]).replace("\0", romanCharacters[i]);
            num = num % romanValues[i];
        }
        return result;
    }

    public static String FormatRupiah(double value) {
        DecimalFormat tes = new DecimalFormat("###,###,###,###");
        return tes.format(value).replaceAll(",", ".");
    }

    public static String TotalPremi(String dasar, String berkala, String tunggal) throws ParseException {
        DecimalFormat formatter = new DecimalFormat("#.##");

        dasar = dasar.replace(".", "").replace(",", ".").trim();
        if (!berkala.trim().equals("")) {
            berkala = berkala.replace(".", "").replace(",", ".").trim();
        } else {
            berkala = "0";
        }
        if (!tunggal.trim().equals("")) {
            tunggal = tunggal.replace(".", "").replace(",", ".").trim();
        } else {
            tunggal = "0";
        }

        BigDecimal total = new BigDecimal(0);
        total = total.add(new BigDecimal(dasar));
        total = total.add(new BigDecimal(berkala));
        total = total.add(new BigDecimal(tunggal));

        DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

        formatRp.setCurrencySymbol("");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');

        kursIndonesia.setDecimalFormatSymbols(formatRp);

        return kursIndonesia.format(total);
    }

    public static String MataUang(String val) {
        String mtu = "";
        if (val.equals("Rupiah")) {
            mtu = "Rp";
        }
        return mtu;
    }

    public static String GantiKet(String val) {
        String vl = "";
        if (val.equals("KPLKLGA")) {
            vl = "KEPALA KELUARGA";
        } else {
            vl = val;
        }
        return vl;
    }

    public static String[] manfaat(String kata) {
        String kata2 = kata.replace("\\n\\n", "\r\n");
        String[] tes = kata2.split("\r\n");
        String[] tmp = new String[tes.length];

        String ok = tes[0];
        for (int i = 0; i < tes.length; i++) {
            tmp[i] = tes[i].substring(tes[i].indexOf(".") + 1).trim();
        }

        return tmp;
    }

    public static String PlusDay(String tgl, int plus) throws ParseException {
        String dt = "";
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

        String bulan[] = {"JANUARI", "FEBRUARI", "MARET", "APRIL", "MEI", "JUNI", "JULI", "AGUSTUS",
            "SEPTEMBER", "OKTOBER", "NOVEMBER", "DESEMBER"};

        String tanggal = tgl.substring(0, tgl.indexOf(" "));
        String bulanx = tgl.substring(tgl.indexOf(" ") + 1, tgl.lastIndexOf(" "));
        String tahun = tgl.substring(tgl.lastIndexOf(" ") + 1);

        int ganti = getIndexOf(bulanx.toUpperCase(), bulan);

        if (ganti > -1) {
            ganti++;
            String dateshort = tanggal + "-" + String.format("%02d", ganti) + "-" + tahun;
            Date d = new Date();
            d = formatter.parse(dateshort);
            Calendar cal = Calendar.getInstance();
            cal.setTime(d);
            cal.add(Calendar.DATE, plus);
            dt = formatter.format(cal.getTime());
        } else {
            dt = "ERROR FORMAT TANGGAL TIDAK SESUAI";
        }
        return dt;
    }

    public static String ToRupiah(String val) throws ParseException {
        NumberFormat number = NumberFormat.getInstance();
        Double total = number.parse(val.trim()).doubleValue();
        DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

        formatRp.setCurrencySymbol("");
        formatRp.setMonetaryDecimalSeparator('.');
        formatRp.setGroupingSeparator(',');

        kursIndonesia.setDecimalFormatSymbols(formatRp);

        return kursIndonesia.format(total);
    }

    static String MasaAsuransi(String masa) {
        String balik = "";
        if (masa.toUpperCase().indexOf("SELAMA") > -1) {
            balik = masa;
        } else {
            balik = "Selama " + masa + " Tahun";
        }
        return balik;
    }

    static int getIndexOf(String toSearch, String[] tab) {
        int i = 0;
        while (!(tab[i].equals(toSearch))) {
            i++;
        }
        return i; // or return tab[i];
    }

    static Font GetCalibri(float size) {
        String fontname = "Calibri";
        if (!FontFactory.isRegistered(fontname)) {
            Path fontPath = Paths.get(Value.LOCAL_LOCATION + "/fonts/calibri.ttf");
            FontFactory.register(String.valueOf(fontPath));
        }
        return FontFactory.getFont(fontname, BaseFont.IDENTITY_H, BaseFont.EMBEDDED, size, 0);
    }

    static Font GetCalibri(float size, int style) {
        String fontname = "Calibri";
        if (!FontFactory.isRegistered(fontname)) {
            URL font_path = Thread.currentThread().getContextClassLoader().getResource(Value.LOCAL_LOCATION + "/fonts/calibri.ttf");
            //Path fontPath = Paths.get(Value.LOCAL_LOCATION+"/fonts/calibri.ttf");
            FontFactory.register(String.valueOf(font_path));
        }
        return FontFactory.getFont(fontname, BaseFont.IDENTITY_H, BaseFont.EMBEDDED, size, style);
    }

    static Font GetArial(float size) {
        /*String fontname = "Times New Roman";
            if (!FontFactory.isRegistered(fontname))
            {
                URL font_path = Thread.currentThread().getContextClassLoader().getResource(Value.LOCAL_LOCATION+"/fonts/times.ttf");
                //Path fontPath = Paths.get(Value.LOCAL_LOCATION+"/fonts/calibri.ttf");
                FontFactory.register(String.valueOf(font_path));                
                /*Path fontPath = Paths.get(Value.LOCAL_LOCATION+"/fonts/arial.ttf");
                FontFactory.register(String.valueOf(fontPath));
            }*/
        //FontFactory.registerDirectories();

        Font font = FontFactory.getFont(Value.LOCAL_LOCATION + "/fonts/times.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, size, 0);
        BaseFont baseFont = font.getBaseFont();
        //URL font_path = Thread.currentThread().getContextClassLoader().getResource(Value.LOCAL_LOCATION+"/fonts/times.ttf");
        //Font fuente = FontFactory.getFont("Times New Roman", BaseFont.IDENTITY_H,BaseFont.EMBEDDED, size, 0);
        return font;
    }

    static Font GetArial(float size, int style) {
        Font font = FontFactory.getFont(Value.LOCAL_LOCATION + "/fonts/times.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, size, style);
        BaseFont baseFont = font.getBaseFont();
        //URL font_path = Thread.currentThread().getContextClassLoader().getResource(Value.LOCAL_LOCATION+"/fonts/times.ttf");
        //Font fuente = FontFactory.getFont("Times New Roman", BaseFont.IDENTITY_H,BaseFont.EMBEDDED, size, 0);
        return font;

    }

    static Font GetFBarcode(float size) {

        /*String fontname = "C39P36DlTt";
        if(!FontFactory.isRegistered(fontname))
        {
                URL font_path = Thread.currentThread().getContextClassLoader().getResource(Value.LOCAL_LOCATION+"/fonts/C39P36DlTt.ttf");
                //Path fontPath = Paths.get(Value.LOCAL_LOCATION+"/fonts/calibri.ttf");
                FontFactory.register(String.valueOf(font_path));                            
            /*Path fontPath = Paths.get(Value.LOCAL_LOCATION+"/fonts/C39P36DlTt.ttf");
            FontFactory.register(String.valueOf(fontPath));
        }
        return FontFactory.getFont(fontname, BaseFont.IDENTITY_H, BaseFont.EMBEDDED, size, Font.NORMAL);     */
        Font font = FontFactory.getFont(Value.LOCAL_LOCATION + "/fonts/C39P36DlTt.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, size);
        BaseFont baseFont = font.getBaseFont();
        //URL font_path = Thread.currentThread().getContextClassLoader().getResource(Value.LOCAL_LOCATION+"/fonts/times.ttf");
        //Font fuente = FontFactory.getFont("Times New Roman", BaseFont.IDENTITY_H,BaseFont.EMBEDDED, size, 0);
        return font;
    }
}
