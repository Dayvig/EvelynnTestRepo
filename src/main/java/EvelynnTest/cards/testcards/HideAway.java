package EvelynnTest.cards.testcards;

import EvelynnTest.cards.AbstractEasyCard;
import EvelynnTest.powers.HidePower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.WeakPower;
import jdk.vm.ci.code.Register;

import static EvelynnTest.EvelynnTestMod.makeID;

public class HideAway extends AbstractEasyCard {
    public final static String ID = makeID("HideAway");

    public static final int UPG_MAGIC = 1;
    public static final int MAGIC = 4;
    public static final int MAGIC2 = 2;
    public static final int UPG_MAGIC2 = -1;

    public HideAway() {
        super(ID, 0, CardType.SKILL, CardRarity.BASIC, CardTarget.SELF);
        this.baseMagicNumber = magicNumber = MAGIC;
        this.baseSecondMagic = secondMagic = MAGIC2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new HidePower(p, magicNumber), magicNumber));
        addToBot(new ApplyPowerAction(p, p, new WeakPower(p, secondMagic, true), secondMagic));
    }

    public void upp() {
        upgradeMagicNumber(UPG_MAGIC);
        upgradeSecondMagic(UPG_MAGIC2);
    }
}