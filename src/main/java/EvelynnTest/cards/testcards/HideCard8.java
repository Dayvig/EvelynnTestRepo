package EvelynnTest.cards.testcards;

import EvelynnTest.cards.AbstractEasyCard;
import EvelynnTest.powers.FearfulPower;
import EvelynnTest.powers.HidePower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static EvelynnTest.EvelynnTestMod.makeID;

public class HideCard8 extends AbstractEasyCard {
    public final static String ID = makeID("HideCard8");

    public static final int UPG_MAGIC = 1;
    public static final int MAGIC = 3;

    public HideCard8() {
            super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
            baseMagicNumber = magicNumber = MAGIC;
        this.color = CardColor.RED;

    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new FearfulPower(p, magicNumber), magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(UPG_MAGIC);
    }
}