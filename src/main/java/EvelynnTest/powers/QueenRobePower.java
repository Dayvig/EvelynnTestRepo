package EvelynnTest.powers;

import EvelynnTest.EvelynnTestMod;
import EvelynnTest.util.Wiz;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.WeakPower;

public class QueenRobePower extends Outfit {

    private static final String SIMPLE_NAME = "QueenRobePower";
    public static final String POWER_ID = EvelynnTestMod.makeID(SIMPLE_NAME);
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String LOC_NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public QueenRobePower(AbstractCreature owner, int amount, AbstractCard outfit) {
        super(SIMPLE_NAME, PowerType.BUFF, false, owner, amount, outfit);
        name = LOC_NAME;
        updateDescription();
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        for (AbstractMonster mo : Wiz.getEnemies()){
            if (mo.hasPower(CharmPower.POWER_ID)){
                if (mo.getPower(CharmPower.POWER_ID).amount >= mo.currentHealth/2){
                    addToBot(new GainBlockAction(this.owner, this.amount));
                }
            }
        }
    }

    @Override
    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        if (power.ID.equals(MindControlledPower.POWER_ID)){
            addToBot(new ApplyPowerAction(target, owner, new StrengthPower(target, amount), amount));
        }
    }

    @Override
    public void onRemove(){
        for (AbstractMonster mo : Wiz.getEnemies()){
            if (mo.hasPower(MindControlledPower.POWER_ID)){
                addToBot(new ReducePowerAction(mo, owner, StrengthPower.POWER_ID, amount));
            }
        }
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1] + amount + DESCRIPTIONS[2];
    }

}
