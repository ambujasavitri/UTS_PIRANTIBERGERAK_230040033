package com.example.uts;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class InputNilaiActivity extends AppCompatActivity {
    Spinner spGrade;
    EditText etNama, etSpeaking, etListening, etWriting;
    Button btnSimpan,btnData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_nilai);

        etNama = findViewById(R.id.etNama);
        etSpeaking = findViewById(R.id.etSpeaking);
        etListening = findViewById(R.id.etListening);
        etWriting = findViewById(R.id.etWriting);

        btnSimpan = findViewById(R.id.btnSimpan);

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseHelper db = new DatabaseHelper(InputNilaiActivity.this);
                String nama = etNama.getText().toString();

                String grade = spGrade.getSelectedItem().toString();

                if (
                        etNama.getText().toString().isEmpty() ||
                                etSpeaking.getText().toString().isEmpty() ||
                                etListening.getText().toString().isEmpty() ||
                                etWriting.getText().toString().isEmpty()
                ) {

                    Toast.makeText(
                            InputNilaiActivity.this,
                            "Semua data harus diisi",
                            Toast.LENGTH_SHORT
                    ).show();

                    return;
                }

                int speaking = Integer.parseInt(etSpeaking.getText().toString());

                int listening = Integer.parseInt(etListening.getText().toString());

                int writing = Integer.parseInt(etWriting.getText().toString());

                double average = (speaking + listening + writing) / 3.0;

                boolean hasil = db.insertData(
                        nama,
                        grade,
                        speaking,
                        listening,
                        writing,
                        average
                );

                if (hasil) {

                    Toast.makeText(
                            InputNilaiActivity.this,
                            "Data berhasil disimpan",
                            Toast.LENGTH_SHORT
                    ).show();

                } else {

                    Toast.makeText(
                            InputNilaiActivity.this,
                            "Data gagal disimpan",
                            Toast.LENGTH_SHORT
                    ).show();

                }

            }

        });


         spGrade = findViewById(R.id.spGrade);

        String[] grade = {"Beginner", "Intermediate", "Advanced"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                grade
        );

        spGrade.setAdapter(adapter);

        btnData = findViewById(R.id.btnData);
        btnData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(
                        InputNilaiActivity.this,
                        DataSiswaActivity.class
                );

                startActivity(intent);

            }
        });

    }
}