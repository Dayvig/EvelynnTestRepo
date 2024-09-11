package EvelynnTest.powers;

import EvelynnTest.EvelynnTestMod;
import EvelynnTest.util.Wiz;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class EveningGlovesPower extends Accessory {

    private static final String SIMPLE_NAME = "EveningGlovesPower";
    public static final String POWER_ID = EvelynnTestMod.makeID(SIMPLE_NAME);
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String LOC_NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public EveningGlovesPower(AbstractCreature owner, int amount, AbstractCard outfit) {
        super(SIMPLE_NAME, PowerType.BUFF, false, owner, amount, outfit);
        name = LOC_NAME;
        updateDescription();
    }

    @Override
    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        super.onApplyPower(power, target, source);
        if (power.ID.equals(CharmPower.POWER_ID) && source.equals(this.owner)){
            addToBot(new GainBlockAction(this.owner, this.amount));
        }
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

}
