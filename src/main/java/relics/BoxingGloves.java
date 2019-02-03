package relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class BoxingGloves extends CustomRelic {
    private static final String ID = "BoxingGloves";
    private static final int HP_UP = 3;

    public BoxingGloves() {
        super(ID, ImageMaster.loadImage("img/relics/glove.png"), ImageMaster.loadImage("img/relics/outline/glove.png"), RelicTier.RARE, LandingSound.SOLID);
    }

    public void atBattleStart() {
        this.counter = 0;
    }

    public void atTurnStart(){
        counter++;
    }


    public void onVictory() {

        if(counter <= 3){
            this.flash();
            AbstractDungeon.player.increaseMaxHp(HP_UP, false);
        }

    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new BoxingGloves();
    }
}