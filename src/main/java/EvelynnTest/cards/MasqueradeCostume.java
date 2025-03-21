package EvelynnTest.cards;

import EvelynnTest.EvelynnTestMod;
import EvelynnTest.powers.BusinessCasualPower;
import EvelynnTest.powers.MasqueradePower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static EvelynnTest.EvelynnTestMod.makeID;

public class MasqueradeCostume extends AbstractEasyCard {

    public final static String ID = makeID("MasqueradeCostume");

    private static final int MAGIC = 1;

    public MasqueradeCostume() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
        tags.add(EvelynnTestMod.CustomTags.OUTFIT);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new MasqueradePower(p, magicNumber, this.makeStatEquivalentCopy()), magicNumber));
    }

    public void upp() {
        this.isInnate = true;
        this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}