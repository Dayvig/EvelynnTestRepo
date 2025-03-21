package EvelynnTest.cards;

import com.megacrit.cardcrawl.actions.utility.DiscardToHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static EvelynnTest.EvelynnTestMod.makeID;

public class ExtraPadding extends AbstractEasyCard {
    public final static String ID = makeID("ExtraPadding");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public ExtraPadding() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = 4;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
    }

    @Override
    public void onPlayCard(AbstractCard c, AbstractMonster m) {
        if (c.type.equals(CardType.POWER) && AbstractDungeon.player.discardPile.group.contains(this)){
            addToBot(new DiscardToHandAction(this));
        }
    }

    public void upp() {
        upgradeBlock(2);
    }
}