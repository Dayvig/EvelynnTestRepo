package EvelynnTest;

import EvelynnTest.powers.BlingPower;
import EvelynnTest.powers.CharmPower;
import basemod.AutoAdd;
import basemod.BaseMod;
import basemod.helpers.RelicType;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.mod.stslib.Keyword;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import EvelynnTest.cards.AbstractEasyCard;
import EvelynnTest.cards.cardvars.SecondDamage;
import EvelynnTest.cards.cardvars.SecondMagicNumber;
import EvelynnTest.relics.AbstractEasyRelic;

import java.nio.charset.StandardCharsets;

@SuppressWarnings({"unused", "WeakerAccess"})
@SpireInitializer
public class EvelynnTestMod implements
        EditCardsSubscriber,
        EditRelicsSubscriber,
        EditStringsSubscriber,
        EditKeywordsSubscriber,
        EditCharactersSubscriber,
        OnPlayerLoseBlockSubscriber
{

    public static final String modID = "evelynntestmod"; //TODO: Change this.

    public static String makeID(String idText) {
        return modID + ":" + idText;
    }

    // This makes debugging so much easier
    public static Logger logger = LogManager.getLogger(EvelynnTestMod.class.getName());

    public static Color characterColor = new Color(MathUtils.random(), MathUtils.random(), MathUtils.random(), 1); // This should be changed eventually

    public static final String SHOULDER1 = modID + "Resources/images/char/mainChar/shoulder.png";
    public static final String SHOULDER2 = modID + "Resources/images/char/mainChar/shoulder2.png";
    public static final String CORPSE = modID + "Resources/images/char/mainChar/corpse.png";
    private static final String ATTACK_S_ART = modID + "Resources/images/512/attack.png";
    private static final String SKILL_S_ART = modID + "Resources/images/512/skill.png";
    private static final String POWER_S_ART = modID + "Resources/images/512/power.png";
    private static final String CARD_ENERGY_S = modID + "Resources/images/512/energy.png";
    private static final String TEXT_ENERGY = modID + "Resources/images/512/text_energy.png";
    private static final String ATTACK_L_ART = modID + "Resources/images/1024/attack.png";
    private static final String SKILL_L_ART = modID + "Resources/images/1024/skill.png";
    private static final String POWER_L_ART = modID + "Resources/images/1024/power.png";
    private static final String CARD_ENERGY_L = modID + "Resources/images/1024/energy.png";
    private static final String CHARSELECT_BUTTON = modID + "Resources/images/charSelect/charButton.png";
    private static final String CHARSELECT_PORTRAIT = modID + "Resources/images/charSelect/charBG.png";

    public EvelynnTestMod() {
        BaseMod.subscribe(this);

        BaseMod.addColor(EvelynnTestChar.Enums.EVELYNN_TEST_COLOR, characterColor, characterColor, characterColor,
                characterColor, characterColor, characterColor, characterColor,
                ATTACK_S_ART, SKILL_S_ART, POWER_S_ART, CARD_ENERGY_S,
                ATTACK_L_ART, SKILL_L_ART, POWER_L_ART,
                CARD_ENERGY_L, TEXT_ENERGY);
    }

    public static String makePath(String resourcePath) {
        return modID + "Resources/" + resourcePath;
    }

    public static String makeImagePath(String resourcePath) {
        return modID + "Resources/images/" + resourcePath;
    }

    public static String makeRelicPath(String resourcePath) {
        return modID + "Resources/images/relics/" + resourcePath;
    }

    public static String makePowerPath(String resourcePath) {
        return modID + "Resources/images/powers/" + resourcePath;
    }

    public static String makeCardPath(String resourcePath) {
        return modID + "Resources/images/cards/" + resourcePath;
    }

    public static void initialize() {
        EvelynnTestMod thismod = new EvelynnTestMod();
    }

    @Override
    public void receiveEditCharacters() {
        BaseMod.addCharacter(new EvelynnTestChar(EvelynnTestChar.characterStrings.NAMES[1], EvelynnTestChar.Enums.EVELYNN_TEST),
                CHARSELECT_BUTTON, CHARSELECT_PORTRAIT, EvelynnTestChar.Enums.EVELYNN_TEST);
    }

    @Override
    public void receiveEditRelics() {
        new AutoAdd(modID)
                .packageFilter(AbstractEasyRelic.class)
                .any(AbstractEasyRelic.class, (info, relic) -> {
                    if (relic.color == null) {
                        BaseMod.addRelic(relic, RelicType.SHARED);
                    } else {
                        BaseMod.addRelicToCustomPool(relic, relic.color);
                    }
                    if (!info.seen) {
                        UnlockTracker.markRelicAsSeen(relic.relicId);
                    }
                });
    }

    @Override
    public void receiveEditCards() {
        BaseMod.addDynamicVariable(new SecondMagicNumber());
        BaseMod.addDynamicVariable(new SecondDamage());
        new AutoAdd(modID)
                .packageFilter(AbstractEasyCard.class)
                .setDefaultSeen(true)
                .cards();
    }


    @Override
    public void receiveEditStrings() {
        BaseMod.loadCustomStringsFile(CardStrings.class, modID + "Resources/localization/eng/Cardstrings.json");

        BaseMod.loadCustomStringsFile(RelicStrings.class, modID + "Resources/localization/eng/Relicstrings.json");

        BaseMod.loadCustomStringsFile(CharacterStrings.class, modID + "Resources/localization/eng/Charstrings.json");

        BaseMod.loadCustomStringsFile(PowerStrings.class, modID + "Resources/localization/eng/Powerstrings.json");

        BaseMod.loadCustomStringsFile(StanceStrings.class, modID + "Resources/localization/eng/Stancestrings.json");
    }

    @Override
    public int receiveOnPlayerLoseBlock(int i) {
        System.out.println(i);
        if (AbstractDungeon.player.hasPower(BlingPower.POWER_ID)){
            int amount = AbstractDungeon.player.getPower(BlingPower.POWER_ID).amount;
            System.out.println(i);
            if (i <= amount){ return 0; }
            else {
                return i-amount;
            }
        }
        return i;
    }

    public static class CustomTags
    {
        @SpireEnum public static AbstractCard.CardTags OUTFIT;
        @SpireEnum public static AbstractCard.CardTags ACCESSORY;
    }

    @Override
    public void receiveEditKeywords() {
        Gson gson = new Gson();
        String json = Gdx.files.internal(modID + "Resources/localization/eng/Keywordstrings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        com.evacipated.cardcrawl.mod.stslib.Keyword[] keywords = gson.fromJson(json, com.evacipated.cardcrawl.mod.stslib.Keyword[].class);

        if (keywords != null) {
            for (Keyword keyword : keywords) {
                BaseMod.addKeyword(modID, keyword.PROPER_NAME, keyword.NAMES, keyword.DESCRIPTION);
            }
        }
    }

    public static boolean isInfatuated(AbstractMonster m){
        return m.hasPower(CharmPower.POWER_ID) && m.getPower(CharmPower.POWER_ID).amount >= (m.currentHealth/2);
    }

    public static float[] getSmartPosition(float yPos) {
        float offsetX = 0f;
        float offsetY = yPos;

        //finds nearest X position to the left of that y value
        for(AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (!mo.isDeadOrEscaped()) {
                if ((mo.drawY) >= offsetY) {
                    offsetX = Math.min(((mo.drawX - (mo.hb.width / 2) - ((float) Settings.WIDTH * 0.75F)) / Settings.scale), offsetX);
                }
            }
        }

        //if x position is in player area, repeat with higher Y position
        if ((offsetX - (75f * Settings.scale) + (((float) Settings.WIDTH * 0.75F) / Settings.scale)) < (AbstractDungeon.player.drawX + (AbstractDungeon.player.hb.width * 2))){
            return getSmartPosition(yPos + (100f * Settings.scale));
        }

        return new float[]{offsetX - (75f * Settings.scale), offsetY};
    }
}
