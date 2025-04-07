package EvelynnTest.powers;

import EvelynnTest.EvelynnTestMod;
import EvelynnTest.cards.Fervor;
import EvelynnTest.util.Wiz;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import java.util.UUID;

public class PheremonePower extends AbstractEasyPower {
    // intellij stuff Example, buff, false
    private static final String SIMPLE_NAME = "PheremonePower";
    public static final String POWER_ID = EvelynnTestMod.makeID(SIMPLE_NAME);
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String LOC_NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public PheremonePower(AbstractCreature owner, int amount) {
        super(SIMPLE_NAME, PowerType.BUFF, true, owner, amount);
        name = LOC_NAME;
        updateDescription();
    }

    @Override
    public void atStartOfTurn() {
        for (AbstractMonster mo : Wiz.getEnemies()){
            double sum = 0;
            for (AbstractPower p : mo.powers){
                if (p.type.equals(PowerType.DEBUFF) && !p.ID.equals(CharmPower.POWER_ID)){
                    sum += p.amount;
                }
            }
            sum *= this.amount;
            if (this.owner.hasPower(AllurePower.POWER_ID)){
                sum += this.owner.getPower(AllurePower.POWER_ID).amount;
            }
            if (sum > 0) {
                addToBot(new ApplyPowerAction(mo, this.owner, new CharmPower(mo, (int) sum), (int) sum));
            }
        }
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

}
