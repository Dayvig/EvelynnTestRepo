package EvelynnTest.powers;

import EvelynnTest.EvelynnTestMod;
import EvelynnTest.cards.Fervor;
import EvelynnTest.cards.HateSpike;
import basemod.cardmods.RetainMod;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import basemod.helpers.CardModifierManager;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnReceivePowerPower;

public class GrudgePower extends AbstractEasyPower implements OnReceivePowerPower  {
    // intellij stuff Example, buff, false
    private static final String SIMPLE_NAME = "GrudgePower";
    public static final String POWER_ID = EvelynnTestMod.makeID(SIMPLE_NAME);
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String LOC_NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    HateSpike spike;

    public GrudgePower(AbstractCreature owner, int amount, boolean isSourceMonster) {
        super(SIMPLE_NAME, PowerType.DEBUFF, false, owner, amount);
        name = LOC_NAME;
        updateDescription();
    }

    @Override
    public void updateDescription() {
        if (this.amount == 1){
            description = DESCRIPTIONS[0];
        }
        else {
            description = DESCRIPTIONS[1] + amount + DESCRIPTIONS[2];
        }
    }

    @Override
    public boolean onReceivePower(AbstractPower abstractPower, AbstractCreature abstractCreature, AbstractCreature abstractCreature1) {
        if (abstractPower.ID.equals(CharmPower.POWER_ID)){
            spike = new HateSpike();
            CardModifierManager.addModifier(spike, new RetainMod());
            addToBot(new MakeTempCardInHandAction(spike, this.amount));
        }
        return true;
    }
}
