package EvelynnTest.powers;

import EvelynnTest.EvelynnTestMod;
import EvelynnTest.stances.DemonShadeStance;
import EvelynnTest.util.Wiz;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.status.VoidCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.stances.AbstractStance;

public class GownPower extends Outfit {

    private static final String SIMPLE_NAME = "GownPower";
    public static final String POWER_ID = EvelynnTestMod.makeID(SIMPLE_NAME);
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String LOC_NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public GownPower(AbstractCreature owner, int amount, AbstractCard outfit) {
        super(SIMPLE_NAME, PowerType.BUFF, false, owner, amount, outfit);
        name = LOC_NAME;
        updateDescription();
    }

    @Override
    public void onCardDraw(AbstractCard card) {
        if (card.type.equals(AbstractCard.CardType.STATUS)){
            addToBot(new GainEnergyAction(2));
            addToBot(new DrawCardAction(this.amount));
        }
    }

    @Override
    public void onChangeStance(AbstractStance oldStance, AbstractStance newStance) {
        if (!oldStance.ID.equals(DemonShadeStance.STANCE_ID) && newStance.ID.equals(DemonShadeStance.STANCE_ID)){
            addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, this));
            addToBot(new ApplyPowerAction(this.owner, this.owner, new VeilPower(this.owner, this.amount == 1 ? 14 : 16, outfitCard), this.amount == 1 ? 14 : 16));
        }
    }


    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

}
