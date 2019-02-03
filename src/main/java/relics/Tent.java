package relics;

import basemod.abstracts.CustomRelic;
import cards.CheapShot;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import powers.KiPower;

public class Tent extends CustomRelic {
    private static final String ID = "Tent";
    private static final int KI = 2;
    private boolean firstTurn = true;


    public Tent() {
        super(ID, ImageMaster.loadImage("img/relics/tent.png"), ImageMaster.loadImage("img/relics/outline/tent.png"), RelicTier.COMMON, LandingSound.FLAT);
    }


    public void atTurnStart() {
        if (this.firstTurn) {
            if (this.counter == -2) {
                this.pulse = false;
                this.counter = -1;
                this.flash();
                AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
                        new KiPower(AbstractDungeon.player, KI), KI));
                AbstractDungeon.actionManager.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            }

            this.firstTurn = false;
        }

    }

    public void atPreBattle() {
        this.firstTurn = true;
    }

    public void setCounter(int counter) {
        super.setCounter(counter);
        if (counter == -2) {
            this.pulse = true;
        }

    }

    public void onEnterRestRoom() {
        this.flash();
        this.counter = -2;
        this.pulse = true;
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new Tent();
    }
}