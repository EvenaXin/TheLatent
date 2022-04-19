package theLatent.relics;

import theLatent.TheLatent;

import static theLatent.LatentMod.makeID;

public class TodoItem extends AbstractEasyRelic {
    public static final String ID = makeID("TodoItem");

    public TodoItem() {
        super(ID, RelicTier.STARTER, LandingSound.FLAT, TheLatent.Enums.LATENT_COLOR);
    }
}
