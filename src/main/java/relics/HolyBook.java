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

import java.util.ArrayList;

public class HolyBook extends CustomRelic {
    private static final String ID = "HolyBook";
    private static final int HEAL = 1;

    public HolyBook() {
        super(ID, ImageMaster.loadImage("img/relics/book.png"), ImageMaster.loadImage("img/relics/outline/book.png"), RelicTier.UNCOMMON, LandingSound.CLINK);
    }

    public void onEquip() {
        this.counter = 0;
    }

    public void atTurnStart() {
        if (this.counter == -1) {
            this.counter += 2;
        } else {
            ++this.counter;
        }

        if (this.counter == 3) {
            this.counter = 0;
            this.flash();
            AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
                    new KiPower(AbstractDungeon.player, 2)));
        }


    }


    public void onVictory() {
        this.counter = -1;
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new HolyBook();
    }
}