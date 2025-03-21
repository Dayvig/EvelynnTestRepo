package EvelynnTest.cards;

import EvelynnTest.powers.AllurePower;
import EvelynnTest.powers.CharmPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.ScrapeEffect;

import static EvelynnTest.EvelynnTestMod.isInfatuated;
import static EvelynnTest.EvelynnTestMod.makeID;

public class MarkEviscerate extends AbstractShiftingCard {
    public final static String ID = makeID("MarkEviscerate");
    public final static int MAGIC = 4;
    public final static int DAMAGE = 4;
    public final static int UPG_MAGIC = 1;
    public final static int MAGIC2 = 5;
    public final static int DAMAGE2 = 5;

    public MarkEviscerate() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        setDemonValues(3, CardType.ATTACK, CardTarget.ENEMY, cardStrings.EXTENDED_DESCRIPTION[0]);
        this.baseMagicNumber = magicNumber = MAGIC;
        this.baseDamage = damage = DAMAGE;
        this.baseSecondDamage = secondDamage = DAMAGE2;
        this.baseSecondMagic = secondMagic = MAGIC2;
    }

    public MarkEviscerate(boolean isCopy) {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY, Form.NORMAL, isCopy);
        setDemonValues(3, CardType.ATTACK, CardTarget.ENEMY, cardStrings.EXTENDED_DESCRIPTION[0]);
        this.baseMagicNumber = magicNumber = MAGIC;
        this.baseDamage = damage = DAMAGE;
        this.baseSecondDamage = secondDamage = DAMAGE2;
        this.baseSecondMagic = secondMagic = MAGIC2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        super.use(p, m);
    }

    @Override
    public void useNormal(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, damage, DamageInfo.DamageType.NORMAL)));
        addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, damage, DamageInfo.DamageType.NORMAL)));
        addToBot(new VFXAction(new ScrapeEffect(abstractMonster.hb.cX, abstractMonster.hb.cY)));
        addToBot(new ApplyPowerAction(abstractMonster, abstractPlayer, new CharmPower(abstractMonster, magicNumber), magicNumber));
    }

    @Override
    public void applyPowers(){
        super.applyPowers();
        this.magicNumber = baseMagicNumber;
        if (AbstractDungeon.player.hasPower(AllurePower.POWER_ID)){
            this.magicNumber += AbstractDungeon.player.getPower(AllurePower.POWER_ID).amount;
        }
        this.isMagicNumberModified = AbstractDungeon.player.hasPower(AllurePower.POWER_ID);
    }

    @Override
    public void useDemon(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        for (int i = 0; i < secondMagic; i++){
            addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, secondDamage, DamageInfo.DamageType.NORMAL)));
            addToBot(new VFXAction(new ScrapeEffect(abstractMonster.hb.cX, abstractMonster.hb.cY)));
        }
    }

    @Override
    public AbstractShiftingCard makeShiftingCopy() {
        return new MarkEviscerate(true);
    }

    public void upp() {
        super.upp();
        upgradeMagicNumber(UPG_MAGIC);
        upgradeDamage(UPG_MAGIC);
        upgradeSecondDamage(UPG_MAGIC);
        initializeDescription();
    }
}