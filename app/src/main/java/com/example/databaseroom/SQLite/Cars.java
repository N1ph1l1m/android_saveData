package com.example.databaseroom.SQLite;

public class Cars {
    private long id;
    private String nameCar;
    private int speedCar;

    Cars(long id, String name, int year){
        this.id = id;
        this.nameCar = name;
        this.speedCar = year;
    }
    public long getId() {
        return id;
    }
    public String getNameCar() {
        return nameCar;
    }

    public void setNameCar(String nameCar) {
        this.nameCar = nameCar;
    }

    public int getSpeedCar() {
        return speedCar;
    }

    public void setSpeedCar(int speedCar) {
        this.speedCar = speedCar;
    }

    @Override
    public String toString() {
        return this.nameCar + " : " + this.speedCar;
    }
}
