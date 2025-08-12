package EvelynnTest.temp;

import EvelynnTest.cards.AbstractShiftingCard;
import EvelynnTest.powers.GownPower;
import EvelynnTest.powers.VeilPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.status.VoidCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static EvelynnTest.EvelynnTestMod.makeID;

public class GownVeil extends AbstractShiftingCard {

    public final static String ID = makeID("GownVeil");

    private static final int MAGIC = 3;
    private static final int UPG_MAGIC1 = 1;

    public GownVeil() {
        super(ID, 1, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
        setDemonValues(1, CardType.POWER, CardTarget.SELF, cardStrings.EXTENDED_DESCRIPTION[0]);
    }

    public GownVeil(boolean isCopy){
        super(ID, 1, CardType.POWER, CardRarity.RARE, CardTarget.SELF, Form.NORMAL, isCopy);
        baseMagicNumber = magicNumber = MAGIC;
        setDemonValues(1, CardType.POWER, CardTarget.SELF, cardStrings.EXTENDED_DESCRIPTION[0]);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        super.use(p, m);
    }

    @Override
    public void useNormal(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer, new GownPower(abstractPlayer, magicNumber, this.makeStatEquivalentCopy()), magicNumber));
    }

    @Override
    public void useDemon(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer, new VeilPower(abstractPlayer, magicNumber, this.makeStatEquivalentCopy()), magicNumber));
    }

    @Override
    public AbstractShiftingCard makeShiftingCopy() {
        return new GownVeil(true);
    }

    public void upp() {
        upgradeMagicNumber(UPG_MAGIC1);
    }
}