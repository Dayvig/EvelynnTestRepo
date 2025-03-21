package EvelynnTest.cards;

import EvelynnTest.powers.CharmPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.MinionPower;
import com.megacrit.cardcrawl.vfx.combat.HemokinesisEffect;

import static EvelynnTest.EvelynnTestMod.isInfatuated;
import static EvelynnTest.EvelynnTestMod.makeID;

public class PainPleasure extends AbstractShiftingCard {
    public final static String ID = makeID("PainPleasure");
    public final static int MAGIC = 16;
    public final static int UPG_MAGIC = 2;

    public PainPleasure() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        setDemonValues(2, CardType.ATTACK, CardTarget.ENEMY, cardStrings.EXTENDED_DESCRIPTION[0]);
        this.baseMagicNumber = magicNumber = MAGIC;
        this.baseBlock = block = MAGIC;
        this.baseDamage = damage = MAGIC;
    }

    public PainPleasure(boolean isCopy){
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY, Form.NORMAL, isCopy);
        setDemonValues(2, CardType.ATTACK, CardTarget.ENEMY, cardStrings.EXTENDED_DESCRIPTION[0]);
        this.baseMagicNumber = magicNumber = MAGIC;
        this.baseBlock = block = MAGIC;
        this.baseDamage = damage = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        super.use(p, m);
    }

    @Override
    public void useNormal(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        if (isInfatuated(abstractMonster)){
            addToBot(new GainBlockAction(abstractPlayer, block));
            addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        }
    }

    @Override
    public void useDemon(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        addToBot(new ApplyPowerAction(abstractMonster, abstractPlayer, new CharmPower(abstractMonster, magicNumber)));
    }

    @Override
    public AbstractShiftingCard makeShiftingCopy() {
        return new PainPleasure(true);
    }

    public void upp() {
        super.upp();
        upgradeMagicNumber(UPG_MAGIC);
        initializeDescription();
    }
}