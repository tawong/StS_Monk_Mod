package relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import powers.EmpowerPower;
import powers.KiPower;

public class ImbuedPendant extends CustomRelic {
    private static final String ID = "ImbuedPendant";
    private static final int Ki = 1;

    public ImbuedPendant() {
        super(ID, ImageMaster.loadImage("img/relics/amulet.png"), ImageMaster.loadImage("img/relics/outline/amulet.png"), RelicTier.STARTER, LandingSound.MAGICAL);
    }

    public void atBattleStart() {
        AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(AbstractDungeon.player, this));

        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
                new KiPower(AbstractDungeon.player, Ki), Ki));
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
                new EmpowerPower(AbstractDungeon.player, 0), 0));
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new ImbuedPendant();
    }
}