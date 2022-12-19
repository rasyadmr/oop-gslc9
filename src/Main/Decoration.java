package Main;

import java.util.Random;

public class Decoration extends Product implements Checker {
	Random rand = new Random();
	
	private String material, fragile;
	private Integer weight;
	
	public Decoration(String name, String type, Integer price, String ID, Integer stock, String material, String fragile,
			Integer weight) {
		super(name, type, price, ID, stock);
		this.material = material;
		this.fragile = fragile;
		this.weight = weight;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public String getFragile() {
		return fragile;
	}

	public void setFragile(String fragile) {
		this.fragile = fragile;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	@Override
	public void checkDefect() {
		Integer cracked = 0;
		System.out.println("Defect items");
		System.out.println("=========================");
		
		// Apabila barang pecah belah
		if (this.fragile.equals("Yes")) {
			// Menghitung barang pecah secara random setidaknya sampai 1/4 stocknya
			cracked = rand.nextInt(this.getStock() / 4);
			this.setStock(this.getStock() - cracked);
			System.out.println("Cracked: " + cracked);
		} else {
			// Tidak mungkin ada barang cacat
			System.out.println("No defect items found!");
		}
		
		System.out.println("=========================");
		System.out.println("Total defect items: " + cracked);
		System.out.println("Current stocks: " + this.getStock());
	}

}
