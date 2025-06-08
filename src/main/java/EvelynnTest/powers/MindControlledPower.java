package EvelynnTest.powers;

import EvelynnTest.EvelynnTestMod;
import EvelynnTest.patches.MindControlPatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.InstantKillAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.random.Random;

public class MindControlledPower extends AbstractEasyPower {

    public static final String RAW_ID = "MindControlledPower";
    public static final String POWER_ID = EvelynnTestMod.makeID(RAW_ID);
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public MindControlledPower(AbstractCreature owner) {
        super(NAME, PowerType.DEBUFF, false, owner, -1);
        this.ID = POWER_ID;
    }

    @Override
    public void onInitialApplication() {
        owner.flipHorizontal = !owner.flipHorizontal;
        for (AbstractPower p : AbstractDungeon.player.powers){
            if (p instanceof Outfit){
                Outfit out = (Outfit)p;
                out.activateWhenFullyCharmed();
            }
        }
    }

    @Override
    public void onRemove() {
        owner.flipHorizontal = !owner.flipHorizontal;
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }

    @Override
    public void atEndOfRound(){
        addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this));
    }

}
