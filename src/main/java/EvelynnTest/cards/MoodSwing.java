package EvelynnTest.cards;

import EvelynnTest.actions.ShiftCardsAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static EvelynnTest.EvelynnTestMod.makeID;

public class MoodSwing extends AbstractEasyCard {
    public final static String ID = makeID("MoodSwing");

    public static final int MAGIC = 1;

    public MoodSwing() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DrawCardAction(1));
        if (!upgraded){
            addToBot(new ShiftCardsAction(p, 1, true));
        }
        else {
            addToBot(new ShiftCardsAction(p, 1));
        }
    }

    public void upp() {
        this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}