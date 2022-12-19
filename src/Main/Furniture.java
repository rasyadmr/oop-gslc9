package Main;

import java.util.Random;

public class Furniture extends Product implements Checker {
	Random rand = new Random();
	
	private String material, flameable, wheeled;
	
	public Furniture(String name, String type, Integer price, String ID, Integer stock, String material, String flameable,
			String wheeled) {
		super(name, type, price, ID, stock);
		this.material = material;
		this.flameable = flameable;
		this.wheeled = wheeled;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public String getFlameable() {
		return flameable;
	}

	public void setFlameable(String flameable) {
		this.flameable = flameable;
	}

	public String getWheeled() {
		return wheeled;
	}

	public void setWheeled(String wheeled) {
		this.wheeled = wheeled;
	}

	@Override
	public void checkDefect() {
		Integer brokenWheel = 0;
		System.out.println("Defect items");
		System.out.println("=========================");
		
		// Apabila barang memiliki roda
		if (this.wheeled.equals("Yes")) {
			// Menghitung barang roda yang cacat secara random setidaknya sampai 1/4 stocknya
			brokenWheel = rand.nextInt(this.getStock() / 4);
			this.setStock(this.getStock() - brokenWheel);
			System.out.println("Broken wheel: " + brokenWheel);
		} else {
			// Tidak mungkin ada barang cacat
			System.out.println("No defect items found!");
		}
		
		System.out.println("=========================");
		System.out.println("Total defect items: " + brokenWheel);
		System.out.println("Current stocks: " + this.getStock());
	}

}
