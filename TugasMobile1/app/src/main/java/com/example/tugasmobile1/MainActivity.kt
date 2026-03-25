package com.example.tugasmobile1

import android.os.Bundle
import android.util.Patterns
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Mengatur padding
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 1. Inisialisasi semua View dari XML
        val etNama = findViewById<EditText>(R.id.etNama)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val rgGender = findViewById<RadioGroup>(R.id.rgGender)
        val cbCoding = findViewById<CheckBox>(R.id.cbCoding)
        val cbReading = findViewById<CheckBox>(R.id.cbReading)
        val cbGaming = findViewById<CheckBox>(R.id.cbGaming)
        val cbSport = findViewById<CheckBox>(R.id.cbSport)
        val spinnerJurusan = findViewById<Spinner>(R.id.spinnerJurusan)
        val btnSubmit = findViewById<Button>(R.id.btnSubmit)

        // 2. Isi Data Spinner
        val listJurusan = arrayOf("Informatika", "Sistem Informasi", "Teknik Komputer", "Desain Komunikasi Visual")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, listJurusan)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerJurusan.adapter = adapter

        // 3. Logika Klik Tombol & Validasi
        btnSubmit.setOnClickListener {
            val nama = etNama.text.toString()
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()

            // Cek Validasi
            if (nama.isEmpty()) {
                etNama.error = "Nama tidak boleh kosong"
            } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                etEmail.error = "Format email salah (contoh: user@mail.com)"
            } else if (password.length < 6) {
                etPassword.error = "Password minimal 6 karakter"
            } else if (rgGender.checkedRadioButtonId == -1) {
                Toast.makeText(this, "Pilih Jenis Kelamin kamu", Toast.LENGTH_SHORT).show()
            } else {
                // memunculkan dialog konfirmasi
                showConfirmDialog(nama, email)
            }
        }

        // 4. Gesture Long Press (Poin 05)
        btnSubmit.setOnLongClickListener {
            Toast.makeText(this, "Aksi Tambahan: Tombol ditekan lama!", Toast.LENGTH_SHORT).show()
            true
        }
    }

    // Fungsi untuk menampilkan Dialog
    private fun showConfirmDialog(nama: String, email: String) {
        AlertDialog.Builder(this)
            .setTitle("Konfirmasi Data")
            .setMessage("Halo $nama, apakah email $email sudah benar?")
            .setPositiveButton("Sudah") { _, _ ->
                Toast.makeText(this, "Data Berhasil Dikirim!", Toast.LENGTH_LONG).show()
            }
            .setNegativeButton("Perbaiki", null)
            .show()
    }
}