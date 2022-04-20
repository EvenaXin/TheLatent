package theLatent.patches;


import com.evacipated.cardcrawl.modthespire.lib.*;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import javassist.CannotCompileException;
import javassist.CtBehavior;
import theLatent.fields.EnergizedField;
import theLatent.fields.WeightlessField;

import java.util.ArrayList;

@SpirePatch(
    cls = "com.megacrit.cardcrawl.characters.AbstractPlayer",
    method = "applyStartOfCombatPreDrawLogic"
)
public class EnergizedPatch {
    @SpirePrefixPatch()
    public static void patchMethod(AbstractPlayer _instance) {
        for (AbstractCard c : _instance.drawPile.group) {
            if (EnergizedField.energized.get(c)) {
                c.freeToPlayOnce = true;
            }
        }
    }
}
