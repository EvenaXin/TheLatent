package theLatent.patches;


import com.evacipated.cardcrawl.modthespire.lib.*;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import javassist.CannotCompileException;
import javassist.CtBehavior;
import theLatent.fields.WeightlessField;

import java.util.ArrayList;

@SpirePatch(
 cls = "com.megacrit.cardcrawl.characters.AbstractPlayer",
 method = "draw"
)
public class WeightlessPatch {
    @SpireInsertPatch(
            locator = Locator.class,
            localvars={"c"}
    )
    public static void patchMethod(AbstractCard _instance, int numCards, AbstractCard c) {
        if (WeightlessField.weightless.get(c)) {
            AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, 1));
        }
    }

    public static class Locator extends SpireInsertLocator {
        @Override
        public int[] Locate(CtBehavior ctBehavior) throws CannotCompileException, PatchingException {
            Matcher triggerMatcher = new Matcher.MethodCallMatcher(AbstractCard.class, "triggerWhenDrawn");
            return LineFinder.findInOrder(ctBehavior, new ArrayList<Matcher>(), triggerMatcher);
        }
    }
}
