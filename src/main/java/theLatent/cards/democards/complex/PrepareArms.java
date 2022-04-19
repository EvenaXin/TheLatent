package theLatent.cards.democards.complex;

import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.StartupCard;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.AutoplayField;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.GraveField;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import theLatent.cards.AbstractEasyCard;

import static theLatent.LatentMod.makeID;

public class PrepareArms extends AbstractEasyCard implements StartupCard {
    public static final String ID = makeID("PrepareArms");
    public PrepareArms() {
        super(ID, 0, CardType.POWER, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = 7;
        AutoplayField.autoplay.set(this, true);
        this.tags.add(CardTags.HEALING);
    }

    @Override
    public void upp() {
        if (!this.upgraded) {
            this.upgradeName();
            GraveField.grave.set(this, true);
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new VulnerablePower(p, 2, false)));
    }

    @Override
    public boolean atBattleStartPreDraw() {
        addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, 2)));
        return true;
    }
}
