package relics;

import basemod.abstracts.CustomRelic;
import cards.CheapShot;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public class Keg extends CustomRelic {
    private static final String ID = "Keg";
    private static final int HEAL = 1;

    public Keg() {
        super(ID, ImageMaster.loadImage("img/relics/keg.png"), ImageMaster.loadImage("img/relics/outline/keg.png"), RelicTier.UNCOMMON, LandingSound.HEAVY);
    }

    public void onEnterRestRoom() {

        this.flash();


        if(AbstractDungeon.player.potionSlots > 0 ){
                AbstractDungeon.player.obtainPotion(AbstractDungeon.returnRandomPotion());
        }


    }


    public void onVictory() {

    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new Keg();
    }
}