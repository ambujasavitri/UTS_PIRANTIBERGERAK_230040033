package com.example.uts;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.content.Intent;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SiswaAdapter extends RecyclerView.Adapter<SiswaAdapter.ViewHolder> {

    ArrayList<Siswa> listSiswa;

    public SiswaAdapter(ArrayList<Siswa> listSiswa) {
        this.listSiswa = listSiswa;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_siswa, parent, false);

        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {



        Siswa siswa = listSiswa.get(position);

        holder.tvNama.setText(
                siswa.getNama() + " | " + siswa.getGrade()
        );

        holder.tvSpeaking.setText(
                String.valueOf(siswa.getSpeaking())
        );

        holder.tvListening.setText(
                String.valueOf(siswa.getListening())
        );

        holder.tvWriting.setText(
                String.valueOf(siswa.getWriting())
        );

        holder.tvAverage.setText(
                "Average : " + siswa.getAverage()
        );

        holder.imgMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(
                        v.getContext(),
                        EditSiswaActivity.class
                );

                intent.putExtra("id", siswa.getId());
                intent.putExtra("nama", siswa.getNama());
                intent.putExtra("grade", siswa.getGrade());
                intent.putExtra("speaking", siswa.getSpeaking());
                intent.putExtra("listening", siswa.getListening());
                intent.putExtra("writing", siswa.getWriting());

                v.getContext().startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return listSiswa.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvNama, tvSpeaking, tvListening, tvWriting, tvAverage;
        ImageView imgMenu;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNama = itemView.findViewById(R.id.tvNama);
            tvSpeaking = itemView.findViewById(R.id.tvSpeaking);
            tvListening = itemView.findViewById(R.id.tvListening);
            tvWriting = itemView.findViewById(R.id.tvWriting);
            tvAverage = itemView.findViewById(R.id.tvAverage);
            imgMenu = itemView.findViewById(R.id.imgMenu);





        }

    }

}