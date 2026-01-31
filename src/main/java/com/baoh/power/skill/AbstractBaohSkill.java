package com.baoh.power.skill;

import com.baoh.power.BaohPowerType;
import com.github.standobyte.jojo.init.power.non_stand.ModPowers;
import it.unimi.dsi.fastutil.objects.Object2BooleanMap;
import net.minecraftforge.registries.ForgeRegistryEntry;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public abstract class AbstractBaohSkill extends ForgeRegistryEntry<AbstractBaohSkill> {
    private final List<Supplier<AbstractBaohSkill>> requirements;

    public AbstractBaohSkill(AbstractBaohSkill.Builder builder){
        this.requirements = builder.requirements;
    }

    public List<Supplier<AbstractBaohSkill>> getRequirements() {
        return requirements;
    }

    public static class Builder{
        private final List<Supplier<AbstractBaohSkill>> requirements = new ArrayList<>();

        public Builder addRequirement(Supplier<AbstractBaohSkill> skillSupplier) {
            this.requirements.add(skillSupplier);
            return this;
        }
    }
}
