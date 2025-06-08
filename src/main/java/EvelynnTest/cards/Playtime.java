package EvelynnTest.cards;

import EvelynnTest.powers.CharmPower;
import EvelynnTest.stances.DemonShadeStance;
import EvelynnTest.util.Wiz;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static EvelynnTest.EvelynnTestMod.isInfatuated;
import static EvelynnTest.EvelynnTestMod.makeID;

public class Playtime extends AbstractEasyCard {
    public final static String ID = makeID("Playtime");

    private static final int MAGIC = 5;
    private static final int UPG_MAGIC = 3;

    public Playtime() {
        super(ID, 2, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        this.exhaust = true;
        this.isMultiDamage = true;
        this.baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ChangeStanceAction(new DemonShadeStance()));
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                for (AbstractMonster mo : Wiz.getEnemies()){
                    if (mo.hasPower(CharmPower.POWER_ID)){
                        addToBot(new LoseHPAction(mo, p, mo.getPower(CharmPower.POWER_ID).amount * magicNumber, AttackEffect.POISON));
                        addToBot(new RemoveSpecificPowerAction(mo, p, CharmPower.POWER_ID));
                    }
                }
                isDone = true;
            }
        });
    }

    public void upp() {
        upgradeMagicNumber(UPG_MAGIC);
        initializeDescription();
    }
}