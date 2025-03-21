package EvelynnTest.powers;

import EvelynnTest.EvelynnTestMod;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;

public class Megaweak extends AbstractEasyPower {
    // intellij stuff Example, buff, false
    private static final String SIMPLE_NAME = "MegaWeak";
    public static final String POWER_ID = EvelynnTestMod.makeID(SIMPLE_NAME);
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String LOC_NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public boolean justApplied = false;

    public Megaweak(AbstractCreature owner, int amount, boolean isSourceMonster) {
        super(SIMPLE_NAME, PowerType.BUFF, true, owner, amount);
        name = LOC_NAME;
        updateDescription();
        if (AbstractDungeon.actionManager.turnHasEnded && isSourceMonster) {
            this.justApplied = true;
        }

    }

    public void atEndOfRound() {
        if (this.justApplied) {
            this.justApplied = false;
        } else {
            if (this.amount == 0) {
                this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, Megaweak.POWER_ID));
            } else {
                this.addToBot(new ReducePowerAction(this.owner, this.owner, Megaweak.POWER_ID, 1));
            }

        }
    }

    public void updateDescription() {
        if (this.amount == 1) {
            if (this.owner != null && !this.owner.isPlayer && AbstractDungeon.player.hasRelic("Paper Crane")) {
                this.description = DESCRIPTIONS[0] + 90 + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2] + DESCRIPTIONS[4];
            } else {
                this.description = DESCRIPTIONS[0] + 75 + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
            }
        } else if (this.owner != null && this.owner.isPlayer && AbstractDungeon.player.hasRelic("Paper Crane")) {
            this.description = DESCRIPTIONS[0] + 90 + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[3] + DESCRIPTIONS[4];
        } else {
            this.description = DESCRIPTIONS[0] + 75 + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[3];
        }
    }

    public float atDamageGive(float damage, DamageInfo.DamageType type) {
        if (type == DamageInfo.DamageType.NORMAL) {
            return this.owner != null && !this.owner.isPlayer && AbstractDungeon.player.hasRelic("Paper Crane") ? damage * 0.1f : damage * 0.25f;
        } else {
            return damage;
        }
    }
}
