package EvelynnTest.cards;

import EvelynnTest.EvelynnTestMod;
import EvelynnTest.actions.ShiftCardsAction;
import EvelynnTest.powers.CoolShadesPower;
import EvelynnTest.powers.RetractableClawsPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static EvelynnTest.EvelynnTestMod.makeID;

public class RetractableClaws extends AbstractEasyCard {

    public final static String ID = makeID("RetractableClaws");

    private static final int UPGRADED_COST = 0;

    public RetractableClaws() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        tags.add(EvelynnTestMod.CustomTags.ACCESSORY);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ShiftCardsAction(p, p.hand.size()));
        addToBot(new ApplyPowerAction(p, p, new RetractableClawsPower(p, magicNumber, this.makeStatEquivalentCopy()), magicNumber));
    }

    public void upp() {
        upgradeBaseCost(UPGRADED_COST);
    }
}