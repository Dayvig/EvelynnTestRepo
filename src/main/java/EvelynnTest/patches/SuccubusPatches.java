package EvelynnTest.patches;

import EvelynnTest.powers.CharmPower;
import EvelynnTest.powers.SpikedCollarPower;
import EvelynnTest.powers.SuccubusPower;
import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.unique.RemoveAllPowersAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;

import java.util.Locale;

public class SuccubusPatches {
    @SpirePatch(
            clz = ReducePowerAction.class,
            method = "update",
            paramtypez = {
            }
    )
    public static class SuccubusReducePatch {
        @SpirePrefixPatch
        public static SpireReturn<Void> Prefix(ReducePowerAction __instance) {
            String toRemoveID = "";
            if ((ReflectionHacks.getPrivate(__instance, ReducePowerAction.class, "powerID") != null)){
                toRemoveID = (ReflectionHacks.getPrivate(__instance, ReducePowerAction.class, "powerID"));
            }
            if ((ReflectionHacks.getPrivate(__instance, ReducePowerAction.class, "powerInstance") != null)) {
                AbstractPower toRemove = ReflectionHacks.getPrivate(__instance, ReducePowerAction.class, "powerInstance");
                toRemoveID = toRemove.ID;
            }
            if (AbstractDungeon.player.hasPower(SuccubusPower.POWER_ID) && toRemoveID.equals(VigorPower.POWER_ID) && __instance.target.equals(AbstractDungeon.player)){
                __instance.isDone = true;
                return SpireReturn.Return();
            }
            return SpireReturn.Continue();
        }
    }

    @SpirePatch(
            clz = RemoveSpecificPowerAction.class,
            method = "update",
            paramtypez = {
            }
    )
    public static class SuccubusRemovePatch {
        @SpirePrefixPatch
        public static SpireReturn<Void> Prefix(RemoveSpecificPowerAction __instance) {
            String toRemoveID = "";
            if ((ReflectionHacks.getPrivate(__instance, RemoveSpecificPowerAction.class, "powerToRemove") != null)){
                toRemoveID = (ReflectionHacks.getPrivate(__instance, RemoveSpecificPowerAction.class, "powerToRemove"));
            }
            if ((ReflectionHacks.getPrivate(__instance, RemoveSpecificPowerAction.class, "powerInstance") != null)) {
                AbstractPower toRemove = ReflectionHacks.getPrivate(__instance, RemoveSpecificPowerAction.class, "powerInstance");
                toRemoveID = toRemove.ID;
            }
            if (AbstractDungeon.player.hasPower(SuccubusPower.POWER_ID) && toRemoveID.equals(VigorPower.POWER_ID) && __instance.target.equals(AbstractDungeon.player)){
                __instance.isDone = true;
                return SpireReturn.Return();
            }
            return SpireReturn.Continue();
        }
    }
    @SpirePatch(
            clz = ApplyPowerAction.class,
            method = "update",
            paramtypez = {
            }
    )
    public static class SuccubusNegativeApplyPatch {
        @SpirePrefixPatch
        public static SpireReturn<Void> Prefix(ApplyPowerAction __instance) {
            if (__instance.amount < 0) {
                AbstractPower toRemove = (ReflectionHacks.getPrivate(__instance, ApplyPowerAction.class, "powerToApply"));
                if (AbstractDungeon.player.hasPower(SuccubusPower.POWER_ID) && toRemove.ID.equals(VigorPower.POWER_ID) && __instance.target.equals(AbstractDungeon.player)) {
                    __instance.isDone = true;
                    return SpireReturn.Return();
                }
            }
            return SpireReturn.Continue();
        }
    }
}