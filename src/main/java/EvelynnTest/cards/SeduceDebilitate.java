package EvelynnTest.cards;

import EvelynnTest.powers.Megavuln;
import EvelynnTest.powers.Megaweak;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ChokePower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

import java.util.Iterator;

import static EvelynnTest.EvelynnTestMod.makeID;

public class SeduceDebilitate extends AbstractShiftingCard {
    public final static String ID = makeID("SeduceDebilitate");
    public final static int MAGIC = 2;
    public final static int UPG_MAGIC = 1;

    public SeduceDebilitate() {
        super(ID, 0, CardType.SKILL, CardRarity.RARE, CardTarget.ENEMY);
        setDemonValues(0, CardType.SKILL, CardTarget.ENEMY, cardStrings.EXTENDED_DESCRIPTION[0]);
        this.baseMagicNumber = magicNumber = MAGIC;
    }

    public SeduceDebilitate(boolean isCopy){
        super(ID, 0, CardType.SKILL, CardRarity.RARE, CardTarget.ENEMY, Form.NORMAL, isCopy);
        setDemonValues(0, CardType.ATTACK, CardTarget.ENEMY, cardStrings.EXTENDED_DESCRIPTION[0]);
        this.baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        super.use(p, m);
    }

    @Override
    public void useNormal(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        if (abstractMonster.hasPower(VulnerablePower.POWER_ID)){
            int amnt = (int)Math.floor((double)abstractMonster.getPower(VulnerablePower.POWER_ID).amount / 5);
            addToBot(new ApplyPowerAction(abstractMonster, abstractPlayer, new Megavuln(abstractMonster, amnt*magicNumber, false), amnt*magicNumber));
            addToBot(new RemoveSpecificPowerAction(abstractMonster, abstractMonster, VulnerablePower.POWER_ID));
        }
    }

    @Override
    public void useDemon(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        if (abstractMonster.hasPower(WeakPower.POWER_ID)){
            int amnt = (int)Math.floor((double)abstractMonster.getPower(WeakPower.POWER_ID).amount / 5);
            addToBot(new ApplyPowerAction(abstractMonster, abstractPlayer, new Megaweak(abstractMonster, amnt*magicNumber, false), amnt*magicNumber));
            addToBot(new RemoveSpecificPowerAction(abstractMonster, abstractMonster, WeakPower.POWER_ID));
        }
    }

    @Override
    public AbstractShiftingCard makeShiftingCopy() {
        return new SeduceDebilitate(true);
    }

    public void upp() {
        super.upp();
        upgradeMagicNumber(UPG_MAGIC);
        initializeDescription();
    }
}