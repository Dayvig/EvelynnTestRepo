package EvelynnTest.cards;

import EvelynnTest.actions.BetterSummonGremlinAction;
import EvelynnTest.powers.AllurePower;
import EvelynnTest.powers.CharmPower;
import EvelynnTest.util.Wiz;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static EvelynnTest.EvelynnTestMod.makeID;

public class AttractAttention extends AbstractEasyCard {
    public final static String ID = makeID("AttractAttention");
    public static final int BLOCK = 18;
    public static final int UPG_BLOCk = 4;
    public static final int MAGIC = 4;
    public static final int UPG_MAGIC = 1;

    public AttractAttention() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = magicNumber = MAGIC;
        baseBlock = block = BLOCK;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, block));
        addToBot(new BetterSummonGremlinAction());
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                for (AbstractMonster mo : Wiz.getEnemies()){
                    addToBot(new ApplyPowerAction(mo, p, new CharmPower(mo, magicNumber), magicNumber));
                }
                isDone = true;
            }
        });
    }

    @Override
    public void applyPowers(){
        super.applyPowers();
        this.magicNumber = baseMagicNumber;
        if (AbstractDungeon.player.hasPower(AllurePower.POWER_ID)){
            this.magicNumber += AbstractDungeon.player.getPower(AllurePower.POWER_ID).amount;
        }
        this.isMagicNumberModified = AbstractDungeon.player.hasPower(AllurePower.POWER_ID);
    }

    public void upp() {
        upgradeMagicNumber(UPG_MAGIC);
        upgradeBlock(UPG_BLOCk);
    }
}