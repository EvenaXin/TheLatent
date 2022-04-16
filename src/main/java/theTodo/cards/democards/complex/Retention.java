package theTodo.cards.democards.complex;

import basemod.cardmods.RetainMod;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theTodo.actions.theLatentActions.AppendCardAction;

public class Retention extends AbstractCard {
    public Retention(String id, String name, String imgUrl, int cost, String rawDescription, CardType type, CardColor color, CardRarity rarity, CardTarget target) {
        super(id, name, imgUrl, cost, rawDescription, type, color, rarity, target);
    }

    @Override
    public void upgrade() {

    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new AppendCardAction(new RetainMod()));
    }

    @Override
    public AbstractCard makeCopy() {
        return null;
    }
}
