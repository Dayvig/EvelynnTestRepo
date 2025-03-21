package EvelynnTest.cards;

import EvelynnTest.EvelynnTestMod;
import EvelynnTest.powers.BlingPower;
import EvelynnTest.powers.EveningGlovesPower;
import EvelynnTest.powers.GrudgePower;
import EvelynnTest.powers.PunishmentPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static EvelynnTest.EvelynnTestMod.makeID;

public class Resentment extends AbstractEasyCard {

    public final static String ID = makeID("Resentment");

    private static final int MAGIC = 1;
    private static final int UPGRADED_COST = 0;

    public Resentment() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.ENEMY);
        this.cardsToPreview = new HateSpike();
        this.baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(m, p, new GrudgePower(m, magicNumber, false), magicNumber));
    }

    public void upp() {
        upgradeBaseCost(UPGRADED_COST);
    }
}