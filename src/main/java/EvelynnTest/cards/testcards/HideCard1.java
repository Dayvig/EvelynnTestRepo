package EvelynnTest.cards.testcards;

import EvelynnTest.cards.AbstractEasyCard;
import EvelynnTest.powers.HidePower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import jdk.vm.ci.code.Register;

import static EvelynnTest.EvelynnTestMod.makeID;

public class HideCard1 extends AbstractEasyCard {
    public final static String ID = makeID("HideCard1");

    public static final int UPG_MAGIC = 1;
    public static final int MAGIC = 4;

    public HideCard1() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        this.baseMagicNumber = magicNumber = MAGIC;
        this.color = CardColor.RED;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new HidePower(p, magicNumber), magicNumber));
        addToBot(new ApplyPowerAction(p,p, new ArtifactPower(p, 1)));
    }

    public void upp() {
        upgradeMagicNumber(UPG_MAGIC);
    }
}