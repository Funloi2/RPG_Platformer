package utilz;

import main.Game;

public class Constants {
    public static final float GRAVITY = 0.04f * Game.SCALE;
    public static final int PLAYER_ANI_SPEED = 15;

    public static class PlayerConstants {

        public static final int ATTACK = 0;
        public static final int ATTACK_2 = 1;
        public static final int ATTACK_COMBO = 2;
        public static final int DEATH = 3;
        public static final int FALL = 4;
        public static final int HIT = 5;
        public static final int IDLE = 6;
        public static final int JUMP = 7;
        public static final int ROLL = 8;
        public static final int RUN = 9;
        public static final int TURN_AROUND = 10;


        public static int GetSpriteAmount(int player_action) {
            switch (player_action) {
                case ATTACK -> {
                    return 4;
                }
                case ATTACK_2 -> {
                    return 6;
                }
                case ATTACK_COMBO, RUN, DEATH, IDLE -> {
                    return 10;
                }
                case FALL, TURN_AROUND, JUMP -> {
                    return 3;
                }
                case HIT -> {
                    return 1;
                }
                case ROLL -> {
                    return 12;
                }
                default -> {
                    return 0;
                }
            }
        }

    }
}
