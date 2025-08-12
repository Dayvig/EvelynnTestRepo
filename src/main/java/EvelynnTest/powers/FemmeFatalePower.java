package EvelynnTest.powers;

import EvelynnTest.EvelynnTestMod;
import EvelynnTest.util.Wiz;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;

public class FemmeFatalePower extends Outfit {

    private static final String SIMPLE_NAME = "FemmeFatalePower";
    public static final String POWER_ID = EvelynnTestMod.makeID(SIMPLE_NAME);
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String LOC_NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public FemmeFatalePower(AbstractCreature owner, int amount, int amount2, AbstractCard outfit) {
        super(SIMPLE_NAME, PowerType.BUFF, false, owner, amount, outfit);
        name = LOC_NAME;
        this.amount2 = amount2;
        updateDescription();
    }

    @Override
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        if (damageAmount > target.currentBlock){
            for (AbstractMonster mo : Wiz.getEnemies()){
                if (!mo.equals(target)){
                    addToBot(new ApplyPowerAction(mo, this.owner, new CharmPower(mo, this.amount)));
                }
            }
        }
    }

    @Override
    public void activateWhenFullyCharmed(){
        //addToBot(new GainBlockAction(this.owner, this.amount2));
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1] + amount2 + DESCRIPTIONS[2];
    }

}
