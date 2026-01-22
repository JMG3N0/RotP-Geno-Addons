package com.baoh.power;

import com.baoh.power.skill.BaohSkill;

import java.util.EnumMap;
import java.util.Map;

public class BaohPowerImpl implements IBaohPower {
    private final Map<BaohStat, Integer> statLevels = new EnumMap<>(BaohStat.class);
    private int getAvailablePoints;

    public BaohPowerImpl() {
        for (BaohStat stat : BaohStat.values()) {
            statLevels.put(stat, 0);
        }
    }

    @Override
    public int availablePoints(){
        return this.getAvailablePoints;
    }

    @Override
    public int getStatLevel(BaohStat stat)
    {
        return statLevels.getOrDefault(stat,0);
    }

    @Override
    public void setStatLevel(BaohStat stat, int level)
    {
        statLevels.put(stat,level);
    }

    @Override
    public void addStatLevel(BaohStat stat)
    {
       // int currentLevel = statLevels.getOrDefault(stat, 0);

      //  statLevels.put(stat, currentLevel +1);

        if (this.getAvailablePoints > 0)
        {
            statLevels.merge(stat,1,Integer::sum);
            this.getAvailablePoints--;
            this.checkUnlockSkill();
        }


    }

//    @Override
//    public boolean isSkillUnlocked(BaohSkill skill)
//    {
//        return unlo
//    }
  //  void unlockSkill(BaohSkill skill);

    private void checkUnlockSkill()
    {

    }
}
