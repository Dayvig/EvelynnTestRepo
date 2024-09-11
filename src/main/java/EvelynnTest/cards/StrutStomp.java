package EvelynnTest.cards;

import EvelynnTest.powers.CharmPower;
import EvelynnTest.powers.StrutPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;

import static EvelynnTest.EvelynnTestMod.makeID;

public class StrutStomp extends AbstractShiftingCard {
    public final static String ID = makeID("StrutStomp");
    public final static int MAGIC = 4;
    public final static int UPG_MAGIC = 1;
    public final static int BLOCK = 7;
    public final static int UPG_BLOCK_DMG = 2;
    public final static int DAMAGE = 7;

    public StrutStomp() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        setDemonValues(0, CardType.ATTACK, CardTarget.ENEMY, cardStrings.EXTENDED_DESCRIPTION[0]);
        this.baseMagicNumber = magicNumber = MAGIC;
        this.baseDamage = damage = DAMAGE;
        this.baseBlock = block = BLOCK;
    }

    public StrutStomp(boolean isCopy){
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF, Form.NORMAL, isCopy);
        setDemonValues(0, CardType.ATTACK, CardTarget.ENEMY, cardStrings.EXTENDED_DESCRIPTION[0]);
        this.baseMagicNumber = magicNumber = MAGIC;
        this.baseDamage = damage = DAMAGE;
        this.baseBlock = block = BLOCK;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        super.use(p, m);
    }

    @Override
    public void useNormal(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new GainBlockAction(abstractPlayer, block));
        addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer, new StrutPower(abstractPlayer, magicNumber), magicNumber));
    }

    @Override
    public void useDemon(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                boolean trigger = false;
                if (abstractMonster.hasPower(CharmPower.POWER_ID)){
                    if (abstractMonster.currentHealth <= abstractMonster.getPower(CharmPower.POWER_ID).amount){
                        trigger = true;
                    }
                }
                if (abstractMonster.hasPower(VulnerablePower.POWER_ID)){
                    trigger = true;
                }
                if (trigger){
                    addToBot(new GainEnergyAction(1));
                    addToBot(new DrawCardAction(1));
                }
                dmg(abstractMonster, AttackEffect.SLASH_VERTICAL);
                isDone = true;
            }
        });
    }

    @Override
    public AbstractShiftingCard makeShiftingCopy() {
        return new StrutStomp(true);
    }

    public void upp() {
        upgradeMagicNumber(UPG_MAGIC);
        upgradeBlock(UPG_BLOCK_DMG);
        upgradeDamage(UPG_BLOCK_DMG);
        initializeDescription();
    }
}