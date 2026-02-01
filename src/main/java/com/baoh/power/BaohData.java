package com.baoh.power;


import com.github.standobyte.jojo.power.impl.nonstand.TypeSpecificData;
import com.baoh.AddonMain;
import net.minecraftforge.fml.common.Mod;


public class BaohData extends TypeSpecificData {

    public int maxStatLevel = 100;
    public int maxLevel = 100;
    private int currentLevel = 0;
    private final int daysToLive = 110;

    public float maxAdrenaline = 100.0F;
    private float currentAdrenaline = 0.0F;

    private int strPoints = 0;
    private int strLevel = 0;
    private int dexPoints = 0;
    private int dexLevel = 0;
    private int willPoints = 0;
    private int willLevel = 0;
    private int conPoints = 0;
    private int conLevel = 0;
    private int mindPoints = 0;
    private int mindLevel = 0;




    private boolean isTransformed = false;
    private boolean isSuspended = false;
    private boolean isUnified = false;
    private boolean saberOut = false;
    private boolean knowsHappiness = false;
    private boolean knowsSadness = false;
    private boolean knowsAnger = false;
    private boolean knowsFear = false;
    private boolean knowsCourage = false;
    private boolean knowsMalice = false;
}
