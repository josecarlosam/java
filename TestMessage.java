import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class TestMessage {

    public static void main(String[] args) {
        
        Message message = new Message();

        for(int i = 0; i < 10; i++){
            message.newMessage(1,"Apple",10.00);       
            message.newMessage(2,"Orange",20.00,4);
            message.newMessage(3,"Banana",30.00,5,"add"); //add, subtract, or multiply
            message.newMessage(3,"Banana",40.00,2,"subtract"); //add, subtract, or multiply
            message.newMessage(3,"Apple",40.00,2,"multiply"); //add, subtract, or multiply
        }
    }
}

class Message {
    Sale sale;
    ArrayList<Sale> saleList = new ArrayList<Sale>();
    Product product;
    ArrayList<Product> productList = new ArrayList<Product>();
    Map<String, Product> productMap = new HashMap<String, Product>();
    int countSales = 0;
    int countSales50 = 0;

    public Message() {

    }
    public void newMessage(int messageType, String product, Double value, int numberOfSales, String operation){
        sale = new Sale(messageType,product,value,numberOfSales,operation);
        saleList.add(sale);
        validateProduct(product,value,numberOfSales,operation);
        printLog();
    }
    public void newMessage(int messageType, String product, Double value, int numberOfSales){
        sale = new Sale(messageType,product,value,numberOfSales);
        saleList.add(sale);
        validateProduct(product,value,numberOfSales,"add");
        printLog();
    }
    public void newMessage(int messageType, String product, Double value){
        sale = new Sale(messageType,product,value);
        saleList.add(sale);
        validateProduct(product,value,1,"add");
        printLog();
    }
    public void validateProduct(String product, Double value, int numberOfSales, String operation){

        if (productMap.containsKey(product)){
            this.product = productMap.get(product);
            this.product.setOperation(operation);
            this.product.setValue(value);
            this.product.setNumberOfSales(numberOfSales);
            this.product.setTotal(value,numberOfSales,this.product.getTotal());
        } else {
            this.product = new Product(product,value,numberOfSales,operation);
            productMap.put(product, this.product);
            productList.add(this.product);
        }

    }
    public void printLog(){
        countSales++;
        countSales50++;
        if (countSales == 10){
            System.out.println("List: " + saleList.size());
            countSales = 0;    
            for(Product p : productList){
                System.out.println("Product: " + p.getProduct() + " - Number Of Sales: " + p.getNumberOfSales() + " - Total Value: " + p.getTotal() );
            }            
        }
        if(countSales50 == 50){
            // System.out.println("List: " + saleList.size());
            countSales50 = 0;
            System.out.println("Application Paused");   
            for(Sale s : saleList){
                countSales50++;
                System.out.println("Sales Number: " + countSales50 + " - Message Type: " + s.getMessageType() + " - Product: " + s.getProduct() + " - Value: " + s.getValue() + " - Number of Sales: " + (s.getNumberOfSales() != 0 ? s.getNumberOfSales() : 1) + " - Operation: " + (s.getOperation() != null ? s.getOperation() : "" ) );
            }   
            countSales50 = 0;               
        }        
    }
}

class Sale{
    private int messageType;
    private String product;
    private Double value;
    private int numberOfSales;
    private String operation;

    public Sale(){

    }
    public Sale(int messageType, String product, Double value, int numberOfSales, String operation){
        this.messageType = messageType;
        this.product = product;
        this.value = value;
        this.numberOfSales = numberOfSales;
        this.operation = operation;
        // System.out.println("Teste " + messageType + " - " + product + " - " + value + " - "  + numberOfSales + " - "  + operation + " - " );
    }    
    public Sale(int messageType, String product, Double value, int numberOfSales){
        this.messageType = messageType;
        this.product = product;
        this.value = value;
        this.numberOfSales = numberOfSales;
        // System.out.println("Teste " + messageType + " - " + product + " - " + value + " - "  + numberOfSales + " - "  + operation + " - " );
    }
    public Sale(int messageType, String product, Double value){
        this.messageType = messageType;
        this.product = product;
        this.value = value;
        // System.out.println("Teste " + messageType + " - " + product + " - " + value + " - "  + numberOfSales + " - "  + operation + " - " );
    }    

	public int getMessageType()
	{
		return this.messageType;
	}

	public void setMessageType(int messageType)
	{
		this.messageType = messageType;
	}

	public String getProduct()
	{
		return this.product;
	}

	public void setProduct(String product)
	{
		this.product = product;
	}

	public Double getValue()
	{
		return this.value;
	}

	public void setValue(Double value)
	{
		this.value = value;
	}

	public int getNumberOfSales()
	{
		return this.numberOfSales;
	}

	public void setNumberOfSales(int numberOfSales)
	{
		this.numberOfSales = numberOfSales;
	}

	public String getOperation()
	{
		return this.operation;
	}

	public void setOperation(String operation)
	{
		this.operation = operation;
	}
}

class Product{
    private String product;
    private Double value;
    private int numberOfSales;
    private String operation;
    private Double total = 0.0;

    public Product(){

    }

    public Product(String product, Double value, int numberOfSales, String operation){
        this.product = product;
        this.value = value;
        this.numberOfSales = numberOfSales;
        this.operation = operation;
        switch (operation) {  //add, subtract, or multiply
            case "add":
                this.total = (value * numberOfSales);
                break;
            case "subtract":
                this.total = (value * numberOfSales);
                break;
            case "multiply":
                this.total = (value * numberOfSales);
                break;                        
            default:
                break;
        }            
    }

	public String getProduct()
	{
		return this.product;
	}

	public void setProduct(String product)
	{
		this.product = product;
	}

	public Double getValue()
	{
		return this.value;
	}

	public void setValue(Double value)
	{       
        switch (operation) {  //add, subtract, or multiply
            case "add":
                this.value = this.value  + value;
                break;
            case "subtract":
                this.value = this.value  - value;
                break;
            case "multiply":
                this.value = this.value  * value;
                break;                        
            default:
                break;
        }              
	}

	public int getNumberOfSales()
	{
		return this.numberOfSales;
	}

	public void setNumberOfSales(int numberOfSales)
	{
        switch (operation) {  //add, subtract, or multiply
            case "add":
                this.numberOfSales = this.numberOfSales  + numberOfSales;
                break;
            case "subtract":
                this.numberOfSales = this.numberOfSales  - numberOfSales;
                break;
            case "multiply":
                this.numberOfSales = this.numberOfSales  * numberOfSales;
                break;                        
            default:
                break;
        }           
    }
    
	public String getOperation()
	{
		return this.operation;
	}

	public void setOperation(String operation)
	{
        this.operation = operation;            
    }  
    
	public Double getTotal()
	{
		return this.total;
	}

	public void setTotal(Double value, int numberOfSales, Double total)
	{     
        switch (operation) {  //add, subtract, or multiply
            case "add":
                this.total = total + (value * numberOfSales);
                break;
            case "subtract":
                this.total = total - (value * numberOfSales);
                break;
            case "multiply":
                this.total = total * (value * numberOfSales);
                break;                        
            default:
                break;
        }            
	}    
}

