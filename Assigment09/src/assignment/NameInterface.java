package assignment;

/**
 * CPS231 - Fall 2021
 * NameInterface to help define a class to represent last name statistical data.
 * @author Adam Divelbiss
 *
 */
public interface NameInterface {
	
	/**
	 * Returns the name 
	 * @return
	 */
	public String getName();
	
	/**
	 * Set the name field
	 * @param name
	 */
	public void setName(String name);
	
	/**
	 * return the rank for the name
	 * @return
	 */
	public int getRank();
	
	/**
	 * Set the rank for the name
	 * @param rank
	 */
	public void setRank(int rank);
	
	/**
	 * The total number of occurrences of the name in the 2000 census.
	 * @return the frequency of the name
	 */
	public int getFrequency();
	
	/**
	 * Set the frequency of the name
	 * @param frequency
	 */
	public void setFrequency(int frequency);
	
	/**
	 * The number of occurrences per 100,000 names.
	 * @return the proportion of the name per 100,000
	 */
	public double getProportion();
	
	/**
	 * Set the Proportion for the name
	 * @param proportion
	 */
	public void setProportion(double proportion);
	
	/**
	 * The cumulative number of occurrences per 100,000 names.
	 * @return the cumulative proportion of the name
	 */
	public double getCumulativeProportion();
	
	/**
	 * Set the cumulative proportion for the name
	 * @param cumulativeProportion
	 */
	public void setCumulativeProportion(double cumulativeProportion);


}
