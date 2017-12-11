package ghoulish.game;

public enum State {
    PlayerTurn("PlayerTurn"), MonsterTurn("MonsterTurn"), Death("Death"), Battle("Battle"), None("Awaiting for player");

    String name;
    State(String str){
        name = str;
    }

    @Override
    public String toString(){
        return name;
    }
}
