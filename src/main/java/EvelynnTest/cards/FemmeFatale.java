package EvelynnTest.cards;

import EvelynnTest.EvelynnTestMod;
import EvelynnTest.powers.BusinessCasualPower;
import EvelynnTest.powers.DevilCostumePower;
import EvelynnTest.powers.FancyDressPower;
import EvelynnTest.powers.FemmeFatalePower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static EvelynnTest.EvelynnTestMod.makeID;

public class FemmeFatale extends AbstractEasyCard {

    public final static String ID = makeID("FemmeFatale");

    private static final int MAGIC = 1;
    private static final int BLOCK = 10;

    public FemmeFatale() {
        super(ID, 2, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
        tags.add(EvelynnTestMod.CustomTags.OUTFIT);
        baseBlock = block = BLOCK;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new FemmeFatalePower(p, magicNumber, upgraded ? 20 : 10, this.makeStatEquivalentCopy()), magicNumber));
    }

    public void upp() {
        upgradeBlock(10);
    }
}