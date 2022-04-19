package theTodo.cardmods;

import basemod.abstracts.AbstractCardModifier;
import basemod.cardmods.EtherealMod;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.helpers.GameDictionary;
import com.megacrit.cardcrawl.localization.LocalizedStrings;
import org.apache.commons.lang3.StringUtils;
import theTodo.fields.WeightlessField;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public abstract class WeightlessMod extends AbstractCardModifier {
    public static final String ID = "thelatent:WeightlessCardModifier";
    public WeightlessMod() {

    }

    public String modifyDescription(String rawDescription, AbstractCard card) {
        return StringUtils.capitalize(GameDictionary.ETHEREAL.NAMES[0]) + LocalizedStrings.PERIOD + " NL " + rawDescription;
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
