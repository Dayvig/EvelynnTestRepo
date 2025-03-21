package EvelynnTest.cards;

import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static EvelynnTest.EvelynnTestMod.makeID;

public class Aftercare extends AbstractEasyCard {
    public final static String ID = makeID("Aftercare");
    public static final int BLOCK = 10;
    public static final int UPG_BLOCK = 3;
    public static final int MAGIC = 8;
    public static final int UPG_MAGIC = -2;
    public Aftercare() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF_AND_ENEMY);
        baseBlock = block = BLOCK;
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        addToBot(new HealAction(m, p, magicNumber));
    }

    public void upp() {
        upgradeBlock(UPG_BLOCK);
        upgradeMagicNumber(UPG_MAGIC);
    }
}