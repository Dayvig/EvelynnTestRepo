package EvelynnTest.cards;

import EvelynnTest.EvelynnTestMod;
import EvelynnTest.powers.BlingPower;
import EvelynnTest.powers.EveningGlovesPower;
import EvelynnTest.powers.PunishmentPower;
import EvelynnTest.powers.StilettoPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static EvelynnTest.EvelynnTestMod.makeID;

public class SharpStilettos extends AbstractEasyCard {

    public final static String ID = makeID("SharpStilettos");

    public SharpStilettos() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        tags.add(EvelynnTestMod.CustomTags.ACCESSORY);
        this.cardsToPreview = new HateSpike();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new StilettoPower(p, -1, this.makeStatEquivalentCopy()), -1));
        if (upgraded){
            addToBot(new MakeTempCardInHandAction(new HateSpike()));
        }
    }

    public void upp() {
        this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}