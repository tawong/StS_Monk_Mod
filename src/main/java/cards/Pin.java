package cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import patches.AbstractCardEnum;
import powers.KiPower;
import powers.PreparationPower;

public class Pin extends CustomCard {
	public static final String ID = "Pin";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 1;
	private static final int BLOCK = 3;
	private static final int BLOCK_UP = 2;
	private static final int KI = 3;

	public Pin() {
		super(ID, NAME, "img/cards/pin.png", COST, DESCRIPTION, CardType.SKILL,
				AbstractCardEnum.VIRIDIAN, CardRarity.COMMON,
				CardTarget.SELF);
		this.baseBlock = BLOCK;
		this.baseMagicNumber = this.magicNumber = KI;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {

		AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new KiPower(p, KI), KI));

		if((EnergyPanel.totalCount-this.costForTurn) == 0) {

			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new KiPower(p, 2), 2));

		}



	}



	public AbstractCard makeCopy() {
		return new Pin();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeBlock(BLOCK_UP);

		}
	}
}