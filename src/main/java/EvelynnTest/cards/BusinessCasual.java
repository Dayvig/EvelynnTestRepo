package EvelynnTest.cards;

import EvelynnTest.EvelynnTestMod;
import EvelynnTest.powers.BusinessCasualPower;
import EvelynnTest.powers.FancyDressPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static EvelynnTest.EvelynnTestMod.makeID;

public class BusinessCasual extends AbstractEasyCard {

    public final static String ID = makeID("BusinessCasual");

    private static final int MAGIC = 2;

    public BusinessCasual() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
        tags.add(EvelynnTestMod.CustomTags.OUTFIT);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new BusinessCasualPower(p, magicNumber, this.makeStatEquivalentCopy()), magicNumber));
        if (upgraded){
            addToBot(new DrawCardAction(2));
        }
    }

    public void upp() {
        this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}