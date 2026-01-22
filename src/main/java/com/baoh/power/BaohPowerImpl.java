package com.baoh.power;

import com.baoh.power.skill.BaohSkill;
import com.github.standobyte.jojo.power.impl.nonstand.INonStandPower;
import com.github.standobyte.jojo.power.impl.nonstand.TypeSpecificData;
import com.github.standobyte.jojo.power.impl.nonstand.type.NonStandPowerType;

import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class BaohPowerImpl extends TypeSpecificData implements IBaohPower {
    private final Map<BaohStat, Integer> statLevels = new EnumMap<>(BaohStat.class);
    private final Set<BaohSkill> unlockedSkills = new HashSet<>();
    private int getAvailablePoints;
    private float energy = 0;
    private boolean hadPowerBefore = false;

    public BaohPowerImpl() {
        for (BaohStat stat : BaohStat.values()) {
            statLevels.put(stat, 0);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends com.github.standobyte.jojo.power.impl.nonstand.TypeSpecificData> T getTypeSpecificData(NonStandPowerType<T> powerType)
    {
        return (T) this;
    }

    @Override
    public boolean hadPowerBefore(NonStandPowerType<?> powerType)
    {
        return hadPowerBefore;
    }

    @Override
    public void addHadPowerBefore(NonStandPowerType<?> powerType)
    {
        this.hadPowerBefore = true;
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

    @Override
    public boolean isSkillUnlocked(BaohSkill skill)
    {
        return unlockedSkills.contains(skill);
    }

    @Override
    public void unlockSkill(BaohSkill skill)
    {
        this.unlockedSkills.add(skill);
    }

    @Override
    public float getEnergy()
    {
        return this.energy;
    }

    @Override
    public void setEnergy(float energy)
    {
        this.energy = Math.max(0, Math.min(energy, getMaxEnergy()));
    }

    @Override
    public float getMaxEnergy()
    {
        return 100.0f;
    }

    @Override
    public boolean consumeEnergy(float amount)
    {
        if (this.getEnergy() <= 0.0f)
        {
            return false;
        }

        int will = getStatLevel(BaohStat.WILL);
        int willCap = Math.min(will, 100);

        float reductionFactor = 1.0f - (willCap * 0.008f);

        float subtractAmount = amount * reductionFactor;

        this.setEnergy(this.getEnergy() - subtractAmount);

        return true;
    }

    @Override
    public boolean hasEnergy(float amount)
    {
        return this.getEnergy() >= amount;
    }

    @Override
    public void addEnergy(float amount)
    {
        int mind = getStatLevel(BaohStat.MIND);
        int mindCap = Math.min(mind, 100);

        float bonusAmount = 1.0f + (mindCap * 0.008f);

        float gainAmount = amount * bonusAmount;

        this.setEnergy(this.getEnergy() + gainAmount);
    }

    private void checkUnlockSkill()
    {

    }
}
