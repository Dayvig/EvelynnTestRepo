package EvelynnTest.cards;

import EvelynnTest.util.Wiz;
import com.evacipated.cardcrawl.mod.stslib.actions.common.FetchAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;

import static EvelynnTest.EvelynnTestMod.makeID;

public class Abuse extends AbstractEasyCard {
    public final static String ID = makeID("Abuse");

    public static final int DAMAGE = 8;
    public static final int BLOCK = 8;
    public static final int UPG_DAMAGE = 1;
    public static final int MAGIC = 1;

    public Abuse() {
        super(ID, 1, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        this.baseDamage = damage = DAMAGE;
        this.baseBlock = block = BLOCK;
        this.baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                if (m.currentHealth <= m.maxHealth/2){
                    addToBot(new GainEnergyAction(magicNumber));
                }
                this.isDone = true;
            }
        });
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        blck();
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo){
        super.calculateCardDamage(mo);
        triggerOnGlowCheck(mo.currentHealth <= mo.maxHealth/2 && Wiz.actuallyHovered(mo.hb));
    }

    public void triggerOnGlowCheck(boolean glowing){
        triggerOnGlowCheck();
        if (this.isGlowing && glowing) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        }
        else if (this.isGlowing) {
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }

    public void upp() {
        upgradeMagicNumber(UPG_DAMAGE);
        upgradeDamage(UPG_DAMAGE);
        upgradeBlock(UPG_DAMAGE);
        this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}