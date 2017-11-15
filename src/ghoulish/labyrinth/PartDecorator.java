package ghoulish.labyrinth;


public abstract class PartDecorator extends Part{
    Part parent;

    PartDecorator(int i, int j, Part _parent, String s) {
        super(i,j,s);
        parent = _parent;
    }

    public String getTextureName() {
        return this.parent.getTextureName() + " " + textureName;
    }

    public boolean trapCheck() {
        return parent != null && parent.trapCheck();

    }

    public boolean lootCheck() {
        return parent != null && parent.lootCheck();
    }

    public Part collapseDanger() {
        parent = parent.collapseDanger();
        return this;
    }

    public Part collapseLoot() {
        parent = parent.collapseLoot();
        return this;
    }

    public boolean hasTrap() {
        return parent.hasTrap();
    }

    public boolean hasDoor() {
        return parent.hasDoor();
    }

    public boolean hasLoot() {
        return parent.hasLoot();
    }

    public void openDoor() {
        parent.openDoor();
    }
}
