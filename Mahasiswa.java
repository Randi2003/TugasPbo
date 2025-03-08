public class Mahasiswa {
    private String nim;
    private String nama;
    private String alamat;
    private int semesterm;
    private int sks;
    private  double ipk;

    public Mahasiswa(String nim, String nama, String alamat, int semesterm, int sks, double ipk) {
        this.nim = nim;
        this.nama = nama;
        this.alamat = alamat;
        this.semesterm = semesterm;
        this.sks = sks;
        this.ipk = ipk;
    }

    public String getNim() {
        return nim;
    }

    public String getNama() {
        return nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public int getSemesterm() {
        return semesterm;
    }

    public int getSks() {
        return sks;
    }

    public double getIpk() {
        return ipk;
    }

    @Override
    public String toString() {
        return "Mahasiswa{" +
                "nim='" + nim + '\'' +
                ", nama='" + nama + '\'' +
                ", alamat='" + alamat + '\'' +
                ", semesterm=" + semesterm +
                ", sks=" + sks +
                ", ipk=" + ipk +
                '}';
    }

}