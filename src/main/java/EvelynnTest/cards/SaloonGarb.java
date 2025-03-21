package EvelynnTest.cards;

import EvelynnTest.EvelynnTestMod;
import EvelynnTest.powers.RitualRobePower;
import EvelynnTest.powers.SaloonGarbPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static EvelynnTest.EvelynnTestMod.makeID;

public class SaloonGarb extends AbstractEasyCard {

    public final static String ID = makeID("SaloonGarb");

    private static final int MAGIC = 3;

    public SaloonGarb() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
        tags.add(EvelynnTestMod.CustomTags.OUTFIT);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new SaloonGarbPower(p, magicNumber, this.makeStatEquivalentCopy()), 0));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}