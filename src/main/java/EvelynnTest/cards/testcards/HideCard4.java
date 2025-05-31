package EvelynnTest.cards.testcards;

import EvelynnTest.cards.AbstractEasyCard;
import EvelynnTest.powers.HideNext;
import EvelynnTest.powers.HidePower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static EvelynnTest.EvelynnTestMod.makeID;

public class HideCard4 extends AbstractEasyCard {
    public final static String ID = makeID("HideCard4");

    public static final int UPG_MAGIC = 2;
    public static final int MAGIC = 8;
    public static final int BLOCK = 15;
    public static final int UPG_BLOCk = 5;

    public HideCard4() {
            super(ID, 3, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        this.baseMagicNumber = magicNumber = MAGIC;
        this.baseBlock = block = BLOCK;
        this.color = CardColor.RED;

    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new HideNext(p, magicNumber), magicNumber));
        blck();
    }

    public void upp() {
        upgradeMagicNumber(UPG_MAGIC);
        upgradeBlock(UPG_BLOCk);
    }
}