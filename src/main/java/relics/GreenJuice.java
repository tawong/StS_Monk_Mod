package relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.RegenPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import powers.EmpowerPower;
import powers.KiPower;

public class GreenJuice extends CustomRelic {
    private static final String ID = "GreenJuice";

    public GreenJuice() {
        super(ID, ImageMaster.loadImage("img/relics/smoothie.png"), ImageMaster.loadImage("img/relics/outline/smoothie.png"), RelicTier.BOSS, LandingSound.MAGICAL);
    }

    public void atBattleStart() {
        AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
                new RegenPower(AbstractDungeon.player, 3), 3));

    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new GreenJuice();
    }
}