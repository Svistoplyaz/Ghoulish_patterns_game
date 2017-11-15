package ghoulish.window;

import ghoulish.game.KeyReader;

public class LanguageAdapter implements KeyReader{
    KeyReader parent;

    LanguageAdapter(KeyReader keyReader){
        parent = keyReader;
    }

    @Override
    public void pressKey(char key) {
        switch (key){
            case 'ц':
                parent.pressKey('w');
                break;
            case 'ф':
                parent.pressKey('a');
                break;
            case 'ы':
                parent.pressKey('s');
                break;
            case 'в':
                parent.pressKey('d');
                break;
            case 'у':
                parent.pressKey('e');
                break;
            default:
                parent.pressKey(key);
        }
    }
}
