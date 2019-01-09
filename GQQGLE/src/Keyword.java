public class Keyword {
	public String name;
	public double weight;
	
	public Keyword(String name, double weight) {  //constructor
		this.name = name;
		this.weight = weight;
	}
	@Override                     //被改寫
	public String toString() {
		return "[" + name +", " + weight +"]"; 
	}
}
