package EvelynnTest.cards.testcards;

import EvelynnTest.cards.AbstractEasyCard;
import EvelynnTest.powers.HidePower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static EvelynnTest.EvelynnTestMod.makeID;

public class HideCard3 extends AbstractEasyCard {
    public final static String ID = makeID("HideCard3");

    public static final int UPG_MAGIC = 2;
    public static final int MAGIC = 4;
    public static final int BLOCK = 10;
    public static final int UPG_BLOCk = 4;

    public HideCard3() {
            super(ID, 2, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        this.baseMagicNumber = magicNumber = MAGIC;
        this.baseBlock = block = BLOCK;
        this.color = CardColor.RED;

    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new HidePower(p, magicNumber), magicNumber));
        blck();
    }

    public void upp() {
        upgradeMagicNumber(UPG_MAGIC);
        upgradeBlock(UPG_BLOCk);
    }
}