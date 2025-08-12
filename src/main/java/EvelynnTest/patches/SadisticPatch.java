package EvelynnTest.patches;

import EvelynnTest.cards.SadoMasochism;
import EvelynnTest.powers.AllurePower;
import EvelynnTest.powers.CharmPower;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class SadisticPatch {
    @SpirePatch(
            clz = AbstractMonster.class,
            method = "damage",
            paramtypez = {
                    DamageInfo.class
            }
    )
public static class SadisticOnAttackPatch {
    @SpirePrefixPatch
    public static void Prefix(AbstractMonster __instance, DamageInfo __info) {
        if (__info.type.equals(DamageInfo.DamageType.NORMAL) && __info instanceof SadoMasochism.SadisticDamageInfo) {
                SadoMasochism.SadisticDamageInfo sadisticInfo = (SadoMasochism.SadisticDamageInfo)__info;
                if (sadisticInfo.isSadistic){
                    int toApply = __info.output;
                    if (AbstractDungeon.player.hasPower(AllurePower.POWER_ID)){
                        toApply += AbstractDungeon.player.getPower(AllurePower.POWER_ID).amount;
                    }
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(__instance, AbstractDungeon.player, new CharmPower(__instance, toApply), toApply));
                }
            }
        }
    }
}