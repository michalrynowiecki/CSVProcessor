package edu.sdse.csvprocessor;

public class CityRecord {
    private int id;
    private int year;
    private String city;
    private int population;

    public CityRecord(int id, int year, String city, int population){
        this.id = id;
        this.year = year;
        this.city = city;
        this.population = population;
    }

    public String toString(){
        String my_string = "id: " + id + ", year: " + year + ", city: " + city + ", population: " + population;
        return my_string;
    }

    int getId(){
        return id;
    }

    int getYear(){
        return year;
    }

    String getCity(){
        return city;
    }
    int getPopulation(){
        return population;
    }


}
