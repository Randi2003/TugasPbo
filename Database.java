import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Database {

    public ArrayList<Mahasiswa> data = new ArrayList<>();
    private String filename = "src/data.csv";
    private Path path = Path.of(filename);

    public Database(){
        open();
    }
    public ArrayList<Mahasiswa> getData() {
        return data;
    }

    public void open() {
        try {
            List<String> Lines = Files.readAllLines(path);
            data = new ArrayList<>();
            for (int i = 1; i < Lines.size(); i++) {
                String line = Lines.get(i);
                String[] element = line.split(",");
                String nim = element[0];
                String nama = element[1];
                String alamat = element[2];
                int semester = Integer.parseInt(element[3]);
                int sks = Integer.parseInt(element[4]);
                double ipk = Double.parseDouble(element[5]);
                Mahasiswa mhs = new Mahasiswa(nim, nama, alamat, semester, sks, ipk);
                data.add(mhs);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void save() {
        StringBuilder sb = new StringBuilder();
        sb.append("NIM,NAMA,ALAMAT (KOTA),SEMESTER,SKS,IPK");
        if (!data.isEmpty()) {
            for (int i = 1; i < data.size(); i++) {
                Mahasiswa mhs = data.get(i);
                String Line = mhs.getNim() + "," + mhs.getNama() + "," + mhs.getAlamat() + "," + mhs.getSemesterm() + "," + mhs.getSks() + "," + mhs.getIpk() + "\n";
                sb.append(Line);

            }
        }
        try {
            Files.writeString(path, sb.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void view() {
        System.out.println("========================================================================================");
        System.out.printf("| %-8.8s |", "NIM");
        System.out.printf(" %-20.20s |", "NAMA");
        System.out.printf(" %-20.20s |", "ALAMAT");
        System.out.printf("| %-8.8s |", "SEMESTER");
        System.out.printf("| %-3.3s |", "SKS");
        System.out.printf("| %-4.4s |", "IPK");
        System.out.println();
        for (Mahasiswa mhs : data) {
            System.out.printf("| %-8.8s |", mhs.getNim());
            System.out.printf(" %-20.20s |", mhs.getNama());
            System.out.printf(" %-20.20s |", mhs.getAlamat());
            System.out.printf("| %-8.8s |", mhs.getSemesterm());
            System.out.printf("| %-3.3s |", mhs.getSks());
            System.out.printf("| %-4.4s |", mhs.getIpk());
            System.out.println();

        }
        System.out.println("--------------------------------------------------------------------------------------");
    }

    boolean insert(String nim, String nama, String alamat, int semester, int sks, double ipk) {
        boolean status = true;

        if (!data.isEmpty()) {
            for (int i = 0; i < data.size(); i++) {
                if (data.get(i).getNim().equalsIgnoreCase(nim)) {
                    status = false;
                    break;
                }
            }
        }
        if (status == true) {
            Mahasiswa mhs = new Mahasiswa(nim, nama, alamat, semester, sks, ipk);
            data.add(mhs);
            save();
        }
        return status;
    }

    public int search(String nim) {
        int index = -1;
        if (!data.isEmpty()) {
            for (int i = 0; i < data.size(); i++) {
                if (data.get(i).getNim().equalsIgnoreCase(nim)) {
                    index = i;
                    break;
                }
            }
        }
        return index;
    }

    public boolean update(int index, String nim, String nama, String alamat, int semester, int sks, double ipk) {
        boolean status = false;
        if (!data.isEmpty()) {
            //update
            if (index >= 0 && index < data.size()) {
                Mahasiswa mhs = new Mahasiswa(nim, nama, alamat, semester, sks, ipk);
                data.set(index, mhs);
                save();
                status = true;
            }
        }
        return status;
    }

    public boolean delete(int index) {
        boolean status = false;
        if (!data.isEmpty()) {
            data.remove(index);
            save();
            status = true;
        }
        return status;
    }

}