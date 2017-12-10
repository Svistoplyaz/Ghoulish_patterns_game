package ghoulish.creatures;

/**
 * Created by Alexander on 10.12.2017.
 */
public interface IVisitor {
    void visit(CleverMonster monster);
    void visit(BlindMonster monster);
    void visit(StaticMonster monster);
}
