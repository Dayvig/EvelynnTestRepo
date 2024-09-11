package EvelynnTest.cards;

import EvelynnTest.powers.EveningGlovesPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static EvelynnTest.EvelynnTestMod.makeID;

public class EveningGloves extends AbstractEasyCard {

    public final static String ID = makeID("EveningGloves");

    private static final int MAGIC = 3;
    private static final int UPG_MAGIC = 1;

    public EveningGloves() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new EveningGlovesPower(p, magicNumber, this.makeStatEquivalentCopy()), magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(UPG_MAGIC);
    }
}