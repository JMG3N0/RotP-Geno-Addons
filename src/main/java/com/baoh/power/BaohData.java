package com.baoh.power;


import com.github.standobyte.jojo.power.impl.nonstand.TypeSpecificData;
import com.baoh.AddonMain;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = AddonMain.MOD_ID)
public class BaohData extends TypeSpecificData {

    public static final int MAX_STAT_LEVEL = 100;

    private static final int[] POINTS_AT_LEVEL;
    static {
        POINTS_AT_LEVEL = new int[MAX_STAT_LEVEL + 1];
        int diff = 0;

        POINTS_AT_LEVEL[0] = 0;
        POINTS_AT_LEVEL[1] = 2;
        for (int i = 2; i < POINTS_AT_LEVEL.length; i++) {
            diff += 3 + (i-1) / 20;

            POINTS_AT_LEVEL[i] = POINTS_AT_LEVEL[i-1] + POINTS_AT_LEVEL[1] + diff;

        }
    }
    public static final int MAX_BAOH_POINTS = pointsAtLevel(MAX_STAT_LEVEL);

    private int strengthPoints;
    private int strengthLevel;
    private int agilityPoints;
    private int agilityLevel;
    private int willPoints;
    private int willLevel;
    private int endurancePoints;
    private int enduranceLevel;
    private int mindPoints;
    private int mindLevel;
    private int totalLevel;
    private int adrenalineAmount;
    private int daysAlive;

    private float damageFactor = 1F;

    private boolean isTransformed;
    private boolean isSuspended;
    private boolean isUnified;
    private boolean saberOut;
    private boolean knowsHappiness;
    private boolean knowsSadness;
    private boolean knowsAnger;
    private boolean knowsFear;

}
