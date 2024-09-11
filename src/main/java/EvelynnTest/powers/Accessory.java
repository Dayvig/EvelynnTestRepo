package EvelynnTest.powers;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class Accessory extends AbstractEasyPower {

    AbstractCard accessoryCard;

    public Accessory(String NAME, PowerType powerType, boolean isTurnBased, AbstractCreature owner, int amount, AbstractCard outfit) {
        super(NAME, powerType, isTurnBased, owner, amount);
        accessoryCard = outfit;
    }

    @Override
    public void onInitialApplication(){
        accessoryCard.updateCost(-1);
    }

    @Override
    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        if (power instanceof Accessory){
            if (!power.ID.equals(this.ID)){
                addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, this));
            }
            addToBot(new MakeTempCardInDrawPileAction(accessoryCard, 1, true, false, false));
        }
    }

    @Override
    public void stackPower(int stackAmount) {
        if (this.amount != -1) {
            this.fontScale = 8.0F;
            this.amount = Math.max(stackAmount, this.amount);
        }
    }

}
