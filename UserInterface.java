import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Locale;

public class UserInterface {

    public static void tampilkanMenu() {
        System.out.println();
        System.out.println("==================+");
        System.out.println("| Pilih menu: |");
        System.out.println("+-----------------+");
        System.out.println("|   [C] : Create  |");
        System.out.println("|   [R] : Read    |");
        System.out.println("|   [U] : Update  |");
        System.out.println("|   [D] : Delete  |");
        System.out.println("|   [X] : Exit    |");
    }

    public static void main(String[] args) {
        Database db = new Database();
        System.out.println(" APLIKASI SIMPLE CRUD TEXT DATABASE");

        while (true) {
            tampilkanMenu();
            Scanner sc = new Scanner(System.in);
            System.out.println("Pilih  : ");
            String pilihan = sc.nextLine();
            pilihan = pilihan.toUpperCase();

            switch (pilihan) {
                case "C":
                    System.out.println("INFO: anda memilih menu create");
                    System.out.println("---------------------------------");
                    System.out.println("INPUT DATA BARU");
                    System.out.print("NIM          : ");
                    String nim = sc.nextLine();
                    System.out.print("NAMA MAHASISWA: ");
                    String nama = sc.nextLine();
                    System.out.print("ALAMAT       : ");
                    String alamat = sc.nextLine();
                    System.out.print("SEMESTER     : ");
                    int semester = sc.nextInt();
                    System.out.print("SKS          : ");
                    int sks = sc.nextInt();

                    // Menggunakan try-catch untuk menangani InputMismatchException saat input IPK
                    double ipk = 0.0;
                    while (true) {
                        try {
                            System.out.print("IPK          : ");
                            String ipkInput = sc.next(); // Ambil input sebagai string

                            // Jika input mengandung koma, ganti dengan titik
                            ipkInput = ipkInput.replace(",", ".");

                            // Parsing input string ke double
                            ipk = Double.parseDouble(ipkInput);
                            break; // Keluar dari loop jika input valid
                        } catch (NumberFormatException e) {
                            System.err.println("Input tidak valid! Harap masukkan angka desimal untuk IPK.");
                        }
                    }

                    System.out.println("--------------------------------------");
                    boolean status = db.insert(nim, nama, alamat, semester, sks, ipk);
                    if (status == true) {
                        System.out.println("DATA BARU BERHASIL DITAMBAHKAN");
                    } else {
                        System.out.println("NIM " + nim + " SUDAH ADA DI DATABASE");
                        System.err.println("GAGAL MENAMBAHKAN DATA BARU");
                    }
                    System.out.println("-------------------------------------------");
                    break;

                case "R":
                    System.out.println("INFO: Anda memilih menu Read");
                    db.view();
                    break;

                case "U":
                    System.out.println("INFO: Anda memilih menu update");
                    db.view();
                    System.out.print("input key (nim mahasiswa yang akan di update): ");
                    String key = sc.nextLine();
                    int index = db.search(key);
                    if (index >= 0) {
                        System.out.println("Anda akan meng-update data " + db.getData().get(index));
                        System.out.println("---------------------------------");
                        System.out.println("INPUT DATA BARU");
                        System.out.print("NIM            : ");
                        nim = sc.nextLine();
                        System.out.print("NAMA MAHASISWA : ");
                        nama = sc.nextLine();
                        System.out.print("ALAMAT         : ");
                        alamat = sc.nextLine();
                        System.out.print("SEMESTER       : ");
                        semester = sc.nextInt();
                        System.out.print("SKS            : ");
                        sks = sc.nextInt();

                        // Menggunakan try-catch untuk menangani InputMismatchException saat input IPK
                        while (true) {
                            try {
                                System.out.print("IPK            : ");
                                String ipkInput = sc.next(); // Ambil input sebagai string

                                // Jika input mengandung koma, ganti dengan titik
                                ipkInput = ipkInput.replace(",", ".");

                                // Parsing input string ke double
                                ipk = Double.parseDouble(ipkInput);
                                break; // Keluar dari loop jika input valid
                            } catch (NumberFormatException e) {
                                System.err.println("Input tidak valid! Harap masukkan angka desimal untuk IPK.");
                            }
                        }

                        System.out.println("--------------------------------------");
                        status = db.update(index, nim, nama, alamat, semester, sks, ipk);
                        if (status == true) {
                            System.out.println("DATA BERHASIL DIPERBAHARUI");
                        } else {
                            System.err.println("GAGAL MEMPERBAHARUI DATA");
                        }
                        System.out.println("--------------------------------------");
                    } else {
                        System.err.println("Mahasiswa dengan nim: " + key + " tidak ada di database");
                    }
                    break;

                case "D":
                    System.out.println("INFO: Anda memilih delete");
                    db.view();
                    System.out.println("Input key (NIM Mahasiswa yang akan dihapus); ");
                    key = sc.nextLine();
                    index = db.search(key);
                    if (index >= 0) {
                        System.out.println("APAKAH ANDA YAKIN AKAN MENGHAPUS DATA "+db.getData().get(index)+"? Y/N");
                        System.out.println("PILIH  : ");
                        pilihan = sc.nextLine();
                        if (pilihan.equalsIgnoreCase("Y")){
                            status = db.delete(index);
                            if(status==true){
                                System.out.println("DATA BERHASIL DIHAPUS");
                            }else{
                                System.err.println("GAGAL MENGHAPUS DATA");
                            }
                        }
                    }else{
                        System.err.println("Mahasiswa dengan NIM: " + key + " tidak ada di database");
                    }
                    break;

                case "X":
                    System.out.println("INFO : Anda memilih menu EXIT");
                    System.out.println("APAKAH ANDA YAKIN AKAN KELUAR DARI APLIKASI? Y/N");
                    System.out.print("Pilih : ");
                    pilihan = sc.nextLine();
                    if (pilihan.equalsIgnoreCase("Y")) {
                        System.exit(0);
                    }
                    break;
            }
        }
    }
}