package ghoulish.window;

import java.util.HashSet;

/**
 * Created by Alexandr on 11.12.2017.
 */
public class LetterHandler implements IHandler {
    HashSet<Character> vocabulary;
    IHandler next;

    LetterHandler(){
        vocabulary = new HashSet<>();

        vocabulary.add('w');
        vocabulary.add('a');
        vocabulary.add('s');
        vocabulary.add('d');
        vocabulary.add('e');
        vocabulary.add('[');
        vocabulary.add(']');
        vocabulary.add('1');
        vocabulary.add('2');
        vocabulary.add('3');
        vocabulary.add('4');
        vocabulary.add('5');
        vocabulary.add('6');
        vocabulary.add('8');
        vocabulary.add('9');
        vocabulary.add('0');
        vocabulary.add(' ');

        setNext(new TurningMachineProxy());
    }

    @Override
    public void setNext(IHandler handler) {
        next = handler;
    }

    @Override
    public void handle(char key) {
        if(next != null){
            if(vocabulary.contains(key))
                next.handle(key);
        }
    }
}
