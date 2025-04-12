package EvelynnTest.cards;

import EvelynnTest.powers.CharmPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static EvelynnTest.EvelynnTestMod.makeID;

public class Beauty extends AbstractShiftingCard {
    public final static String ID = makeID("Beauty");

    public static final int MAGIC = 8;
    public static final int UPG_MAGIC = 3;
    public static final int DAMAGE = 3;
    public static final int MAGIC2 = 3;
    public static final int UPG_DAMAGE = 1;

    public Beauty() {
        super(ID, 1, CardType.SKILL, CardRarity.BASIC, CardTarget.ENEMY);
        setDemonValues(1, CardType.ATTACK, CardTarget.ENEMY, cardStrings.EXTENDED_DESCRIPTION[0]);
        this.baseMagicNumber = magicNumber = 8;
        this.baseDamage = damage = DAMAGE;
    }

    public Beauty(boolean isCopy) {
        super(ID, 1, CardType.SKILL, CardRarity.BASIC, CardTarget.ENEMY, Form.NORMAL, isCopy);
        setDemonValues(1, CardType.ATTACK, CardTarget.ENEMY, cardStrings.EXTENDED_DESCRIPTION[0]);
        this.baseMagicNumber = magicNumber = 8;
        this.baseDamage = damage = DAMAGE;
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
       super.use(p, m);
    }

    @Override
    public void useNormal(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster){
        addToBot(new ApplyPowerAction(abstractMonster, abstractPlayer, new CharmPower(abstractMonster, magicNumber), magicNumber));
    }

    @Override
    public void useDemon(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        for (int i = 0; i < 3; i++){
            addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        }
    }

    @Override
    public AbstractShiftingCard makeShiftingCopy() {
        return new Beauty(true);
    }

    public void upp() {
        super.upp();
        upgradeMagicNumber(UPG_MAGIC);
        upgradeDamage(UPG_DAMAGE);
        initializeDescription();
    }
}