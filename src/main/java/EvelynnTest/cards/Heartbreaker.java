package EvelynnTest.cards;

import EvelynnTest.powers.CharmPower;
import EvelynnTest.powers.MindControlledPower;
import EvelynnTest.util.Wiz;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static EvelynnTest.EvelynnTestMod.makeID;

public class Heartbreaker extends AbstractEasyCard {
    public final static String ID = makeID("Heartbreaker");

    public static final int UPGRADED_COST = 2;
    public static final int DAMAGE = 30;

    public Heartbreaker() {
        super(ID, 3, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_HEAVY));
        this.baseDamage = damage = DAMAGE;
    }

    @Override
    public void applyPowers(){
        this.rawDescription = cardStrings.DESCRIPTION;
        this.baseDamage = damage = DAMAGE;
        initializeDescription();
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo){
        this.baseDamage = 30;
        if (mo.hasPower(MindControlledPower.POWER_ID) && (mo.currentHealth/2) > 30){
            this.baseDamage = (mo.currentHealth/2);
        }
        super.calculateCardDamage(mo);
        this.rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0] + damage + cardStrings.EXTENDED_DESCRIPTION[1];
        initializeDescription();
    }

    public void upp() {
        upgradeBaseCost(UPGRADED_COST);
    }
}