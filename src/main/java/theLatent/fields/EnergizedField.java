package theLatent.fields;


import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;

@SpirePatch(
        cls = "com.megacrit.cardcrawl.cards.AbstractCard",
        method = "<class>"
)
public class EnergizedField {
    public static SpireField<Boolean> energized = new SpireField(() -> {
        return false;
    });

    public EnergizedField() {
    }
}
