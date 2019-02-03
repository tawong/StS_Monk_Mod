//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import java.util.Iterator;

public class PlannedAttackAction extends AbstractGameAction {
    private static final UIStrings uiStrings;
    public static final String[] TEXT;
    private AbstractPlayer p;

    public PlannedAttackAction(AbstractCreature source) {
        this.p = AbstractDungeon.player;
        this.setValues((AbstractCreature)null, source, this.amount);
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FASTER;
        this.amount = 3;
    }

    public void update() {
        if (AbstractDungeon.getCurrRoom().isBattleEnding()) {
            this.isDone = true;
        } else {
            if (this.duration == Settings.ACTION_DUR_FASTER) {
                if (this.p.discardPile.isEmpty()) {
                    this.isDone = true;
                    return;
                }

                if (this.p.discardPile.size() == 1) {
                    AbstractCard tmp = this.p.discardPile.getTopCard();
                    this.p.discardPile.removeCard(tmp);
                    this.p.discardPile.moveToDeck(tmp, false);
                }

                if (this.p.discardPile.size() == 2) {

                    for(int i =0; i<2; i++){
                        AbstractCard tmp = this.p.discardPile.getTopCard();
                        this.p.discardPile.removeCard(tmp);
                        this.p.discardPile.moveToDeck(tmp, false);
                    }
                }

                if (this.p.discardPile.size() == 3) {

                    for(int i =0; i<3; i++){
                        AbstractCard tmp = this.p.discardPile.getTopCard();
                        this.p.discardPile.removeCard(tmp);
                        this.p.discardPile.moveToDeck(tmp, false);
                    }
                }

                if (this.p.discardPile.group.size() > this.amount) {
                    AbstractDungeon.gridSelectScreen.open(this.p.discardPile, 3, TEXT[0], false, false, false, false);
                    this.tickDuration();
                    return;
                }
            }

            if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
                Iterator var3 = AbstractDungeon.gridSelectScreen.selectedCards.iterator();

                while(var3.hasNext()) {
                    AbstractCard c = (AbstractCard)var3.next();
                    this.p.discardPile.removeCard(c);
                    this.p.hand.moveToDeck(c, false);
                }

                AbstractDungeon.gridSelectScreen.selectedCards.clear();
                AbstractDungeon.player.hand.refreshHandLayout();
            }

            this.tickDuration();
        }
    }

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString("DiscardPileToTopOfDeckAction");
        TEXT = uiStrings.TEXT;
    }
}
