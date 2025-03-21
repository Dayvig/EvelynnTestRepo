package EvelynnTest.cards;

import EvelynnTest.actions.ShiftCardsAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static EvelynnTest.EvelynnTestMod.makeID;

public class Unpredictability extends AbstractEasyCard {
    public final static String ID = makeID("Unpredictability");

    public static final int BLOCK = 6;
    public static final int UPG_BLOCK = 2;
    public static final int MAGIC = 2;
    public static final int UPG_MAGIC = 1;

    public Unpredictability() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = block = BLOCK;
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        addToBot(new ShiftCardsAction(p, magicNumber));
    }

    public void upp() {
        upgradeBlock(UPG_BLOCK);
        upgradeMagicNumber(UPG_MAGIC);
    }
}