package EvelynnTest.patches;

import EvelynnTest.powers.FriendlyMonsterPower;
import EvelynnTest.powers.MindControlledPower;
import EvelynnTest.powers.RingOfPainPower;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.RollMoveAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.MonsterQueueItem;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import javassist.CtBehavior;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class FriendlyPatches {

    @SpirePatch(
            clz = GameActionManager.class,
            method = "getNextAction"
    )
    public static class DontAttackPatch {
        @SpirePrefixPatch
        public static void Prefix(GameActionManager __instance) {
            __instance.monsterQueue.removeIf(m -> m.monster.hasPower(FriendlyMonsterPower.POWER_ID) && !m.monster.intent.equals(AbstractMonster.Intent.BUFF) && !m.monster.hasPower(MindControlledPower.POWER_ID));
        }
    }
}
