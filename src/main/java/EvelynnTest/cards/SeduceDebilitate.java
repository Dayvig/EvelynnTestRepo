package EvelynnTest.cards;

import EvelynnTest.powers.DominatrixPower;
import EvelynnTest.powers.Megavuln;
import EvelynnTest.powers.Megaweak;
import EvelynnTest.powers.SeductressPower;
import basemod.interfaces.AddAudioSubscriber;
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
    public final static int MAGIC = 50;
    public final static int MAGIC2 = 15;
    public final static int UPGRADED_COST = 0;

    public SeduceDebilitate() {
        super(ID, 1, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        setDemonValues(0, CardType.SKILL, CardTarget.ENEMY, cardStrings.EXTENDED_DESCRIPTION[0]);
        this.baseMagicNumber = magicNumber = MAGIC;
        this.baseSecondMagic = secondMagic = MAGIC2;
    }

    public SeduceDebilitate(boolean isCopy){
        super(ID, 0, CardType.POWER, CardRarity.RARE, CardTarget.ENEMY, Form.NORMAL, isCopy);
        setDemonValues(0, CardType.ATTACK, CardTarget.ENEMY, cardStrings.EXTENDED_DESCRIPTION[0]);
        this.baseMagicNumber = magicNumber = MAGIC;
        this.baseSecondMagic = secondMagic = MAGIC2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        super.use(p, m);
    }

    @Override
    public void useNormal(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer, new SeductressPower(abstractPlayer, magicNumber), magicNumber));
    }

    @Override
    public void useDemon(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer, new DominatrixPower(abstractPlayer, secondMagic), secondMagic));
    }

    @Override
    public AbstractShiftingCard makeShiftingCopy() {
        return new SeduceDebilitate(true);
    }

    public void upp() {
        super.upp();
        upgradeBaseCost(UPGRADED_COST);
        initializeDescription();
    }
}