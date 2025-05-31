package EvelynnTest.cards.testcards;

import EvelynnTest.cards.AbstractEasyCard;
import EvelynnTest.powers.HideNext;
import EvelynnTest.powers.HidePower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static EvelynnTest.EvelynnTestMod.makeID;

public class HideCard5 extends AbstractEasyCard {
    public final static String ID = makeID("HideCard5");


    public HideCard5() {
            super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        this.exhaust = true;
        this.color = CardColor.RED;

    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (p.hasPower(HidePower.POWER_ID)){
            if (upgraded) {
                addToBot(new ApplyPowerAction(p, p, new HidePower(p, p.getPower(HidePower.POWER_ID).amount * 2), p.getPower(HidePower.POWER_ID).amount * 2));
            }
            else {
                addToBot(new ApplyPowerAction(p, p, new HidePower(p, p.getPower(HidePower.POWER_ID).amount), p.getPower(HidePower.POWER_ID).amount));
            }
        }
    }

    public void upp() {
        this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}