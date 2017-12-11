package ghoulish.window;

/**
 * Created by Alexandr on 11.12.2017.
 */
public interface IHandler {
    void setNext(IHandler handler);

    void handle(char key);
}
