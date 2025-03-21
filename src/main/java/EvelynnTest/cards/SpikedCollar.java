package EvelynnTest.cards;

import EvelynnTest.EvelynnTestMod;
import EvelynnTest.powers.EveningGlovesPower;
import EvelynnTest.powers.SpikedCollarPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static EvelynnTest.EvelynnTestMod.makeID;

public class SpikedCollar extends AbstractEasyCard {

    public final static String ID = makeID("SpikedCollar");

    private static final int MAGIC = 2;
    private static final int UPG_MAGIC = 1;

    public SpikedCollar() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
        tags.add(EvelynnTestMod.CustomTags.ACCESSORY);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new SpikedCollarPower(p, magicNumber, this.makeStatEquivalentCopy()), magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(UPG_MAGIC);
    }
}