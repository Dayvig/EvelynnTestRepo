package EvelynnTest.powers;

import EvelynnTest.EvelynnTestMod;
import EvelynnTest.util.Wiz;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;

public class FashionistaPower extends AbstractEasyPower {

    private static final String SIMPLE_NAME = "FashionistaPower";
    public static final String POWER_ID = EvelynnTestMod.makeID(SIMPLE_NAME);
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String LOC_NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public FashionistaPower(AbstractCreature owner, int amount) {
        super(SIMPLE_NAME, PowerType.BUFF, false, owner, amount);
        name = LOC_NAME;
        updateDescription();
    }

    @Override
    public void onAfterUseCard(AbstractCard card, UseCardAction action) {
        if (card.type.equals(AbstractCard.CardType.POWER)){
            addToBot(new ApplyPowerAction(this.owner, this.owner, new DexterityPower(this.owner, this.amount), this.amount));
        }
    }

    @Override
    public void onInitialApplication(){
        for (AbstractPower p : this.owner.powers){
            if (p instanceof Outfit){
                ((Outfit) p).outfitCard.updateCost(2);
            }
            if (p instanceof Accessory){
                ((Accessory) p).accessoryCard.updateCost(2);
            }
        }
    }


    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

}
