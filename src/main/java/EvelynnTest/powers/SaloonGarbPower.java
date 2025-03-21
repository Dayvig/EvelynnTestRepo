package EvelynnTest.powers;

import EvelynnTest.EvelynnTestMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class SaloonGarbPower extends Outfit {

    private static final String SIMPLE_NAME = "SaloonGarbPower";
    public static final String POWER_ID = EvelynnTestMod.makeID(SIMPLE_NAME);
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String LOC_NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public SaloonGarbPower(AbstractCreature owner, int amount, AbstractCard outfit) {
        super(SIMPLE_NAME, PowerType.BUFF, false, owner, amount, outfit);
        name = LOC_NAME;
        updateDescription();
        isTwoAmount = true;
        amount2 = amount;
        canGoNegative2 = false;
        canGoNegative = false;
    }

    @Override
    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        if (power.type == PowerType.DEBUFF && !power.ID.equals("Shackled") && source == this.owner && target != this.owner && !target.hasPower("Artifact")) {
            this.flash();
            if (this.amount2 > 0) {
                this.addToBot(new ApplyPowerAction(this.owner, this.owner, new StrengthPower(this.owner, 1), 1));
                addToBot(new AbstractGameAction() {
                    @Override
                    public void update() {
                        amount2--;
                        amount2 = Math.max(amount2, 0);
                        flash();
                        this.isDone = true;
                    }
                });
            }
        }
    }


    @Override
    public void updateDescription() {
        if (amount2 == 0){
            description = DESCRIPTIONS[2];
        }
        else {
            description = DESCRIPTIONS[0] + amount2 + DESCRIPTIONS[1];
        }
    }

}
