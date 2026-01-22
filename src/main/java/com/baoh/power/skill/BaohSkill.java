package com.baoh.power.skill;

import com.github.standobyte.jojo.action.Action;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;
import java.util.Optional;

public class BaohSkill extends ForgeRegistryEntry<BaohSkill> {
    private final Action<?> rewardAction;
    private final BaohSkill parent;
    private final int cost;

    public BaohSkill(Action<?> rewardAction, @Nullable BaohSkill parent, int cost) {
        this.rewardAction = rewardAction;
        this.parent = parent;
        this.cost = cost;
    }

    public Action<?> getAction() {
        return rewardAction;
    }

    public Optional<BaohSkill> getParent()
    {
        return Optional.ofNullable(parent);
    }

    public int getCost()
    {
        return cost;
    }
}
