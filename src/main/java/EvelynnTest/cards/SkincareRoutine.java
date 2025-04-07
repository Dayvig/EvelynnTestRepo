package EvelynnTest.cards;

import EvelynnTest.powers.ArtifactDownPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;

import static EvelynnTest.EvelynnTestMod.makeID;

public class SkincareRoutine extends AbstractEasyCard {
    public final static String ID = makeID("SkincareRoutine");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public final static int BLOCK = 14;
    public final static int UPG_BLOCK = 3;
    public final static int MAGIC = 1;
    public final static int UPG_MAGIC = 2;

    public SkincareRoutine() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = block = BLOCK;
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        addToBot(new ApplyPowerAction(p, p, new ArtifactPower(p, magicNumber), magicNumber));
        addToBot(new ApplyPowerAction(p, p, new ArtifactDownPower(p, magicNumber), magicNumber));
    }

    public void upp() {
        upgradeBlock(UPG_BLOCK);
        upgradeMagicNumber(UPG_MAGIC);
    }
}