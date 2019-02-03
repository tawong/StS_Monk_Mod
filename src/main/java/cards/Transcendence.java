//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cards;

import actions.AlignSelfAction;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.vfx.RainbowCardEffect;
import patches.AbstractCardEnum;
import powers.KiPower;


public class Transcendence extends CustomCard {
    public static final String ID = "Transcendence";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    private static final int COST = 1;
    private static final int UPGRADE_COST = 0;

    public Transcendence() {
        super(ID, NAME, "img/cards/transcendence.png", COST, DESCRIPTION, CardType.SKILL,
                AbstractCardEnum.VIRIDIAN, CardRarity.RARE,
                CardTarget.SELF);
        this.baseDamage = 0;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {


        int KiAmt = 0;

        if (AbstractDungeon.player.hasPower("KiPower")) {
            KiAmt = p.getPower("KiPower").amount;

            AbstractDungeon.actionManager.addToBottom(new VFXAction(new RainbowCardEffect()));

            AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(KiAmt));
            AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, KiAmt));

            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new KiPower(p, -KiAmt), -KiAmt));

        }



    }

    public AbstractCard makeCopy() {
        return new Transcendence();
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(UPGRADE_COST);
        }

    }


}
