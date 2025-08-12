package EvelynnTest.cards;

import EvelynnTest.util.Wiz;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ChokePower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.vfx.combat.ClashEffect;

import java.util.Iterator;

import static EvelynnTest.EvelynnTestMod.makeID;

public class TeaseRake extends AbstractShiftingCard {
    public final static String ID = makeID("TeaseRake");
    public final static int MAGIC = 1;
    public final static int UPG_MAGIC = 1;
    public final static int DAMAGE = 9;
    public final static int UPG_DAMAGE = 1;
    public final static int BLOCK = 6;
    public final static int UPG_BLOCK = 1;

    public TeaseRake() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.ENEMY);
        setDemonValues(1, CardType.ATTACK, CardTarget.ENEMY, cardStrings.EXTENDED_DESCRIPTION[0]);
        this.baseMagicNumber = magicNumber = MAGIC;
        this.baseDamage = damage = DAMAGE;
        this.baseBlock = block = BLOCK;
    }

    public TeaseRake(boolean isCopy){
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.ENEMY, Form.NORMAL, isCopy);
        setDemonValues(1, CardType.ATTACK, CardTarget.ENEMY, cardStrings.EXTENDED_DESCRIPTION[0]);
        this.baseMagicNumber = magicNumber = MAGIC;
        this.baseDamage = damage = DAMAGE;
        this.baseBlock = block = BLOCK;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        super.use(p, m);
    }

    @Override
    public void useNormal(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new ApplyPowerAction(abstractMonster, abstractPlayer, new VulnerablePower(abstractMonster, magicNumber, false)));
        addToBot(new GainBlockAction(abstractPlayer, block));
    }

    @Override
    public void useDemon(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        if (abstractMonster.hasPower(VulnerablePower.POWER_ID)){
            addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, damage, DamageInfo.DamageType.NORMAL)));
            addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, damage, DamageInfo.DamageType.NORMAL)));
            addToBot(new VFXAction(new ClashEffect(abstractMonster.hb.cX, abstractMonster.hb.cY)));
        }
        else {
            addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        }
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo){
        super.calculateCardDamage(mo);
        triggerOnGlowCheck(mo.hasPower(VulnerablePower.POWER_ID) && Wiz.actuallyHovered(mo.hb));
    }

    public void triggerOnGlowCheck(boolean glowing){
        triggerOnGlowCheck();
        if (currentForm.equals(Form.DEMON) && this.isGlowing && glowing) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        }
        else if (currentForm.equals(Form.DEMON) && this.isGlowing) {
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }



    @Override
    public void applyPowers(){
        if (!AbstractDungeon.player.isDraggingCard){triggerOnGlowCheck(false);}
    }

    @Override
    public AbstractShiftingCard makeShiftingCopy() {
        return new TeaseRake(true);
    }

    public void upp() {
        super.upp();
        upgradeMagicNumber(UPG_MAGIC);
        upgradeDamage(UPG_DAMAGE);
        upgradeBlock(UPG_BLOCK);
        initializeDescription();
    }
}