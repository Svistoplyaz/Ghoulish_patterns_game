package ghoulish.window;

/**
 * Created by Alexandr on 11.12.2017.
 */
public class CaseHandler implements IHandler {
    private IHandler next;

    CaseHandler(){
        setNext(new LetterHandler());
    }

    @Override
    public void setNext(IHandler handler) {
        next = handler;
    }

    @Override
    public void handle(char key) {
        if(next != null)
            next.handle(Character.toLowerCase(key));
    }
}
