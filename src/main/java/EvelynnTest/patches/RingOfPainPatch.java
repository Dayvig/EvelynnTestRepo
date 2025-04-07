package EvelynnTest.patches;

import EvelynnTest.cards.RingOfPain;
import EvelynnTest.powers.CharmPower;
import EvelynnTest.powers.RingOfPainPower;
import EvelynnTest.powers.SpikedCollarPower;
import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;

public class RingOfPainPatch {

    @SpirePatch(
            clz = VulnerablePower.class,
            method = "atDamageReceive",
            paramtypez = {
                    float.class,
                    DamageInfo.DamageType.class
            }
    )
    public static class RingPatch {
        @SpirePostfixPatch
        public static float Postfix(float result, VulnerablePower __instance, float __damage, DamageInfo.DamageType __type) {
            if (AbstractDungeon.player.hasPower(RingOfPainPower.POWER_ID) && !__instance.owner.isPlayer){
                return __damage * ((result/__damage) + (AbstractDungeon.player.getPower(RingOfPainPower.POWER_ID).amount * 0.01f));
            }
            else {
                return result;
            }
        }
    }
}
