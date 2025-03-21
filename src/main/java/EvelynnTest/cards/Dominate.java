package EvelynnTest.cards;

import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.actions.common.FetchAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.combat.ViolentAttackEffect;

import static EvelynnTest.EvelynnTestMod.makeID;

public class Dominate extends AbstractEasyCard {
    public final static String ID = makeID("Dominate");

    public static final int DAMAGE = 10;
    public static final int MAGIC = 3;
    public static final int UPG_DAMAGE = 4;
    public static final int UPG_MAGIC = 1;

    public Dominate() {
        super(ID, 2, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        this.baseDamage = damage = DAMAGE;
        this.baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.NONE));
        int stackAmount = 0;
        if (m.hasPower(WeakPower.POWER_ID)){stackAmount += m.getPower(WeakPower.POWER_ID).amount;}
        if (m.hasPower(VulnerablePower.POWER_ID)){stackAmount += m.getPower(VulnerablePower.POWER_ID).amount;}
        if (stackAmount > 12){ stackAmount = 12; }
        if (stackAmount < 1){stackAmount = 1;}
        for (int i = 0; i < (int)Math.ceil((double)stackAmount/2); i++){
            addToBot(new VFXAction(new ViolentAttackEffect(m.hb.cX, m.hb.cY, Color.FIREBRICK.cpy())));
        }
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo){
        super.calculateCardDamage(mo);
        if (mo.hasPower(WeakPower.POWER_ID)){
            isDamageModified = true;
            damage += (mo.getPower(WeakPower.POWER_ID).amount * magicNumber);
        }
        if (mo.hasPower(VulnerablePower.POWER_ID)){
            isDamageModified = true;
            damage += (mo.getPower(VulnerablePower.POWER_ID).amount * magicNumber);
        }
    }

    public void upp() {
        upgradeDamage(UPG_DAMAGE);
        upgradeMagicNumber(UPG_MAGIC);
    }
}