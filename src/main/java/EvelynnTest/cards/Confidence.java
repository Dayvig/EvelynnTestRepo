package EvelynnTest.cards;

import com.evacipated.cardcrawl.mod.stslib.actions.common.FetchAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import javax.smartcardio.Card;
import java.util.function.Predicate;

import static EvelynnTest.EvelynnTestMod.makeID;

public class Confidence extends AbstractEasyCard {
    public final static String ID = makeID("Confidence");
    // intellij stuff skill, self, basic, , ,  5, 3, ,
    public static final int BLOCK = 7;
    public static final int UPG_BLOCK = 3;

    public Confidence() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseBlock = block = BLOCK;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        CardGroup specialGroup = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        specialGroup.group.addAll(p.discardPile.group);
        specialGroup.group.addAll(p.drawPile.group);
        addToBot(new FetchAction(specialGroup, card -> (card.type.equals(CardType.SKILL))));
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                for (AbstractCard c : AbstractDungeon.player.hand.group){
                    if (c.type.equals(CardType.SKILL)){
                        c.setCostForTurn(c.cost-1);
                    }
                }
                isDone = true;
            }
        });
    }

    public void upp() {
        upgradeBaseCost(0);
    }
}