package com.example.uts;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditSiswaActivity extends AppCompatActivity {

    EditText etNama, etSpeaking, etListening, etWriting;

    Spinner spGrade;

    Button btnUpdate, btnDelete;

    DatabaseHelper db;

    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_siswa);

        etNama = findViewById(R.id.etNama);

        etSpeaking = findViewById(R.id.etSpeaking);

        etListening = findViewById(R.id.etListening);

        etWriting = findViewById(R.id.etWriting);

        spGrade = findViewById(R.id.spGrade);

        btnUpdate = findViewById(R.id.btnUpdate);

        btnDelete = findViewById(R.id.btnDelete);


        db = new DatabaseHelper(this);


        String[] grade = {
                "Beginner",
                "Intermediate",
                "Advanced"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                grade
        );

        spGrade.setAdapter(adapter);


        id = getIntent().getIntExtra("id", 0);

        String nama = getIntent().getStringExtra("nama");

        String gradeSiswa = getIntent().getStringExtra("grade");

        int speaking = getIntent().getIntExtra("speaking", 0);

        int listening = getIntent().getIntExtra("listening", 0);

        int writing = getIntent().getIntExtra("writing", 0);


        etNama.setText(nama);

        etSpeaking.setText(String.valueOf(speaking));

        etListening.setText(String.valueOf(listening));

        etWriting.setText(String.valueOf(writing));


        if (gradeSiswa.equals("Beginner")) {

            spGrade.setSelection(0);

        } else if (gradeSiswa.equals("Intermediate")) {

            spGrade.setSelection(1);

        } else {

            spGrade.setSelection(2);

        }


        btnUpdate.setOnClickListener(v -> {

            String namaBaru =
                    etNama.getText().toString();

            String gradeBaru =
                    spGrade.getSelectedItem().toString();

            int speakingBaru =
                    Integer.parseInt(
                            etSpeaking.getText().toString()
                    );

            int listeningBaru =
                    Integer.parseInt(
                            etListening.getText().toString()
                    );

            int writingBaru =
                    Integer.parseInt(
                            etWriting.getText().toString()
                    );


            double average = (
                    speakingBaru
                            + listeningBaru
                            + writingBaru
            ) / 3.0;


            boolean hasil = db.updateData(
                    id,
                    namaBaru,
                    gradeBaru,
                    speakingBaru,
                    listeningBaru,
                    writingBaru,
                    average
            );


            if (hasil) {

                Toast.makeText(
                        EditSiswaActivity.this,
                        "Data berhasil diupdate",
                        Toast.LENGTH_SHORT
                ).show();

                finish();

            }

        });


        btnDelete.setOnClickListener(v -> {

            AlertDialog.Builder builder =
                    new AlertDialog.Builder(
                            EditSiswaActivity.this
                    );

            builder.setTitle("Konfirmasi");

            builder.setMessage(
                    "Anda yakin ingin menghapus data ini?"
            );


            builder.setPositiveButton(
                    "Iya",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(
                                DialogInterface dialog,
                                int which
                        ) {

                            db.deleteData(id);

                            Toast.makeText(
                                    EditSiswaActivity.this,
                                    "Data berhasil dihapus",
                                    Toast.LENGTH_SHORT
                            ).show();

                            finish();

                        }
                    }
            );


            builder.setNegativeButton(
                    "Tidak",
                    null
            );

            builder.show();

        });

    }
}

