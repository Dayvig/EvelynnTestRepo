package EvelynnTest.cards;

import com.evacipated.cardcrawl.mod.stslib.actions.common.FetchAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static EvelynnTest.EvelynnTestMod.makeID;

public class PlayCoy extends AbstractEasyCard {
    public final static String ID = makeID("Harder");

    public static final int BLOCK = 5;
    public static final int UPG_BLOCK = 3;

    public PlayCoy() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        this.cardsToPreview = new HateSpike();
        this.baseBlock = block = BLOCK;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        addToBot(new FetchAction(p.drawPile, card -> (card.type.equals(CardType.SKILL))));
    }

    public void upp() {
        upgradeMagicNumber(UPG_BLOCK);
    }
}