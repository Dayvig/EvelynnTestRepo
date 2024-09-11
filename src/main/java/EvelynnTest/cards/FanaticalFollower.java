package EvelynnTest.cards;

import EvelynnTest.actions.BetterSummonGremlinAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static EvelynnTest.EvelynnTestMod.makeID;

public class FanaticalFollower extends AbstractEasyCard {
    public final static String ID = makeID("FanaticalFollower");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public FanaticalFollower() {
        super(ID, 0, CardType.SKILL, CardRarity.BASIC, CardTarget.NONE);
        this.baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new BetterSummonGremlinAction());
    }

    public void upp() {
    }
}