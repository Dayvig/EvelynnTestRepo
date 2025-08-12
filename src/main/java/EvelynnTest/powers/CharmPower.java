package EvelynnTest.powers;

import EvelynnTest.cards.HateSpike;
import EvelynnTest.cards.SadoMasochism;
import EvelynnTest.util.Wiz;
import basemod.cardmods.RetainMod;
import basemod.helpers.CardModifierManager;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.powers.StunMonsterPower;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.HealthBarRenderPower;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnReceivePowerPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import EvelynnTest.EvelynnTestMod;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

public class CharmPower extends AbstractEasyPower implements HealthBarRenderPower, OnReceivePowerPower {
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
        addToBot(checkForFullCharm());
    }

    /*Threshold attack detonation
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
    }*/

    @Override
    public void atEndOfRound() {
        CharmPower instance = this;
        if (this.amount >= 0) {
            addToBot(new AbstractGameAction() {
                @Override
                public void update() {
                    instance.stackPower(instance.amount);
                    instance.updateDescription();
                    AbstractDungeon.onModifyPower();
                    instance.flash();
                    this.isDone = true;
                }
            });
        }
        addToBot(checkForFullCharm());
    }

    public AbstractGameAction checkForFullCharm(){
        return new AbstractGameAction() {
            @Override
            public void update() {
                if (!owner.hasPower(CharmPower.POWER_ID)){
                    this.isDone = true;
                    return;
                }
                if (owner.getPower(CharmPower.POWER_ID).amount >= owner.currentHealth) {
                    addToBot(new ApplyPowerAction(owner, owner, new StunMonsterPower((AbstractMonster)owner)));
                    addToBot(new ApplyPowerAction(owner, owner, new VulnerablePower(owner, 2, false), 2));
                    addToTop(new RemoveSpecificPowerAction(owner, owner, CharmPower.POWER_ID));
                    owner.getPower(CharmPower.POWER_ID).amount = 0;
                    for (AbstractPower p : AbstractDungeon.player.powers){
                        if (p instanceof Outfit){
                            Outfit out = (Outfit)p;
                            out.activateWhenFullyCharmed();
                        }
                    }
                }
                this.isDone = true;
            }
        };
    }

    @Override
    public int getHealthBarAmount() {
        return this.amount;
    }

    @Override
    public Color getColor() {
        return Color.PINK.cpy();
    }

    @Override
    public boolean onReceivePower(AbstractPower abstractPower, AbstractCreature abstractCreature, AbstractCreature abstractCreature1) {
        addToBot(checkForFullCharm());
        return true;
    }

    @Override
    public void wasHPLost(DamageInfo info, int damageAmount) {
        super.wasHPLost(info, damageAmount);
        addToBot(checkForFullCharm());
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }
}