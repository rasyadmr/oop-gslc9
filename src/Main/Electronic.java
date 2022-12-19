package Main;

import java.util.Random;

public class Electronic extends Product implements Checker {
	Random rand = new Random();
	
	private String fragile, rechargeable, waterproof;

	public Electronic(String name, String type, Integer price, String ID, Integer stock, String fragile, String rechargeable,
			String waterproof) {
		super(name, type, price, ID, stock);
		this.fragile = fragile;
		this.rechargeable = rechargeable;
		this.waterproof = waterproof;
	}

	public String getFragile() {
		return fragile;
	}

	public void setFragile(String fragile) {
		this.fragile = fragile;
	}

	public String getRechargeable() {
		return rechargeable;
	}

	public void setRechargeable(String rechargeable) {
		this.rechargeable = rechargeable;
	}

	public String getWaterproof() {
		return waterproof;
	}

	public void setWaterproof(String waterproof) {
		this.waterproof = waterproof;
	}

	@Override
	public void checkDefect() {
		Integer cracked = 0, brokenCharge = 0;
		System.out.println("Defect items");
		System.out.println("=========================");
		
		// Apabila barang pecah belah
		if (this.fragile.equals("Yes")) {
			// Menghitung barang pecah secara random setidaknya sampai 1/4 stocknya
			cracked = rand.nextInt(this.getStock() / 4);
			this.setStock(this.getStock() - cracked);
			System.out.println("Cracked: " + cracked);
		}
		
		// Apabila barang dapat dicharge
		if (this.rechargeable.equals("Yes")) {
			// Menghitung barang tidak dapat dicharge secara random setidaknya sampai 1/4 stocknya
			brokenCharge = rand.nextInt(this.getStock() / 4);
			this.setStock(this.getStock() - brokenCharge);
			System.out.println("Broken Charge: " + brokenCharge);
		}
		
		if (this.rechargeable.equals("No") && this.fragile.equals("No")) {
			// Tidak mungkin ada barang cacat
			System.out.println("No defect items found!");
		}
		
		System.out.println("=========================");
		System.out.println("Total defect items: " + (cracked + brokenCharge));
		System.out.println("Current stocks: " + this.getStock());
	}

}
