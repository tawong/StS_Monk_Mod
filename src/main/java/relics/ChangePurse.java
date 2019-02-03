package relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import powers.KiPower;

public class ChangePurse extends CustomRelic {
    private static final String ID = "ChangePurse";
    private static final int GOLD_UP = 5;

    public ChangePurse() {
        super(ID, ImageMaster.loadImage("img/relics/purse.png"), ImageMaster.loadImage("img/relics/outline/purse.png"), RelicTier.COMMON, LandingSound.CLINK);
    }

    public void onEquip(){

    }

    public void onGainGold() {
        this.flash();
        AbstractDungeon.player.gold += 5;

    }


    public void onVictory() {

    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new ChangePurse();
    }
}