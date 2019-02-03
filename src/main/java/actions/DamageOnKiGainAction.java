
package actions;

import cards.ShadowBlock;
import cards.ShadowRestore;
import cards.ShadowFist;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.Iterator;

public class DamageOnKiGainAction extends AbstractGameAction {
    private AbstractCard card;

    public DamageOnKiGainAction(int amount) {
        this.duration = Settings.ACTION_DUR_FAST;
//        this.card = card;
        this.amount = amount;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {

            Iterator var1 = AbstractDungeon.player.discardPile.group.iterator();

            AbstractCard c;
            while(var1.hasNext()) {
                c = (AbstractCard)var1.next();
                if (c instanceof ShadowFist) {
                    c.baseDamage += this.amount;
                    c.applyPowers();
                }
                if (c instanceof ShadowRestore) {
                    c.baseMagicNumber += this.amount;
                    c.applyPowers();
                }
                if (c instanceof ShadowBlock) {
                    c.baseBlock += this.amount;
                    c.applyPowers();
                }
            }

            var1 = AbstractDungeon.player.drawPile.group.iterator();

            while(var1.hasNext()) {
                c = (AbstractCard)var1.next();
                if (c instanceof ShadowFist) {
                    c.baseDamage += this.amount;
                    c.applyPowers();
                }
                if (c instanceof ShadowRestore) {
                    c.baseMagicNumber += this.amount;
                    c.applyPowers();
                }
                if (c instanceof ShadowBlock) {
                    c.baseBlock += this.amount;
                    c.applyPowers();
                }
            }

            var1 = AbstractDungeon.player.hand.group.iterator();

            while(var1.hasNext()) {
                c = (AbstractCard)var1.next();
                if (c instanceof ShadowFist) {
                    c.baseDamage += this.amount;
                    c.applyPowers();
                }
                if (c instanceof ShadowRestore) {
                    c.baseMagicNumber += this.amount;
                    c.applyPowers();
                }
                if (c instanceof ShadowBlock) {
                    c.baseBlock += this.amount;
                    c.applyPowers();
                }
            }
        }

        this.tickDuration();
    }
}
