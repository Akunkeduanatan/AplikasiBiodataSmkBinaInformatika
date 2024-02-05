package com.example.aplikasibiodatasmkbinainformatika;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editTextNIS;
    private EditText editTextName;
    private EditText editTextAlamat;
    private Spinner spinnerKota;
    private RadioButton radioButtonPerempuan;
    private RadioButton radioButtonPria;
    private EditText editTextUmur;
    private Button buttonAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inisialisasi Views
        editTextNIS = findViewById(R.id.editTextNIS);
        editTextName = findViewById(R.id.editTextName);
        editTextAlamat = findViewById(R.id.editTextAlamat);
        spinnerKota = (Spinner) findViewById(R.id.editspinnerKota);
        radioButtonPerempuan = findViewById(R.id.radioButtonPerempuan);
        radioButtonPria = findViewById(R.id.radioButtonPria);
        editTextUmur = findViewById(R.id.editTextUmur);
        buttonAdd = findViewById(R.id.buttonAdd);

        // Menetapkan listener untuk tombol
        buttonAdd.setOnClickListener(this);

        // Menyiapkan spinner kota
        String[] cities = new String[]{
                "Bandung", "Surabaya", "Tangerang Selatan",
                "Jakarta Selatan", "Jakarta Utara", "Jakarta Timur", "Jakarta Barat"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, cities);
        spinnerKota.setAdapter(adapter);

        // Menetapkan listener untuk RadioButton
        radioButtonPerempuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioButtonPria.setChecked(false);
            }
        });

        radioButtonPria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioButtonPerempuan.setChecked(false);
            }
        });
    }

    private void addStudent() {
        final String nis = editTextNIS.getText().toString().trim();
        final String name = editTextName.getText().toString().trim();
        final String alamat = editTextAlamat.getText().toString().trim();
        final String kota = spinnerKota.getSelectedItem().toString();
        final String jenisKelamin = radioButtonPerempuan.isChecked() ? "Perempuan" : "Pria";
        final String umur = editTextUmur.getText().toString().trim();

        class AddStudent extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(MainActivity.this, "Adding...", "Wait...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG).show();
            }
            @Override
            protected String doInBackground(Void... v) {
                HashMap<String, String> params = new HashMap<>();
                params.put(Konfigurasi.KEY_EMP_NIS, nis);
                params.put(Konfigurasi.KEY_EMP_NAMA, name);
                params.put(Konfigurasi.KEY_EMP_ALAMAT, alamat);
                params.put(Konfigurasi.KEY_EMP_KOTA, kota);
                params.put(Konfigurasi.KEY_EMP_JENISKELAMIN, jenisKelamin);
                params.put(Konfigurasi.KEY_EMP_UMUR, umur);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Konfigurasi.URL_ADD, params);
                return res;
            }
        }

        AddStudent ae = new AddStudent();
        ae.execute();
    }

    @Override
    public void onClick(View v) {
        if (v == buttonAdd) {
            addStudent();
        }
    }
}