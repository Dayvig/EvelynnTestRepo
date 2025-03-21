package EvelynnTest.cards;

import EvelynnTest.powers.CharmPower;
import EvelynnTest.powers.MindControlledPower;
import EvelynnTest.stances.DemonShadeStance;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static EvelynnTest.EvelynnTestMod.makeID;

public class DemonShade extends AbstractEasyCard {
    public final static String ID = makeID("DemonShade");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public DemonShade() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.NONE);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ChangeStanceAction(new DemonShadeStance()));
    }

    public void upp() {
        upgradeBaseCost(0);
    }
}