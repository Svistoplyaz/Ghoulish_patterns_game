import parts.*;
import proxy.*;
import storage.*;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Alexandr on 16.10.2017.
 */
public class Main {
    public static ArrayList<Product> allProds;

    public static void main(String[] args) {
        initProds();

        Chef ch = new Chef();

        Console cn = new Console();
        ProxyConsole pr = new ProxyConsole(cn);

        HashMap<Product, Double> adding = new HashMap<>();
        adding.put(allProds.get(0), 5.0);
        adding.put(allProds.get(2), 15.0);
        pr.addRecipe(1, new Recipe("Блюдо 1", adding, RType.salad));

        adding = new HashMap<>();
        adding.put(allProds.get(4), 7.0);
        adding.put(allProds.get(2), 8.0);
        pr.addRecipe(0, new Recipe("Блюдо 2", adding, RType.soup));

        Storage st = new Storage(initStorage());
        ProxyStorage pst = new ProxyStorage(st);

        pst.addProducts(1,allProds.get(1),100.0);

        ch.initStorage(st);

    }

    public static void initProds(){
        allProds.add(new Product("Лук", 20, 100, PType.vegetable));
        allProds.add(new Product("Морковь", 30, 250, PType.vegetable));
        allProds.add(new Product("Молоко", 10, 50, PType.dairy));
        allProds.add(new Product("Сливки", 35, 275, PType.dairy));
        allProds.add(new Product("Яблоко", 15, 150, PType.fruit));
    }

    public static HashMap<Product, Double> initStorage(){
        HashMap<Product, Double> ans = new HashMap<>();

        ans.put(allProds.get(0),100.0);
        ans.put(allProds.get(2),150.0);
        ans.put(allProds.get(4),50.0);

        return ans;
    }

}
