package controllers.movement;

public enum MoveType {
    UP,
    DOWN,
    LEFT,
    RIGHT,
    STAY;

    public static final MoveType[] moveTypes = {MoveType.STAY,MoveType.DOWN,MoveType.UP,MoveType.RIGHT,MoveType.LEFT};

    public static String fileNameOf(MoveType moveType) {
        if (moveType==UP) return "up";
        if (moveType==DOWN) return "down";
        if (moveType==LEFT) return "left";
        if (moveType==RIGHT) return "right";
        return "stay";
    }
}
