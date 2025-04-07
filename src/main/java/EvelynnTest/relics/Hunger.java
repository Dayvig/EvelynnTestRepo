package EvelynnTest.relics;

import EvelynnTest.EvelynnTestChar;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static EvelynnTest.EvelynnTestMod.makeID;

public class Hunger extends AbstractEasyRelic {
    public static final String ID = makeID("Hunger");
    int bonusAchieved = 0;


    public Hunger() {
        super(ID, RelicTier.STARTER, LandingSound.FLAT, EvelynnTestChar.Enums.EVELYNN_TEST_COLOR);
    }

    @Override
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        if (counter < 30) {
            if (target.currentHealth <= target.maxHealth/2){
                counter++;
            }
            counter++;
        }
        if (counter >= 10 && bonusAchieved == 0){
            addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, 1)));
            addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new DexterityPower(AbstractDungeon.player, 1)));
            bonusAchieved++;
            this.flash();
        }
        if (counter >= 20 && bonusAchieved == 1){
            addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, 1)));
            addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new DexterityPower(AbstractDungeon.player, 1)));
            bonusAchieved++;
            this.flash();
        }
        if (counter >= 30 && bonusAchieved == 2){
            addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, 1)));
            addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new DexterityPower(AbstractDungeon.player, 1)));
            bonusAchieved++;
            this.flash();
        }
    }

    @Override
    public void atBattleStart(){
        counter = 0;
        bonusAchieved = 0;
    }
}
