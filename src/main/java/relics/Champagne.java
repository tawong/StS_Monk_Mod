package relics;

import basemod.abstracts.CustomRelic;
import cards.CheapShot;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import powers.EmpowerPower;
import powers.KiPower;

public class Champagne extends CustomRelic {
    private static final String ID = "Champagne";
    private static final int Ki = 5;

    public Champagne() {
        super(ID, ImageMaster.loadImage("img/relics/champagne.png"), ImageMaster.loadImage("img/relics/outline/champagne.png"), RelicTier.UNCOMMON, LandingSound.CLINK);
    }

    public void onUsePotion() {
        this.flash();
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
                    new KiPower(AbstractDungeon.player, Ki), Ki));
        }

    }
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new Champagne();
    }
}