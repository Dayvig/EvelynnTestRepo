package EvelynnTest.cards;

import EvelynnTest.EvelynnTestMod;
import EvelynnTest.powers.FancyDressPower;
import EvelynnTest.powers.FashionistaPower;
import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static EvelynnTest.EvelynnTestMod.makeID;

public class Fashionista extends AbstractEasyCard {

    public final static String ID = makeID("Fashionista");

    private static final int MAGIC = 1;
    private static final int UPGRADED_COST = 1;

    public Fashionista() {
        super(ID, 2, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new FashionistaPower(p, magicNumber), magicNumber));
    }


    public void upp() {
        upgradeBaseCost(UPGRADED_COST);
    }
}