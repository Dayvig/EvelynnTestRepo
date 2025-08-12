package EvelynnTest.temp;

import EvelynnTest.EvelynnTestMod;
import EvelynnTest.cards.AbstractEasyCard;
import EvelynnTest.powers.FancyDressPower;
import EvelynnTest.powers.MindControlledPower;
import EvelynnTest.powers.QueenRobePower;
import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static EvelynnTest.EvelynnTestMod.makeID;

public class QueenRobe extends AbstractEasyCard {

    public final static String ID = makeID("QueenRobe");

    private static final int MAGIC = 4;
    private static final int UPG_MAGIC = 2;

    public QueenRobe() {
        super(ID, 1, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
        tags.add(EvelynnTestMod.CustomTags.OUTFIT);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters){
            if (mo.hasPower(MindControlledPower.POWER_ID)){
                addToBot(new ApplyPowerAction(mo, p, new StrengthPower(mo, magicNumber), magicNumber));
            }
        }
        addToBot(new ApplyPowerAction(p, p, new QueenRobePower(p, magicNumber, this.makeStatEquivalentCopy()), magicNumber));
    }


    public void upp() {
        upgradeMagicNumber(UPG_MAGIC);
    }
}