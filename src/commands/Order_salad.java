package commands;

import java.util.ArrayList;

/**
 * Created by Alexandr on 16.10.2017.
 */
public class Order_salad implements IAction{
    public void perform_action(ArrayList<String> args){
        if(args.get(0).equals("1"))
            System.out.print("Your salad was sent in queue\n");
        else
            System.out.print(args.get(0) + "your salads were sent in queue\n");
    }
}
