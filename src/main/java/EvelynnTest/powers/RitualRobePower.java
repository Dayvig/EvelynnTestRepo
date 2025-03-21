package EvelynnTest.powers;

import EvelynnTest.EvelynnTestMod;
import EvelynnTest.util.Wiz;
import com.evacipated.cardcrawl.mod.stslib.powers.abstracts.TwoAmountPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.WeakPower;

public class RitualRobePower extends Outfit {

    private static final String SIMPLE_NAME = "RitualRobePower";
    public static final String POWER_ID = EvelynnTestMod.makeID(SIMPLE_NAME);
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String LOC_NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public RitualRobePower(AbstractCreature owner, int amount, AbstractCard outfit) {
        super(SIMPLE_NAME, PowerType.BUFF, false, owner, amount, outfit);
        name = LOC_NAME;
        updateDescription();
        isTwoAmount = true;
        amount2 = 3;
        canGoNegative2 = false;
    }

    @Override
    public void onAfterUseCard(AbstractCard card, UseCardAction action) {
        if (card.type.equals(AbstractCard.CardType.ATTACK) && this.amount2 > 0){
            addToBot(new HealAction(owner, owner, this.amount));
            addToBot(new AbstractGameAction() {
                @Override
                public void update() {
                    amount2--;
                    amount2 = Math.max(amount2, 0);
                    flashWithoutSound();
                    this.isDone = true;
                }
            });
        }
    }

    @Override
    public void updateDescription() {
        if (amount2 == 0){
            description = DESCRIPTIONS[3];
        }
        else {
            description = DESCRIPTIONS[0] + amount2 + DESCRIPTIONS[1] + amount + DESCRIPTIONS[2];
        }
    }

}
