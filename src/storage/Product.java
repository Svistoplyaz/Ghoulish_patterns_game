package storage;

/**
 * Created by Alexander on 16.10.2017.
 */
public class Product {
    String name;
    int cost;
    int calorie;
    PType type;

    public Product(String _name, int _cost, int _calorie, PType _type){
        name = _name;
        cost = _cost;
        calorie = _calorie;
        type = _type;
    }


}
