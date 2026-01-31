package com.baoh.power;


import com.github.standobyte.jojo.power.impl.nonstand.TypeSpecificData;
import com.baoh.AddonMain;
import net.minecraftforge.fml.common.Mod;


public class BaohData extends TypeSpecificData {

    public static final int MAX_STAT_LEVEL = 100;



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
