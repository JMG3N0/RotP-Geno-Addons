package com.baoh.power;

import com.github.standobyte.jojo.power.impl.nonstand.INonStandPower;


public interface IBaohPower extends INonStandPower {

    public enum BaohStat {
        STRENGTH,
        AGILITY,
        WILL,
        ENDURANCE,
        MIND
    }

    int availablePoints();
    int getStatLevel(BaohStat stat);
    void setStatLevel(BaohStat stat, int level);
    void addStatLevel(BaohStat stat);
    boolean isSkillUnlocked(BaohSkill skill);
    void unlockSkill(BaohSkill skill);


}
