package EvelynnTest.cards;

import EvelynnTest.powers.HotColdPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static EvelynnTest.EvelynnTestMod.makeID;

public class HotCold extends AbstractEasyCard {
    public final static String ID = makeID("HotCold");

    public HotCold() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new HotColdPower(p, 0)));
    }

    public void upp() {
        upgradeBaseCost(0);
    }
}