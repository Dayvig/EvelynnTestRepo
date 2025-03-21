package EvelynnTest.cards;

import EvelynnTest.actions.BetterSummonGremlinAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static EvelynnTest.EvelynnTestMod.makeID;

public class FanaticalFollower extends AbstractEasyCard {
    public final static String ID = makeID("FanaticalFollower");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public FanaticalFollower() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        this.baseMagicNumber = magicNumber = 1;
        this.selfRetain = false;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new BetterSummonGremlinAction());
    }

    public void upp() {
        this.selfRetain = true;
        this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}