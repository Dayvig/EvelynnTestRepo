package EvelynnTest.cards;

import EvelynnTest.powers.CharmPower;
import EvelynnTest.util.Wiz;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;

import static EvelynnTest.EvelynnTestMod.makeID;

public class EnticeDominate extends AbstractShiftingCard {
    public final static String ID = makeID("EnticeDominate");
    public final static int MAGIC = 3;
    public final static int UPG_MAGIC = 1;

    public EnticeDominate() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        setDemonValues(1, CardType.SKILL, CardTarget.ALL_ENEMY, cardStrings.EXTENDED_DESCRIPTION[0]);
        this.baseMagicNumber = magicNumber = MAGIC;
        isInnate = true;
        exhaust = true;
    }

    public EnticeDominate(boolean isCopy){
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY, Form.NORMAL, isCopy);
        setDemonValues(1, CardType.SKILL, CardTarget.ALL_ENEMY, cardStrings.EXTENDED_DESCRIPTION[0]);
        this.baseMagicNumber = magicNumber = MAGIC;
        isInnate = true;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        super.use(p, m);
    }

    @Override
    public void useNormal(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        for (AbstractMonster mo : Wiz.getEnemies()){
            addToBot(new ApplyPowerAction(mo, abstractPlayer, new WeakPower(mo, magicNumber, false), magicNumber));
        }
    }

    @Override
    public void useDemon(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        for (AbstractMonster mo : Wiz.getEnemies()){
            addToBot(new ApplyPowerAction(mo, abstractPlayer, new VulnerablePower(mo, magicNumber, false), magicNumber));
        }
    }

    @Override
    public AbstractShiftingCard makeShiftingCopy() {
        return new EnticeDominate(true);
    }

    public void upp() {
        upgradeMagicNumber(UPG_MAGIC);
        initializeDescription();
    }
}