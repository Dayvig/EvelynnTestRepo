package EvelynnTest.cards;

import EvelynnTest.EvelynnTestMod;
import EvelynnTest.powers.BusinessCasualPower;
import EvelynnTest.powers.FancyDressPower;
import EvelynnTest.powers.PopstarOutfitPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static EvelynnTest.EvelynnTestMod.makeID;

public class PopstarOutfit extends AbstractEasyCard {

    public final static String ID = makeID("PopstarOutfit");

    private static final int MAGIC = 2;
    private static final int UPG_PLUS_MAGIC = 1;

    public PopstarOutfit() {
        super(ID, 2, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
        tags.add(EvelynnTestMod.CustomTags.OUTFIT);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new PopstarOutfitPower(p, magicNumber, this.makeStatEquivalentCopy()), magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(UPG_PLUS_MAGIC);
    }
}