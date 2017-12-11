package ghoulish.window;


import java.util.HashMap;

public class LanguageHandler implements IHandler {
    private HashMap<Character, Character> letters;
    private IHandler next;

    LanguageHandler() {
        letters = new HashMap<>();

        letters.put('ц', 'w');
        letters.put('ф', 'a');
        letters.put('ы', 's');
        letters.put('в', 'd');
        letters.put('у', 'e');
        letters.put('х', '[');
        letters.put('ъ', ']');

        setNext(new CaseHandler());
    }

    @Override
    public void setNext(IHandler handler) {
        next = handler;
    }

    @Override
    public void handle(char key) {
        if (next != null) {
            if (letters.containsKey(key))
                next.handle(letters.get(key));

            next.handle(key);
        }
    }
}
