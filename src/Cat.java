
public class Cat {
	
	private String name;
	private int age;
	private static int cats;
	
	public Cat(String name, int age) {
		this.name = name;
		this.age = age;
		cats++;
	}
	
	public static int catCount() {
		return cats;
	}
	
	public void meow() {
		System.out.println("Meow! I am " + this.name + " and I am " + this.age + " years old");
	}
}
