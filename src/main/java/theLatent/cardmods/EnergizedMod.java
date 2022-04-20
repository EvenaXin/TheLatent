package theLatent.cardmods;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.helpers.GameDictionary;
import com.megacrit.cardcrawl.localization.LocalizedStrings;
import org.apache.commons.lang3.StringUtils;
import theLatent.fields.EnergizedField;
import theLatent.fields.WeightlessField;

public class EnergizedMod extends AbstractCardModifier {
    public static final String ID = "thelatent:EnergizedCardModifier";
    public EnergizedMod() {

    }

    public String modifyDescription(String rawDescription, AbstractCard card) {
        return StringUtils.capitalize(GameDictionary.ETHEREAL.NAMES[0]) + LocalizedStrings.PERIOD + " NL " + rawDescription;
    }

    public boolean shouldApply(AbstractCard card) {
        return !EnergizedField.energized.get(card);
    }

    public void onInitialApplication(AbstractCard card) {
        EnergizedField.energized.set(card, true);
    }

    public void onRemove(AbstractCard card) {
        EnergizedField.energized.set(card, false);
    }

    public AbstractCardModifier makeCopy() {
        return new EnergizedMod() {
        };
    }

    public String identifier(AbstractCard card) {
        return ID;
    }
}
