package relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import powers.EmpowerPower;
import powers.KiPower;

public class JadeIdol extends CustomRelic {
    private static final String ID = "JadeIdol";

    public JadeIdol() {
        super(ID, ImageMaster.loadImage("img/relics/jade.png"), ImageMaster.loadImage("img/relics/outline/jade.png"), RelicTier.COMMON, LandingSound.SOLID);
    }

    public void atBattleStart() {
        AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
                new EmpowerPower(AbstractDungeon.player, 1), 1));
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new JadeIdol();
    }
}