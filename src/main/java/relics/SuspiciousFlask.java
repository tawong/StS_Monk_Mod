package relics;

import basemod.abstracts.CustomRelic;
import cards.CheapShot;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public class SuspiciousFlask extends CustomRelic {
    private static final String ID = "SuspiciousFlask";
    private static final int HEAL = 1;

    public SuspiciousFlask() {
        super(ID, ImageMaster.loadImage("img/relics/flask.png"), ImageMaster.loadImage("img/relics/outline/flask.png"), RelicTier.STARTER, LandingSound.CLINK);
    }

    public void onUsePotion() {
        this.flash();
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new CheapShot(), 1, false));
        }

    }


    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new SuspiciousFlask();
    }
}