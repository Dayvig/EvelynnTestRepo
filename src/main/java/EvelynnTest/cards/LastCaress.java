package EvelynnTest.cards;

import EvelynnTest.EvelynnTestMod;
import EvelynnTest.powers.CharmPower;
import EvelynnTest.powers.FervorPower;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.actions.common.FetchAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.ClashEffect;

import static EvelynnTest.EvelynnTestMod.makeID;

public class LastCaress extends AbstractEasyCard {
    public final static String ID = makeID("LastCaress");

    public static final int DAMAGE = 20;
    public static final int UPG_DAMAGE = 5;

    public static int originalBaseDamage;

    public LastCaress() {
        super(ID, 3, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        this.baseDamage = damage = DAMAGE;
        originalBaseDamage = baseDamage;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        addToBot(new VFXAction(new ClashEffect(m.hb.cX, m.hb.cY)));
    }


    @Override
    public void calculateCardDamage(AbstractMonster mo){
        super.calculateCardDamage(mo);
        int doubles = 0;
        if (EvelynnTestMod.isInfatuated(mo)){
            this.damage *= 2;
            isDamageModified = true;
            doubles++;
        }
        if (mo.currentHealth <= mo.maxHealth/2){
            this.damage *= 2;
            isDamageModified = true;
            doubles++;
        }
        triggerOnGlowCheck();
        if (isGlowing) {
            this.glowColor = doubles > 0 ? doubles > 1 ? Color.RED.cpy() : AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy() : AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }

    @Override
    public void triggerOnGlowCheck(){
        super.triggerOnGlowCheck();
        if (this.isGlowing && (this.glowColor == Color.RED.cpy() || this.glowColor == AbstractCard.GOLD_BORDER_GLOW_COLOR)){
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }

    public void upp() {
        upgradeDamage(UPG_DAMAGE);
    }
}