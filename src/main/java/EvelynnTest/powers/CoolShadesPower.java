package EvelynnTest.powers;

import EvelynnTest.EvelynnTestMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
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
        addToBot(new ApplyPowerAction(this.owner, this.owner, new AllurePower(this.owner, strVigor), strVigor));
        prevStrengthAndVigor = strVigor;
    }

    public void setShades(){
        int newStrVigor = StrengthAndVigor();
        int baseAllureValue = 0;
        if (this.owner.hasPower(AllurePower.POWER_ID)){
            baseAllureValue += this.owner.getPower(AllurePower.POWER_ID).amount;
        }
        baseAllureValue -= prevStrengthAndVigor;
        if (owner.hasPower(AllurePower.POWER_ID)){
            owner.getPower(AllurePower.POWER_ID).amount = baseAllureValue + newStrVigor;
        }
        else {
            addToBot(new ApplyPowerAction(this.owner, this.owner, new AllurePower(this.owner, baseAllureValue + newStrVigor), baseAllureValue + newStrVigor));
        }
        prevStrengthAndVigor = newStrVigor;
    }

    @Override
    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        setShades();
    }

    public int StrengthAndVigor(){
        int amnt = 0;
        if (this.owner.hasPower(StrengthPower.POWER_ID)){
            amnt += this.owner.getPower(StrengthPower.POWER_ID).amount;
        }
        if (this.owner.hasPower(VigorPower.POWER_ID)){
            amnt += this.owner.getPower(VigorPower.POWER_ID).amount;
        }
        return amnt;
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }
}
