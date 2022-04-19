package theTodo.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.unique.RetainCardsAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theTodo.cards.AbstractEasyCard;

import static theTodo.TodoMod.makeID;

public class HoldOut extends AbstractEasyCard {
    public final static String ID = makeID("HoldOut");

    public HoldOut() {
        super(ID, 1, CardType.ATTACK, CardRarity.BASIC, CardTarget.SELF);
        baseBlock = 7;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, block));
        addToBot(new RetainCardsAction(p, 1));
    }

    @Override
    public void upp() {
        upgradeBlock(3);
    }
}
