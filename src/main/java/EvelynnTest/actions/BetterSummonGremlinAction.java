
package EvelynnTest.actions;

import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ModHelper;
import com.megacrit.cardcrawl.helpers.MonsterHelper;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.MinionPower;
import com.megacrit.cardcrawl.powers.SlowPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import java.util.ArrayList;

public class BetterSummonGremlinAction extends AbstractGameAction {
    private AbstractMonster m;
    int num;
    public BetterSummonGremlinAction() {
        this.actionType = ActionType.SPECIAL;
        if (Settings.FAST_MODE) {
            this.startDuration = Settings.ACTION_DUR_FAST;
        } else {
            this.startDuration = Settings.ACTION_DUR_LONG;
        }

        this.duration = this.startDuration;
            this.m = this.getRandomGremlin();
            for (AbstractRelic r : AbstractDungeon.player.relics) {
                r.onSpawnMonster(this.m);
            }
            num = 1;
    }

    public BetterSummonGremlinAction(int count) {
        this.actionType = ActionType.SPECIAL;
        if (Settings.FAST_MODE) {
            this.startDuration = Settings.ACTION_DUR_FAST;
        } else {
            this.startDuration = Settings.ACTION_DUR_LONG;
        }

        this.duration = this.startDuration;
        this.m = this.getRandomGremlin();
        for (AbstractRelic r : AbstractDungeon.player.relics) {
            r.onSpawnMonster(this.m);
        }
        num = count;
    }

    private AbstractMonster getRandomGremlin() {
        ArrayList<String> pool = new ArrayList<>();
        pool.add("GremlinWarrior");
        pool.add("GremlinWarrior");
        pool.add("GremlinThief");
        pool.add("GremlinThief");
        pool.add("GremlinFat");
        pool.add("GremlinFat");
        pool.add("GremlinTsundere");
        pool.add("GremlinWizard");
        float x = getSmartPosition(0f)[0];
        float y = getSmartPosition(0f)[1];

        System.out.println("xPos "+x);
        System.out.println("yPos "+y);


        return MonsterHelper.getGremlin((String)pool.get(AbstractDungeon.aiRng.random(0, pool.size() - 1)), x, y);
    }

    private float[] getSmartPosition(float yPos) {
        float offsetX = 0f;
        float offsetY = yPos;

        //finds nearest X position to the left of that y value
        for(AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            System.out.println((mo.drawY) + "|" + offsetY);
            if ((mo.drawY) >= offsetY){
                offsetX = Math.min(((mo.drawX - (mo.hb.width/2) - ((float) Settings.WIDTH * 0.75F)) / Settings.scale), offsetX);
            }
        }

        //if x position is in player area, repeat with higher Y position
        if ((offsetX - (75f * Settings.scale) + (((float) Settings.WIDTH * 0.75F) / Settings.scale)) < (AbstractDungeon.player.drawX + (AbstractDungeon.player.hb.width * 2))){
            return getSmartPosition(yPos + (100f * Settings.scale));
        }

        return new float[]{offsetX - (75f * Settings.scale), offsetY};
    }

    public boolean isGremlin(AbstractMonster monster){
        return ((monster.id.equals("GremlinWarrior")) ||
                (monster.id.equals("GremlinThief")) ||
                (monster.id.equals("GremlinFat")) ||
                (monster.id.equals("GremlinTsundere")) ||
                (monster.id.equals("GremlinWizard")));
    }

    public void update() {
        if (this.duration == this.startDuration) {
            this.m.animX = 1200.0F * Settings.xScale;
            this.m.init();
            this.m.applyPowers();
            AbstractDungeon.getCurrRoom().monsters.addMonster(0, this.m);
            if (ModHelper.isModEnabled("Lethality")) {
                this.addToBot(new ApplyPowerAction(this.m, this.m, new StrengthPower(this.m, 3), 3));
            }

            if (ModHelper.isModEnabled("Time Dilation")) {
                this.addToBot(new ApplyPowerAction(this.m, this.m, new SlowPower(this.m, 0)));
            }

            this.addToBot(new ApplyPowerAction(this.m, this.m, new MinionPower(this.m)));
        }

        this.tickDuration();
        if (this.isDone) {
            this.m.animX = 0.0F;
            this.m.showHealthBar();
            this.m.usePreBattleAction();
        } else {
            this.m.animX = Interpolation.fade.apply(0.0F, 1200.0F * Settings.xScale, this.duration);
        }

    }
}
