package EvelynnTest.cards;

import EvelynnTest.EvelynnTestMod;
import EvelynnTest.powers.StilettoPower;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static EvelynnTest.EvelynnTestMod.makeID;

public class HateSpike extends AbstractEasyCard {
    public final static String ID = makeID("HateSpike");

    public static final int DAMAGE = 3;
    public static final int UPG_DAMAGE = 2;

    public HateSpike() {
        super(ID, 0, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ALL_ENEMY, CardColor.COLORLESS);
        baseDamage = damage = DAMAGE;
        this.isMultiDamage = true;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        allDmg(AbstractGameAction.AttackEffect.SLASH_VERTICAL);
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo){
        super.calculateCardDamage(mo);
        if (AbstractDungeon.player.hasPower(StilettoPower.POWER_ID)){
            damage *= 2;
            isDamageModified = true;
        }
    }

    @Override
    public void applyPowers(){
        super.applyPowers();
        if (AbstractDungeon.player.hasPower(StilettoPower.POWER_ID)){
            System.out.println("test");
            damage *= 2;
            isDamageModified = true;
        }
    }

    public void upp() {
        upgradeDamage(UPG_DAMAGE);
    }
}
