## "Ghoulish" the programming patterns demonstrating game

### Used patterns description:

- #### Singleton(Layer1, Layer0, Player, Visualiser)
    We using `Singleton` pattern for this classes to create global access point for each of them, because they are used all over the application. Also we restrict that all of these classes can have one instance at most.

- #### Proxy(TurningMachineProxy)
    This class filters keys that were pressed, whether player want to move to another cell(w,a,s,d), loot cell(e), choose number for fight(1,2,3,4,5,6), print debug statistics to console(0,8,9), save([) or load(]) game.

- #### Decorator(PartDecorator)
    This class decorates class `Part`. `Floor` and `Wall` classes inherit class `Part`. All texturing and player interaction with bones, traps and doors are easily done by `PartDecorator` inheritance for corresponding classes.

- #### Composite(Composite)
    Composite is a class that can store all `TextureHolder` objects those need to be visualized. Class `TextureHolder` has its priority, that sets order of textures drawing, like that player or monster should be painted over floor cell. After adding all proper objects(Player, Cells, Monsters) to composite we can call `getTexture` method to get one solid image with every object on it.

- #### Iterator(MonsterIterator)
    Iterator pattern is used for simplifying system for monsters to take turn for movement, now we can request next monster for movement and ask are there any monsters left to move this turn.

- #### Facade(MoveAnswer)
    This pattern hides all game logic and provides simple API to interact with. For example: using its method `fight` to get result for current battle we are in and number that we chose or using method `placeToBorn` for `StaticMonster` to get free place for its new copy.

- #### Object Pool(TextureContainer) combined with FlyWeight(BufferedImage)
    We are providing visualizer with textures by using `TextureContainer`, it saves our memory, because we don't duplicate the same texture again and again for every similar cell or monster. At this point `BufferedImage` plays FlyWeight role in this pool.

- #### Factory(IMonsterFactory)
    Classes `BlindMonsterFactory`, `CleverMonsterFactory`, `StaticMonsterFactory` inherit interface IMonsterFactory. Every of these classes are responsible for generating each kind of monster.

- #### State(IStateForMachine)
    Classes `BattleTurn`, `DeathTurn`, `MonsterTurn` и `PlayerTurn` inherit interface `IStateForMachine`. Each of this classes have `execute` method, for `BattleTurn` this method contains waiting for fighting keys(1 - 6) to be pressed to perform fight, for `MonsterTurn` it initializes Iterator and iterates over all monsters for deciding their moves.

- #### Memento(Memento)
    Saving and loading of game is realized by using this pattern. When we saving game(pressing [) we cloning all in-game objects and writing it to `Memento`. When we want to load game(pressing ]) we choosing Memento and replace current in-game objects with all objects we saved in it.

- #### Observer(ISubject, ISubscriber)
    Classes `MoveAnswer`, `TurningStateMachine`, `Visualiser` inherit `ISubscriber` interface. Classes `Layer1` and `Player` inherit `ISubject` interface. When we are loading new Memento we need to set new instances for Layer1 and Player, after that we need to renew them in all subscriber classes. We managing subscribers in subject by `addSub` and `deleteSub` methods, notifying subscribers about changes by `notifySubs` method. Every subscriber has `update` method that calls when subject notifies about changes.

- #### Chain of Responsibility(IHandler)
    Classes `CaseHandler`, `LanguageHandler`, `LetterHandler` and `TurningMachineProxy` inherit `IHandler` interface, each of them filtering characters that were read by key pressing. For example: `CaseHandler` sets all characters to lowercase to unify them, `LanguageHandler` changes cyrillic characters to latin characters keyboard layout analogue.

- #### Mediator(Mediator)
    `Mediator` connects `MoveAnswer`, `Visualiser` and `TurningStateMachine` together. They don't need to contact each other directly anymore. If you want to add another big class in your project that need to contact these classes you will just add it to Mediator. Communication between this classes become less complicated.

- #### Strategy(IStrategy)
    Classes `BlindStrategy`, `CleverStrategy` and `StaticStrategy` inherit `IStrategy` interface, each of this strategies help monster to decide how he should move by implementing method `move`. At this point we can move to any monster without minding what exact kind of monster it is. For example: for blind monster it's just move in random direction to the cell where is no obstacles placed, for clever monster try to chase player movement decision and for static monster it's just staying on the same place.

#### Game rules
##### Starting screen
![Starting screen](https://i.imgur.com/BSKsp7m.png)

##### Terrain rules
Use W to go up, S to go down, A to go left, D to go right.

There are different types of basic cells:
- Wall cell
- Floor cell

Floor cell can have multiple object on it:
- Bones(you can loot them by pressing E and heal half of heart)
- Trap(if you step on it, it will damage you for half of heart with 50% probability)
- Door(at the beginning it is closed, monsters cannot open it)

##### Enemies:
You can fight every enemy on the map simply by taking step to the cell where it standing, monster can do the same with you on his turn. When fight is staring monster guesses its week point, it will be number from 1 to 6, you should guess it to kill monster. For every wrong guess you will be damaged for a half of heart

There are different kinds of monsters:
- Blind enemy(moves randomly)
- Clever enemy(chases the player if can find a path)
- Static enemy(stands and generating it copies to fill every open space cell around it, spreading rate can be easily changed)

#### Game goal

You should loot all the bones on map. Fastest way is to play peacefully without killing monsters if it is possible. Make love not war.
