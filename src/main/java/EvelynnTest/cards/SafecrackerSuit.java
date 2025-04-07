package EvelynnTest.cards;

import EvelynnTest.EvelynnTestMod;
import EvelynnTest.powers.FancyDressPower;
import EvelynnTest.powers.MindControlledPower;
import EvelynnTest.powers.QueenRobePower;
import EvelynnTest.powers.SafecrackerSuitPower;
import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static EvelynnTest.EvelynnTestMod.makeID;

public class SafecrackerSuit extends AbstractEasyCard {

    public final static String ID = makeID("SafecrackerSuit");

    private static final int MAGIC = 8;
    private static final int UPG_MAGIC = 6;

    public SafecrackerSuit() {
        super(ID, 1, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
        tags.add(EvelynnTestMod.CustomTags.OUTFIT);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new SafecrackerSuitPower(p, magicNumber, this.makeStatEquivalentCopy()), magicNumber));
    }


    public void upp() {
        upgradeMagicNumber(UPG_MAGIC);
    }
}