package cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import patches.AbstractCardEnum;
import powers.EmpowerPower;
import powers.KiPower;

public class ThinkAhead extends CustomCard {
	public static final String ID = "ThinkAhead";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	private static final int COST = 1;
	private static final int KI = 10;
//	private static final int COST_UPGRADE = 1;

	public ThinkAhead() {
		super(ID, NAME, "img/cards/thinkahead.png", COST, DESCRIPTION, AbstractCard.CardType.SKILL,
				AbstractCardEnum.VIRIDIAN, AbstractCard.CardRarity.UNCOMMON,
				AbstractCard.CardTarget.SELF);
		this.magicNumber = this.baseMagicNumber = KI;
		this.exhaust = true;
		this.isEthereal = true;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {



		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new KiPower(p, this.magicNumber), this.magicNumber));



	}

	public AbstractCard makeCopy() {
		return new ThinkAhead();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
//			upgradeBaseCost(COST_UPGRADE);
			this.rawDescription = UPGRADE_DESCRIPTION;
			this.isEthereal = false;
			initializeDescription();
		}
	}
}