package theTodo.actions.theLatentActions;

import basemod.abstracts.AbstractCardModifier;
import basemod.cardmods.RetainMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import theTodo.actions.ApplyCardModifierAction;

import java.util.ArrayList;
import java.util.Iterator;

public class AppendCardAction extends AbstractGameAction {
    private static final UIStrings uiStrings;
    public static final String[] TEXT;
    private AbstractPlayer p;
    private ArrayList<AbstractCard> cannotChange = new ArrayList();
    private AbstractCardModifier modToAppend;

    public AppendCardAction(AbstractCardModifier mod) {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.p = AbstractDungeon.player;
        this.duration = Settings.ACTION_DUR_FAST;
        this.modToAppend = mod;
    }
    @Override
    public void update() {
        Iterator var1;
        AbstractCard c1;
        AbstractCard c2;
        boolean cardInMasterDeck;
        Iterator var2 = AbstractDungeon.player.masterDeck.group.iterator();
        if (this.duration == Settings.ACTION_DUR_FAST) {
            var1 = this.p.hand.group.iterator();
            while(var1.hasNext()) {
                c1 = (AbstractCard)var1.next();
                cardInMasterDeck = false;
                while(var2.hasNext()) {
                    c2 = (AbstractCard)var2.next();
                    if (c1.uuid.equals(c2.uuid)) {
                        cardInMasterDeck = true;
                    }
                }
                if(!cardInMasterDeck) {
                    cannotChange.add(c1);
                }
                if(modToAppend.equals(new RetainMod()) && c1.isEthereal) {
                    cannotChange.add(c1);
                }
            }
            if (this.cannotChange.size() == this.p.hand.group.size()) {
                this.isDone = true;
                return;
            }
            if (this.p.hand.group.size() - this.cannotChange.size() == 1) {
                var1 = this.p.hand.group.iterator();

                while(var1.hasNext()) {
                    c1 = (AbstractCard)var1.next();
                    while(var2.hasNext()) {
                        c2 = (AbstractCard) var2.next();
                        if (c1.uuid.equals(c2.uuid)) {
                            addToTop(new ApplyCardModifierAction(c1, modToAppend));
                            addToTop(new ApplyCardModifierAction(c2, modToAppend));
                            c1.superFlash();
                        }
                    }
                }
            }

            this.p.hand.group.removeAll(this.cannotChange);
            if (this.p.hand.group.size() > 1) {
                AbstractDungeon.handCardSelectScreen.open(TEXT[0], 1, false, false, false, false);
                this.tickDuration();
                return;
            }

            if (this.p.hand.group.size() == 1) {
                while(var2.hasNext()) {
                    c2 = (AbstractCard)var2.next();
                    if(this.p.hand.getTopCard().uuid.equals(c2)) {
                        addToTop(new ApplyCardModifierAction(c2, modToAppend));
                        addToTop(new ApplyCardModifierAction(this.p.hand.getTopCard(), modToAppend));
                    }
                }
                this.p.hand.getTopCard().superFlash();
                this.returnCards();
                this.isDone = true;
            }
        }

        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            var1 = AbstractDungeon.handCardSelectScreen.selectedCards.group.iterator();

            while(var1.hasNext()) {
                c1 = (AbstractCard)var1.next();
                while(var2.hasNext()) {
                    c2 = (AbstractCard)var2.next();
                    if(this.p.hand.getTopCard().uuid.equals(c2)) {
                        addToTop(new ApplyCardModifierAction(c2, modToAppend));
                        addToTop(new ApplyCardModifierAction(c1, modToAppend));
                        c1.superFlash();
                        this.p.hand.addToTop(c1);
                    }
                }
            }

            this.returnCards();
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
            AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();
            this.isDone = true;
        }

        this.tickDuration();
    }

    private void returnCards() {
        Iterator var1 = this.cannotChange.iterator();

        while(var1.hasNext()) {
            AbstractCard c = (AbstractCard)var1.next();
            this.p.hand.addToTop(c);
        }

        this.p.hand.refreshHandLayout();
    }
    static {
        uiStrings = CardCrawlGame.languagePack.getUIString("AppendCardAction");
        TEXT = uiStrings.TEXT;
    }
}
