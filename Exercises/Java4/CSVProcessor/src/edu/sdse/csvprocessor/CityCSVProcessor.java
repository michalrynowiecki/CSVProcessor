package edu.sdse.csvprocessor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CityCSVProcessor {
	
	public void readAndProcess(File file) {

		ArrayList<CityRecord> allRecords = new ArrayList<CityRecord>();
		HashMap<String, ArrayList<CityRecord>> cityMap = new HashMap<String, ArrayList<CityRecord>>();

		//Try with resource statement (as of Java 8)
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			//Discard header row
			br.readLine();
			
			String line;
			
			while ((line = br.readLine()) != null) {
				// Parse each line
				String[] rawValues = line.split(",");
				
				int id = convertToInt(rawValues[0]);
				int year = convertToInt(rawValues[1]);
				String city = convertToString(rawValues[2]);
				int population = convertToInt(rawValues[3]);
				
				CityRecord myCity = new CityRecord(id,year,city,population);
				//System.out.println(myCity.toString());
				allRecords.add(myCity);

				if (cityMap.containsKey(myCity.getCity())) {
					cityMap.get(myCity.getCity()).add(myCity);
				}
				else {
					ArrayList<CityRecord> temp = new ArrayList<CityRecord>();
					cityMap.put(myCity.getCity(), temp);
				}

				//TODO: Extend the program to process entries!
			}
			//No of entries
			for (Map.Entry<String, ArrayList<CityRecord>> entry : cityMap.entrySet()) {
				System.out.println("Total number of entries for " + entry.getKey() + ": " + entry.getValue().size());

				int pop = 0;
				int maxYear = 0;
				int minYear = 3000;
				for (CityRecord p: entry.getValue()) {
					pop += p.getPopulation();

					if (p.getYear() > maxYear){
						maxYear = p.getYear();
					}
					if (p.getYear() < minYear){
						minYear = p.getYear();
					}
				}
				pop = pop/entry.getValue().size();

				System.out.println("Average population = " + pop);
				System.out.println("Min year: " + minYear);
				System.out.println("Max year: " + maxYear);
			}


			
		} catch (Exception e) {
			System.err.println("An error occurred:");
			e.printStackTrace();
		}
	}
	
	private String cleanRawValue(String rawValue) {
		return rawValue.trim();
	}
	
	private int convertToInt(String rawValue) {
		rawValue = cleanRawValue(rawValue);
		return Integer.parseInt(rawValue);
	}
	
	private String convertToString(String rawValue) {
		rawValue = cleanRawValue(rawValue);
		
		if (rawValue.startsWith("\"") && rawValue.endsWith("\"")) {
			return rawValue.substring(1, rawValue.length() - 1);
		}
		
		return rawValue;
	}
	
	public static final void main(String[] args) {
		CityCSVProcessor reader = new CityCSVProcessor();
		
		File dataDirectory = new File("data/");
		File csvFile = new File(dataDirectory, "Cities.csv");
		
		reader.readAndProcess(csvFile);
	}
}
