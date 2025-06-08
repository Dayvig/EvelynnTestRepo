package EvelynnTest.cards;

import EvelynnTest.powers.AllurePower;
import EvelynnTest.powers.CharmPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;

import static EvelynnTest.EvelynnTestMod.makeID;

public class SenseWeakness extends AbstractShiftingCard {
    public final static String ID = makeID("SenseWeakness");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String[] EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
    public static final int MAGIC2 = 3;
    public static final int UPG_MAGIC = 1;
        public static final int MAGIC = 1;

    public SenseWeakness() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
        setDemonValues(upgraded ? 0:1, CardType.SKILL, CardTarget.ENEMY, cardStrings.EXTENDED_DESCRIPTION[0]);
        baseMagicNumber = magicNumber = MAGIC;
        baseSecondMagic = secondMagic = MAGIC2;
    }
    public SenseWeakness(boolean isCopy){
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY, Form.NORMAL, isCopy);
        setDemonValues(upgraded ? 0:1, CardType.SKILL, CardTarget.ENEMY, cardStrings.EXTENDED_DESCRIPTION[0]);
        baseMagicNumber = magicNumber = MAGIC;
        baseSecondMagic = secondMagic = MAGIC2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        super.use(p, m);
    }

    @Override
    public void useNormal(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                if (abstractMonster != null && abstractMonster.getIntentBaseDmg() >= 0) {
                    this.addToBot(new ApplyPowerAction(abstractMonster, AbstractDungeon.player, new VulnerablePower(abstractMonster, secondMagic, false), secondMagic));
                } else {
                    AbstractDungeon.effectList.add(new ThoughtBubble(AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, 3.0F, EXTENDED_DESCRIPTION[5], true));
                }
                this.isDone = true;
            }
        });
    }

    @Override
    public void useDemon(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisTurn){
            if (c.type.equals(CardType.ATTACK)){
                addToBot(new ApplyPowerAction(abstractMonster, abstractPlayer, new CharmPower(abstractMonster, magicNumber), magicNumber));
            }
        }
    }

    @Override
    public void applyPowers(){
        super.applyPowers();
        this.magicNumber = baseMagicNumber;
        if (AbstractDungeon.player.hasPower(AllurePower.POWER_ID)){
            this.magicNumber += AbstractDungeon.player.getPower(AllurePower.POWER_ID).amount;
        }
        this.isMagicNumberModified = AbstractDungeon.player.hasPower(AllurePower.POWER_ID);

        if (AbstractDungeon.isPlayerInDungeon()) {
            if (this.currentForm.equals(Form.DEMON)) {
                int attacks = 0;
                for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisTurn) {
                    if (c.type.equals(CardType.ATTACK)) {
                        attacks++;
                    }
                }
                this.rawDescription = EXTENDED_DESCRIPTION[3] + attacks + EXTENDED_DESCRIPTION[4];
                initializeDescription();
            }
        }
    }

    @Override
    public AbstractShiftingCard makeShiftingCopy() {
        return new SenseWeakness(true);
    }

    @Override
    public void upp() {
        super.upp();
        upgradeSecondMagic(UPG_MAGIC);
    }
}