package ghoulish.game;

import java.util.ArrayList;

/**
 * Created by Alexandr on 09.12.2017.
 */
public interface ISubject {
    void addSub(ISubscriber sub);

    void deleteSub(ISubscriber sub);

    void notifySubs();
}
