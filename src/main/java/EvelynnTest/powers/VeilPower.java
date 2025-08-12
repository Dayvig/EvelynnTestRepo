package EvelynnTest.powers;

import EvelynnTest.EvelynnTestMod;
import EvelynnTest.stances.DemonShadeStance;
import EvelynnTest.util.Wiz;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.status.VoidCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.stances.AbstractStance;

public class VeilPower extends Outfit {

    private static final String SIMPLE_NAME = "VeilPower";
    public static final String POWER_ID = EvelynnTestMod.makeID(SIMPLE_NAME);
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String LOC_NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public VeilPower(AbstractCreature owner, int amount, AbstractCard outfit) {
        super(SIMPLE_NAME, PowerType.BUFF, false, owner, amount, outfit);
        name = LOC_NAME;
        updateDescription();
    }

    @Override
    public void onAfterUseCard(AbstractCard card, UseCardAction action) {
        if (card.type.equals(AbstractCard.CardType.ATTACK) && this.amount > 0) {
            AbstractCreature randomTarget = Wiz.getRandomItem(Wiz.getEnemies());
            addToBot(new ApplyPowerAction(randomTarget, this.owner, new VulnerablePower(randomTarget, 1, false), 1));
            addToBot(new AbstractGameAction() {
                final AbstractPower thisPower = owner.getPower(VeilPower.POWER_ID);
                @Override
                public void update() {
                    thisPower.reducePower(this.amount);
                    thisPower.updateDescription();
                    AbstractDungeon.onModifyPower();
                    this.isDone = true;
                }
            });
        }
    }

    @Override
    public void onChangeStance(AbstractStance oldStance, AbstractStance newStance) {
        if (oldStance.ID.equals(DemonShadeStance.STANCE_ID) && !newStance.ID.equals(DemonShadeStance.STANCE_ID)){
            addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, this));
            addToBot(new ApplyPowerAction(this.owner, this.owner, new GownPower(this.owner, this.amount == 14 ? 1 : 2, outfitCard), this.amount == 14 ? 1 : 2));
        }
    }


    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

}
