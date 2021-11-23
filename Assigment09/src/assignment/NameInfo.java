package assignment;
/**
 * cps 231
 * 11/18/2021
 * @author mikep
 *Implementation of the NameInterface class
 */
public class NameInfo implements NameInterface {
	private String name;
	private int rank;
	private int frequency;
	private double proportion;
	private double cumulativeProportion;

	@Override
	public int hashCode() {
		return 0;
		
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}else {
			return false;
		}
		
		
	}
	
	
	
	
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
		
	}

	@Override
	public int getRank() {
		
		return rank;
	}

	@Override
	public void setRank(int rank) {
		this.rank = rank;
		
	}

	@Override
	public int getFrequency() {
		
		return frequency;
	}

	@Override
	public void setFrequency(int frequency) {
		this.frequency = frequency;
		
	}

	@Override
	public double getProportion() {
		
		return proportion;
	}

	@Override
	public void setProportion(double proportion) {
		this.proportion = proportion;
		
	}

	@Override
	public double getCumulativeProportion() {
		
		return cumulativeProportion;
	}

	@Override
	public void setCumulativeProportion(double cumulativeProportion) {
	this.cumulativeProportion = cumulativeProportion;
		
	}

}
