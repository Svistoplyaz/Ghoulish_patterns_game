package proxy;

import parts.DevicePart;
import parts.Storage;

/**
 * Created by Alexandr on 16.10.2017.
 */
public class ProxyStorage extends DevicePart{
    Storage st;
    ProxyStorage(Storage _st){
        st = _st;
    }

    void add_products(int path, String args){
        if(path == 1){
            st.add_prodcuts(args);
        }
    }
    
}
