package Main;

import java.util.Random;

public class Bathroom extends Product implements Checker {
	Random rand = new Random();
	
	private String fragile, material;

	public Bathroom(String name, String type, Integer price, String ID, Integer stock, String fragile, String material) {
		super(name, type, price, ID, stock);
		this.fragile = fragile;
		this.material = material;
	}

	public String getFragile() {
		return fragile;
	}

	public void setFragile(String fragile) {
		this.fragile = fragile;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
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
