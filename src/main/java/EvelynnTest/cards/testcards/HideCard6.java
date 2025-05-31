package EvelynnTest.cards.testcards;

import EvelynnTest.cards.AbstractEasyCard;
import EvelynnTest.powers.HidePower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static EvelynnTest.EvelynnTestMod.makeID;

public class HideCard6 extends AbstractEasyCard {
    public final static String ID = makeID("HideCard6");

    private static final int MAGIC = 3;
    private static final int UPG_MAGIC = 1;

    public HideCard6() {
            super(ID, -1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
            this.baseMagicNumber = magicNumber = MAGIC;
        this.color = CardColor.RED;

    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ScurryAction(p, this.magicNumber, this.freeToPlayOnce, this.energyOnUse));
    }

    public void upp() {
        upgradeMagicNumber(UPG_MAGIC);
    }
}