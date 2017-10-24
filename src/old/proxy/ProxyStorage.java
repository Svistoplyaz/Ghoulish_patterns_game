package old.proxy;

import old.parts.DevicePart;
import old.parts.Storage;
import old.storage.Product;

/**
 * Created by Alexandr on 16.10.2017.
 */
public class ProxyStorage extends DevicePart{
    Storage st;
    public ProxyStorage(Storage _st){
        st = _st;
    }

    public void addProducts(int path, Product pr, double we){
        if(path == 1){
            st.addProducts(pr, we);
        }
    }
    
}
