/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fungsi;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.swing.JOptionPane;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import propert.T_Produk;

/**
 *
 * @author someone
 */
public class InsertTabel {

    public static HashMap<String, propert.Data> InsertData(JSONArray dt) {
        HashMap<String, propert.Data> TB_MASTER = new HashMap<String, propert.Data>();

        for (int i = 0; i < dt.length(); i++) {
            JSONObject obj = dt.getJSONObject(i);
            propert.Data vl = new propert.Data();
            if (obj.has("ID") && !obj.isNull("ID")) {
                vl.setID(obj.getString("ID"));
            }
            if (obj.has("No surat") && !obj.isNull("No surat")) {
                vl.setNo_Surat(obj.getString("No surat"));
            }
            if (obj.has("Tanggal surat") && !obj.isNull("Tanggal surat")) {
                vl.setTgl_Surat(obj.getString("Tanggal surat"));
            }
            if (obj.has("Nomor polis") && !obj.isNull("Nomor polis")) {
                vl.setNomor_Polis(obj.getString("Nomor polis"));
            }
            if (obj.has("Nama pemegang polis") && !obj.isNull("Nama pemegang polis")) {
                vl.setNama_Pemegang_Polis(obj.getString("Nama pemegang polis"));
            }
            if (obj.has("HP pempol") && !obj.isNull("HP pempol")) {
                vl.setHP_pempol(obj.getString("HP pempol"));
            }
            if (obj.has("Tanggal lahir pemegang polis") && !obj.isNull("Tanggal lahir pemegang polis")) {
                vl.setTgl_Lahir_pempol(obj.getString("Tanggal lahir pemegang polis"));
            }
            if (obj.has("Nama produk") && !obj.isNull("Nama produk")) {
                vl.setNama_Produk(obj.getString("Nama produk"));
            }
            if (obj.has("Tipe jatuh tempo") && !obj.isNull("Tipe jatuh tempo")) {
                vl.setTipe_Jatuh_Tempo(obj.getString("Tipe jatuh tempo"));
            }
            if (obj.has("Tanggal jatuh tempo") && !obj.isNull("Tanggal jatuh tempo")) {
                vl.setTgl_Jatuh_Tempo(obj.getString("Tanggal jatuh tempo"));
            }
            if (obj.has("Periode jatuh tempo") && !obj.isNull("Periode jatuh tempo")) {
                vl.setPeriode_Jatuh_Tempo(obj.getString("Periode jatuh tempo"));
            }
            if (obj.has("Tipe ARO") && !obj.isNull("Tipe ARO")) {
                vl.setTipe_ARO(obj.getString("Tipe ARO"));
            } 
            if (obj.has("Nama tipe ARO") && !obj.isNull("Nama tipe ARO")) {
                vl.setNama_Tipe_ARO(obj.getString("Nama tipe ARO"));
            }
            if (obj.has("Total premi") && !obj.isNull("Total premi")) {
                vl.setTotal_Premi(obj.getString("Total premi"));
            }
            if (obj.has("Jumlah investasi") && !obj.isNull("Jumlah investasi")) {
                vl.setJumlah_Investasi(obj.getString("Jumlah investasi"));
            }
            if (obj.has("Nilai polis") && !obj.isNull("Nilai polis")) {
                vl.setNilai_Polis(obj.getString("Nilai polis"));
            }
            if (obj.has("Tanggal mulai berikutnya") && !obj.isNull("Tanggal mulai berikutnya")) {
                vl.setTanggal_Mulai_berikutnya(obj.getString("Tanggal mulai berikutnya"));
            }
            if (obj.has("Tingkat target investasi") && !obj.isNull("Tingkat target investasi")) {
                vl.setTingkat_Target_Investasi(obj.getString("Tingkat target investasi"));
            }
            if (obj.has("Nama pemilik rekening") && !obj.isNull("Nama pemilik rekening")) {
                vl.setNama_pemilik_rekening(obj.getString("Nama pemilik rekening"));
            }
            if (obj.has("Nomor rekening") && !obj.isNull("Nomor rekening")) {
                vl.setNomor_rekening(obj.getString("Nomor rekening"));
            }
            if (obj.has("Nama bank") && !obj.isNull("Nama bank")) {
                vl.setNama_Bank(obj.getString("Nama bank"));
            } 
            if (obj.has("Nama bank cabang") && !obj.isNull("Nama bank cabang")) {
                vl.setNama_Bank_Cabang(obj.getString("Nama bank cabang"));
            }
            if (obj.has("Email call center") && !obj.isNull("Email call center")) {
                vl.setEmail_Call_Center(obj.getString("Email call center"));
            }
            if (obj.has("No tlp call center") && !obj.isNull("No tlp call center")) {
                vl.setNotlp_call_center(obj.getString("No tlp call center"));
            }
            if (obj.has("No WA call center") && !obj.isNull("No WA call center")) {
                vl.setNoWA_call_center(obj.getString("No WA call center"));
            }
            if (obj.has("Alamat POS") && !obj.isNull("Alamat POS")) {
                vl.setAlamat_POS(obj.getString("Alamat POS"));
            }
            if (obj.has("Jenis cetakan") && !obj.isNull("Jenis cetakan")) {
                vl.setJenis_cetakan(obj.getString("Jenis cetakan"));
            }            
            if (obj.has("Tipe jatuh tempo") && !obj.isNull("Tipe jatuh tempo")) {
                vl.setProduk(obj.getString("Tipe jatuh tempo"));
            } 
            
            if (obj.has("Metode Pembayaran") && !obj.isNull("Metode Pembayaran")) {
                vl.setMetode_Pembayaran(obj.getString("Metode Pembayaran"));
            } 
            if (obj.has("Cara Bayar") && !obj.isNull("Cara Bayar")) {
                vl.setCara_Bayar(obj.getString("Cara Bayar"));
            } 
            if (obj.has("Premi Dasar") && !obj.isNull("Premi Dasar")) {
                vl.setPremi_Dasar(obj.getString("Premi Dasar"));
            } 
            if (obj.has("Premi Top Up Reguler") && !obj.isNull("Premi Top Up Reguler")) {
                vl.setPremi_Top_Up_Reguler(obj.getString("Premi Top Up Reguler"));
            } 
            if (obj.has("Premi Rider") && !obj.isNull("Premi Rider")) {
                vl.setPremi_Rider(obj.getString("Premi Rider"));
            } 
            if (obj.has("Extra Premi ") && !obj.isNull("Extra Premi ")) {
                vl.setExtra_Premi(obj.getString("Extra Premi "));
            } 
            if (obj.has("Total Tagihan Premi") && !obj.isNull("Total Tagihan Premi")) {
                vl.setTotal_Tagihan_Premi(obj.getString("Total Tagihan Premi"));
            } 
            if (obj.has("BRI VA") && !obj.isNull("BRI VA")) {
                vl.setBRI_VA(obj.getString("BRI VA"));
            } 
            if (obj.has("BCA VA") && !obj.isNull("BCA VA")) {
                vl.setBCA_VA(obj.getString("BCA VA"));
            } 
            if (obj.has("BNI VA") && !obj.isNull("BNI VA")) {
                vl.setBNI_VA(obj.getString("BNI VA"));
            } 
            if (obj.has("MDR VA") && !obj.isNull("MDR VA")) {
                vl.setMDR_VA(obj.getString("MDR VA"));
            }             
            
            if(!TB_MASTER.containsKey(vl.getNomor_Polis())){
                TB_MASTER.put(vl.getNomor_Polis(), vl);
            }
        }
        return TB_MASTER;
    }
    
    public static Map<String,String> Sorter(JSONArray dt){
        Map<String, String> sort = new HashMap<String, String>();        
        for(int x=0; x< dt.length(); x++){
            JSONObject O_Pro = dt.getJSONObject(x);            
            sort.put(O_Pro.getString("Nomor polis"), "E-JATUHTEMPO");
        }            
        return sortByComparator(sort,true);       
    }
    
    private static Map<String, String> sortByComparator(Map<String, String> unsortMap, final boolean order)
    {

        List<Entry<String, String>> list = new LinkedList<Entry<String, String>>(unsortMap.entrySet());

        // Sorting the list based on values
        Collections.sort(list, new Comparator<Entry<String, String>>()
        {
            public int compare(Entry<String, String> o1,
                    Entry<String, String> o2)
            {
                if (order)
                {
                    return o1.getValue().compareTo(o2.getValue());
                }
                else
                {
                    return o2.getValue().compareTo(o1.getValue());

                }
            }
        });

        // Maintaining insertion order with the help of LinkedList
        Map<String, String> sortedMap = new LinkedHashMap<String, String>();
        for (Entry<String, String> entry : list)
        {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }     
}
