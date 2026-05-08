package com.example.uts;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.database.Cursor;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class DataSiswaActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    ArrayList<Siswa> listSiswa;

    DatabaseHelper db;

    SiswaAdapter adapter;
    Button btnTambah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_data_siswa);

        recyclerView = findViewById(R.id.recyclerView);

        listSiswa = new ArrayList<>();

        db = new DatabaseHelper(this);

        Cursor cursor = db.getAllData();

        if (cursor.getCount() == 0) {

            Toast.makeText(
                    this,
                    "Data kosong",
                    Toast.LENGTH_SHORT
            ).show();

        } else {

            while (cursor.moveToNext()) {

                int id = cursor.getInt(0);

                String nama = cursor.getString(1);

                String grade = cursor.getString(2);

                int speaking = cursor.getInt(3);

                int listening = cursor.getInt(4);

                int writing = cursor.getInt(5);

                double average = cursor.getDouble(6);

                listSiswa.add(
                        new Siswa(
                                id,
                                nama,
                                grade,
                                speaking,
                                listening,
                                writing,
                                average
                        )
                );

            }

        }
        adapter = new SiswaAdapter(listSiswa);

        recyclerView.setLayoutManager(
                new LinearLayoutManager(this)
        );

        recyclerView.setAdapter(adapter);

        btnTambah = findViewById(R.id.btnTambah);
        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(
                        DataSiswaActivity.this,
                        InputNilaiActivity.class
                );

                startActivity(intent);

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        listSiswa.clear();

        Cursor cursor = db.getAllData();

        while (cursor.moveToNext()) {

            int id = cursor.getInt(0);

            String nama = cursor.getString(1);

            String grade = cursor.getString(2);

            int speaking = cursor.getInt(3);

            int listening = cursor.getInt(4);

            int writing = cursor.getInt(5);

            double average = cursor.getDouble(6);

            listSiswa.add(
                    new Siswa(
                            id,
                            nama,
                            grade,
                            speaking,
                            listening,
                            writing,
                            average
                    )
            );

        }

        adapter.notifyDataSetChanged();

    }


}