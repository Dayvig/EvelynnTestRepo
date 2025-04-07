package EvelynnTest.cards;

import EvelynnTest.EvelynnTestMod;
import EvelynnTest.powers.AccessorizedPower;
import com.evacipated.cardcrawl.mod.stslib.actions.common.FetchAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.function.Predicate;

import static EvelynnTest.EvelynnTestMod.makeID;

public class Accessorize extends AbstractEasyCard {
    public final static String ID = makeID("Accessorize");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public Accessorize() {
        super(ID, 0, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new AccessorizedPower(p, -1)));
        if (upgraded){
             addToBot(new FetchAction(p.drawPile, card -> (card.hasTag(EvelynnTestMod.CustomTags.ACCESSORY)), 1));
        }
    }

    public void upp() {
        this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}