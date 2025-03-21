package EvelynnTest.powers;

import EvelynnTest.EvelynnTestMod;
import EvelynnTest.util.Wiz;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;

public class WardrobePower extends AbstractEasyPower {

    private static final String SIMPLE_NAME = "WardrobePower";
    public static final String POWER_ID = EvelynnTestMod.makeID(SIMPLE_NAME);
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String LOC_NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public WardrobePower(AbstractCreature owner, int amount) {
        super(SIMPLE_NAME, PowerType.BUFF, false, owner, amount);
        name = LOC_NAME;
        updateDescription();
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c.tags.contains(EvelynnTestMod.CustomTags.ACCESSORY) || c.tags.contains(EvelynnTestMod.CustomTags.OUTFIT)) {
                c.retain = true;
            }
        }
    }

    @Override
    public void atStartOfTurnPostDraw(){
        System.out.println("ech");
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c.tags.contains(EvelynnTestMod.CustomTags.ACCESSORY) || c.tags.contains(EvelynnTestMod.CustomTags.OUTFIT)) {
                if (c.cost > 0 && c.costForTurn > 0 && !c.freeToPlayOnce){
                    c.updateCost(-this.amount);
                    System.out.println("updated" + c.costForTurn + "|" + c.cost);
                    break;
                }
            }
        }
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}
