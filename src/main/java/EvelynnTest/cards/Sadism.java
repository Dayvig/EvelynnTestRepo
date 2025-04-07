package EvelynnTest.cards;

import EvelynnTest.EvelynnTestMod;
import EvelynnTest.powers.BlingPower;
import EvelynnTest.powers.EveningGlovesPower;
import EvelynnTest.powers.PunishmentPower;
import EvelynnTest.powers.SadismPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static EvelynnTest.EvelynnTestMod.makeID;

public class Sadism extends AbstractEasyCard {

    public final static String ID = makeID("Sadism");

    public static final int UPG_COST = 1;

    public Sadism() {
        super(ID, 2, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new SadismPower(p, 1, false), 1));
    }

    public void upp() {
        upgradeBaseCost(UPG_COST);
    }
}