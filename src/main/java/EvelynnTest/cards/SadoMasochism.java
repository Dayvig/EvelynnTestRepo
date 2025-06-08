package EvelynnTest.cards;

import EvelynnTest.powers.CharmPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ChokePower;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static EvelynnTest.EvelynnTestMod.makeID;

public class SadoMasochism extends AbstractShiftingCard {
    public final static String ID = makeID("SadoMasochism");
    public final static int MAGIC = 2;
    public final static int MAGIC2 = 3;
    public final static int DAMAGE = 5;
    public final static int UPG_DAMAGE = 5;
    public final static int UPG_MAGIC2 = 1;
    public final static int UPG_BLOCK = 2;
    public final static int BLOCK = 12;

    public SadoMasochism() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.ENEMY);
        setDemonValues(1, CardType.ATTACK, CardTarget.ENEMY, cardStrings.EXTENDED_DESCRIPTION[0]);
        this.baseMagicNumber = magicNumber = MAGIC;
        this.baseDamage = damage = DAMAGE;
        this.baseBlock = block = BLOCK;
        this.baseSecondMagic = secondMagic = MAGIC2;
    }

    public SadoMasochism(boolean isCopy){
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.ENEMY, Form.NORMAL, isCopy);
        setDemonValues(1, CardType.ATTACK, CardTarget.ENEMY, cardStrings.EXTENDED_DESCRIPTION[0]);
        this.baseMagicNumber = magicNumber = MAGIC;
        this.baseDamage = damage = DAMAGE;
        this.baseBlock = block = BLOCK;
        this.baseSecondMagic = secondMagic = MAGIC2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        super.use(p, m);
    }

    public class SadisticDamageInfo extends DamageInfo{
        public boolean isSadistic;
        public SadisticDamageInfo(AbstractCreature damageSource, int base, DamageType type, boolean sadistic) {
            super(damageSource, base, type);
            isSadistic = sadistic;
        }
        public SadisticDamageInfo(AbstractCreature damageSource, int base, DamageType type) {
            super(damageSource, base, type);
            isSadistic = false;
        }
    }

    @Override
    public void useNormal(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new GainBlockAction(abstractPlayer, block));
        addToBot(new ApplyPowerAction(abstractMonster, abstractPlayer, new StrengthPower(abstractMonster, magicNumber), magicNumber));
        addToBot(new ApplyPowerAction(abstractMonster, abstractPlayer, new GainStrengthPower(abstractMonster, -this.magicNumber), -this.magicNumber));
    }

    @Override
    public void useDemon(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new DamageAction(abstractMonster, new SadisticDamageInfo(abstractPlayer, damage, DamageInfo.DamageType.NORMAL, true), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        addToBot(new ApplyPowerAction(abstractMonster, abstractPlayer, new CharmPower(abstractMonster, secondMagic), secondMagic));
    }
    @Override
    public AbstractShiftingCard makeShiftingCopy() {
        return new SadoMasochism(true);
    }

    public void upp() {
        super.upp();
        upgradeBlock(UPG_BLOCK);
        upgradeDamage(UPG_DAMAGE);
        upgradeSecondMagic(UPG_MAGIC2);
        initializeDescription();
    }

}
