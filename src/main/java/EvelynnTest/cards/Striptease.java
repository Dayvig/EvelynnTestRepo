package EvelynnTest.cards;

import EvelynnTest.powers.AllurePower;
import EvelynnTest.powers.CharmPower;
import EvelynnTest.powers.Outfit;
import EvelynnTest.util.Wiz;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static EvelynnTest.EvelynnTestMod.makeID;

public class Striptease extends AbstractEasyCard {
    public final static String ID = makeID("Striptease");

    public static final int MAGIC = 12;
    public static final int UPG_MAGIC = 3;

    public Striptease() {
        super(ID, 0, CardType.SKILL, CardRarity.RARE, CardTarget.ALL_ENEMY);
        this.baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                Outfit out;
                this.isDone = true;
                for (AbstractPower pow : p.powers){
                    if (pow instanceof Outfit){
                       out = (Outfit)pow;
                       addToBot(new RemoveSpecificPowerAction(p, p, out));
                       addToBot(new MakeTempCardInDiscardAction(out.outfitCard, 1));
                       break;
                    }
                }
            }
        });
        for (AbstractMonster mo : Wiz.getEnemies()){
            addToBot(new ApplyPowerAction(mo, p, new CharmPower(mo, magicNumber), magicNumber));
        }
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        for (AbstractPower pow : p.powers){
            if (pow instanceof Outfit){
                return super.canUse(p, m);
            }
        }
        return false;
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

    public void upp() {
        upgradeMagicNumber(UPG_MAGIC);
    }
}