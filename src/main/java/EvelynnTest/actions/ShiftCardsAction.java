package EvelynnTest.actions;


import EvelynnTest.cards.AbstractShiftingCard;
import EvelynnTest.cards.Playtime;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;

import java.util.ArrayList;
import java.util.Iterator;

public class ShiftCardsAction extends AbstractGameAction {

    boolean random = false;

    public ShiftCardsAction(AbstractCreature source, int amount) {
        this.setValues(AbstractDungeon.player, source, amount);
        this.actionType = ActionType.CARD_MANIPULATION;
        random = false;
    }

    public ShiftCardsAction(AbstractCreature source, int amount, boolean isRandom) {
        this.setValues(AbstractDungeon.player, source, amount);
        this.actionType = ActionType.CARD_MANIPULATION;
        random = isRandom;
    }

    public void update() {
        if (this.duration == 0.5F) {
            if (random){
                ArrayList<AbstractShiftingCard> shiftableCards = new ArrayList<>();
                for (AbstractCard c : AbstractDungeon.player.hand.group){
                    if (c instanceof AbstractShiftingCard){
                        shiftableCards.add( (AbstractShiftingCard) c);
                    }
                }
                if (!shiftableCards.isEmpty()){
                    int randomCard = AbstractDungeon.cardRng.random(0, shiftableCards.size()-1);
                    shiftableCards.get(randomCard).Shift();
                }
                this.isDone = true;
            }
            else {
                boolean hasShiftingCards = false;
                for (AbstractCard c : AbstractDungeon.player.hand.group) {
                    if (c instanceof AbstractShiftingCard) {
                        hasShiftingCards = true;
                        break;
                    }
                }
                if (hasShiftingCards) {
                    AbstractDungeon.handCardSelectScreen.open("Shift.", this.amount, false, true, false, false, true);
                    this.addToBot(new WaitAction(0.25F));
                    this.tickDuration();
                } else {
                    this.isDone = true;
                }
            }
        } else {
            if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
                AbstractCard c;
                for (Iterator var1 = AbstractDungeon.handCardSelectScreen.selectedCards.group.iterator(); var1.hasNext(); AbstractDungeon.player.hand.addToTop(c)) {
                    c = (AbstractCard) var1.next();
                    if (c instanceof AbstractShiftingCard) {
                        ((AbstractShiftingCard) c).Shift();
                    }
                }

                AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
            }

            this.tickDuration();
        }
    }
}

