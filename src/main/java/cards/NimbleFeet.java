package cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.RegenPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import com.sun.org.apache.regexp.internal.RE;
import patches.AbstractCardEnum;
import powers.KiPower;

public class NimbleFeet extends CustomCard {
	public static final String ID = "NimbleFeet";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 1;
	private static final int BLOCK = 6;
	private static final int BLOCK_UP = 3;
	private static final int HEAL = 3;

	public NimbleFeet() {
		super(ID, NAME, "img/cards/nimblefeet.png", COST, DESCRIPTION, CardType.SKILL,
				AbstractCardEnum.VIRIDIAN, CardRarity.UNCOMMON,
				CardTarget.SELF);
		this.baseBlock = this.block = BLOCK;
		this.baseMagicNumber = this.magicNumber = HEAL;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {

//		int prepAmt = 0;
//
//		if(AbstractDungeon.player.hasPower("PreparationPower")){
//			prepAmt = p.getPower("PreparationPower").amount;
//		}
//
//		if(prepAmt > 0){
			AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
			AbstractDungeon.actionManager.addToBottom(new HealAction(p, p, this.magicNumber));
//		}



	}



	public AbstractCard makeCopy() {
		return new NimbleFeet();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeBlock(BLOCK_UP);
		}
	}
}