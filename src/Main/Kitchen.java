package Main;

public class Kitchen extends Product implements Checker {
	private String size, sharp;

	public Kitchen(String name, String type, Integer price, String ID, Integer stock, String size, String sharp) {
		super(name, type, price, ID, stock);
		this.size = size;
		this.sharp = sharp;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getSharp() {
		return sharp;
	}

	public void setSharp(String sharp) {
		this.sharp = sharp;
	}

	@Override
	public void checkDefect() {
		System.out.println("=========================");
		System.out.println("No defects items in this type");
		System.out.println("Current stocks: " + this.getStock());
	}

}
