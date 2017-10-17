package parts;

import storage.Product;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Alexandr on 16.10.2017.
 */
public class Storage extends DevicePart {
    HashMap<Product, Double> prods;

    public Storage(HashMap<Product, Double> _prods){
        prods = _prods;
    }

    public void addProducts(Product pr, double we){
        if(prods.get(pr) != null)
            prods.put(pr,prods.get(pr)+we);
        else
            prods.put(pr, we);
    }

    public boolean checkAmount(Product pr, double amount){
        return (prods.get(pr)-amount) >= 0;
    }

    public void decreaseAmount(Product pr, double amount){
        prods.put(pr, prods.get(pr) - amount);
    }
}
