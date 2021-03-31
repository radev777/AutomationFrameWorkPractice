package AutoFramework;

/**
 * Created by dimitarrad
 * on 2/24/2021
 */
public class ItemDetails {

    public String itemName;
    public String price;
    public String quant;
    public String size;
    public String col;

    public ItemDetails(String item, String price, String quant, String size, String col) {
        this.itemName = item;
        this.price = price;
        this.quant = quant;
        this.size = size;
        this.col = col;
    }

}
