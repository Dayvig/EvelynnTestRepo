package EvelynnTest.patches;

import EvelynnTest.cards.testcards.*;
import EvelynnTest.powers.RingOfPainPower;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.Ironclad;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.VulnerablePower;

import java.util.ArrayList;

public class AddCardsToIroncladPatch {

        @SpirePatch(
            clz = Ironclad.class,
            method = "getCardPool",
            paramtypez = {
                    ArrayList.class
            }
    )
    public static class AddCards {
        @SpirePrefixPatch
        public static SpireReturn<Void> Prefix(Ironclad __instance, ArrayList<AbstractCard> __tmpPool){
            System.out.println("Adding cards");
            __tmpPool.add(new HideCard1());
            __tmpPool.add(new HideCard2());
            __tmpPool.add(new HideCard3());
            __tmpPool.add(new HideCard4());
            __tmpPool.add(new HideCard5());
            __tmpPool.add(new HideCard6());
            __tmpPool.add(new HideCard7());
            __tmpPool.add(new HideCard8());
            __tmpPool.add(new HideCard9());
            __tmpPool.add(new HideCard1());
            __tmpPool.add(new HideCard2());
            __tmpPool.add(new HideCard3());
            __tmpPool.add(new HideCard4());
            __tmpPool.add(new HideCard5());
            __tmpPool.add(new HideCard6());
            __tmpPool.add(new HideCard7());
            __tmpPool.add(new HideCard8());
            __tmpPool.add(new HideCard9());
            return SpireReturn.Continue();
        }
    }
}
