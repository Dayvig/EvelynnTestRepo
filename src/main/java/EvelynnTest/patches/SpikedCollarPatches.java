package EvelynnTest.patches;

import EvelynnTest.powers.CharmPower;
import EvelynnTest.powers.SpikedCollarPower;
import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.unique.RemoveAllPowersAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;

public class SpikedCollarPatches {
    @SpirePatch(
            clz = ReducePowerAction.class,
            method = "update",
            paramtypez = {
            }
    )
    public static class SpikedCollarReducePatch {
        @SpirePostfixPatch
        public static void Postfix(ReducePowerAction __instance) {
            boolean hasCharmPowerID = (ReflectionHacks.getPrivate(__instance, ReducePowerAction.class, "powerID") != null && ReflectionHacks.getPrivate(__instance, ReducePowerAction.class, "powerID").equals(CharmPower.POWER_ID));
            boolean hasCharmPower = (ReflectionHacks.getPrivate(__instance, ReducePowerAction.class, "powerInstance") != null && ReflectionHacks.getPrivate(__instance, ReducePowerAction.class, "powerInstance") instanceof CharmPower);
            if (
                    __instance.amount > 0 &&
                            AbstractDungeon.player.hasPower(SpikedCollarPower.POWER_ID) &&
                            __instance.target != AbstractDungeon.player && (hasCharmPower || hasCharmPowerID)
                )
            {
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new VigorPower(AbstractDungeon.player, AbstractDungeon.player.getPower(SpikedCollarPower.POWER_ID).amount), AbstractDungeon.player.getPower(SpikedCollarPower.POWER_ID).amount));
                __instance.isDone = true;
            }
        }
    }

    @SpirePatch(
            clz = RemoveSpecificPowerAction.class,
            method = "update",
            paramtypez = {
            }
    )
    public static class SpikedCollarRemovePatch {
        @SpirePostfixPatch
        public static void Postfix(RemoveSpecificPowerAction __instance) {
            boolean hasCharmPowerID = (ReflectionHacks.getPrivate(__instance, RemoveSpecificPowerAction.class, "powerToRemove") != null && ReflectionHacks.getPrivate(__instance, RemoveSpecificPowerAction.class, "powerToRemove").equals(CharmPower.POWER_ID));
            boolean hasCharmPower = (ReflectionHacks.getPrivate(__instance, RemoveSpecificPowerAction.class, "powerInstance") != null && ReflectionHacks.getPrivate(__instance, RemoveSpecificPowerAction.class, "powerInstance") instanceof CharmPower);

            if (
                    AbstractDungeon.player.hasPower(SpikedCollarPower.POWER_ID) &&
                    __instance.target != AbstractDungeon.player &&
                    (hasCharmPower || hasCharmPowerID)
            )
            {
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new VigorPower(AbstractDungeon.player, AbstractDungeon.player.getPower(SpikedCollarPower.POWER_ID).amount), AbstractDungeon.player.getPower(SpikedCollarPower.POWER_ID).amount));
                __instance.isDone = true;
            }
        }
    }
    @SpirePatch(
            clz = ApplyPowerAction.class,
            method = "update",
            paramtypez = {
            }
    )
    public static class SpikedCollarNegativeApplyPatch {
        @SpirePostfixPatch
        public static void Postfix(ApplyPowerAction __instance) {
            if (AbstractDungeon.player.hasPower(SpikedCollarPower.POWER_ID)) {
                boolean hasCharmPower = (ReflectionHacks.getPrivate(__instance, ApplyPowerAction.class, "powerInstance") != null && ReflectionHacks.getPrivate(__instance, ApplyPowerAction.class, "powerInstance") instanceof CharmPower);
                if (
                        AbstractDungeon.player.hasPower(SpikedCollarPower.POWER_ID) &&
                                __instance.target != AbstractDungeon.player &&
                                __instance.amount < 0 && hasCharmPower
                ) {
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new VigorPower(AbstractDungeon.player, AbstractDungeon.player.getPower(SpikedCollarPower.POWER_ID).amount), AbstractDungeon.player.getPower(SpikedCollarPower.POWER_ID).amount));
                    __instance.isDone = true;
                }
            }
        }
    }
    @SpirePatch(
            clz = RemoveAllPowersAction.class,
            method = "update",
            paramtypez = {
            }
    )
    public static class SpikedCollarRemoveAll {
        @SpirePostfixPatch
        public static void Postfix(RemoveAllPowersAction __instance) {
            AbstractCreature targetcreature = ReflectionHacks.getPrivate(__instance, RemoveAllPowersAction.class, "c");
            if (
                    targetcreature != null && AbstractDungeon.player.hasPower(SpikedCollarPower.POWER_ID) &&
                            targetcreature.hasPower(CharmPower.POWER_ID) &&
                            targetcreature != AbstractDungeon.player
            )
            {
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new VigorPower(AbstractDungeon.player, AbstractDungeon.player.getPower(SpikedCollarPower.POWER_ID).amount), AbstractDungeon.player.getPower(SpikedCollarPower.POWER_ID).amount));
                __instance.isDone = true;
            }
        }
    }
}