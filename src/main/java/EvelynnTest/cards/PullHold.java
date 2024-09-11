package EvelynnTest.cards;

import EvelynnTest.powers.CharmPower;
import com.evacipated.cardcrawl.mod.stslib.actions.common.FetchAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.DrawPileToHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ChokePower;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;

import java.util.function.Predicate;

import static EvelynnTest.EvelynnTestMod.makeID;

public class PullHold extends AbstractShiftingCard {
    public final static String ID = makeID("PullHold");
    public final static int MAGIC = 8;
    public final static int MAGIC2 = 4;
    public final static int UPG_MAGIC = 2;

    public PullHold() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
        setDemonValues(1, CardType.SKILL, CardTarget.ENEMY, cardStrings.EXTENDED_DESCRIPTION[0]);
        this.baseMagicNumber = magicNumber = MAGIC;
        this.baseSecondMagic = secondMagic = MAGIC2;
    }

    public PullHold(boolean isCopy){
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY, Form.NORMAL, isCopy);
        setDemonValues(1, CardType.SKILL, CardTarget.ENEMY, cardStrings.EXTENDED_DESCRIPTION[0]);
        this.baseMagicNumber = magicNumber = MAGIC;
        this.baseSecondMagic = secondMagic = MAGIC2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        super.use(p, m);
    }

    @Override
    public void useNormal(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new ApplyPowerAction(abstractMonster, abstractPlayer, new CharmPower(abstractMonster, magicNumber), magicNumber));
        addToBot(new ApplyPowerAction(abstractMonster, abstractPlayer, new VigorPower(abstractPlayer, secondMagic), secondMagic));
    }

    @Override
    public void useDemon(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        this.addToBot(new ApplyPowerAction(abstractMonster, abstractPlayer, new StrengthPower(abstractMonster, -this.magicNumber), -this.magicNumber));
        if (abstractMonster != null && !abstractMonster.hasPower("Artifact")) {
            this.addToBot(new ApplyPowerAction(abstractMonster, abstractPlayer, new GainStrengthPower(abstractMonster, this.magicNumber), this.magicNumber));
        }
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                Predicate<AbstractCard> isAttack = card -> (card.type.equals(CardType.ATTACK));
                if (upgraded){
                    addToBot(new FetchAction(AbstractDungeon.player.drawPile, isAttack, 1));
                }
                else {
                    addToBot(new DrawPileToHandAction(1, CardType.ATTACK));
                }
                isDone = true;
            }
        });
    }


    @Override
    public AbstractShiftingCard makeShiftingCopy() {
        return new PullHold(true);
    }

    public void upp() {
        upgradeSecondMagic(UPG_MAGIC);
        initializeDescription();
    }
}