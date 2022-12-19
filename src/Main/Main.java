package Main;

/*
 * ISUNIB merupakan aplikasi pendataan bagi karyawan ISUNIB untuk memanage produk-produk yang akan dijual
 * ISUNIB mirip dengan sebuah toko yang bernama IKEA
*/

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

public class Main {
	ArrayList<Product> data = new ArrayList<>(); // Array list untuk menyimpan data product terdaftar
	Scanner input = new Scanner(System.in); // Variabel untuk input
	Random rand = new Random(); // Variabel untuk Random

	public static void main(String[] args) {
		new Main();
	}
	
	// Fungsi untuk mengosongkan console
	public void clear() {
		for (int i = 0; i < 50; i++) System.out.println("");
	}
	
	// Fungsi untuk menunggu enter
	public void enter() {
		System.out.println("=========================");
		System.out.println("Press ENTER to continue");
		input.nextLine();
	}
	
	// Fungsi untuk mencari index 
	public Integer getIndex() {
		String ID;
		
		// Mendapatkan input ID
		System.out.print("Input product ID: ");
		ID = input.nextLine();
		
		Boolean flag = false; // Untuk penanda apakah data ditemukan atau tidak
		Integer index; // Untuk menampung indexnya
		
		// Looping untuk mencari index
		for (index = 0; index < data.size(); index++) {
			if (ID.equals(data.get(index).getID())) {  // Apakah ID yang diinput sama dengan ID di data
				flag = true; // Apabila ditemukan, set true
				break;
			}
		}
		
		if (flag == false) {
			return -1; // Apabila tidak ditemukan return -1
		} else {
			return index; // Apabila ditemukan return indexnya
		}
	}
	
	// Membuat ID
	public String generateID(String type) {
		String result = ""; // Inisiasi hasil ID
		
		// Dua huruf depan berdasarkan tipe produk
		if (type.equals("Furniture")) {
			result = "FN";
		} else if (type.equals("Electronic")) {
			result = "ET";
		} else if (type.equals("Decoration")) {
			result = "DR";
		} else if (type.equals("Kitchen")) {
			result = "KC";
		} else {
			result = "BR";
		}
		
		// Menambahkan 3 angka random untuk ID
		for (int i = 0; i < 3; i++) {
			result += rand.nextInt(10);
		}
		
		// Mengecek apakah data produk ada isinya
		if (data.isEmpty()) {
			// Bila tidak ada, langsung return
			return result;
		} else {
			// Bila ada, dicek supaya ID tidak sama
			Boolean flag = false; // Untuk membedakan apakah ada yang sama atau tidak
			for (Product item: data) {
				if (item.getID().equals(result)) {
					flag = true;
					break;
				}
			}
			
			// Bila tidak ada yang sama, langsung return
			if (flag == false) {
				return result;
			}
		}
		
		// Bila ada yang sama, lakukan rekursif
		return generateID(type);
	}
	
	// Main untuk menu
	public Main() {
		Integer choose; // Variabel untuk pilihan menu
		do {
			clear();
			// Tampilan menu
			System.out.println("Welcome to ISUNIB");
			System.out.println("========================================");
			System.out.println("1. Add new product");
			System.out.println("2. View all registered product");
			System.out.println("3. Check registered product");
			System.out.println("4. Remove registered product");
			System.out.println("5. Exit");
			System.out.println("6. Auto generate registered product");
			System.out.println("========================================");
			
			System.out.print(">> ");
			choose = input.nextInt(); // Input untuk pilihan menu
			input.nextLine();
			
			// Menu yang dipilih
			switch(choose) {
			case 1:
				addProduct(); // Perbarui data yang lama
				System.out.println("New product succesfully added!");
				enter();
				break;
			case 2:
				viewProduct(); // Memperlihatkan produk terdaftar
				enter();
				break;
			case 3:
				checkProduct(); // Mengecek produk apakah ada yang cacat
				enter();
				break;
			case 4:
				removeProduct(); // Menghapus produk dari daftar
				enter();
				break;
			case 5:
				System.out.println("Thank you for using our application!");
				System.exit(0); // Mematikan program
			case 6:
				initialize();
				break;
			default:
				System.out.println("Unknown menu!");
				continue;
			}
			
		} while (choose != 5);
	}
	
	public void addProduct() {
		clear();
		
		// Deklarasi variabel untuk kebutuhan class
		String name, type;
		Integer price, stock;
		
		// Validasi nama produk maksimal 15 karakter
		do {
			System.out.print("Enter product name [Max. 15]: ");
			name = input.nextLine();
		} while (name.length() > 15);
		
		// Validasi tipe produk antara Furniture/Electronic/Decoration/Kitchen/Bathroom
		do {
			System.out.print("Enter product type [Furniture | Electronic | Decoration | Kitchen | Bathroom]: ");
			type = input.nextLine();
		} while (!(type.equals("Furniture")) && !(type.equals("Electronic")) && !(type.equals("Decoration")) && 
				!(type.equals("Kitchen")) && !(type.equals("Bathroom")));
		
		// Validasi harga produk antara 1 sampai 1000000
		do {
			System.out.print("Enter product price [1 - 1000000]: ");
			price = input.nextInt(); input.nextLine();
		} while (price < 1 || price > 1000000);
		
		// Validasi stok produk antara 1 sampai 1000
		do {
			System.out.print("Enter product stock [1 - 1000]: ");
			stock = input.nextInt(); input.nextLine();
		} while (stock < 1 || stock > 1000);
		
		// Penambahan parameter untuk memenuhi kebutuhan masing-masing tipe produk
		if (type.equals("Furniture")) {
			String material, flameable, wheeled;
			
			// Validasi jenis material antara Wood/Metal
			do {
				System.out.print("Input material [Wood | Metal]: ");
				material = input.nextLine();
			} while (!(material.equals("Wood")) && !(material.equals("Metal")));
			
			// Validasi apakah barang mudah terbakar atau tidak
			do {
				System.out.print("Flameable [Yes/No]: ");
				flameable = input.nextLine();
			} while (!(flameable.equals("Yes")) && !(flameable.equals("No")));
			
			// Validasi apakah barang memiliki roda
			do {
				System.out.print("Wheeled [Yes/No]: ");
				wheeled = input.nextLine();
			} while (!(wheeled.equals("Yes")) && !(wheeled.equals("No")));
			
			// Memasukan object Furniture kedalam data
			data.add(new Furniture(name, type, price, generateID(type), stock, material, flameable, wheeled));
		} else if (type.equals("Electronic")) {
			String fragile, rechargeable, waterproof;
			
			// Validasi apakah barang mudah pecah
			do {
				System.out.print("Fragile [Yes/No]: ");
				fragile = input.nextLine();
			} while (!(fragile.equals("Yes")) && !(fragile.equals("No")));
			
			// Validasi apakah barang dapat dicharge
			do {
				System.out.print("Rechargeable [Yes/No]: ");
				rechargeable = input.nextLine();
			} while (!(rechargeable.equals("Yes")) && !(rechargeable.equals("No")));
			
			// Validasi apakah barang tahan air
			do {
				System.out.print("Waterproof [Yes/No]: ");
				waterproof = input.nextLine();
			} while (!(waterproof.equals("Yes")) && !(waterproof.equals("No")));
			
			// Memasukan object Electronic kedalam data
			data.add(new Electronic(name, type, price, generateID(type), stock, fragile, rechargeable, waterproof));
		}  else if (type.equals("Decoration")) {
			String material, fragile;
			Integer weight;
			
			// Validasi jenis material antara Wood/Metal/Glass
			do {
				System.out.print("Input material [Wood | Metal | Glass]: ");
				material = input.nextLine();
			} while (!(material.equals("Wood")) && !(material.equals("Metal")) && !(material.equals("Glass")));

			// Apabila produk terbuat dari gelas maka barang mudah pecah
			if (material.equals("Glass")) {
				fragile = "Yes";
			} else {
				fragile = "No";
			}
			
			// Validasi berat produk antara 1 - 100
			do {
				System.out.print("Input product weight [1 - 100]kg: ");
				weight = input.nextInt(); input.nextLine();
			} while (weight < 1 || weight > 100);
			
			// Memasukan object Decoration kedalam data
			data.add(new Decoration(name, type, price, generateID(type), stock, material, fragile, weight));
		} else if (type.equals("Kitchen")) {
			String size, sharp;
			
			// Validasi ukuran produk antara Small/Medium/Large
			do {
				System.out.print("Input size [Small | Medium | Large]: ");
				size = input.nextLine();
			} while (!(size.equals("Small")) && !(size.equals("Medium")) && !(size.equals("Large")));
			
			// Validasi apakah produk benda tajam
			do {
				System.out.print("Sharp object [Yes/No]: ");
				sharp = input.nextLine();
			} while (!(sharp.equals("Yes")) && !(sharp.equals("No")));
			
			// Memasukan object Kitchen kedalam data
			data.add(new Kitchen(name, type, price, generateID(type), stock, size, sharp));
		} else {
			String fragile, material;
			
			// Validasi jenis material antara Wood/Metal/Glass
			do {
				System.out.print("Input material [Wood | Metal | Glass]: ");
				material = input.nextLine();
			} while (!(material.equals("Wood")) && !(material.equals("Metal")) && !(material.equals("Glass")));

			// Apabila produk terbuat dari gelas maka barang mudah pecah
			if (material.equals("Glass")) {
				fragile = "Yes";
			} else {
				fragile = "No";
			}
			
			// Memasukan object Bathroom kedalam data
			data.add(new Bathroom(name, type, price, generateID(type), stock, fragile, material));
		}
	}
	
	public void viewProduct() {
		clear();
		System.out.println("View Products");
		
		// Mengecek apakah data tersebut kosong atau tidak
		if (data.isEmpty()) { // Data kosong maka
			System.out.println("No product is registered!");
		} else { // Data terisi maka
			Integer i = 1; // Inisiasi iterasi dari 1
			System.out.println("Furniture");
			System.out.println("====================================================================================="
					+ "===========");
			System.out.println("| No | ID    | Name            | Type       | Price   | Stock | Material | Flameable"
					+ " | Wheeled |");
			
			// Foreach untuk setiap type produk
			for (Product item: data) {
				if (item instanceof Furniture) { // Apabila item merupakan subclass Furniture
					System.out.printf("| %-2d | %-5s | %-15s | %-10s | %-7d | %-5d | %-8s | %-9s | %-7s |\n", i,
							item.getID(), item.getName(), item.getType(), item.getPrice(), item.getStock(), 
							((Furniture)item).getMaterial(), ((Furniture)item).getFlameable(),
							((Furniture)item).getWheeled());
					i++;
				}
			}
			
			if (i == 1) { // Apabila tipe produk tidak ada
				System.out.println("No registered in this product type!");
			}
			
			System.out.println("====================================================================================="
					+ "===========");
			
			i = 1;
			System.out.println("Electronic");
			System.out.println("======================================================================================="
					+ "==============");
			System.out.println("| No | ID    | Name            | Type       | Price   | Stock | Fragile | Rechargeable"
						+ " | Waterproof |");
			
			for (Product item: data) {
				if (item instanceof Electronic) { // Apabila item merupakan subclass Electronic
					System.out.printf("| %-2d | %-5s | %-15s | %-10s | %-7d | %-5d | %-7s | %-12s | %-10s |\n", i,
							item.getID(), item.getName(), item.getType(), item.getPrice(), item.getStock(), 
							((Electronic)item).getFragile(), ((Electronic)item).getRechargeable(),
							((Electronic)item).getWaterproof());
					i++;
				}
			}
			
			if (i == 1) { // Apabila tipe produk tidak ada
				System.out.println("No registered in this product type!");
			}
			
			System.out.println("======================================================================================="
					+ "==============");
			
			i = 1;
			System.out.println("Decoration");
			System.out.println("==================================================================================="
					+ "==========");
			System.out.println("| No | ID    | Name            | Type       | Price   | Stock | Material | Fragile"
						+ " | Weight |");
			
			for (Product item: data) {
				if (item instanceof Decoration) { // Apabila item merupakan subclass Decoration
					System.out.printf("| %-2d | %-5s | %-15s | %-10s | %-7d | %-5d | %-8s | %-7s | %-6d |\n", i,
							item.getID(), item.getName(), item.getType(), item.getPrice(), item.getStock(), 
							((Decoration)item).getMaterial(), ((Decoration)item).getFragile(),
							((Decoration)item).getWeight());
					i++;
				}
			}
			
			if (i == 1) { // Apabila tipe produk tidak ada
				System.out.println("No registered in this product type!");
			}
			
			System.out.println("==================================================================================="
					+ "==========");
			
			i = 1;
			System.out.println("Kitchen");
			System.out.println("================================================================================");
			System.out.println("| No | ID    | Name            | Type       | Price   | Stock | Size   | Sharp |");
			
			for (Product item: data) {
				if (item instanceof Kitchen) { // Apabila item merupakan subclass Kitchen
					System.out.printf("| %-2d | %-5s | %-15s | %-10s | %-7d | %-5d | %-6s | %-5s |\n", i,
							item.getID(), item.getName(), item.getType(), item.getPrice(), item.getStock(), 
							((Kitchen)item).getSize(), ((Kitchen)item).getSharp());
					i++;
				}
			}
			
			if (i == 1) { // Apabila tipe produk tidak ada
				System.out.println("No registered in this product type!");
			}
			
			System.out.println("================================================================================");
			
			i = 1;
			System.out.println("Bathroom");
			System.out.println("====================================================================================");
			System.out.println("| No | ID    | Name            | Type       | Price   | Stock | Material | Fragile |");
			
			for (Product item: data) {
				if (item instanceof Bathroom) { // Apabila item merupakan subclass Bathroom
					System.out.printf("| %-2d | %-5s | %-15s | %-10s | %-7d | %-5d | %-8s | %-7s |\n", i,
							item.getID(), item.getName(), item.getType(), item.getPrice(), item.getStock(), 
							((Bathroom)item).getMaterial(), ((Bathroom)item).getFragile());
					i++;
				}
			}
			
			if (i == 1) { // Apabila tipe produk tidak ada
				System.out.println("No registered in this product type!");
			}
			
			System.out.println("====================================================================================");
		}
	}
	
	public void checkProduct() {
		// Deklarasi kebutuhan variable cek produk
		Integer choose, index;
		while (true) {
			Integer i = 1; // Inisiasi iterasi dari 1
			clear();
			System.out.println("Check product");
			System.out.println("===============");
			System.out.println("1. Furniture");
			System.out.println("2. Electronic");
			System.out.println("3. Decoration");
			System.out.println("4. Kitchen");
			System.out.println("5. Bathroom");
			System.out.println("===============");
			
			System.out.print("Choose product type [1 - 5]: ");
			choose = input.nextInt(); input.nextLine(); // Memilih type kategori berdasarkan menu
			
			System.out.println("| No | ID    | Name            | Type       | Price   | Stock |");
			
			// Foreach untuk menampilkan data yang sesuai dengan tipenya
			for (Product item: data) {
				switch (choose) {
				case 1:
					if (item instanceof Furniture) { // Apabila item merupakan subclass Furniture
						System.out.printf("| %-2d | %-5s | %-15s | %-10s | %-7d | %-5d |\n", i,
								item.getID(), item.getName(), item.getType(), item.getPrice(), item.getStock());
						i++;
					}
					
					break;
				case 2:
					if (item instanceof Electronic) { // Apabila item merupakan subclass Electronic
						System.out.printf("| %-2d | %-5s | %-15s | %-10s | %-7d | %-5d |\n", i,
								item.getID(), item.getName(), item.getType(), item.getPrice(), item.getStock());
						i++;
					}
					
					break;
				case 3:
					if (item instanceof Decoration) { // Apabila item merupakan subclass Decoration
						System.out.printf("| %-2d | %-5s | %-15s | %-10s | %-7d | %-5d |\n", i,
								item.getID(), item.getName(), item.getType(), item.getPrice(), item.getStock());
						i++;
					}
					
					break;
				case 4:
					if (item instanceof Kitchen) { // Apabila item merupakan subclass Kitchen
						System.out.printf("| %-2d | %-5s | %-15s | %-10s | %-7d | %-5d |\n", i,
								item.getID(), item.getName(), item.getType(), item.getPrice(), item.getStock());
						i++;
					}
					
					break;
				case 5:
					if (item instanceof Bathroom) { // Apabila item merupakan subclass Bathroom
						System.out.printf("| %-2d | %-5s | %-15s | %-10s | %-7d | %-5d |\n", i,
								item.getID(), item.getName(), item.getType(), item.getPrice(), item.getStock());
						i++;
					}
					
					break;
				default:
					break;
				}
			}
			
			// Melakukan checking berdasarkan subclassnya
			switch (choose) {
			case 1:
				index = getIndex(); // Mendapatkan indexnya dari inputan ID
				if (index < 0) {
					System.out.println("Invalid ID");
				} else {
					Product temp = data.get(index); // Memasukan datanya ke dalam variabel temp
					((Furniture)temp).checkDefect();
				}
				return;
			case 2:
				index = getIndex(); // Mendapatkan indexnya dari inputan ID
				if (index < 0) {
					System.out.println("Invalid ID");
				} else {
					Product temp = data.get(index); // Memasukan datanya ke dalam variabel temp
					((Electronic)temp).checkDefect(); // Melakukan method checkDefect() sesuai dengan subclassnya
				}
				return;
			case 3:
				index = getIndex(); // Mendapatkan indexnya dari inputan ID
				if (index < 0) {
					System.out.println("Invalid ID");
				} else {
					Product temp = data.get(index); // Memasukan datanya ke dalam variabel temp
					((Decoration)temp).checkDefect(); // Melakukan method checkDefect() sesuai dengan subclassnya
				}
				return;
			case 4:
				index = getIndex(); // Mendapatkan indexnya dari inputan ID
				if (index < 0) {
					System.out.println("Invalid ID");
				} else {
					Product temp = data.get(index); // Memasukan datanya ke dalam variabel temp
					((Kitchen)temp).checkDefect(); // Melakukan method checkDefect() sesuai dengan subclassnya
				}
				return;
			case 5:
				index = getIndex(); // Mendapatkan indexnya dari inputan ID
				if (index < 0) {
					System.out.println("Invalid ID");
				} else {
					Product temp = data.get(index); // Memasukan datanya ke dalam variabel temp
					((Bathroom)temp).checkDefect(); // Melakukan method checkDefect() sesuai dengan subclassnya
				}
				return;
			default:
				System.out.println("Invalid input type!");
				enter();
				break;
			}
		}
	}
	
	public void removeProduct() {
		// Deklarasi variabel yang akan digunakan untuk menghapus
		Integer choose, index;
		while (true) {
			Integer i = 1; // Inisiasi iterasi dari 1
			clear();
			System.out.println("Delete product");
			System.out.println("===============");
			System.out.println("1. Furniture");
			System.out.println("2. Electronic");
			System.out.println("3. Decoration");
			System.out.println("4. Kitchen");
			System.out.println("5. Bathroom");
			System.out.println("===============");
			
			System.out.print("Choose product type: ");
			choose = input.nextInt(); // Input untuk menampilkan data dengan jenis yang diinginkan
			input.nextLine();
			
			clear();
			System.out.println("===============================================================");
			System.out.println("| No | ID    | Name            | Type       | Price   | Stock |");
			
			// Foreach untuk menampilkan data berdasarkan tipe yang diinginkan
			for (Product item: data) {	
				switch (choose) {
				case 1:
					if (item instanceof Furniture) { // Apabila item merupakan subclass Furniture
						System.out.printf("| %-2d | %-5s | %-15s | %-10s | %-7d | %-5d |\n", i,
								item.getID(), item.getName(), item.getType(), item.getPrice(), item.getStock());
						i++;
					}
					
					continue;
				case 2:
					if (item instanceof Electronic) { // Apabila item merupakan subclass Electronic
						System.out.printf("| %-2d | %-5s | %-15s | %-10s | %-7d | %-5d |\n", i,
								item.getID(), item.getName(), item.getType(), item.getPrice(), item.getStock());
						i++;
					}
					
					continue;
				case 3:
					if (item instanceof Decoration) { // Apabila item merupakan subclass Decoration
						System.out.printf("| %-2d | %-5s | %-15s | %-10s | %-7d | %-5d |\n", i,
								item.getID(), item.getName(), item.getType(), item.getPrice(), item.getStock());
						i++;
					}
					
					continue;
				case 4:
					if (item instanceof Kitchen) { // Apabila item merupakan subclass Kitchen
						System.out.printf("| %-2d | %-5s | %-15s | %-10s | %-7d | %-5d |\n", i,
								item.getID(), item.getName(), item.getType(), item.getPrice(), item.getStock());
						i++;
					}
					
					continue;
				case 5:
					if (item instanceof Bathroom) { // Apabila item merupakan subclass Bathroom
						System.out.printf("| %-2d | %-5s | %-15s | %-10s | %-7d | %-5d |\n", i,
								item.getID(), item.getName(), item.getType(), item.getPrice(), item.getStock());
						i++;
					}
					
					continue;
				default:
					System.out.println("Invalid input!");
					return;
				}
			}
			
			System.out.println("===============================================================");
			
			System.out.print("Enter ID: ");
			String ID = input.nextLine(); // Input untuk mendapatkan ID yang ingin dihapus
			
			Boolean flag = false; // Penandaan apakah data yang sesuai ada atau tidak
			
			// Looping untuk mencari data yang memiliki ID yang sesuai
			for (index = 0; index < data.size(); index++) {
				if (ID.equals(data.get(index).getID())) { // Mengecek apakah ID sama atau tidak
					flag = true;
					break;
				}
			}
			
			Product temp = data.get(index); // Memasukan object ke variabel temp
			
			if (flag == false) { // Apabila tidak ada maka langsung ke menu
				System.out.println("Invalid ID!");
				return;
			} else { // Apabila data ada maka dihapus
				data.remove(temp); // Penghapusan
				System.out.println("Registered product deleted");
				return;
			}
		}
	}
	
	public void initialize() {
		data.add(new Furniture("Kasur", "Furniture", 1000000, generateID("Furniture"), 100, "Wood", "Yes", "No"));
		data.add(new Furniture("Drawer", "Furniture", 250000, generateID("Furniture"), 200, "Wood", "Yes", "Yes"));
		data.add(new Electronic("Lamp", "Electronic", 100000, generateID("Electronic"), 500, "Yes", "No", "No"));
		data.add(new Electronic("Study Lamp", "Electronic", 200000, generateID("Electronic"), 400, "Yes", "Yes", "No"));
		data.add(new Decoration("Vas", "Decoration", 350000, generateID("Decoration"), 100, "Glass", "Yes", 25));
		data.add(new Decoration("Pot", "Decoration", 125000, generateID("Decoration"), 150, "Metal", "No", 20));
		data.add(new Kitchen("Spoon", "Kitchen", 50000, generateID("Kitchen"), 600, "Small", "No"));
		data.add(new Kitchen("Knife", "Kitchen", 75000, generateID("Kitchen"), 550, "Small", "Yes"));
		data.add(new Bathroom("Sink", "Bathroom", 900000, generateID("Bathroom"), 40, "Yes", "Glass"));
		data.add(new Bathroom("Bathub", "Bathroom", 1000000, generateID("Bathroom"), 80, "Yes", "Glass"));
	}

}
