package tiles;

import Levels.Level;
import ca.graphics.Colours;
import ca.graphics.Screen;

public abstract class Tile {

    public static final Tile[] tiles = new Tile[256];
    public static final Tile VOID = new BasicSolidTile(0, 0, 0, Colours.get(000, -1, -1, -1), 0xFF000000);
    public static final Tile STONE = new BasicSolidTile(1, 1, 0, Colours.get(-1, 333, -1, -1), 0xFF555555);
    public static final Tile GRASS = new BasicTile(2, 2, 0, Colours.get(-1, 131, 141, -1), 0xFF00FF00);
    public static final Tile ROAD = new BasicTile(4, 3, 26, Colours.get(-1,0,555,333), 0xFFFFFFF0);
    public static final Tile ROADTOPLEFT = new BasicTile(5, 5, 26,  Colours.get(-1,0,555,333), 0xFF222222);
    public static final Tile ROADTOPRIGHT = new BasicTile(6, 1, 26,  Colours.get(-1,0,555,333), 0xFFEEEE5D);
    public static final Tile ROADLEFT = new BasicTile(7, 4, 26,  Colours.get(-1,0,555,333), 0xFF5D90EE);
    public static final Tile ROADRIGHT = new BasicTile(8, 2, 26,  Colours.get(-1,0,555,333), 0xFF2067EA);
    public static final Tile ROADTOP = new BasicTile(9, 6, 26,  Colours.get(-1,0,555,333), 0xFF044FD9);
    public static final Tile ROADBOTTOM = new BasicTile(10, 7, 26,  Colours.get(-1,0,555,333), 0xFF013CA8);
    public static final Tile ROADBOTTOMRIGHT = new BasicTile(11, 8, 26,  Colours.get(-1,0,555,333), 0xFF8497BB);
    public static final Tile ROADBOTTOMLEFT = new BasicTile(12, 9, 26,  Colours.get(-1,0,555,333), 0xFF9FB6E0);
    public static final Tile ROADSTRIPETOP = new BasicTile(13, 10, 26,  Colours.get(-1,0,555,326), 0xFFCDB700);
    public static final Tile ROADSTRIPLEFT = new BasicTile(14, 11, 26,  Colours.get(-1,0,555,326), 0xFFA69715);
    public static final Tile WATER = new AnimatedTile(3, new int[][] { { 0, 5 }, { 1, 5 }, { 2, 5 }, { 1, 5 } },
            Colours.get(-1, 004, 115, -1), 0xFF0000FF, 1000);
    
    protected byte id;
    protected boolean solid;
    protected boolean emitter;
    private int levelColour;

    public Tile(int id, boolean isSolid, boolean isEmitter, int levelColour) {
        this.id = (byte) id;
        if (tiles[id] != null)
            throw new RuntimeException("Duplicate tile id on " + id);
        this.solid = isSolid;
        this.emitter = isEmitter;
        this.levelColour = levelColour;
        tiles[id] = this;
    }

    public byte getId() {
        return id;
    }

    public boolean isSolid() {
        return solid;
    }

    public boolean isEmitter() {
        return emitter;
    }

    public int getLevelColour() {
        return levelColour;
    }

    public abstract void tick();

    public abstract void render(Screen screen, Level level, int x, int y);
}
