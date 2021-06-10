/**
 * This course project is copyright of CyberTek ©CyberTek[2017]. All rights reserved. 
 * Any redistribution or reproduction of part or all of the contents in any form is 
 * prohibited without the express consent of CyberTek.
 */

package cities;

/**
 * This class holds the city information.
 */
public class City {

	public String name;
	public double latitude;
	public double longitude;
	public int population;
	public int rank;
	public String state;
	
	public City(String name,  double latitude, double longitude, 
			int population, int rank, String state){
		
		this.name  = name;
		this.latitude = latitude;
		this.longitude = longitude;
		this.population = population;
		this.rank = rank;
		this.state = state;
	}
	
}
