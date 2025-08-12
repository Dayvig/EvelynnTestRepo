package EvelynnTest.relics;

import EvelynnTest.EvelynnTestChar;
import EvelynnTest.relics.AbstractEasyRelic;

import static EvelynnTest.EvelynnTestMod.makeID;

public class WhiteFlag extends AbstractEasyRelic {
    public static final String ID = makeID("WhiteFlag");

    public WhiteFlag() {
        super(ID, RelicTier.STARTER, LandingSound.FLAT, EvelynnTestChar.Enums.EVELYNN_TEST_COLOR);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }
}
