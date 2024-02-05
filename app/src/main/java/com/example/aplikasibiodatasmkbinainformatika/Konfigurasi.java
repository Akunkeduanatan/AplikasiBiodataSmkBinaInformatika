package com.example.aplikasibiodatasmkbinainformatika;

public class Konfigurasi {

    // URL ke server dimana skrip CRUD PHP disimpan
    // Menggunakan IP komputer sebagai host lokal dimana file PHP berada
    public static final String URL_ADD = "http://192.168.8.179/androiddb/tambahPgw.php";

    // Kunci yang akan digunakan untuk mengirim permintaan ke Skrip PHP
    public static final String KEY_EMP_NIS = "nis";
    public static final String KEY_EMP_NAMA = "name";
    public static final String KEY_EMP_ALAMAT = "address";
    public static final String KEY_EMP_KOTA = "city";
    public static final String KEY_EMP_JENISKELAMIN = "gender"; // Variabel untuk jenis kelamin
    public static final String KEY_EMP_UMUR = "age"; // Variabel untuk umur

    // JSON Tags
    public static final String TAG_JSON_ARRAY = "result";
    public static final String TAG_NIS = "nis";
    public static final String TAG_NAMA = "name";
    public static final String TAG_ALAMAT = "address";
    public static final String TAG_KOTA = "city";
    public static final String TAG_JENISKELAMIN = "gender";
    public static final String TAG_UMUR = "age";

    // ID pegawai (Employee ID)
    public static final String EMP_ID = "emp_id";
}