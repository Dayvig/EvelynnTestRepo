package EvelynnTest.cards;

import EvelynnTest.powers.CharmPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.MinionPower;
import com.megacrit.cardcrawl.vfx.combat.HemokinesisEffect;

import static EvelynnTest.EvelynnTestMod.makeID;

public class KissSuck extends AbstractShiftingCard {
    public final static String ID = makeID("KissSuck");
    public final static int MAGIC = 10;
    public final static int UPG_MAGIC = 2;

    public KissSuck() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.ENEMY);
        setDemonValues(1, CardType.SKILL, CardTarget.ENEMY, cardStrings.EXTENDED_DESCRIPTION[0]);
        this.baseMagicNumber = magicNumber = MAGIC;
    }

    public KissSuck(boolean isCopy){
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.ENEMY, Form.NORMAL, isCopy);
        setDemonValues(1, CardType.SKILL, CardTarget.ENEMY, cardStrings.EXTENDED_DESCRIPTION[0]);
        this.baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        super.use(p, m);
    }

    @Override
    public void useNormal(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new ApplyPowerAction(abstractMonster, abstractPlayer, new CharmPower(abstractMonster, magicNumber), magicNumber));
        addToBot(new HealAction(abstractMonster, abstractPlayer, magicNumber));
    }

    @Override
    public void useDemon(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                addToBot(new VFXAction(new HemokinesisEffect(abstractMonster.hb.cX, abstractMonster.hb.cY, abstractPlayer.hb.cX, abstractPlayer.hb.cY)));
                addToBot(new WaitAction(0.5f));
                if (abstractMonster.currentHealth <= magicNumber && !abstractMonster.hasPower(MinionPower.POWER_ID)){
                    addToBot(new HealAction(abstractPlayer, abstractPlayer, magicNumber));
                }
                addToBot(new LoseHPAction(abstractMonster, abstractMonster, magicNumber));
                isDone = true;
            }
        });
    }

    @Override
    public AbstractShiftingCard makeShiftingCopy() {
        return new KissSuck(true);
    }

    public void upp() {
        upgradeMagicNumber(UPG_MAGIC);
        initializeDescription();
    }
}