package EvelynnTest.cards;

import EvelynnTest.EvelynnTestMod;
import com.evacipated.cardcrawl.mod.stslib.actions.common.FetchAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static EvelynnTest.EvelynnTestMod.makeID;

public class QuickChange extends AbstractEasyCard {
    public final static String ID = makeID("QuickChange");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public QuickChange() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new FetchAction(p.drawPile, card -> (card.hasTag(EvelynnTestMod.CustomTags.ACCESSORY) || card.hasTag(EvelynnTestMod.CustomTags.OUTFIT)), 1));
    }

    public void upp() {
        this.exhaust = false;
        this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}