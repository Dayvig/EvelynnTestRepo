package EvelynnTest.cards;

import EvelynnTest.EvelynnTestMod;
import EvelynnTest.actions.BetterSummonAction;
import EvelynnTest.actions.BetterSummonGremlinAction;
import EvelynnTest.powers.FriendlyMonsterPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.exordium.GremlinNob;

import static EvelynnTest.EvelynnTestMod.makeID;

public class BigFan extends AbstractEasyCard {
    public final static String ID = makeID("BigFan");
    public final static int MAGIC = 15;
    public final static int UPG_MAGIC = -5;

    public BigFan() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.NONE);
        baseMagicNumber = magicNumber = MAGIC;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        GremlinNob nob = new GremlinNob(EvelynnTestMod.getSmartPosition(0f)[0], EvelynnTestMod.getSmartPosition(0f)[1]);
        nob.currentHealth = magicNumber;
        addToBot(new BetterSummonAction(nob));
        addToBot(new ApplyPowerAction(nob, nob, new FriendlyMonsterPower(nob, 1)));
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                nob.setMove((byte)3, AbstractMonster.Intent.BUFF);
                nob.createIntent();
                this.isDone = true;
            }
        });
    }

    public void upp() {
        upgradeMagicNumber(UPG_MAGIC);
    }
}