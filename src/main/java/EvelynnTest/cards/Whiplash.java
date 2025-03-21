package EvelynnTest.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.actions.watcher.NotStanceCheckAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ChokePower;
import com.megacrit.cardcrawl.vfx.combat.EmptyStanceEffect;

import java.util.Iterator;

import static EvelynnTest.EvelynnTestMod.makeID;

public class Whiplash extends AbstractShiftingCard {
    public final static String ID = makeID("Whiplash");
    public final static int MAGIC = 2;
    public final static int SECONDMAGIC = 4;
    public final static int DAMAGE = 6;
    public final static int UPG_DAMAGE = 4;
    public final static int SECONDDAMAGE = 12;

    public Whiplash() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        setDemonValues(1, CardType.ATTACK, CardTarget.ENEMY, cardStrings.EXTENDED_DESCRIPTION[0]);
        this.baseMagicNumber = magicNumber = MAGIC;
        this.baseSecondMagic = secondMagic = SECONDMAGIC;
        this.baseDamage = damage = DAMAGE;
        this.baseSecondDamage = secondDamage = SECONDDAMAGE;
    }

    public Whiplash(boolean isCopy){
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY, Form.NORMAL, isCopy);
        setDemonValues(1, CardType.ATTACK, CardTarget.ENEMY, cardStrings.EXTENDED_DESCRIPTION[0]);
        this.baseMagicNumber = magicNumber = MAGIC;
        this.baseSecondMagic = secondMagic = SECONDMAGIC;
        this.baseDamage = damage = DAMAGE;
        this.baseSecondDamage = secondDamage = SECONDDAMAGE;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        super.use(p, m);
    }

    @Override
    public void useNormal(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        addToBot(new DrawCardAction(magicNumber));
    }

    @Override
    public void useDemon(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, secondDamage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_HEAVY));
        addToBot(new DrawCardAction(secondMagic));
        Shift();
        addToBot(new NotStanceCheckAction("Neutral", new VFXAction(new EmptyStanceEffect(abstractPlayer.hb.cX, abstractPlayer.hb.cY), 0.1F)));
        addToBot(new ChangeStanceAction("Neutral"));
    }


    @Override
    public AbstractShiftingCard makeShiftingCopy() {
        return new Whiplash(true);
    }

    public void upp() {
        super.upp();
        upgradeDamage(UPG_DAMAGE);
        upgradeSecondDamage(UPG_DAMAGE);
        initializeDescription();
    }
}