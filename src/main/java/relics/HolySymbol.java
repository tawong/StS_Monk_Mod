package relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.cardManip.PurgeCardEffect;
import powers.KiPower;

import java.util.ArrayList;
import java.util.List;

public class HolySymbol extends CustomRelic {
    private static final String ID = "HolySymbol";

    public HolySymbol() {
        super(ID, ImageMaster.loadImage("img/relics/symbol.png"), ImageMaster.loadImage("img/relics/outline/symbol.png"), RelicTier.UNCOMMON, LandingSound.CLINK);
    }

    public void onEquip() {

        List<String> curses = new ArrayList();

        for(int i = AbstractDungeon.player.masterDeck.group.size() - 1; i >= 0; --i) {
            if (((AbstractCard)AbstractDungeon.player.masterDeck.group.get(i)).type == AbstractCard.CardType.CURSE && !((AbstractCard)AbstractDungeon.player.masterDeck.group.get(i)).inBottleFlame && !((AbstractCard)AbstractDungeon.player.masterDeck.group.get(i)).inBottleLightning && ((AbstractCard)AbstractDungeon.player.masterDeck.group.get(i)).cardID != "AscendersBane" && ((AbstractCard)AbstractDungeon.player.masterDeck.group.get(i)).cardID != "Necronomicurse") {
                AbstractDungeon.effectList.add(new PurgeCardEffect((AbstractCard)AbstractDungeon.player.masterDeck.group.get(i)));
                curses.add(((AbstractCard)AbstractDungeon.player.masterDeck.group.get(i)).cardID);
                AbstractDungeon.player.masterDeck.removeCard((AbstractCard)AbstractDungeon.player.masterDeck.group.get(i));
            }
        }
    }



    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new HolySymbol();
    }
}