package theTodo.cards.democards.complex;

import basemod.cardmods.RetainMod;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.FleetingField;
import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theTodo.actions.theLatentActions.AppendCardAction;
import theTodo.cards.AbstractEasyCard;

import java.util.Iterator;

import static theTodo.TodoMod.makeID;

public class Retention extends AbstractEasyCard {
    public final static String ID = makeID("Defend");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public Retention() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        FleetingField.fleeting.set(this, true);
        baseBlock = 5;
        tags.add(CardTags.STARTER_DEFEND);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new AppendCardAction(new RetainMod()));
    }

    @Override
    public void upgrade() {
        Iterator var1 = AbstractDungeon.player.masterDeck.group.iterator();
        while(var1.hasNext()) {
            AbstractCard c1 = (AbstractCard)var1.next();
            if (this.uuid == c1.uuid) {

            }
        }
    }

    public void upp() {
        upgradeBlock(3);
    }
}