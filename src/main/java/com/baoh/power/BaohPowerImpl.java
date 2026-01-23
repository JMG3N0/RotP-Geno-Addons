package com.baoh.power;

import com.baoh.power.skill.BaohSkill;
import com.github.standobyte.jojo.power.impl.nonstand.INonStandPower;
import com.github.standobyte.jojo.power.impl.nonstand.TypeSpecificData;
import com.github.standobyte.jojo.power.impl.nonstand.type.NonStandPowerType;
import com.github.standobyte.jojo.power.impl.nonstand.TypeSpecificData;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import org.jetbrains.annotations.Nullable;


import java.util.*;

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
    public void syncWithUserOnly(ServerPlayerEntity player) {

    }

    @Override
    public <T extends TypeSpecificData> void copyFrom(T oldData){
        if (oldData instanceof BaohPowerImpl) {
            BaohPowerImpl oldPower = (BaohPowerImpl) oldData;

            this.setEnergy(oldPower.getEnergy());
            this.getAvailablePoints = oldPower.getAvailablePoints;
            this.hadPowerBefore = oldPower.hadPowerBefore;

            this.statLevels.clear();
            this.statLevels.putAll(oldPower.statLevels);

            this.unlockedSkills.clear();
            this.unlockedSkills.addAll(oldPower.unlockedSkills);
        }
    }

    @Override
    public CompoundNBT writeNBT() {
        CompoundNBT nbt = new CompoundNBT();
        nbt.putFloat("AdrenalineAmount", this.getEnergy());
        nbt.putInt("AvailablePoints", this.getAvailablePoints);
        nbt.putBoolean("HadPowerBefore", this.hadPowerBefore);

        CompoundNBT baohStats = new CompoundNBT();
        for (Map.Entry<BaohStat, Integer> entry : statLevels.entrySet()) {
            baohStats.putInt(entry.getKey().name(), entry.getValue());
        }
        nbt.put("BaohStats", baohStats);
        return nbt;
    }

    @Override
    public void readNBT(CompoundNBT nbt)
    {
        this.setEnergy(nbt.getFloat("AdrenalineAmount"));
        this.getAvailablePoints = nbt.getInt("AvailablePoints");
        this.hadPowerBefore = nbt.getBoolean("HadPowerBefore");

        CompoundNBT baohStats = nbt.getCompound("BaohStats");
        for (BaohStat stat : BaohStat.values()) {
            if (baohStats.contains(stat.name())){
                this.statLevels.put(stat, baohStats.getInt(stat.name()));
            }
        }
    }

    // What the fuck is a getTypeSpecificData
//    @Override
//    @SuppressWarnings("unchecked")
//    public <T extends TypeSpecificData> T getTypeSpecificData(NonStandPowerType<T> powerType)
//    {
//        if (powerType == this.getType()) {
//            return (T) this;
//        }
//        return null;
//    }

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
    @SuppressWarnings("unchecked")
    public <T extends NonStandPowerType<D>, D extends TypeSpecificData> java.util.Optional<D> getTypeSpecificData(@Nullable T requiredType) {
        // If the game is asking for the Baoh type, return this class wrapped in an Optional
        if (requiredType == this.getType()) {
            // We cast 'this' to D through Object, then wrap it in an Optional
            return java.util.Optional.of((D) (Object) this);
        }
        // Otherwise, return empty so the mod knows the requested data isn't here
        return java.util.Optional.empty();
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
