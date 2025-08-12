package EvelynnTest.cards.testcards;

import EvelynnTest.cards.AbstractEasyCard;
import EvelynnTest.powers.HidePower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static EvelynnTest.EvelynnTestMod.makeID;

public class HideCard7 extends AbstractEasyCard {
    public final static String ID = makeID("HideCard7");

    public static final int UPG_MAGIC = 2;
    public static final int MAGIC = 4;
    public static final int DAMAGE = 8;

    public HideCard7() {
            super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
            baseDamage = damage = DAMAGE;
            baseMagicNumber = magicNumber = MAGIC;
        this.color = CardColor.RED;

    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        addToBot(new ApplyPowerAction(p, p, new HidePower(p, magicNumber), magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(UPG_MAGIC);
    }
}