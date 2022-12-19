package Main;

public abstract class Product {
	private String name;
	private String type;
	private Integer price;
	private String ID;
	private Integer stock;
	
	public Product(String name, String type, Integer price, String ID, Integer stock) {
		this.name = name;
		this.type = type;
		this.price = price;
		this.ID = ID;
		this.stock = stock;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public String getID() {
		return ID;
	}

	public void setID(String ID) {
		this.ID = ID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}
	
}
