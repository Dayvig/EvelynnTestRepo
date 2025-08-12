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

    public Heartbreaker() {
        super(ID, 3, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_HEAVY));
        this.baseDamage = damage = 0;
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return (m.hasPower(CharmPower.POWER_ID));
    }

    @Override
    public void applyPowers(){
        this.rawDescription = cardStrings.DESCRIPTION;
        this.baseDamage = damage = 0;
        initializeDescription();
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo){
        if (mo.hasPower(CharmPower.POWER_ID)){
            this.baseDamage = (mo.getPower(CharmPower.POWER_ID).amount/2);
            super.calculateCardDamage(mo);
            this.rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0] + damage + cardStrings.EXTENDED_DESCRIPTION[1];
        }
        else {
            this.rawDescription = cardStrings.DESCRIPTION;
        }
        initializeDescription();
    }

    public void upp() {
        upgradeBaseCost(UPGRADED_COST);
    }
}