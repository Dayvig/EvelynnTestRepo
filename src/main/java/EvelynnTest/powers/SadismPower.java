package EvelynnTest.powers;

import EvelynnTest.EvelynnTestMod;
import EvelynnTest.cards.Fervor;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import java.util.UUID;

public class SadismPower extends AbstractEasyPower {
    // intellij stuff Example, buff, false
    private static final String SIMPLE_NAME = "SadismPower";
    public static final String POWER_ID = EvelynnTestMod.makeID(SIMPLE_NAME);
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String LOC_NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public AbstractCard lastTriggered;
    public int lastDamage = 0;

    public SadismPower(AbstractCreature owner, int amount, boolean isSourceMonster) {
        super(SIMPLE_NAME, PowerType.BUFF, false, owner, amount);
        name = LOC_NAME;
        updateDescription();
    }

    @Override
    public void onAfterUseCard(AbstractCard card, UseCardAction action) {
        if (!card.target.equals(AbstractCard.CardTarget.ENEMY) && !card.target.equals(AbstractCard.CardTarget.SELF_AND_ENEMY)){
            return;
        }
        if (card.type.equals(AbstractCard.CardType.ATTACK) &&
                action.target.currentHealth+lastDamage <= ((action.target.maxHealth / 2)) && (card.cost <= 1 || card.costForTurn <= 1 || card.freeToPlayOnce) &&
                (lastTriggered == null || lastTriggered != card)) {
            AbstractCard newCard = card.makeStatEquivalentCopy();
            lastTriggered = newCard;
            newCard.freeToPlayOnce = true;
            addToBot(new NewQueueCardAction(newCard, action.target));
        }
    }

    @Override
    public float atDamageFinalGive(float damage, DamageInfo.DamageType type) {
        lastDamage = (int)damage;
        return super.atDamageFinalGive(damage, type);
    }


    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }
}
