package EvelynnTest.powers;

import EvelynnTest.cards.HateSpike;
import EvelynnTest.cards.SadoMasochism;
import basemod.cardmods.RetainMod;
import basemod.helpers.CardModifierManager;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.HealthBarRenderPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import EvelynnTest.EvelynnTestMod;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

public class CharmPower extends AbstractEasyPower {
    // intellij stuff Example, buff, false
    private static final String SIMPLE_NAME = "CharmPower";
    public static final String POWER_ID = EvelynnTestMod.makeID(SIMPLE_NAME);
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String LOC_NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public CharmPower(AbstractCreature owner, int amount) {
        super(SIMPLE_NAME, PowerType.DEBUFF, false, owner, amount);
        name = LOC_NAME;
        updateDescription();
    }

    @Override
    public void onInitialApplication(){
        if (this.owner.hasPower(FriendlyMonsterPower.POWER_ID)){
            addToBot(new ApplyPowerAction(this.owner, this.owner, new MindControlledPower(this.owner)));
            addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this));
        }
    }

    @Override
    public void onRemove() {
        if (AbstractDungeon.player.hasPower(SuccubusPower.POWER_ID)){
            addToBot(new ApplyPowerAction(this.owner, this.owner, new CharmPower(this.owner, 2)));
        }
    }

    public int onAttacked(DamageInfo info, int damageAmount) {
        if (info instanceof SadoMasochism.SadisticDamageInfo){
            SadoMasochism.SadisticDamageInfo sadisticInfo = (SadoMasochism.SadisticDamageInfo)info;
            if (sadisticInfo.isSadistic){
                return damageAmount;
            }
        }
        if (damageAmount > owner.currentBlock && info.type.equals(DamageInfo.DamageType.NORMAL)){
            addToBot(new ApplyPowerAction(this.owner, this.owner, new VulnerablePower(this.owner, this.amount, true)));
            addToBot(new ApplyPowerAction(this.owner, this.owner, new WeakPower(this.owner, this.amount, true)));
            int charmThreshold = this.owner.hasPower(CharmThresholdPower.POWER_ID) ? this.owner.getPower(CharmThresholdPower.POWER_ID).amount : 5;
            if (this.amount >= charmThreshold){
                addToBot(new ApplyPowerAction(this.owner, this.owner, new MindControlledPower(this.owner)));
                if (this.owner.hasPower(CharmThresholdPower.POWER_ID)) {
                    addToBot(new ApplyPowerAction(this.owner, this.owner, new CharmThresholdPower(this.owner, 2)));
                }else {
                    addToBot(new ApplyPowerAction(this.owner, this.owner, new CharmThresholdPower(this.owner, 5)));
                }
            }
            addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this));
        }
        return damageAmount;
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + (this.owner.hasPower(CharmThresholdPower.POWER_ID) ? this.owner.getPower(CharmThresholdPower.POWER_ID).amount : 5) + DESCRIPTIONS[1];
    }
}