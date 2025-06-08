package EvelynnTest.powers;

import EvelynnTest.EvelynnTestMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;

public class CoolShadesPower extends Accessory {

    private static final String SIMPLE_NAME = "CoolShadesPower";
    public static final String POWER_ID = EvelynnTestMod.makeID(SIMPLE_NAME);
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String LOC_NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public int prevStrengthAndVigor = 0;

    public CoolShadesPower(AbstractCreature owner, int amount, AbstractCard outfit) {
        super(SIMPLE_NAME, PowerType.BUFF, false, owner, amount, outfit);
        name = LOC_NAME;
        updateDescription();
    }

    public void onAfterCardPlayed(AbstractCard usedCard) {
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                setShades();
                this.isDone = true;
            }
        });
    }

    public void onInitialApplication() {
        int strVigor = StrengthAndVigor();
        if (strVigor != 0) {
            addToBot(new ApplyPowerAction(this.owner, this.owner, new AllurePower(this.owner, strVigor), strVigor));
        }
        prevStrengthAndVigor = strVigor;
    }

    public void setShades(){
        int newStrVigor = StrengthAndVigor();
        if (newStrVigor == 1 && prevStrengthAndVigor == 0){
            addToBot(new ApplyPowerAction(this.owner, this.owner, new AllurePower(this.owner, this.amount), this.amount));
        }
        if (newStrVigor == 0 && prevStrengthAndVigor == 1){
            addToBot(new ReducePowerAction(this.owner, this.owner, this.owner.getPower(AllurePower.POWER_ID), this.amount));
        }
        prevStrengthAndVigor = newStrVigor;
    }

    @Override
    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        setShades();
    }

    public int StrengthAndVigor(){
        if (this.owner.hasPower(VigorPower.POWER_ID)){
            return 1;
        }
        return 0;
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }
}
