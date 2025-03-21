package EvelynnTest.cards;

import EvelynnTest.EvelynnTestMod;
import EvelynnTest.powers.BlingPower;
import EvelynnTest.powers.EveningGlovesPower;
import EvelynnTest.powers.PunishmentPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static EvelynnTest.EvelynnTestMod.makeID;

public class RidingCrop extends AbstractEasyCard {

    public final static String ID = makeID("RidingCrop");

    private static final int MAGIC = 2;
    private static final int UPG_MAGIC = 1;

    public RidingCrop() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
        tags.add(EvelynnTestMod.CustomTags.ACCESSORY);
        this.cardsToPreview = new HateSpike();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        addToBot(new ApplyPowerAction(p, p, new PunishmentPower(p, magicNumber, this.makeStatEquivalentCopy()), magicNumber));
        addToBot(new MakeTempCardInHandAction(new HateSpike(), magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(UPG_MAGIC);
    }
}