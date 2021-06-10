/******************************************************************************
 *       1000 largest US Cities
 *       
 *       Each city has name, population, latitude, longitude, rank, and state
 *       You implement the method to find the given city's population, rank, state, and
 *       calculate the distance between two cities.
 *
 ******************************************************************************

/**
 * This course project is copyright of CyberTek ©CyberTek[2017]. All rights reserved.
 * Any redistribution or reproduction of part or all of the contents in any form is
 * prohibited without the express consent of CyberTek.
 */

package cities;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.lang.Math;

public class CityApp {
	
	private City[] cityList; //This array holds all City objects
	private int N = 1000;  // number of cities


	public CityApp(){
		cityList = new City[N];
		String path = System.getProperty("user.dir");
		loadCities(path+"/data/cities.txt");



	}
	
	/**
	 * This method loads the words from a given file
	 * @param fileName	input file name
	 * DO NOT CHANGE THIS METHOD
	 */

	private void loadCities(String fileName){	
		// This will reference one line at a time
        String line = null;
        int count = 0;
		try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =  new BufferedReader(fileReader);
            while((line = bufferedReader.readLine()) != null) {
            	if(line.startsWith("#")) continue;
            	String[] c = line.split(",");
            	String name = c[0];
            	double latitude = Double.parseDouble(c[2]);
            	double longitude = Double.parseDouble(c[3]);
            	int population = Integer.parseInt(c[4]);
            	int rank = Integer.parseInt(c[5]);
            	String state = c[6];
            	City ct = new City(name, latitude,longitude, population, rank,state);
            	cityList[count++] = ct;
            }   
            // Always close files.
            bufferedReader.close(); 
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + fileName + "'"); 
        }
	}
	
	
	public static void main(String[] args) {

		// Sample run in main method

		CityApp c = new CityApp();
		int p = c.population("chicago");
		System.out.println("Population of Chicago: " + p);
		int d = (int) c.distance("Chicago", "New York");
		System.out.println("Distance between Chicago and New York: " + d + " km");
		
		String city = c.cityByRank(4);
		System.out.println("The 4th largest city in USA is " + city);
		
		int count = c.citiesInState("california");
		System.out.println("Number of large cities in California:" + count);

		System.out.println(c.camelCase("asa ceva nu"));
		System.out.println(c.cityList[1].name);

		System.out.println(c.state("Chicago"));
		System.out.println(c.cityByRank(1));
		System.out.println(c.citiesInState("maryland"));

		System.out.println(c.latitude("chicago"));
		System.out.println(c.longitude("chicago"));

		System.out.println(c.distance("chicago","new york"));
		System.out.println(c.citiesInState("Illinois"));





	}

	/**
	 * Convert the first letter of each word in a string to upper case.
	 * words are delimited by a space.
	 * Leading and trailing space must be deleted.
	 * All city names in our list are camel cased.
	 * For example:
	 * 	input: my name is alice.
	 *  output: My Name Is Alice.
	 * 
	 * camelCase("java is fun") -> "Java Is Fun"
	 * 
	 * @param in: input string
	 * @return a string in which the first letter of each word is converted to upper-case.
	 */

	public String camelCase(String in){
		//TODO

		String [] arr = in.split(" ");
		for( int i = 0; i < arr.length; i++){
			arr[i] = arr[i].substring(0,1).toUpperCase() + arr[i].substring(1);
		}

		String result = String.join(" ",arr);

		return result;

//		for( int i = 0; i < in.length()-1; i++){
//			if(i == 0 && Character.isLetter(in.charAt(i))){
//				in.charAt(i) = Character.toUpperCase(in.charAt(i));
//			}else if(in.charAt(i) == ' '){
//				Character.toUpperCase(in.charAt(i + 1));
//			}
//		}
//		return in;                              //need more work
	}
	
	/**
	 * Get the population of a given city. 
	 * 
	 * PLEASE USE cityList ARRAY. It is already declared and 
	 * already holds all the city objects
	 * 
	 * . 
	 * For example:  
	 * 		City c = citiList[0];  //c is first city in the array.
	 *  	c.population   // population of this city
	 *  	c.rank			// rank of the city
	 *  c.state        //state of the city
	 * 
	 * population("New York") -> 8405837
	 * population("Chicago") -> 2718782
	 * population("Invalid City") -> -1
	 * 
	 * @param city: name of the city
	 * @return population of the city. -1 if the city does not exist
	 */

	public int population(String city){
		//TODO
		int result = -1;
		for( int i = 0; i < cityList.length; i++){
			if(cityList[i].name.equals(camelCase(city))){
				result = cityList[i].population;
				break;
			}
		}
		return result;
	}

	/**
	 * Get the State of a given city belongs to
	 * 
	 * 
	 * PLEASE USE cityList ARRAY. It is already declared and 
	 * already holds all the city objects
	 * 
	 * 
	 * @param city: name of the city
	 * @return the state, to which the city belongs to. 
	 * Returns null if the city does not exist.
	 * For example:
	 * 	input: Alexandria
	 *  Output: Virginia
	 *  
	 *  state("Alexandria") -> "Virginia"
	 *  state("Chicago") -> "Illinois"
	 *  state("Invalid City") -> null
	 *  
	 */

	public String state(String city){
		//TODO
		String result = null;

		for( City each : cityList){
			if(each.name.equals(camelCase(city))){
				result = each.state;
				break;
			}
		}
		return result;
	}
	
	/**
	 * Find the city based on its rank
	 * @param rank: rank n represents it is nth largest city in US 
	 * @return the city name
	 * 
	 * cityByRank(3) -> "Chicago"
	 * 
	 */
	public String cityByRank(int rank){
		//TODO
		String city = "";
		for( int i = 0; i < cityList.length; i++){
			if(cityList[i].rank == rank){
				city = cityList[i].name;
			}
		}
		
		return city;
	}
	
	/**
	 * Count the cities in a given state
	 * @param state
	 * @return: number of large cities in this state
	 * 
	 * CitiesInState("maryland") -> 7
	 * CitiesInState("maine") -> 1
	 * 
	 */

	public int citiesInState(String state){
		//TODO
		int count = 0;
		for (int i = 0 ; i < cityList.length; i++){
			if(cityList[i].state.equals(camelCase(state))){
				count++;
			}
		}
		return count;
	}
	
	
	/**
	 * Get the latitude of a given city
	 * 
	 * PLEASE USE cityList ARRAY. It is already declared and 
	 * already holds all the city objects
	 * 
	 * @param city: input 
	 * @return the latitude of the city
	 * 
	 * latitude("Chicago") -> 41.8781136
	 * latitude("Invalid City") -> -1
	 * 
	 */

	private double latitude(String city){
		//TODO
		double lat = -1;
		for( City each : cityList){
			if(each.name.equals(camelCase(city))){
				lat = each.latitude;
			}
		}
		return lat;
	}
	
	/**
	 * Get the longitude of a given city
	 * 
	 *  PLEASE USE cityList ARRAY. It is already declared and 
	 * already holds all the city objects
	 * 
	 * @param city: input 
	 * @return the longitude of the city
	 * 
	 * latitude("Chicago") -> -87.6297982
	 * latitude("Invalid City") -> -1
	 * 
	 */

	private double longitude(String city){
		//TODO
		double lat = -1;
		for( City each : cityList){
			if(each.name.equals(camelCase(city))){
				lat = each.longitude;
			}
		}
		return lat;
		

	}

	/**

	 Formula:

	 d =  radius * acos(sin(lat1) * sin(lat2) + cos(lat1) * cos(lat2) * cos(lon1 - lon2))

	 -> Where radius = 6371
	 -> latitude and longitude are in radians

	 Use the Math library (java.lang.Math) for all the math operators: sin, cos, etc

	 to convert a number to radians: Math.toRadians(num)

	 Hint: static import the Math class and create variables for lat1, lat2, etc

	 * @param c1: start city
	 * @param c2: destination city
	 * @return the distance between the two cities.

	 */

	public double distance(String c1, String c2){
		//TODO
		double d = 0;
		double lat1 = Math.toRadians(latitude(c1));
		double lat2 = Math.toRadians(latitude(c2));

		double lon1 = Math.toRadians(longitude(c1));
		double lon2 = Math.toRadians(longitude(c2));

//d =  radius * acos(sin(lat1) * sin(lat2) + cos(lat1) * cos(lat2) * cos(lon1 - lon2))


		d = 6371 * Math.acos(Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1) * Math.cos(lat2)
				* Math.cos(lon1 - lon2));

		return d;
	}


	

}
