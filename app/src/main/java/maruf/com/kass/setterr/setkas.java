package maruf.com.kass.setterr;

public class setkas {


    String idkas;
    String nama;
    String ranting;
    String jumlah;
    String desk;

    public setkas() {
    }

    public String getIdkas() {
        return idkas;
    }

    public void setIdkas(String idkas) {
        this.idkas = idkas;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getRanting() {
        return ranting;
    }

    public void setRanting(String ranting) {
        this.ranting = ranting;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    public String getDesk() {
        return desk;
    }

    public void setDesk(String desk) {
        this.desk = desk;
    }

    public setkas(String idkas, String nama, String ranting, String jumlah, String desk) {
        this.idkas = idkas;
        this.nama = nama;
        this.ranting = ranting;
        this.jumlah = jumlah;
        this.desk = desk;
    }
}