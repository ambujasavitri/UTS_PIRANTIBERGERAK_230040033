package com.example.uts;

public class Siswa {

    int id;
    String nama;
    String grade;
    int speaking;
    int listening;
    int writing;
    double average;

    public Siswa(
            int id,
            String nama,
            String grade,
            int speaking,
            int listening,
            int writing,
            double average
    ) {

        this.id = id;
        this.nama = nama;
        this.grade = grade;
        this.speaking = speaking;
        this.listening = listening;
        this.writing = writing;
        this.average = average;

    }

    public int getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getGrade() {
        return grade;
    }

    public int getSpeaking() {
        return speaking;
    }

    public int getListening() {
        return listening;
    }

    public int getWriting() {
        return writing;
    }

    public double getAverage() {
        return average;
    }

}