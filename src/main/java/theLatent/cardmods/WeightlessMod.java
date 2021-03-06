package theLatent.cardmods;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.GameDictionary;
import com.megacrit.cardcrawl.localization.LocalizedStrings;
import org.apache.commons.lang3.StringUtils;
import theLatent.LatentMod;
import theLatent.fields.WeightlessField;

public class WeightlessMod extends AbstractCardModifier {
    public static final String ID = "thelatent:WeightlessCardModifier";
    public WeightlessMod() {

    }

    public String modifyDescription(String rawDescription, AbstractCard card) {
        return LatentMod.makeID("Weightless. NL") + rawDescription;
    }

    public boolean shouldApply(AbstractCard card) {
        return !WeightlessField.weightless.get(card);
    }

    public void onInitialApplication(AbstractCard card) {
        WeightlessField.weightless.set(card, true);
    }

    public void onRemove(AbstractCard card) {
        WeightlessField.weightless.set(card, false);
    }

    public AbstractCardModifier makeCopy() {
        return new WeightlessMod() {
        };
    }

    public String identifier(AbstractCard card) {
        return ID;
    }
}
