package EvelynnTest.cards;

import EvelynnTest.powers.FervorPower;
import com.evacipated.cardcrawl.mod.stslib.actions.common.FetchAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static EvelynnTest.EvelynnTestMod.makeID;

public class Fervor extends AbstractEasyCard {
    public final static String ID = makeID("Fervor");

    public static final int DAMAGE = 6;
    public static final int UPG_DAMAGE = 3;
    public static final int MAGIC = 2;

    public Fervor() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.baseDamage = damage = DAMAGE;
        this.baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        FervorPower Fpower = new FervorPower(p, magicNumber, true);
        Fpower.initialApplication = true;
        addToBot(new ApplyPowerAction(p, p, Fpower, magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(UPG_DAMAGE);
    }
}