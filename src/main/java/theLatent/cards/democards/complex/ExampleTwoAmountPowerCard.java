package theLatent.cards.democards.complex;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theLatent.cards.AbstractEasyCard;
import theLatent.powers.ExampleTwoAmountPower;

import static theLatent.LatentMod.makeID;
import static theLatent.util.Wiz.applyToSelf;

public class ExampleTwoAmountPowerCard extends AbstractEasyCard {
    public final static String ID = makeID("ExampleTwoAmountPowerCard");
    private final static int MAGIC = 1;
    private final static int UPGRADE_MAGIC = 1;
    private final static int COST = 1;

    public ExampleTwoAmountPowerCard() {
        super(ID, COST, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new ExampleTwoAmountPower(p, magicNumber, 1));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}