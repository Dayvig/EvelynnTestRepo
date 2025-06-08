package EvelynnTest.cards;

import EvelynnTest.powers.AllurePower;
import EvelynnTest.powers.CharmPower;
import EvelynnTest.util.Wiz;
import com.evacipated.cardcrawl.mod.stslib.actions.common.FetchAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.function.Predicate;

import static EvelynnTest.EvelynnTestMod.makeID;

public class Shower extends AbstractEasyCard {
    public final static String ID = makeID("Shower");

    public static final int MAGIC = 1;
    public static final int UPG_MAGIC = 1;

    public Shower() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        this.baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ExhaustAction(p, p, 3, false, true, true));
        for (AbstractMonster mo : Wiz.getEnemies()){
            addToBot(new ApplyPowerAction(mo, p, new CharmPower(mo, magicNumber), magicNumber));
        }
    }

    @Override
    public void applyPowers(){
        super.applyPowers();
        this.magicNumber = baseMagicNumber;
        if (AbstractDungeon.player.hasPower(AllurePower.POWER_ID)){
            this.magicNumber += AbstractDungeon.player.getPower(AllurePower.POWER_ID).amount;
        }
        this.isMagicNumberModified = AbstractDungeon.player.hasPower(AllurePower.POWER_ID);
    }

    public void upp() {
        upgradeMagicNumber(UPG_MAGIC);
    }
}