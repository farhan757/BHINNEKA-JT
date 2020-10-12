/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propert;

/**
 *
 * @author someone
 */
public class Rincian_Trans {
    public class Rincian_Transaksi
    {
        private String JENIS_INVESTASI;
        private String TGL_TRANSAKSI;
        private String KETERANGAN ;
        private String JUMLAH_DANA ;
        private String JUMLAH_INVESTASI_PREMI_DASAR;
        private String JUMLAH_INVESTASI_PREMI_TOPUP;
        private String HARGA_UNIT;
        private String JUMLAH_UNIT_PREMI_DASAR;
        private String JUMLAH_UNIT_PREMI_TOPUP;

        public String getJUMLAH_INVESTASI_PREMI_DASAR() {
            return JUMLAH_INVESTASI_PREMI_DASAR;
        }

        public void setJUMLAH_INVESTASI_PREMI_DASAR(String JUMLAH_INVESTASI_PREMI_DASAR) {
            this.JUMLAH_INVESTASI_PREMI_DASAR = JUMLAH_INVESTASI_PREMI_DASAR;
        }

        public String getJUMLAH_INVESTASI_PREMI_TOPUP() {
            return JUMLAH_INVESTASI_PREMI_TOPUP;
        }

        public void setJUMLAH_INVESTASI_PREMI_TOPUP(String JUMLAH_INVESTASI_PREMI_TOPUP) {
            this.JUMLAH_INVESTASI_PREMI_TOPUP = JUMLAH_INVESTASI_PREMI_TOPUP;
        }

        public String getHARGA_UNIT() {
            return HARGA_UNIT;
        }

        public void setHARGA_UNIT(String HARGA_UNIT) {
            this.HARGA_UNIT = HARGA_UNIT;
        }

        public String getJUMLAH_UNIT_PREMI_DASAR() {
            return JUMLAH_UNIT_PREMI_DASAR;
        }

        public void setJUMLAH_UNIT_PREMI_DASAR(String JUMLAH_UNIT_PREMI_DASAR) {
            this.JUMLAH_UNIT_PREMI_DASAR = JUMLAH_UNIT_PREMI_DASAR;
        }

        public String getJUMLAH_UNIT_PREMI_TOPUP() {
            return JUMLAH_UNIT_PREMI_TOPUP;
        }

        public void setJUMLAH_UNIT_PREMI_TOPUP(String JUMLAH_UNIT_PREMI_TOPUP) {
            this.JUMLAH_UNIT_PREMI_TOPUP = JUMLAH_UNIT_PREMI_TOPUP;
        }
        private String DANA_INVESTASI ;
        private String NAB_UNIT ;
        private String JUMLAH_UNIT ;   
        
        public void setJENIS_INVESTASI(String JENIS_INVESTASI)
        {
            this.JENIS_INVESTASI = JENIS_INVESTASI;
        }
        public String getJENIS_INVESTASI()
        {
            return this.JENIS_INVESTASI;
        }
        public void setTGL_TRANSAKSI(String TGL_TRANSAKSI)
        {
            this.TGL_TRANSAKSI = TGL_TRANSAKSI;
        }
        public String getTGL_TRANSAKSI()
        {
            return this.TGL_TRANSAKSI;
        }
        public void setKETERANGAN(String KETERANGAN)
        {
            this.KETERANGAN = KETERANGAN;
        }
        public String getKETERANGAN()
        {
            return this.KETERANGAN;
        }
        public void setJUMLAH_DANA(String JUMLAH_DANA)
        {
            this.JUMLAH_DANA = JUMLAH_DANA;
        }
        public String getJUMLAH_DANA()
        {
            return this.JUMLAH_DANA;
        }    
        public void setDANA_INVESTASI(String DANA_INVESTASI)
        {
            this.DANA_INVESTASI = DANA_INVESTASI;
        }
        public String getDANA_INVESTASI()
        {
            return this.DANA_INVESTASI;
        }       
        public void setNAB_UNIT(String NAB_UNIT)
        {
            this.NAB_UNIT = NAB_UNIT;
        }
        public String getNAB_UNIT()
        {
            return this.NAB_UNIT;
        }
        public void setJUMLAH_UNIT(String JUMLAH_UNIT)
        {
            this.JUMLAH_UNIT = JUMLAH_UNIT;
        }
        public String getJUMLAH_UNIT()
        {
            return this.JUMLAH_UNIT;
        }           
    }
    
    public class Ringkasan_Transaksi
    {
        private String TANGGAL;
        private String JENIS_DANA_INVESTASI;
        private String JUMLAH_UNIT;
        private String NAB_UNIT;
        private String JUMLAH_DANA;  
        private String JUMLAH_INVESTASI_PREMI_DASAR;

        public String getJUMLAH_INVESTASI_PREMI_DASAR() {
            return JUMLAH_INVESTASI_PREMI_DASAR;
        }

        public void setJUMLAH_INVESTASI_PREMI_DASAR(String JUMLAH_INVESTASI_PREMI_DASAR) {
            this.JUMLAH_INVESTASI_PREMI_DASAR = JUMLAH_INVESTASI_PREMI_DASAR;
        }

        public String getJUMLAH_INVESTASI_PREMI_TOPUP() {
            return JUMLAH_INVESTASI_PREMI_TOPUP;
        }

        public void setJUMLAH_INVESTASI_PREMI_TOPUP(String JUMLAH_INVESTASI_PREMI_TOPUP) {
            this.JUMLAH_INVESTASI_PREMI_TOPUP = JUMLAH_INVESTASI_PREMI_TOPUP;
        }

        public String getHARGA_UNIT() {
            return HARGA_UNIT;
        }

        public void setHARGA_UNIT(String HARGA_UNIT) {
            this.HARGA_UNIT = HARGA_UNIT;
        }

        public String getJUMLAH_UNIT_PREMI_DASAR() {
            return JUMLAH_UNIT_PREMI_DASAR;
        }

        public void setJUMLAH_UNIT_PREMI_DASAR(String JUMLAH_UNIT_PREMI_DASAR) {
            this.JUMLAH_UNIT_PREMI_DASAR = JUMLAH_UNIT_PREMI_DASAR;
        }

        public String getJUMLAH_UNIT_PREMI_TOPUP() {
            return JUMLAH_UNIT_PREMI_TOPUP;
        }

        public void setJUMLAH_UNIT_PREMI_TOPUP(String JUMLAH_UNIT_PREMI_TOPUP) {
            this.JUMLAH_UNIT_PREMI_TOPUP = JUMLAH_UNIT_PREMI_TOPUP;
        }

        public String getTOTAL_JUMLAH_UNIT() {
            return TOTAL_JUMLAH_UNIT;
        }

        public void setTOTAL_JUMLAH_UNIT(String TOTAL_JUMLAH_UNIT) {
            this.TOTAL_JUMLAH_UNIT = TOTAL_JUMLAH_UNIT;
        }

        public String getTOTAL_JUMLAH_DANA() {
            return TOTAL_JUMLAH_DANA;
        }

        public void setTOTAL_JUMLAH_DANA(String TOTAL_JUMLAH_DANA) {
            this.TOTAL_JUMLAH_DANA = TOTAL_JUMLAH_DANA;
        }
        private String JUMLAH_INVESTASI_PREMI_TOPUP;
        private String HARGA_UNIT;
        private String JUMLAH_UNIT_PREMI_DASAR;
        private String JUMLAH_UNIT_PREMI_TOPUP;        
        private String TOTAL_JUMLAH_UNIT;
        private String TOTAL_JUMLAH_DANA;
        
        public void setTANGGAL(String TANGGAL)
        {
            this.TANGGAL = TANGGAL;
        }
        public String getTANGGAL()
        {
            return this.TANGGAL;
        }   
        public void setJENIS_DANA_INVESTASI(String JENIS_DANA_INVESTASI)
        {
            this.JENIS_DANA_INVESTASI = JENIS_DANA_INVESTASI;
        }
        public String getJENIS_DANA_INVESTASI()
        {
            return this.JENIS_DANA_INVESTASI;
        }   
        public void setJUMLAH_UNIT(String JUMLAH_UNIT)
        {
            this.JUMLAH_UNIT = JUMLAH_UNIT;
        }
        public String getJUMLAH_UNIT()
        {
            return this.JUMLAH_UNIT;
        }   
        public void setNAB_UNIT(String NAB_UNIT)
        {
            this.NAB_UNIT = NAB_UNIT;
        }
        public String getNAB_UNIT()
        {
            return this.NAB_UNIT;
        }   
        public void setJUMLAH_DANA(String JUMLAH_DANA)
        {
            this.JUMLAH_DANA = JUMLAH_DANA;
        }
        public String getJUMLAH_DANA()
        {
            return this.JUMLAH_DANA;
        }           
    }
    
}
