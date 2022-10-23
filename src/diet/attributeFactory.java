package diet;

public class attributeFactory {
	public static animalFactory createFactory(String attr) {
		if(attr=="Carnivore")
			return new Carnivore();
		else if(attr=="Herbivore")
			return new Herbivore();
		else {
			return new Omnivore();
		}
		
	}
}
