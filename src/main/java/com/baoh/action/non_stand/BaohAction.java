package com.baoh.action.non_stand;

import com.baoh.power.skill.AbstractBaohSkill;
//import com.github.standobyte.jojo.action.non_stand.NonStandAction;

public abstract class BaohAction extends Action<BaohPower> {
    private AbstractBaohSkill unlockingSkill;

    public BaohAction(NonStandAction.AbstractBuilder<?> builder){
        super(builder);
        if (builder.baseSkillBranch != null)
    }
}
