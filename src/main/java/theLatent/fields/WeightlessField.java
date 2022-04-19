package theLatent.fields;


import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;

@SpirePatch(
        cls = "com.megacrit.cardcrawl.cards.AbstractCard",
        method = "<CLASS>"
)
public class WeightlessField {
    public static SpireField<Boolean> weightless = new SpireField(() -> {
        return false;
    });

    public WeightlessField() {
    }
}
