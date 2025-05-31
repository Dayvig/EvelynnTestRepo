package EvelynnTest.cards.testcards;

import EvelynnTest.cards.AbstractEasyCard;
import EvelynnTest.powers.HidePower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;

import static EvelynnTest.EvelynnTestMod.makeID;

public class HideCard2 extends AbstractEasyCard {
    public final static String ID = makeID("HideCard2");

    public static final int UPG_MAGIC = 4;
    public static final int MAGIC = 12;

    public HideCard2() {
            super(ID, 3, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        this.baseMagicNumber = magicNumber = MAGIC;
        this.color = CardColor.RED;

    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new HidePower(p, magicNumber), magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(UPG_MAGIC);
    }
}