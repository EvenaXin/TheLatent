package theTodo.patches;


import com.evacipated.cardcrawl.modthespire.lib.*;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import javassist.CannotCompileException;
import javassist.CtBehavior;
import theTodo.fields.WeightlessField;

import java.util.ArrayList;

@SpirePatch(
 cls = "com.megacrit.cardcrawl.characters.AbstractPlayer",
 method = "draw"
)
public class WeightlessPatch {
    public WeightlessPatch() {
    }

    @SpireInsertPatch(
            locator = Locator.class,
            localvars={""}
    )
    public static void patchMethod(AbstractCard _instance) {
        if (WeightlessField.weightless.get(_instance)) {
            AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, 1));
        }
    }

    public static class Locator extends SpireInsertLocator {
        @Override
        public int[] Locate(CtBehavior ctBehavior) throws CannotCompileException, PatchingException {
            Matcher triggerMatcher = new Matcher.MethodCallMatcher(AbstractPlayer.class, "triggerWhenDrawn");
            return LineFinder.findInOrder(ctBehavior, new ArrayList<Matcher>(), triggerMatcher);
        }
    }
}
