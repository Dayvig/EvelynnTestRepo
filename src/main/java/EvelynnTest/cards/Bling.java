package EvelynnTest.cards;

import EvelynnTest.EvelynnTestMod;
import EvelynnTest.powers.BlingPower;
import EvelynnTest.powers.EveningGlovesPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static EvelynnTest.EvelynnTestMod.makeID;

public class Bling extends AbstractEasyCard {

    public final static String ID = makeID("Bling");

    private static final int MAGIC = 10;
    private static final int UPG_MAGIC = 5;
    private static final int BLOCK = 10;

    public Bling() {
        super(ID, 2, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
        tags.add(EvelynnTestMod.CustomTags.ACCESSORY);
        this.baseBlock = block = BLOCK;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        addToBot(new ApplyPowerAction(p, p, new BlingPower(p, magicNumber, this.makeStatEquivalentCopy()), magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(UPG_MAGIC);
        upgradeBlock(UPG_MAGIC);
    }
}